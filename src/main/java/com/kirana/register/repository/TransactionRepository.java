package com.kirana.register.repository;

import com.kirana.register.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTimestampBetween(LocalDate start, LocalDate end);

    List<Transaction> findByTimestamp(LocalDate timestamp);
}
