package com.jiajiao.web.service.Impl;

import com.jiajiao.web.form.RegisterForm;
import com.jiajiao.web.myenum.UserRoleEnum;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.dao.UserMapper;
import com.jiajiao.web.form.LoginForm;
import com.jiajiao.web.pojo.User;
import com.jiajiao.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    final String HEAD_IMAGE_URL="https://files-cdn.cnblogs.com/files/poloyy/touxianga.bmp";
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;

    /**
     * 注册功能
     * 使用password + code 登录
     * @param form 注册表单 code=1234
     * @return
     */
    public ResponseVo Register(RegisterForm form) {
        //获取redis中的手机验证码
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        String codeStr = (String)opsForHash.get("phone", String.valueOf(form.getPhone()));
        if(codeStr==null||"".equals(codeStr)){
            return ResponseVo.error("请重新获取验证码");
        }

        //校验验证码
        Integer code=Integer.parseInt(codeStr);
        if(code!=form.getCode()){
            return ResponseVo.error("验证码错误");
        }

        //检查数据库是否存在该手机号
        User userSql = userMapper.selectByPhone(form.getPhone());
        if(userSql!=null){
            return ResponseVo.success("登录成功");
        }

        //插入数据
        User user=new User();
        user.setPhone(form.getPhone());
        this.setUserDefaultData(user);
        int num = userMapper.insert(user);
        if(num==0){
            return ResponseVo.error();
        }
        return ResponseVo.success("注册并登录成功");

    }

    /**
     * 登录功能
     * 使用 phone + password登录
     * @param form
     * @return
     */
    public ResponseVo Login(LoginForm form) {

        User userSql = userMapper.selectByPhone(form.getPhone());
        if(userSql==null){
            return ResponseVo.error("该账号不存在");
        }
        if (!userSql.getPassword().equals(form.getPassword())) {
            return ResponseVo.error("密码错误，可尝试用验证码登录");
        }
        return ResponseVo.success("登录成功");
    }

    /**
     * 设置user的一些默认的值
     * @param user
     */
    private void setUserDefaultData(User user){
        user.setRole(UserRoleEnum.CUMSTOMER.getCode());
        user.setName(UserRoleEnum.CUMSTOMER.getMsg()+user.getPhone());
        user.setHeadImg(HEAD_IMAGE_URL);
    }
}
