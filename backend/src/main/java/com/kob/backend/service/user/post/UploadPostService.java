package com.kob.backend.service.user.post;

import java.sql.Date;
import java.util.Map;

public interface UploadPostService {
    Map<String,String> upload_a_post(String content, String create_time, Integer id);
}
