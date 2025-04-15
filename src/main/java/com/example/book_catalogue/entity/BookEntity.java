package com.example.book_catalogue.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books", schema = "catalogue")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_gen")
    @SequenceGenerator(name = "books_id_gen", sequenceName = "catalogue.books_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "view_counter", nullable = false)
    private Long viewCounter;

    @Column(name = "date_added", nullable = false)
    private Instant dateAdded;

    @OneToMany(mappedBy = "bookEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<BookAuthorEntity> bookAuthorEntities = new ArrayList<>();
}