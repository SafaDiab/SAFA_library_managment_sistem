package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.dto.MemberDto;
import com.library.managment.library_managment_sistem.entity.Book;
import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.mapper.MemberMapper;
import com.library.managment.library_managment_sistem.repositry.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;

    public List<Member> searchMembers(String name , Integer age) {
        return memberRepository.searchMembers(name,age);
    }
    public Member addMember(Member member) {
        log.info("Adding a new member: {}", member.getName());
        return memberRepository.save(member);
    }

    public List<MemberDto> listMembers() {
        log.info("Get A List of all Members");
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }
    public MemberDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
        return memberMapper.toDto(member);
    }

    public void deleteMember(Long id) {
        log.warn("Deleting Member with ID: {}", id);
        memberRepository.deleteById(id);
    }

    public Member updateMember(Long id, Member updatedMember) {
        log.info("Updating Member with ID: {}", id);
        Member existingMember =memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Member Not Found"));
existingMember.setName(updatedMember.getName());
existingMember.setEmail(updatedMember.getEmail());
existingMember.setAge(updatedMember.getAge());
return memberRepository.save(existingMember);
    }
}
