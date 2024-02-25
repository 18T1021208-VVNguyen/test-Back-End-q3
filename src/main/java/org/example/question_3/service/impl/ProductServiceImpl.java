package org.example.question_3.service.impl;

import jakarta.transaction.Transactional;
import org.example.question_3.entity.CategoryEntity;
import org.example.question_3.entity.ProductEntity;
import org.example.question_3.model.CategoryModel;
import org.example.question_3.model.FilterModel;
import org.example.question_3.model.ProductModel;
import org.example.question_3.repository.CategoryRepository;
import org.example.question_3.repository.ProductRepository;
import org.example.question_3.service.FilesStorageService;
import org.example.question_3.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveProduct(ProductModel productModel) {
        filesStorageService.init();
        ProductEntity productEntity =  mapper.map(productModel, ProductEntity.class);
        Optional<CategoryEntity> categoryEntity= categoryRepository.findById(productModel.getIdCategory());
        productEntity.setImgUrl( filesStorageService.saveFile(productModel.getFile()));
        productEntity.setCategoryEntity(categoryEntity.get());
        productRepository.save(productEntity);
    }

    @Transactional
    @Override
    public List<ProductModel> getAllProduct() {
        return productRepository.findAll().stream().map(
                item -> ProductModel.builder()
                        .name(item.getName())
                        .id(item.getId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .description(item.getDescription())
                        .imgUrl(item.getImgUrl())
                        .nameCategory(item.getCategoryEntity().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<ProductModel> filter(FilterModel filterModel) {
        Float price = 0f;
//        0 or 1
        if(filterModel.getTypeFilePrice() != -1){
            price = 10000000f;
        }
        if(filterModel.getCategoryId().isEmpty()){
           List<Long> ids =  categoryRepository.findAll().stream().map(item ->
                   item.getId()).collect(Collectors.toList());
           filterModel.setCategoryId(ids);
        }

        if(filterModel.getTypeFilePrice() == -1 || filterModel.getTypeFilePrice() == 1 ){
            List<ProductEntity> entityList =  productRepository.filterOption2(filterModel.getCategoryId() , price);
            return entityList.stream().map(
                    item -> ProductModel.builder()
                            .name(item.getName())
                            .id(item.getId())
                            .price(item.getPrice())
                            .quantity(item.getQuantity())
                            .description(item.getDescription())
                            .imgUrl(item.getImgUrl())
                            .nameCategory(item.getCategoryEntity().getName())
                            .build()
            ).collect(Collectors.toList());
        }
        else {
            return productRepository.filterOption1(filterModel.getCategoryId() ,price).stream().map(
                    item -> ProductModel.builder()
                            .name(item.getName())
                            .id(item.getId())
                            .price(item.getPrice())
                            .quantity(item.getQuantity())
                            .description(item.getDescription())
                            .imgUrl(item.getImgUrl())
                            .nameCategory(item.getCategoryEntity().getName())
                            .build()
            ).collect(Collectors.toList());
        }

    }

    @Override
    public List<ProductModel> search(String search) {
        return productRepository.searchByName(search).stream().map(
                item -> ProductModel.builder()
                        .name(item.getName())
                        .id(item.getId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .description(item.getDescription())
                        .imgUrl(item.getImgUrl())
                        .nameCategory(item.getCategoryEntity().getName())
                        .build()
        ).collect(Collectors.toList());
    }


}
