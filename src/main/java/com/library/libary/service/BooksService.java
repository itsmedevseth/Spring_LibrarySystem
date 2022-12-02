package com.library.libary.service;

import com.library.libary.Repository.BookRepository;
import com.library.libary.enity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BookRepository  bookRepository;

    public List<Books> findAllBook()
    {
        return bookRepository.findAll();
    }
    public Books findBookById(Long id)
    {
        Books book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        return book;
    }

    public void CreateBook(Books books)
    {
        bookRepository.save(books);
    }
    public void DeleteBook(Long id)
    {
        Books book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        bookRepository.deleteById(book.getId());
    }
    public void updateBook(Books books)
    {
        bookRepository.save(books);
    }

    public boolean checkname(String item) {
        if (bookRepository.existsByName(item)) {
            return true;
        }
        return false;
    }
}
