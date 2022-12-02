package com.library.libary.Repository;

import com.library.libary.enity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    boolean existsByName(String name);
}
