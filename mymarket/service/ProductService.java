package ru.summer.mymarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.summer.mymarket.model.Product;
import ru.summer.mymarket.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



    public Product findById(long id){
       return productRepository.findById(id).get();
    }

    public void addToProductList(Product product){
        productRepository.save(product);
    }

    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    public Product removeProductFromList(long id){
        Product product = productRepository.getById(id);
        productRepository.delete(product);
        return product;
    }


}
