package com.jiajiao.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties(prefix = "aliyun.sms")
@Component
public class SmsConfig {

    private String signName;
    @Value("${aliyun.sms.templates.key1.templateCode:#{null}}")
    private String templateCode;
}
