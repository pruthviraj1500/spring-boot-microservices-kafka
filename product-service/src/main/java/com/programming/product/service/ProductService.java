package com.programming.product.service;

import com.programming.product.dto.ProductRequest;
import com.programming.product.dto.ProductResponse;
import com.programming.product.model.Product;
import com.programming.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){

        Product product = Product.builder()
                .name(productRequest.name())
                .descripton(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created succesfully");
        return new ProductResponse(product.getId(), product.getName(), product.getDescripton(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescripton(),product.getPrice()))
                .toList();
    }
}
