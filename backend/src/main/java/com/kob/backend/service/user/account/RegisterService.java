package com.kob.backend.service.user.account;

import java.util.Map;

public interface RegisterService {
    public Map<String,String> Register(String username,String password,String confirmedPassword);
}
