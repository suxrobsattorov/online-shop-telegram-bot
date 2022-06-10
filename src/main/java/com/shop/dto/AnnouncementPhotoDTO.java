package com.shop.dto;

import com.shop.entity.Announcement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnnouncementPhotoDTO {

    private Long id;

    private Announcement announcement;

    private String key;

    private String originName;

    private String path;

    private Long size;

    private String extension;

    private LocalDateTime created_at;

}
