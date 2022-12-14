package com.kob.backend.service.impl.user.post;

import com.kob.backend.mapper.CommentMapper;
import com.kob.backend.pojo.Comment;
import com.kob.backend.service.user.post.UpdateCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UpdateCommentServiceImpl implements UpdateCommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Map<String, String> update_a_comment(String content, Integer pid, Integer id) {
        Map<String,String> map = new HashMap<>();
        if(content==""){
            map.put("error_message","评论不能为空");
            return map;
        }
        Comment comment = new Comment(null,content,pid,id);
        commentMapper.insert(comment);
        map.put("error_message","success");
        return map;
    }
}
