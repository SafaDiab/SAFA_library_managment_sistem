package com.library.managment.library_managment_sistem.controller;

import com.library.managment.library_managment_sistem.dto.BookDto;
import com.library.managment.library_managment_sistem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        BookDto savedBookDto = bookService.addBook(bookDto);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }

@GetMapping
public ResponseEntity<List<BookDto>> listBooks() {
    List<BookDto> books = bookService.listBooks();
    return new ResponseEntity<>(books, HttpStatus.OK);
}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        BookDto updatedBookDto = bookService.updateBook(id, bookDto);
        return new ResponseEntity<>(updatedBookDto, HttpStatus.OK);
    }
//    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
//        return bookService.updateBook(id, updatedBook);
//    }
}
