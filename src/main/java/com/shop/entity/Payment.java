package com.shop.entity;

import com.shop.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends AbcEntity {

    @OneToOne(fetch = FetchType.EAGER)
    private OrderDetails order;

    private Double totalPrice;

    private String paymentType;

    private String status;
}
