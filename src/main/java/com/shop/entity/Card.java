package com.shop.entity;

import com.shop.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbcEntity {

    private String name;

    private String number;

    private Double balance;

    private Instant expired_data;

    private String status;

    @ManyToOne
    private User user;

}
