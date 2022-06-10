package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbcEntity {

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent")
    private Category parent;

}
