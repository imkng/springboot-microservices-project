package com.programming.productservice.service;

import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
//    private final ProductRepository productRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
        log.info("Product {} is saved " + product.getId());

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        return productList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setDescription(product.getDescription());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
