package com.ibm.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("aliyun.oss")
public class GetProperties {
    private String endpoint;
    private String bucketName;
}
