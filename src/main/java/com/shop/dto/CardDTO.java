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
public class CardDTO {

    private Long id;

    private String name;

    private String number;

    private Double balance;

    private Instant expired_data;

    private String status;

    private UserDTO user;

    private LocalDateTime created_at;

}
