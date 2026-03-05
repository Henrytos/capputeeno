package br.com.capputeeno.product.models;

import br.com.capputeeno.product.models.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Inheritance(strategy = InheritanceType.JOINED) // estudar sobre
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;


    private String name;

    private BigDecimal price;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private Boolean isAvailable = true;

}
