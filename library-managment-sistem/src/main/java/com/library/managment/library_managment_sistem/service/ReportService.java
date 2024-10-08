package com.library.managment.library_managment_sistem.service;

import com.library.managment.library_managment_sistem.entity.Borrowing;
import com.library.managment.library_managment_sistem.repositry.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private BorrowingRepository borrowingRepository;


    public List<Borrowing> generateMonthlyReport(LocalDate startDate, LocalDate endDate) {
        return borrowingRepository.findAllByBorrowDateBetween(startDate, endDate);
    }


}
