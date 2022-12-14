package com.kob.backend.service.user.post;

import java.util.List;
import java.util.Map;

public interface GetCommentService {
    List<Map<String,String>> get_comment(int pid);
}
