package com.library.managment.library_managment_sistem.mapper;

import com.library.managment.library_managment_sistem.dto.BookDto;
import com.library.managment.library_managment_sistem.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto bookToBookDto(Book book);
    Book bookDtoToBook(BookDto bookDTO);
}
