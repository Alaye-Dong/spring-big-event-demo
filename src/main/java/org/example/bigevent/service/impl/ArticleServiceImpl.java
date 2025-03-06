package org.example.bigevent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.bigevent.mapper.ArticleMapper;
import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.PageBean;
import org.example.bigevent.service.ArticleService;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建 PageBean 对象
        PageBean<Article> pageBean = new PageBean<>();

        // 开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 获取当前线程的用户 ID
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        // 查询文章列表
        List<Article> articles = articleMapper.list(userId, categoryId, state);

        // 获取分页信息
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        // 设置总记录数和分页数据
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setItems(pageInfo.getList());

        return pageBean;
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.findById(id);
    }

}
