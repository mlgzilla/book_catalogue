package com.example.book_catalogue.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors", schema = "catalogue")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_id_gen")
    @SequenceGenerator(name = "authors_id_gen", sequenceName = "catalogue.authors_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "biography", length = 128)
    private String biography;

    @OneToMany(mappedBy = "authorEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<BookAuthorEntity> bookAuthorEntities = new ArrayList<>();
}