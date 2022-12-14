package com.kob.backend.controller.user.post;


import com.kob.backend.service.user.post.UpdateCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UploadCommentController {
    @Autowired
    private UpdateCommentService updateCommentService;
    @PostMapping("/user/post/comment/")
    public Map<String,String> comment(@RequestParam Map<String,String> map){
        String content = map.get("content");
        Integer pid = Integer.parseInt(map.get("pid"));
        Integer id = Integer.parseInt(map.get("id"));
        return updateCommentService.update_a_comment(content,pid,id);
    }
}
