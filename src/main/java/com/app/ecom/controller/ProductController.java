package com.app.ecom.controller;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponseDTO;
import com.app.ecom.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<ProductResponseDTO>(productService.createProduct(productRequest),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequest productRequest,
                                                            @PathVariable Long id) {
        return productService.updateProduct(productRequest, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
