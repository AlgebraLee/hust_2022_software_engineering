package com.kob.backend.service.impl.user.post;

import com.kob.backend.mapper.AgreeMapper;
import com.kob.backend.pojo.Agree;
import com.kob.backend.service.user.post.AddAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AddAgreeServiceImpl implements AddAgreeService {
    @Autowired
    private AgreeMapper agreeMapper;
    @Override
    public Map<String, String> AddAgree(int pid, int id) {
        Map<String,String> map = new HashMap<>();
        Agree agree = new Agree(null,pid,id);
        agreeMapper.insert(agree);
        map.put("error_message","success");
        return map;
    }
}
