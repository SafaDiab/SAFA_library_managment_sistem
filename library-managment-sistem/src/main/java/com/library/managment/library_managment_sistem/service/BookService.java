package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.repositry.BookRepository;
import com.library.managment.library_managment_sistem.repositry.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) {
        Book existingBook =bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found"));
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook =bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found"));
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsAvailable(updatedBook.getIsAvailable());
        return bookRepository.save(existingBook);
    }

}
