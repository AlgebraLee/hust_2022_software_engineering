package com.kob.backend.controller.user.post;


import com.kob.backend.service.user.post.CountAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CountAgreeController {
    @Autowired
    private CountAgreeService countAgreeService;
    @GetMapping("/user/agree/count/")
    public Map<String,Integer> count(@RequestParam Integer pid){
        return countAgreeService.CountAgree(pid);
    }
}
