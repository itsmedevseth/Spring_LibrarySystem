package com.library.libary.service;

import com.library.libary.Repository.PublisherRepository;
import com.library.libary.enity.Author;
import com.library.libary.enity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherSerivce {
    @Autowired
    PublisherRepository publisherRepository;


    public List<Publisher> findAllPublisher()
    {
        return publisherRepository.findAll();
    }
    public Publisher findPublisherById(Long id)
    {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
        return publisher;
    }

    public void CreatePublisher(Publisher publisher)
    {
        publisherRepository.save(publisher);
    }

    public void UpdatePublisher(Publisher publisher)
    {
        publisherRepository.save(publisher);
    }


    public void DeletePublisher(Long id)
    {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(()->new RuntimeException(" not found"));
        publisherRepository.deleteById(publisher.getId());
    }
    public boolean checkname(String item) {
        if (publisherRepository.existsByName(item)) {
            return true;
        }
        return false;
    }
}
