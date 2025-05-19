package com.clip.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "api.tiktok")
@Getter
@Setter
public class TiktokConfig {
    private String urlAuth;
    private String urlUplod;
    private String clientId;
    private String secretId;
    private String grantType;
}
