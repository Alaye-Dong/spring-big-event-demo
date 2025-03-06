package org.example.bigevent.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.ArticleService;
import org.example.bigevent.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }
}
