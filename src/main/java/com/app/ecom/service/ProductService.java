package com.app.ecom.service;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponseDTO;
import com.app.ecom.model.Product;
import com.app.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequest productRequest) {
        Product product = new Product();
        mapToProductRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);

        return mapToProductResponse(savedProduct);
    }


    private void mapToProductRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
    }

    private ProductResponseDTO mapToProductResponse(Product savedProduct) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(savedProduct.getId());
        productResponseDTO.setName(savedProduct.getName());
        productResponseDTO.setActive(savedProduct.getActive());
        productResponseDTO.setCategory(savedProduct.getCategory());
        productResponseDTO.setDescription(savedProduct.getDescription());
        productResponseDTO.setPrice(savedProduct.getPrice());
        productResponseDTO.setImageUrl(savedProduct.getImageUrl());
        productResponseDTO.setStockQuantity(savedProduct.getStockQuantity());

        return productResponseDTO;
    }


    public Optional<ProductResponseDTO> updateProduct(ProductRequest productRequest, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    mapToProductRequest(existingProduct, productRequest);
                    Product updatedProduct = productRepository.save(existingProduct);

                    return mapToProductResponse(updatedProduct);
                });
    }
}
