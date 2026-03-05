package br.com.capputeeno.product.dtos;

import br.com.capputeeno.product.models.AuthorEntity;
import br.com.capputeeno.product.models.BookEntity;
import br.com.capputeeno.product.models.ProductEntity;
import br.com.capputeeno.product.models.enums.CategoryBook;
import br.com.capputeeno.product.models.enums.ProductType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    private UUID id;

    private String name;

    private BigDecimal price;

    private String description;

    private String imageUrl;

    private ProductType type;

    private Boolean isAvailable;

    //    book

    private Integer quantityPages;

    private CategoryBook category;

    private List<AuthorEntity> authors;

    private String publish;

    public ProductResponseDTO(UUID id, String name, BigDecimal price, String description, String imageUrl, ProductType type, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public ProductResponseDTO(ProductEntity product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getImageUrl(), product.getType(), product.getIsAvailable());
    }

    public ProductResponseDTO(BookEntity book){
        this(book.getId(), book.getName(), book.getPrice(), book.getDescription(), book.getImageUrl(), book.getType(), book.getIsAvailable());

        this.quantityPages = book.getQuantityPages();
        this.category = book.getCategory();
        this.authors = new ArrayList<>();
        this.authors.addAll(book.getAuthors());
        this.publish = book.getPublish();
    }
}
