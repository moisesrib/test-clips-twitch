package com.clip.tiktok.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clip.core.service.ExternalApiService;

@Component
public class TikTok {

    @Autowired
    private ExternalApiService externalApiService;

    @Scheduled(cron = "0/30 * * * * *")
    public void testAuthenticate() {
        externalApiService.authenticateTiktok();
    }
}
