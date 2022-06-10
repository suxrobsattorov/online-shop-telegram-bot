package com.shop.entity;

import com.shop.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPhoto extends AbcEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    private String key;

    private String originName;

    private String path;

    private Long size;

    private String extension;

}
