package com.library.managment.library_managment_sistem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingDto {
    private Long id;

    private MemberDto member;

    private BookDto book;

    private LocalDate borrowDate;
    private LocalDate returnDate;
}
