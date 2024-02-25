package org.example.question_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.question_3.model.CategoryModel;
import org.example.question_3.model.FilterModel;
import org.example.question_3.model.ProductModel;
import org.example.question_3.service.CategoryService;
import org.example.question_3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @GetMapping({"/index" , "/"})
    public String home(   Model model){

        List<CategoryModel> modelList =  categoryService.findAll();
        List<ProductModel> productModelList = productService.getAllProduct();
        model.addAttribute("categorys",modelList);
        model.addAttribute("product" , new ProductModel());
        model.addAttribute("products", productModelList);
        return "index";
    }

    @PostMapping({"/add-product" })
    public String addProduct(@Valid @ModelAttribute("product") ProductModel model , HttpServletRequest request){
        productService.saveProduct(model);
        return "redirect:index";
    }

    @GetMapping({"/delete-product"})
    public String deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
        return "redirect:index";
    }

    @GetMapping("/search-product")
    public ResponseEntity<?> searchProduct(@RequestParam(name = "search") String search){
        List<ProductModel> productModels = productService.search(search);
        return  ResponseEntity.ok().body(productModels);
    }

    @PostMapping("/filter-product")
    public ResponseEntity<?> filterProduct(@RequestBody FilterModel filterModel){
        List<ProductModel> productModels = productService.filter(filterModel);
        return  ResponseEntity.ok().body(productModels);
    }



}
