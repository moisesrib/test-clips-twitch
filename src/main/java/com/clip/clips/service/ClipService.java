package com.clip.clips.service;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clip.clips.dto.CreateClipDTO;
import com.clip.clips.dto.TwitchClipsResponseDTO;
import com.clip.clips.models.Clip;
import com.clip.clips.repository.ClipRepository;
import com.clip.core.service.ExternalApiService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClipService {

    @Autowired
    private  ClipRepository clipRepository;

    @Autowired
    private ExternalApiService externalApiService;
    private final ObjectMapper objectMapper = new ObjectMapper();

 

    public void fetchAndSaveClips() {
        try {
            HttpResponse<String> response = externalApiService.getClips();
    
            TwitchClipsResponseDTO twitchClips =
                objectMapper.readValue(response.body(), TwitchClipsResponseDTO.class);
    
            for (CreateClipDTO dto : twitchClips.getData()) {
                if (!clipRepository.existsByClipId(dto.getClipId())) {
                    Clip clip = new Clip();
                    clip.setClipId(dto.getClipId());
                    clip.setTitle(dto.getTitle());
                    clip.setUrl(dto.getUrl());
                    clip.setAuthor(dto.getAuthor());
                    clip.setAuthorId(dto.getAuthorId());
                    clip.setGameId(dto.getGameId());
                    clip.setThumbnailUrl(dto.getThumbnailUrl());
                    clip.setDuration(String.valueOf(dto.getDuration()));
    
                    clipRepository.save(clip);
                    System.out.println("Clip saved: " + clip.getTitle());
                } else {
                    System.out.println("Clip already exists: " + dto.getClipId());
                }
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
