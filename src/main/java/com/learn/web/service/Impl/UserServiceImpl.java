package com.learn.web.service.Impl;

import com.learn.web.dao.UserMapper;
import com.learn.web.form.LoginForm;
import com.learn.web.form.RegisterForm;
import com.learn.web.pojo.User;
import com.learn.web.service.IUserService;
import com.learn.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;
    // form 表单code == 1234
    public ResponseVo Register(RegisterForm form) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        String code = (String)opsForHash.get("phone", String.valueOf(form.getPhone()));
        System.out.println(code);
        return null;
    }

    @Override
    public ResponseVo Login(LoginForm form) {

        User user = userMapper.selectByPhone(form.getPhone());
        System.out.println(user);
        String password = user.getPassword();
        System.out.println(password);

        return null;
    }
}
