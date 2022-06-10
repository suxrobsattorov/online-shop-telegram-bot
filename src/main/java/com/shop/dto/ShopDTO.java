package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {

    private Long id;

    private String name;

    private Integer floor;

    private String status;

    private UserDTO user;

    private CategoryDTO category;

}
