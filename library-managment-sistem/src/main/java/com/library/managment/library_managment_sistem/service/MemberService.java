package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.repositry.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
    public Member addMember(Member member) {
        logger.info("Adding member: {}", member.getName());
        return memberRepository.save(member);
    }

    public List<Member> listMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Member updateMember(Long id, Member updatedMember) {
Member existingMember =memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Member Not Found"));
existingMember.setName(updatedMember.getName());
existingMember.setEmail(updatedMember.getEmail());
return memberRepository.save(existingMember);
    }

}
