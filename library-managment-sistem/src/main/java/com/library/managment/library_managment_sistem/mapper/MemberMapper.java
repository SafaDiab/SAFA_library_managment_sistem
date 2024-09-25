package com.library.managment.library_managment_sistem.mapper;

import com.library.managment.library_managment_sistem.dto.MemberDto;
import com.library.managment.library_managment_sistem.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toDto(Member member);

    Member toEntity(MemberDto memberDTO);
}
