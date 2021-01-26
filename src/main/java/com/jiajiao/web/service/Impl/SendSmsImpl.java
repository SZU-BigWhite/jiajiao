package com.jiajiao.web.service.Impl;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jiajiao.web.factory.SmsTemplate;
import com.jiajiao.web.service.ISendSms;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SendSmsImpl implements ISendSms {

    @Autowired
    SmsTemplate smsTemplate;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public ResponseVo sendSms(String phone) {
        //获得手机对应的验证码
        String code = getCodeAndAddRedis(phone);
        //发送验证码短信
        try {
            SendSmsResponse sendSmsResponse = smsTemplate.sendByPhone(phone, code);
            if(sendSmsResponse.getMessage().equals("OK")){
                return ResponseVo.success("获取验证码成功");
            }
            return ResponseVo.error(sendSmsResponse.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
            return ResponseVo.error("不可系统错误");
        }


    }
    private String getCodeAndAddRedis(String phoneNum){
        //从redis找code
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String code = opsForValue.get(phoneNum);
        if(code!=null)
            return code;

        //自行生成code
        code=String.valueOf((int)(Math.random()*9000+1000));
        opsForValue.set(phoneNum,code,1,TimeUnit.DAYS);
        System.out.println(code);
        return code;
    }
}
