package com.kob.backend.controller.user.post;


import com.kob.backend.service.user.post.AddAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddAgreeController {
    @Autowired
    private AddAgreeService addAgreeService;
    @GetMapping("/user/agree/add/")
    public Map<String,String> AddAgree(@RequestParam Map<String,String> map){
        Integer pid = Integer.parseInt(map.get("pid"));
        Integer id = Integer.parseInt(map.get("id"));
        return addAgreeService.AddAgree(pid,id);
    }
}
