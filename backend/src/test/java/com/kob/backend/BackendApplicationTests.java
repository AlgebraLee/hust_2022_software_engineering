package com.kob.backend;

import com.kob.backend.controller.user.post.UploadController;
import com.kob.backend.service.impl.user.account.RegisterServiceImpl;
import com.kob.backend.service.impl.user.post.GetPostServiceImpl;
import com.kob.backend.service.impl.user.post.UploadPostServiceImpl;
import com.kob.backend.service.user.account.RegisterService;
import com.kob.backend.service.user.post.GetPostService;
import com.kob.backend.service.user.post.UploadPostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {

    }

}
