package com.kirana.register.controller;

import com.kirana.register.TransactionDTO;
import com.kirana.register.entity.Transaction;
import com.kirana.register.request.TransactionInput;
import com.kirana.register.sevice.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionInput transaction) {
        transactionService.addTransaction(transaction);
        return ResponseEntity.ok("Transaction added successfully");
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewTransactions() {
        List<Transaction> response = transactionService.getTransactions();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byTimestamp")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByTimestamp(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate timestamp) {
        try {
            List<TransactionDTO> transactions = transactionService.getTransactionsByTimestamp(timestamp);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            // Handle the exception and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/by-date-range")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByDateRange(startDate, endDate);
        return ResponseEntity.ok(transactions);
    }
}
