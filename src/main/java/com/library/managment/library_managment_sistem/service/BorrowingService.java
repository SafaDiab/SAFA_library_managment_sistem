package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Borrowing;
import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.repositry.BookRepository;
import com.library.managment.library_managment_sistem.repositry.BorrowingRepository;
import com.library.managment.library_managment_sistem.repositry.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private MemberRepository memberRepository;

@Autowired
    private BookRepository bookRepository;


    public List<Borrowing> listBorrowings() {
        return borrowingRepository.findAll();

    }
//    public Borrowing borrowBook(Borrowing borrowing) {
//        if (!borrowing.getBook().getIsAvailable()) {
//            throw new IllegalStateException("Book is not available for borrowing");
//        }
//        borrowing.getBook().setIsAvailable(false);
//        return borrowingRepository.save(borrowing);
//    }
    public Borrowing borrowBook(Borrowing borrowing) {

        Book book = bookRepository.findById(borrowing.getBook().getId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Member member = memberRepository.findById(borrowing.getMember().getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        if (!book.getIsAvailable()) {
            throw new IllegalStateException("The book is not available for borrowing");
        }


        book.setIsAvailable(false);
        bookRepository.save(book);

        borrowing.setMember(member);
        borrowing.setBook(book);
        // حفظ عملية الاستعارة
        return borrowingRepository.save(borrowing);
    }
    ///////////////////
    public void deleteBorrowing(Long id) {
        Borrowing existingBorrowing =borrowingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found"));
        borrowingRepository.deleteById(id);

    }

    public Borrowing updateBorrowing(Long id, Borrowing updateBorrowing) {
        Borrowing existingBorrowing =borrowingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boooooo*oolpl Not Found"));
        existingBorrowing.setBook(updateBorrowing.getBook());
        existingBorrowing.setBorrowDate(updateBorrowing.getBorrowDate());
        existingBorrowing.setMember(updateBorrowing.getMember());
        existingBorrowing.setReturnDate(updateBorrowing.getReturnDate());

        return borrowingRepository.save(existingBorrowing);
    }

}