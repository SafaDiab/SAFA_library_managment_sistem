package com.library.managment.library_managment_sistem.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    @NotNull(message = "Title Can't be null")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;

    @NotNull(message = "Author Can't be null")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    private String author;
    private Boolean isAvailable;
}