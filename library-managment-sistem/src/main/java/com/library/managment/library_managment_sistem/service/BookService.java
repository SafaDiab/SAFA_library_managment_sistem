package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.dto.BookDto;
import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.mapper.BookMapper;
import com.library.managment.library_managment_sistem.repositry.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    public Book addBook(Book book) {
        logger.info("Adding book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    public BookDto addBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.bookToBookDto(savedBook);
    }

public List<BookDto> listBooks() {
    return bookRepository.findAll()
            .stream()
            .map(bookMapper::bookToBookDto)
            .collect(Collectors.toList());
}

    public void deleteBook(Long id) {
        Book existingBook =bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found"));
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(Long id, BookDto updatedBookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

        existingBook.setAuthor(updatedBookDto.getAuthor());
        existingBook.setTitle(updatedBookDto.getTitle());
        existingBook.setIsAvailable(updatedBookDto.getIsAvailable());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.bookToBookDto(updatedBook);

//    public Book updateBook(Long id, Book updatedBook) {
//        Book existingBook =bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found"));
//        existingBook.setAuthor(updatedBook.getAuthor());
//        existingBook.setTitle(updatedBook.getTitle());
//        existingBook.setIsAvailable(updatedBook.getIsAvailable());
//        return bookRepository.save(existingBook);
    }

}
