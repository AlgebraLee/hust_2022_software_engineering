package com.kob.backend.service.impl.user.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.AgreeMapper;
import com.kob.backend.pojo.Agree;
import com.kob.backend.service.user.post.CountAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CountAgreeServiceImpl implements CountAgreeService {
    @Autowired
    private AgreeMapper agreeMapper;

    @Override
    public Map<String, Integer> CountAgree(int pid) {
        Map<String,Integer> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",pid);
        List<Agree> list = agreeMapper.selectList(queryWrapper);
        map.put("cnt",list.size());
        return map;
    }
}
