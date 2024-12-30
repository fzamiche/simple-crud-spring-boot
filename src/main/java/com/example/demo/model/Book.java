package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a Book entity with attributes for identification, title, and author.
 * This class is mapped to a database table named "Books" using JPA annotations.
 * It utilizes Lombok annotations for generating boilerplate code such as constructors, 
 * getters, setters, and the toString method.
 */
@Entity
@Table(name="Books")
@NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String author;

}
