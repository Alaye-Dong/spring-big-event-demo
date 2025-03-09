## 介绍

本仓库是个人在学习[黑马程序员SpringBoot3+Vue3全套视频](https://www.bilibili.com/video/BV14z4y1N7pg/?share_source=copy_web&vd_source=b91af0127331bb4444b9388984a23393)的代码及笔记。以及收集跟随视频教程学习中，容易踩的坑，希望对所有学习者有所帮助。

## 笔记

### Lombok @Data 错误

运行项目，出现如下的@Data注解报错

```shell
java: 找不到符号
  符号:   方法 getPassword()
  位置: 类型为org.example.bigevent.pojo.User的变量 loginUser
```

可以检查IDEA的设置，`构建、执行、部署〉编译器〉注解处理器` 使用 `处理器路径` 将会报错。应设置为从项目类路径获取处理器。

![img.png](assets/img.png)

疑惑的点是，在修改之后的某次运行项目，这个设置可能会被莫名其妙的恢复，再次导致错误，需要重新设置。

### Pagehelper ClassCastException 错误

跟随视频教程，在`UserController`中添加了分页功能，但是运行项目，报错

```shell
java.lang.ClassCastException: class java.util.ArrayList cannot be cast to class com.github.pagehelper.Page (java.util.ArrayList is in module java.base of loader 'bootstrap'; com.github.pagehelper.Page is in unnamed module of loader 'app')
```

`java.lang.ClassCastException` 是因为在 `ArticleServiceImpl.list` 方法中尝试将 `ArrayList` 类型的对象强制转换为 `Page` 类型。具体来说，`articleMapper.list` 返回的是一个普通的 `List<Article>`，而不是 `PageHelper` 的 `Page<Article>`。

要解决这个问题，可以按照以下步骤进行修改：

1. **确保使用了 PageHelper 插件**：确保在项目中正确引入了 `PageHelper` 依赖，并且配置正确。

2. **检查返回类型**：`articleMapper.list` 方法应该返回 `Page<Article>` 而不是普通的 `List<Article>`。如果 `articleMapper.list` 返回的是普通列表，则需要调整该方法的实现或使用 `PageHelper` 提供的方法来获取分页结果。

3. **直接从 PageHelper 获取分页信息**：可以直接通过 `PageHelper` 提供的静态方法获取分页信息，而不需要手动转换。


解释：
- **PageHelper.startPage(pageNum, pageSize)**：开始分页，设置当前页码和每页显示的记录数。
- **PageInfo<Article> pageInfo = new PageInfo<>(articles)**：使用 `PageInfo` 来封装查询结果，`PageInfo` 是 `PageHelper` 提供的一个类，用于获取分页信息。
- **pageBean.setTotal(pageInfo.getTotal())** 和 **pageBean.setItems(pageInfo.getList())**：将分页信息设置到 `PageBean` 中。

这样修改后，代码会正确处理分页逻辑，并避免 `ClassCastException` 错误。

修改如下

```java
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
```