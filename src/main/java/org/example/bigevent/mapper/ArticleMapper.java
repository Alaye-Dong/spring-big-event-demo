package org.example.bigevent.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.bigevent.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            " values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);


    List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    @Delete("delete from article where id=#{id}")
    void delete(Integer id);

    @Insert("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);
}
