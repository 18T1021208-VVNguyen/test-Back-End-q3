package org.example.question_3.service.impl;

import org.example.question_3.entity.CategoryEntity;
import org.example.question_3.model.CategoryModel;
import org.example.question_3.repository.CategoryRepository;
import org.example.question_3.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<CategoryModel> findAll() {
        List<CategoryEntity> listEntity = categoryRepository.findAll();
        List<CategoryModel> result = listEntity.stream().map(
                item -> mapper.map(item, CategoryModel.class)
        ).collect(Collectors.toList());
       return result;
    }
}
