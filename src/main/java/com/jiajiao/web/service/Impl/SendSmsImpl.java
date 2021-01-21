package com.jiajiao.web.service.Impl;


import cn.javaer.aliyun.sms.SmsClient;
import cn.javaer.aliyun.sms.SmsTemplate;
import com.jiajiao.web.service.ISendSms;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SendSmsImpl implements ISendSms {

    @Autowired
    SmsClient smsClient;
    @Autowired
    SmsTemplate smsTemplate;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public ResponseVo sendSms(List<String> phoneNumbers) {

        //获得手机对应的验证码
        smsTemplate.setPhoneNumbers(phoneNumbers);
        String code = getCodeAndAddRedis(phoneNumbers.get(0));

        //发送验证码短信
        Map<String,String> param=new HashMap<>();
        param.put("code",code);
        smsTemplate.setTemplateParam(param);
        smsClient.send(smsTemplate);

        return ResponseVo.success("获取验证码成功");
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
