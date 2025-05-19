package com.clip.clips.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchClipsResponseDTO {
    private List<CreateClipDTO> data;
}
