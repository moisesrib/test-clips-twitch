package com.clip.clips.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateClipDTO {

    @JsonProperty("id")
    private String clipId;

    private String title;
    private String url;

    @JsonProperty("creator_name")
    private String author;

    @JsonProperty("creator_id")
    private String authorId;

    @JsonProperty("game_id")
    private String gameId;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    private double duration;
}