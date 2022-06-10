package com.shop.entity;

import com.shop.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement extends AbcEntity {

    @Column(nullable = false, length = 400)
    private String title;

    @Column(nullable = false)
    private String caption;

    private String status;

}

