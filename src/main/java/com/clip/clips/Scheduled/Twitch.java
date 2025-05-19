package com.clip.clips.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clip.clips.service.ClipService;

@Component
public class Twitch {

    @Autowired
    private ClipService clipService;
    
    @Scheduled(cron = "0/30 * * * * *")
    public void getClips() {
        //clipService.fetchAndSaveClips();
    }
}
