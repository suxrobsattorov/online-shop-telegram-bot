package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPhotoDTO {

    private Long id;

    private ProductDTO product;

    private String key;

    private String originName;

    private String path;

    private Long size;

    private String extension;
}
