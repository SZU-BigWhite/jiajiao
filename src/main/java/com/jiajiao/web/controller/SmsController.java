package com.jiajiao.web.controller;

import com.jiajiao.web.service.Impl.SendSmsImpl;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    SendSmsImpl sendSms;

    /**
     * 获取验证码接口
     * @param phone
     * @return
     */
    @GetMapping("/get/phone/code")
    public ResponseVo getPhoneCode(Long phone){
        ResponseVo res = sendSms.sendSms(String.valueOf(phone));
        return  res;
    }
}
