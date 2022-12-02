package com.library.libary.enity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Books  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", length = 50,nullable = false,unique = true)
    private String isbn;
    @Column(name = "name", length = 50,nullable = false,unique = true)
    private String name;
    @Column(name = "description", length = 250,nullable = false)
    private String description;



    @ManyToMany
    @JoinTable(name = "book_authors",
    joinColumns = {@JoinColumn(name = "book_id")},
    inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<Author>() ;

    @ManyToMany
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "categories_id")})
    private Set<Category> categories = new HashSet<Category>() ;

    @ManyToMany
    @JoinTable(name = "books_publishers",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publishers_id")})
    private Set<Publisher> publishers = new HashSet<Publisher>();

    public Books(String isbn, String name, String description) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
    }
    public  void removePublisher(Publisher publisher){
        this.publishers.remove(publisher);
        publisher.getBooks().remove(publisher);
    }
    public void addPublisher(Publisher publisher){
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }
    public  void removeCategory(Category category){
        this.categories.remove(category);
        category.getBooks().remove(category);
    }
    public void addCategory(Category category){
        this.categories.add(category);
        category.getBooks().add(this);
    }
    public  void removeAuthors(Author author){
        this.authors.remove(author);
        author.getBooks().remove(author);
    }
    public void addAuthors(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }
}
