package com.clip.clips.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clips")
@Data
public class Clip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clip_id")
    private String clipId;

    private String title;

    private String url;

    private String author;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "game_id")
    private String gameId;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    private String duration;
}
