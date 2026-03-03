package br.com.capputeeno.product.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductEntity extends EntityAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;


}
