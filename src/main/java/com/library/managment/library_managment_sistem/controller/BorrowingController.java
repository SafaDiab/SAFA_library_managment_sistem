package com.library.managment.library_managment_sistem.controller;

import com.library.managment.library_managment_sistem.entity.Borrowing;
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
    public ResponseEntity<Borrowing> borrowBorrowing(@RequestBody Borrowing borrowing) { Borrowing savedBorrowing = borrowingService.borrowBook(borrowing);
        return new ResponseEntity<>(savedBorrowing, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Borrowing> listBorrowings() {
        return borrowingService.listBorrowings();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        borrowingService.deleteBorrowing(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public Borrowing updateBorrowing(@PathVariable Long id, @RequestBody Borrowing updatedBorrowing) {
        return borrowingService.updateBorrowing(id, updatedBorrowing);
    }

}
