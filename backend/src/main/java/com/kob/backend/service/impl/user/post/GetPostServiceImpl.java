package com.kob.backend.service.impl.user.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.PostMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Post;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.post.GetPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetPostServiceImpl implements GetPostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Map<String, String>> get_post() {
        List<Post> list = postMapper.selectList(null);
        List<Map<String,String>> res = new ArrayList<>();
        for(Post post : list){
            Map<String,String> map = new HashMap<>();
            map.put("id",post.getId().toString());
            map.put("pid",post.getPid().toString());
            map.put("content",post.getContent());
            map.put("timer", post.getTimer());
            User user = userMapper.selectById(post.getId());
            map.put("photo",user.getPhoto());
            map.put("username",user.getUsername());
            res.add(map);
        }
        return res;
    }
}
