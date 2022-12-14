package com.kob.backend.service.impl.user.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.AgreeMapper;
import com.kob.backend.pojo.Agree;
import com.kob.backend.service.user.post.DeleteAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class DeleteAgreeServiceImpl implements DeleteAgreeService {
    @Autowired
    private AgreeMapper agreeMapper;
    @Override
    public Map<String, String> DeleteAgree(Integer pid, Integer id) {
        Map<String,String> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",pid);
        queryWrapper.eq("id",id);
        agreeMapper.delete(queryWrapper);
        map.put("error_message","success");
        return map;
    }
}
