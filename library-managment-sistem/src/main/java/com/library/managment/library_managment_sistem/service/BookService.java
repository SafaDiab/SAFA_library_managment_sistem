package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.dto.BookDto;
import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Category;
import com.library.managment.library_managment_sistem.mapper.BookMapper;
import com.library.managment.library_managment_sistem.repositry.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
// jwt sistem güvenliği , sistem içinde integrasyon ekleme , isteklemele ekleme , Acess etkesi
@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }
    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.findByCategory(category);}

    public BookDto addBook(BookDto bookDto) {
        log.info("Adding a new book: {}", bookDto.getTitle());
        Book book = bookMapper.bookDtoToBook(bookDto);
        Book savedBook = bookRepository.save(book);
       // book.setCategory(bookDto.getCategory());
        return bookMapper.bookToBookDto(savedBook);
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookMapper.bookToBookDto(book);

    }

    public List<BookDto> listBooks() {
        log.info("Get a list of all books");
        return bookRepository.findAll()
            .stream()
            .map(bookMapper::bookToBookDto)
            .collect(Collectors.toList());
}

    public void deleteBook(Long id) {
        log.warn("Deleting book with ID: {}", id);
        Book existingBook =bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found"));
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(Long id, BookDto updatedBookDto) {
        log.info("Updating book with ID: {}", id);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

        existingBook.setAuthor(updatedBookDto.getAuthor());
        existingBook.setTitle(updatedBookDto.getTitle());
        existingBook.setIsAvailable(updatedBookDto.getIsAvailable());
        existingBook.setCategory(updatedBookDto.getCategory());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.bookToBookDto(updatedBook);
    }

}
