package com.kob.backend.service.user.post;

import java.util.Map;

public interface UpdateCommentService {
    Map<String,String> update_a_comment(String content,Integer pid,Integer id);
}
