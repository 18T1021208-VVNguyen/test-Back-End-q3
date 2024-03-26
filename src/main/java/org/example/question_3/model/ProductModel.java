package org.example.question_3.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

    @NotNull
    private MultipartFile file;

    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private Float price;

    @NotBlank
    private String description;

    private String dataFileBase64;

    private Long id;

    @NotNull
    private Long idCategory;

    private String nameCategory;


}
