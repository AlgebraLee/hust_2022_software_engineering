package com.kob.backend.controller.user.post;


import com.kob.backend.service.user.post.DeleteAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DeleteAgreeController {
    @Autowired
    private DeleteAgreeService deleteAgreeService;
    @GetMapping("/user/agree/delete/")
    public Map<String,String> DeleteAgree(@RequestParam Map<String,String> map){
        Integer pid = Integer.parseInt(map.get("pid"));
        Integer id = Integer.parseInt(map.get("id"));
        return deleteAgreeService.DeleteAgree(pid,id);
    }
}
