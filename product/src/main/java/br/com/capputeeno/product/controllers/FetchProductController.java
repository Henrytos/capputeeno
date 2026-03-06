package br.com.capputeeno.product.controllers;

import br.com.capputeeno.product.dtos.ProductResponseDTO;
import br.com.capputeeno.product.models.enums.ProductType;
import br.com.capputeeno.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class FetchProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> fetchProducts(
            @PageableDefault(size = 12) Pageable pageable,
            @RequestParam(defaultValue = "all") String productType,
            @RequestParam(defaultValue = "") String search) {

        Page<ProductResponseDTO> response;

        if (productType.equalsIgnoreCase("all") && search.isEmpty())
            response = this.productService.fetch(pageable);
        else if (productType.equalsIgnoreCase("all") && !search.isEmpty())
            response = this.productService.fetch(pageable, search);
        else
            response = this.productService.fetch(pageable, ProductType.valueOf(productType));

        return ResponseEntity.ok(response);
    }

}
