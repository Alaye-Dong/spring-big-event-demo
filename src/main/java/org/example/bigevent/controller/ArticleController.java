package org.example.bigevent.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/) {
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("文章数据");
//        } catch (Exception e) {
//            response.setStatus(401);
//            return Result.error("token无效");
//        }
        return Result.success("文章数据");
    }
}
