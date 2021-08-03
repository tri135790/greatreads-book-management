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
@Table(name = "shelves")
public class Shelf extends BaseEntity {

    @Column(name = "status")
    private String status;

}
