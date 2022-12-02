package com.library.libary.Repository;

import com.library.libary.enity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books,Long> {
    boolean existsByName(String name);
}
