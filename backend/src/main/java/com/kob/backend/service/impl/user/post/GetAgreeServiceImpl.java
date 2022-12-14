package com.kob.backend.service.impl.user.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.AgreeMapper;
import com.kob.backend.pojo.Agree;
import com.kob.backend.service.user.post.GetAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GetAgreeServiceImpl implements GetAgreeService {
    @Autowired
    private AgreeMapper agreeMapper;
    @Override
    public Map<Integer, Boolean> GetAgree(Integer id) {
        Map<Integer,Boolean> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        List<Agree> list = agreeMapper.selectList(queryWrapper);
        for(Agree agree:list){
            Integer pid = agree.getPid();
            map.put(pid,true);
        }
        return map;
    }
}
