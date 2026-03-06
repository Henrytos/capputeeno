package br.com.capputeeno.product.respositories;

import br.com.capputeeno.product.models.ProductEntity;
import br.com.capputeeno.product.models.enums.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Page<ProductEntity> findByType(ProductType productType, Pageable pageable);

    Page<ProductEntity> findByNameContainingIgnoreCase(String search, Pageable pageable);

}
