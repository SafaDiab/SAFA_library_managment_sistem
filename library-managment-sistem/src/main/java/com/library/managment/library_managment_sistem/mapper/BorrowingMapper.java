package com.library.managment.library_managment_sistem.mapper;
import com.library.managment.library_managment_sistem.dto.BorrowingDto;
import com.library.managment.library_managment_sistem.entity.Borrowing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { MemberMapper.class, BookMapper.class })
public interface BorrowingMapper {


    BorrowingDto borrowingToBorrowingDTO(Borrowing borrowing);

    Borrowing borrowingDTOToBorrowing(BorrowingDto borrowingDTO);
}
