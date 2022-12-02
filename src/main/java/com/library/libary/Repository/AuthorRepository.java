package com.library.libary.Repository;

import com.library.libary.enity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public   interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByName(String name);
}
