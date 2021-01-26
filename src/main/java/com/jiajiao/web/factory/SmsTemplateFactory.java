package com.jiajiao.web.factory;

import com.aliyuncs.exceptions.ClientException;
import com.jiajiao.web.config.SmsConfigue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsTemplateFactory {
    @Autowired
    SmsConfigue smsConfigue;
    @Bean
    public SmsTemplate smsTemplate() throws ClientException {
        return new SmsTemplate(smsConfigue);
    }
}
