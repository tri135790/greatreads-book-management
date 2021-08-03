package com.example.greatreadsbookmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "types")
public class BookType extends BaseEntity{

    @Column(name = "name")
    private String name;
}
