package com.jiajiao.web.service;

import com.jiajiao.web.vo.ResponseVo;

import java.util.List;

public interface ISendSms {
    public ResponseVo sendSms(String phoneNumbers);
}
