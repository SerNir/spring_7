package ru.summer.mymarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.summer.mymarket.model.Product;
import ru.summer.mymarket.service.ProductService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
   private final ProductService productService;



    //  http://localhost:8088/summer/add_product
    @GetMapping("/add_product")
    public String showAddProductForm() {
        return "add_product";
    }

    @PostMapping("/add_product_list")
    public String addProductToList( @RequestParam String title, @RequestParam float price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        productService.addToProductList(product);
        return "redirect:/add_product";
    }

    //  http://localhost:8088/summer/get_product_list
    @GetMapping("/get_product_list")
    public String getProductList(Model model) {

        model.addAttribute("list", productService.getProductList());
        return "products";
    }

    @GetMapping("/products/{id}")
    public String showInfo(Model model, @PathVariable int id) {
        model.addAttribute(productService.findById(id));
        return "info_product";
    }


    @PostMapping("/remove_product/{id}")
    public String removeProductFromList(Model model,@PathVariable long id){
        model.addAttribute(productService.removeProductFromList(id));
        return "redirect:/get_product_list";
    }

}
