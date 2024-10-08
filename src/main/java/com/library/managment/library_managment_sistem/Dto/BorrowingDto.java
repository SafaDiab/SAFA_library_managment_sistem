package com.library.managment.library_managment_sistem.Dto;

import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingDto {
    private Long id;

    private Member member;

    private Book book;

    private LocalDate borrowDate;
    private LocalDate returnDate;
}
