package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.dto.BorrowingDto;
import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Borrowing;
import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.mapper.BorrowingMapper;
import com.library.managment.library_managment_sistem.repositry.BookRepository;
import com.library.managment.library_managment_sistem.repositry.BorrowingRepository;
import com.library.managment.library_managment_sistem.repositry.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


    @Slf4j
    @Service

    public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowingMapper borrowingMapper;

    public List<BorrowingDto> listBorrowings() {
        log.info("Get a Lİst of all Borrowings");
        return borrowingRepository.findAll().stream()
                .map(borrowingMapper::borrowingToBorrowingDTO)
                .collect(Collectors.toList());
    }

public BorrowingDto borrowBook(BorrowingDto borrowingDTO) {

    Book book = bookRepository.findById(borrowingDTO.getBook().getId()).orElse(null);
    Member member = memberRepository.findById(borrowingDTO.getMember().getId()).orElse(null);

    if (book == null && member == null) {
        throw new IllegalArgumentException("Both book and member not found");
    } else if (book == null) {
        throw new IllegalArgumentException("Book not found");
    } else if (member == null) {
        throw new IllegalArgumentException("Member not found");
    }

    if (!book.getIsAvailable()) {
        throw new IllegalStateException("The book is not available for borrowing");
    }

    book.setIsAvailable(false);
    bookRepository.save(book);

    Borrowing borrowing = borrowingMapper.borrowingDTOToBorrowing(borrowingDTO);
    borrowing.setMember(member);
    borrowing.setBook(book);

    borrowing = borrowingRepository.save(borrowing);
    return borrowingMapper.borrowingToBorrowingDTO(borrowing);
}

    public BorrowingDto getBorrowingById(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrowing not found with id: " + id));
        return borrowingMapper.borrowingToBorrowingDTO(borrowing);
    }

    public void deleteBorrowing(Long id) {
        log.warn("Deleting Borrowing with ID: {}", id);
        Borrowing existingBorrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrowing not found"));
        borrowingRepository.deleteById(id);
    }

    public BorrowingDto updateBorrowing(Long id, BorrowingDto updateBorrowingDTO) {
        log.info("Updating Borrowing with ID: {}", id);
        Borrowing existingBorrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrowing not found"));


        Borrowing updatedBorrowing = borrowingMapper.borrowingDTOToBorrowing(updateBorrowingDTO);
        updatedBorrowing.setId(id);

        updatedBorrowing = borrowingRepository.save(updatedBorrowing);
        return borrowingMapper.borrowingToBorrowingDTO(updatedBorrowing);
    }
}