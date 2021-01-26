package com.jiajiao.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
@EnableConfigurationProperties
@Data
public class SmsConfigue {
    public String accessKeyId;//你的accessKeyId,参考本文档步骤2
    public String accessKeySecret;//你的accessKeySecret，参考本文档步骤2
    public String signName;
    public String templateCode;
}
