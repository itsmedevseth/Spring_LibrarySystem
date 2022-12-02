package com.library.libary.enity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Publisher  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",length = 50,nullable = false,unique = true)
    private String name;
    @ManyToMany(mappedBy = "publishers",cascade = CascadeType.ALL)
    private Set<Books> books= new HashSet<Books>();

    public Publisher(String name) {
        this.name = name;
    }

}
