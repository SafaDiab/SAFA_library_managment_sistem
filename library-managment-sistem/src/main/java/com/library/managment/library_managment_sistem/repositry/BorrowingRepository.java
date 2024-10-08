package com.library.managment.library_managment_sistem.repositry;

import com.library.managment.library_managment_sistem.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findAllByBorrowDateBetween(LocalDate startDate, LocalDate endDate);

}


