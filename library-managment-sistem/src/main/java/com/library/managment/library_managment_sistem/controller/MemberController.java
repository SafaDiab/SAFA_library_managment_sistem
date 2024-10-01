package com.library.managment.library_managment_sistem.controller;


import com.library.managment.library_managment_sistem.dto.MemberDto;
import com.library.managment.library_managment_sistem.entity.Book;
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
    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(    @RequestParam(required = false) String name,
                                                          @RequestParam(required = false) Integer age)
    {
        List<Member> members = memberService.searchMembers(name,age);
        return ResponseEntity.ok(members);
    }
@GetMapping
public ResponseEntity<List<MemberDto>> getAllMembers() {
    List<MemberDto> members = memberService.listMembers();
    return new ResponseEntity<>(members, HttpStatus.OK);
}
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
        MemberDto member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
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
