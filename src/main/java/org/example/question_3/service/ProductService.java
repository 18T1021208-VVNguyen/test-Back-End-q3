package org.example.question_3.service;

import org.example.question_3.model.FilterModel;
import org.example.question_3.model.ProductModel;

import java.util.List;

public interface ProductService {

    public void saveProduct(ProductModel productModel);

    public List<ProductModel> getAllProduct();

    public void deleteProduct(Long id);

    public List<ProductModel>  filter(FilterModel filterModel);

    public List<ProductModel> search(String search);
}
