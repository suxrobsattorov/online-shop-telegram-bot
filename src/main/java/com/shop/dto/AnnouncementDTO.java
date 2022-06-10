package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementDTO {

    private Long id;

    private String title;

    private String caption;

    private String status;

    private LocalDateTime created_at;

}
