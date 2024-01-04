package com.kirana.register.repository;

import com.kirana.register.entity.DebitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitTransactionRepository extends JpaRepository<DebitTransaction, Long> {
}
