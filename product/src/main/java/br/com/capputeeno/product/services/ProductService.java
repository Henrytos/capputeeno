package br.com.capputeeno.product.services;

import br.com.capputeeno.product.dtos.ProductResponseDTO;
import br.com.capputeeno.product.models.BookEntity;
import br.com.capputeeno.product.models.ProductEntity;
import br.com.capputeeno.product.models.enums.ProductType;
import br.com.capputeeno.product.respositories.ProductRepository;
import br.com.capputeeno.product.services.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductResponseDTO> fetch(Pageable pageable) {

        return this.productRepository.findAll(pageable).map(product -> {
            if (product instanceof BookEntity book)
                return new ProductResponseDTO(book);

            return new ProductResponseDTO(product);
        });
    }

    public Page<ProductResponseDTO> fetch(Pageable pageable, ProductType productType) {

        return this.productRepository.findByType(productType, pageable).map(product -> {
            if (product instanceof BookEntity book)
                return new ProductResponseDTO(book);

            return new ProductResponseDTO(product);
        });
    }

    @Cacheable(value = "products")
    public ProductResponseDTO findByProductId(UUID productId) {
        log.info("Buscando produto {}", productId);
        ProductEntity product = this.productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        if (product instanceof BookEntity book)
            return new ProductResponseDTO(book);

        return new ProductResponseDTO(product);
    }

    public Page<ProductResponseDTO> fetch(Pageable pageable, String search) {

        return this.productRepository.findByNameContainingIgnoreCase(search, pageable).map(product -> {
            if (product instanceof BookEntity book)
                return new ProductResponseDTO(book);

            return new ProductResponseDTO(product);
        });
    }
}
