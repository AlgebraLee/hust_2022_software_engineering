package com.kob.backend.controller.user.post;


import com.kob.backend.service.user.post.GetCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetCommentController {
    @Autowired
    private GetCommentService getCommentService;
    @GetMapping("/user/post/getComment/")
    public List<Map<String,String>> get_comment(@RequestParam Integer pid) {
        return getCommentService.get_comment(pid);
    }
}
