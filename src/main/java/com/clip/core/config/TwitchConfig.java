package com.clip.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "api.twitch")
@Getter
@Setter
public class TwitchConfig {
    private String urlAuth;
    private String urlClips;
    private String broadcasterId;
    private String clientId;
    private String secretId;
    private String grantType;
}
