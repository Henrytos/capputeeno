package br.com.capputeeno.product.models;

import br.com.capputeeno.product.models.enums.CategoryBook;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
public class BookEntity extends ProductEntity {

    private Integer quantityPages;

    @Enumerated(EnumType.STRING)
    private CategoryBook category;

    @ManyToMany()
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity> authors = new ArrayList<>();

    private String publish;

}
