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
public class OrderDetails extends AbcEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Product products;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Integer countOfProduct;

    private Integer quantity;

    private Double price;

    private boolean isDeleted = false;
}
