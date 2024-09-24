package com.library.managment.library_managment_sistem.mapper;

import com.library.managment.library_managment_sistem.Dto.MemberDto;
import com.library.managment.library_managment_sistem.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    // تحويل Entity إلى DTO
    MemberDto toDto(Member member);

    // تحويل DTO إلى Entity (عند الحاجة)
    Member toEntity(MemberDto memberDTO);
}
