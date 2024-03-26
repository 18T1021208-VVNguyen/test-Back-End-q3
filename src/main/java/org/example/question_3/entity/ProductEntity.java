package org.example.question_3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity extends  BaseEntity{

    @Column(name = "name" )
    @NotBlank
    private String name;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "price")
    @NotNull
    private Float price;

    @Column(name = "description")
    private String description;

    @Column(name ="data_file" , columnDefinition="MEDIUMBLOB")
    @Lob
    private byte[] dataFile;

    @Column(name = "type_file")
    private String typeFile;

    @Column(name = "name_file")
    private String nameFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;
}
