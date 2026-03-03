package br.com.capputeeno.product.models;

import jakarta.persistence.*;

@Table(name = "products_sold")
@Entity
public class ProductSold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_sold_id")
    private Long id;

    @ManyToOne @JoinColumn(name = "product_id")
    private ProductEntity product;

    Integer quantity;

}
