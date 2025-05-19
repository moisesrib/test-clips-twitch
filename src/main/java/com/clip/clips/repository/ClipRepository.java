package com.clip.clips.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clip.clips.models.Clip;

public interface ClipRepository extends JpaRepository<Clip, String> {
    boolean existsByClipId(String clipId);
}