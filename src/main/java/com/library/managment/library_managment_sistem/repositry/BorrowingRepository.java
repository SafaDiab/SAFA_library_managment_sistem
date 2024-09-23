package com.library.managment.library_managment_sistem.repositry;

import com.library.managment.library_managment_sistem.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
