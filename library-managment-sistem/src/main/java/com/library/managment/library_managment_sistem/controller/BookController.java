package com.library.managment.library_managment_sistem.controller;

import com.library.managment.library_managment_sistem.dto.BookDto;
import com.library.managment.library_managment_sistem.entity.Book;
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

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return ResponseEntity.ok(books);
    }

@GetMapping
public ResponseEntity<List<BookDto>> listBooks() {
    List<BookDto> books = bookService.listBooks();
    return new ResponseEntity<>(books, HttpStatus.OK);
}

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
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
}
