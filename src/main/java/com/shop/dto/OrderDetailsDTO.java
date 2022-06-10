package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsDTO {

    private Long id;

    private ProductDTO products;

    private UserDTO user;

    private Integer quantity;

    private Double price;

    private boolean isDeleted;
}
