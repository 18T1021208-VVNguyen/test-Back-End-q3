package org.example.question_3.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FilterModel {
    private List<Long> categoryId = new ArrayList<>();

    private Integer typeFilePrice;


}
