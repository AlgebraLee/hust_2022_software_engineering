package com.kob.backend.controller.user.post;


import com.kob.backend.service.user.post.GetAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetAgreeController {
    @Autowired
    private GetAgreeService getAgreeService;
    @GetMapping("/user/agree/get/")
    public Map<Integer,Boolean> GetAgree(@RequestParam Integer id){
        return getAgreeService.GetAgree(id);
    }
}
