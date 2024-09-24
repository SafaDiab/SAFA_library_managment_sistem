package com.library.managment.library_managment_sistem.controller;


import com.library.managment.library_managment_sistem.Dto.MemberDto;
import com.library.managment.library_managment_sistem.entity.Member;
import com.library.managment.library_managment_sistem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.addMember(member), HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<Member> listMembers() {
//        return memberService.listMembers();
//    }555555g
@GetMapping
public ResponseEntity<List<MemberDto>> getAllMembers() {
    List<MemberDto> members = memberService.listMembers();
    return new ResponseEntity<>(members, HttpStatus.OK);
}///kllllllll
//    @GetMapping("/{id}")
//    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
//        MemberDto member = memberService.getMemberById(id);
//        return new ResponseEntity<>(member, HttpStatus.OK);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        return memberService.updateMember(id, updatedMember);
    }
}
