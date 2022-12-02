package com.library.libary.service;

import com.library.libary.Repository.AuthorRepository;
import com.library.libary.Repository.BookRepository;
import com.library.libary.enity.Author;
import com.library.libary.enity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthor()
    {
        return authorRepository.findAll();
    }
    public Author findAuthorById(Long id)
    {
        Author author = authorRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        return author;
    }

    public void CreateAuthor(Author author)
    {
        authorRepository.save(author);
    }

    public void UpdateAuthor(Author author)
    {
        authorRepository.save(author);
    }


    public void DeleteAuthor(Long id)
    {
        Author author = authorRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        authorRepository.deleteById(author.getId());
    }
    public boolean checkname(String item) {
        if (authorRepository.existsByName(item)) {
            return true;
        }
        return false;
    }
}
