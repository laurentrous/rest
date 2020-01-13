package com.wcs.restQuet.controller;

import com.wcs.restQuet.entity.Book;
import com.wcs.restQuet.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("books/{id}")
    public Book show(@PathVariable Long id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String keyword = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book bookFromDb = bookRepository.findById(id).get();
        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setAuthor(book.getAuthor());
        bookFromDb.setDescription(book.getDescription());
        return bookRepository.save(bookFromDb);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        bookRepository.deleteById(id);
        return true;
    }
}
