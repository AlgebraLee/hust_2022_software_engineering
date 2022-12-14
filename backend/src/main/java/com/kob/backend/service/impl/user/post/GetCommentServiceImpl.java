package com.kob.backend.service.impl.user.post;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.CommentMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Comment;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.post.GetCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetCommentServiceImpl implements GetCommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Map<String, String>> get_comment(int pid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",pid);
        List<Comment> list = commentMapper.selectList(queryWrapper);
        List<Map<String,String>> res = new ArrayList<>();
        for(Comment comment:list){
            Map<String,String> map = new HashMap<>();
            map.put("content",comment.getContent());
            map.put("pid",comment.getPid().toString());
            User user = userMapper.selectById(comment.getId());
            map.put("username",user.getUsername());
            map.put("photo",user.getPhoto());
            res.add(map);
        }
        return res;
    }
}
