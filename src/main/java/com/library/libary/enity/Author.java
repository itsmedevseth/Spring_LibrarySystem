package com.library.libary.enity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100,nullable = false,unique = true)
    private String name;
    @Column(name = "description", length = 250,nullable = false)
    private String description;
    @ManyToMany(mappedBy = "authors",cascade = CascadeType.ALL)
    private Set<Books> books= new HashSet<>();

    public Author(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
