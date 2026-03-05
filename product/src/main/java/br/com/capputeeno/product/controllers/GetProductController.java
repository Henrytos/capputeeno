package br.com.capputeeno.product.controllers;

import br.com.capputeeno.product.dtos.ProductResponseDTO;
import br.com.capputeeno.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class GetProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> fetch(@PathVariable String productId) {
        ProductResponseDTO response = this.productService.findByProductId(UUID.fromString(productId));

        return ResponseEntity.ok(response);
    }
}
