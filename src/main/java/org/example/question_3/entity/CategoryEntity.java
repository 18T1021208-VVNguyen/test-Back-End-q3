package org.example.question_3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "categorys", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_CATEGORY_NAME", columnNames = "name") })
public class CategoryEntity extends  BaseEntity{

    @NotBlank
    @Column(name = "name" )
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryEntity")
    private Set<ProductEntity> product = new HashSet<>();
}
