package com.programmingtechie.productservice.service;

import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository ;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build(); // we have created the instance of product object

        productRepository.save(product);
        log.info("Product {}is Saved",product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> Products = productRepository.findAll(); // reads all the products from repository

      return  Products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .name(product.getDescription())
                .description((product.getDescription()))
                .price(product.getPrice())
                .build();
    }
}
