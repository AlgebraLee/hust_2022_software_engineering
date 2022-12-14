package com.kob.backend.controller.user.post;

import com.kob.backend.service.user.post.GetPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetController {
    @Autowired
    private GetPostService getPostService;
    @GetMapping("/user/post/get/")
    public List<Map<String,String>> get(){
        return getPostService.get_post();
    }
}
