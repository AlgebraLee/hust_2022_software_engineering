package com.kob.backend.service.impl.user.post;

import com.kob.backend.mapper.PostMapper;
import com.kob.backend.pojo.Post;
import com.kob.backend.service.user.post.UploadPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UploadPostServiceImpl implements UploadPostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public Map<String, String> upload_a_post(String content, String timer, Integer id) {
        Map<String,String> map = new HashMap<>();
        if(content==""){
            map.put("error_message","内容不能为空");
            return map;
        }
        Post post = new Post(null,content,timer,id,true);
        postMapper.insert(post);
        map.put("error_message", "success");
        return map;
    }
}
