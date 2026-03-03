package br.com.capputeeno.product.models;

import br.com.capputeeno.product.models.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class EntityAbstract {

    private String name;

    private BigDecimal price;

    private String description;

    private String imageUrl;

    private ProductType type;

    private Boolean isAvailable = true;

}
