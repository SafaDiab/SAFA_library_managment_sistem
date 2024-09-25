package com.library.managment.library_managment_sistem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private Boolean isAvailable;
}