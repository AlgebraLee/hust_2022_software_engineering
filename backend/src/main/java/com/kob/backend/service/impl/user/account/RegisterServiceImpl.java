package com.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> Register(String username, String password, String confirmedPassword) {
        Map<String,String> map = new HashMap<>();
        if(username.length()==0) {
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password.length()==0||confirmedPassword.length()==0){
            map.put("error_message","密码不能为空");
            return map;
        }
        if(!password.equals(confirmedPassword)){
            map.put("error_message","两次密码不一致");
            return map;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> list = userMapper.selectList(queryWrapper);
        if(!list.isEmpty()){
            map.put("error_message","用户名已存在");
            return map;
        }
        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://pic2.zhimg.com/80/v2-bed439ff53b5cc854445e58c85e37c45_1440w.webp";
        User user = new User(null,username,encodedPassword,photo);
        userMapper.insert(user);
        map.put("error_message","success");
        return map;
    }
}
