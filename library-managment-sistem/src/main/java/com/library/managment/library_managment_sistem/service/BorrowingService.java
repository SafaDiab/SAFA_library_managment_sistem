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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return borrowingRepository.findAll().stream()
                .map(borrowingMapper::borrowingToBorrowingDTO)
                .collect(Collectors.toList());
    }

public BorrowingDto borrowBook(BorrowingDto borrowingDTO) {

    Book book = bookRepository.findById(borrowingDTO.getBook().getId())
            .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    Member member = memberRepository.findById(borrowingDTO.getMember().getId())
            .orElseThrow(() -> new IllegalArgumentException("Member not found"));

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

    public void deleteBorrowing(Long id) {
        Borrowing existingBorrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrowing not found"));
        borrowingRepository.deleteById(id);
    }

    public BorrowingDto updateBorrowing(Long id, BorrowingDto updateBorrowingDTO) {
        Borrowing existingBorrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrowing not found"));


        Borrowing updatedBorrowing = borrowingMapper.borrowingDTOToBorrowing(updateBorrowingDTO);
        updatedBorrowing.setId(id);

        updatedBorrowing = borrowingRepository.save(updatedBorrowing);
        return borrowingMapper.borrowingToBorrowingDTO(updatedBorrowing);
    }
}