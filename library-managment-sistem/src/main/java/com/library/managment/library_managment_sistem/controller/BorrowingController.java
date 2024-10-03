package com.library.managment.library_managment_sistem.controller;


import com.library.managment.library_managment_sistem.dto.BorrowingDto;
import com.library.managment.library_managment_sistem.service.BorrowingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<BorrowingDto> borrowBook(@RequestBody BorrowingDto borrowingDTO) {
        BorrowingDto newBorrowing = borrowingService.borrowBook(borrowingDTO);
        return new ResponseEntity<>(newBorrowing, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BorrowingDto>> getAllBorrowings() {
        List<BorrowingDto> borrowings = borrowingService.listBorrowings();
        return new ResponseEntity<>(borrowings, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingDto> getBorrowingById(@PathVariable Long id) {
        BorrowingDto borrowingDto = borrowingService.getBorrowingById(id);
        return new ResponseEntity<>(borrowingDto, HttpStatus.OK);
    }

@PutMapping("/{id}")
public ResponseEntity<BorrowingDto> updateBorrowing(@PathVariable Long id, @RequestBody BorrowingDto borrowingDTO) {
    BorrowingDto updatedBorrowing = borrowingService.updateBorrowing(id, borrowingDTO);
    return new ResponseEntity<>(updatedBorrowing, HttpStatus.OK);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        borrowingService.deleteBorrowing(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}