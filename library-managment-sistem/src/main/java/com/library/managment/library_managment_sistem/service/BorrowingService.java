package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.Dto.BorrowingDto;
import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Borrowing;
import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.mapper.BorrowingMapper;
import com.library.managment.library_managment_sistem.repositry.BookRepository;
import com.library.managment.library_managment_sistem.repositry.BorrowingRepository;
import com.library.managment.library_managment_sistem.repositry.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
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
        // استخدام المابر لتحويل قائمة الاستعارات إلى قائمة من DTOs
        return borrowingRepository.findAll().stream()
                .map(borrowingMapper::borrowingToBorrowingDTO)
                .collect(Collectors.toList());
    }
//    public Borrowing borrowBook(Borrowing borrowing) {
//        if (!borrowing.getBook().getIsAvailable()) {
//            throw new IllegalStateException("Book is not available for borrowing");
//        }
//        borrowing.getBook().setIsAvailable(false);
//        return borrowingRepository.save(borrowing);
//    }
//    public Borrowing borrowBook(Borrowing borrowing) {
//
//        Book book = bookRepository.findById(borrowing.getBook().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
//        Member member = memberRepository.findById(borrowing.getMember().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
//        if (!book.getIsAvailable()) {
//            throw new IllegalStateException("The book is not available for borrowing");
//        }
//
//
//        book.setIsAvailable(false);
//        bookRepository.save(book);
//
//        borrowing.setMember(member);
//        borrowing.setBook(book);
//        return borrowingRepository.save(borrowing);
//    }
//
//    public void deleteBorrowing(Long id) {
//        Borrowing existingBorrowing =borrowingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found"));
//        borrowingRepository.deleteById(id);
//
//    }
//
//    public Borrowing updateBorrowing(Long id, Borrowing updateBorrowing) {
//        Borrowing existingBorrowing =borrowingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boooooo*oolpl Not Found"));
//        existingBorrowing.setBook(updateBorrowing.getBook());
//        existingBorrowing.setBorrowDate(updateBorrowing.getBorrowDate());
//        existingBorrowing.setMember(updateBorrowing.getMember());
//        existingBorrowing.setReturnDate(updateBorrowing.getReturnDate());
//
//        return borrowingRepository.save(existingBorrowing);
//    }
//
//}
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

    Borrowing borrowing = borrowingMapper.borrowingDTOToBorrowing(borrowingDTO); // تحويل DTO إلى كيان
    borrowing.setMember(member);
    borrowing.setBook(book);

    borrowing = borrowingRepository.save(borrowing);
    return borrowingMapper.borrowingToBorrowingDTO(borrowing); // تحويل الكيان إلى DTO عند الإرجاع
}

    public void deleteBorrowing(Long id) {
        Borrowing existingBorrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrowing not found"));
        borrowingRepository.deleteById(id);
    }

    public BorrowingDto updateBorrowing(Long id, BorrowingDto updateBorrowingDTO) {
        Borrowing existingBorrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrowing not found"));

        // استخدام المابر لتحديث البيانات من DTO إلى الكيان
        Borrowing updatedBorrowing = borrowingMapper.borrowingDTOToBorrowing(updateBorrowingDTO);
        updatedBorrowing.setId(id);  // تأكد من تحديث المعرف

        updatedBorrowing = borrowingRepository.save(updatedBorrowing);
        return borrowingMapper.borrowingToBorrowingDTO(updatedBorrowing); // إرجاع الكيان المحدث كـ DTO
    }
}