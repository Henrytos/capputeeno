package br.com.capputeeno.product.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "authors")
@Data
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "author_id")
    private UUID  id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntity>  books = new ArrayList<>();
}
