package com.jiajiao.web.service.Impl;

import com.jiajiao.web.enums.RedisIllegalConst;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.form.RegisterForm;
import com.jiajiao.web.enums.HttpStatusEnum;
import com.jiajiao.web.enums.UserRoleEnum;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.dao.UserMapper;
import com.jiajiao.web.form.LoginForm;
import com.jiajiao.web.pojo.User;
import com.jiajiao.web.service.IUserService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
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
    public ResponseVo register(RegisterForm form) {

        if(!checkLegal(form.getPhone())){
            return ResponseVo.error("该账号已被封禁");
        }
        //获取redis中的手机验证码
        ValueOperations<String, String> opsForValue  = redisTemplate.opsForValue();
        String codeStr = opsForValue.get(String.valueOf(form.getPhone()));
        if(codeStr==null||"".equals(codeStr)){
            return ResponseVo.error("请重新获取验证码");
        }

        //校验验证码
        Integer code=Integer.parseInt(codeStr);
        if(!code.equals(form.getCode())){
            return ResponseVo.error("验证码错误");
        }

        //检查数据库是否存在该手机号
        User userSql = userMapper.selectByPhone(form.getPhone());
        if(userSql!=null){
            return ResponseVo.success("登录成功",userSql);
        }

        //插入数据
        User user=new User();
            user.setPhone(form.getPhone());
            user.setRole(UserRoleEnum.CUMSTOMER.getCode());
            user.setName(UserRoleEnum.CUMSTOMER.getMsg()+"_"+user.getPhone());
            user.setHeadImg(HEAD_IMAGE_URL);
        int num = userMapper.insertSelective(user);
        if(num==0){
            return ResponseVo.error();
        }

        return ResponseVo.success("注册并登录成功",user);
    }

    /**
     * 登录功能
     * 使用 phone + password登录
     * @param form
     * @return
     */
    public ResponseVo login(LoginForm form) {
        if(!checkLegal(form.getPhone())){
            return ResponseVo.error("该账号已被封禁");
        }
        User userSql = userMapper.selectByPhone(form.getPhone());
        if(userSql==null){
            return ResponseVo.error("该账号不存在");
        }
        if(userSql.getPassword()==null){
            return ResponseVo.error("请先设置密码");
        }
        if (!userSql.getPassword().equals(form.getPassword())) {
            return ResponseVo.error("密码错误!");
        }
        return ResponseVo.success("登录成功",userSql);
    }

    /**
     * 删除redis的session记录
     * @param cookie
     * @return
     */
    public ResponseVo logout(Cookie cookie){
        String userSession = cookie.getValue();
        Boolean res = redisTemplate.delete(userSession);
        if(!res){
            return ResponseVo.error("退出失败，删除Session失败");
        }
        return ResponseVo.success("退出成功");
    }

    /**
     * 给用户设置7天的session时间
     * @param user
     * @param response
     */
    public void setSession(User user, HttpServletResponse response) {

        //设置浏览器session
        String userSession = DigestUtils.md5DigestAsHex(("" + user.getId() + user.getPhone()).getBytes());
        //添加sessionid到Cookie
        response.addCookie(new Cookie(UserReqConst.UESR_SESSION,userSession));
        //添加 sessionid -》 userid  到 redis
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(userSession,user.getId().toString(),7L,TimeUnit.DAYS);
    }


    /**
     * 设置user 一些相关的信息
     * 设置多少，写入多少
     * @param user
     * @return
     */
    public ResponseVo updateUser(User user,Integer uId) {
        //根据uId更新相关信息
        user.setId(uId);
        //不能修改手机号
        user.setPhone(null);
        int i =userMapper.updateByPrimaryKeySelective(user);
        if(i==0){
            return ResponseVo.error(HttpStatusEnum.ERROR_SERVER.getMsg());
        }
        return ResponseVo.success("设置成功");
    }

    private boolean checkLegal(Long phone){
        Object check =redisTemplate.opsForHash().get(RedisIllegalConst.ILLEGAL_PHONES, String.valueOf(phone));
        if(check==null)
            return true;
        return false;
    }
}
