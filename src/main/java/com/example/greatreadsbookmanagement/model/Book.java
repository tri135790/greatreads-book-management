package com.example.greatreadsbookmanagement.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(name = "title")
    @NotEmpty
    private String title;

    @Column(name = "author")
    @NotEmpty
    private String author;

    @Column(name = "rating")
    @NotEmpty
    private int rating;

    @Column(name = "data_added")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAdded;

    @Column(name = "date_finished")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinished;

    @ManyToOne
    @JoinColumn(name = "types_id")
    private BookType bookType;

    @ManyToOne
    @JoinColumn(name = "shelves_id")
    private Shelf shelf;

}
