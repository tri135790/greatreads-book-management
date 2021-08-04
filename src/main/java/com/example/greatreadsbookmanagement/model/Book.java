package com.example.greatreadsbookmanagement.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Setter
@Getter
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
    private Integer rating;

    @Column(name = "date_added")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAdded;

    @Column(name = "date_finished")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinished;

    @Column(name = "is_my_book")
    private boolean isMyBook;

    @ManyToOne
    @JoinColumn(name = "types_id")
    private BookType bookType;

    @ManyToOne
    @JoinColumn(name = "shelves_id")
    private Shelf shelf;

}
