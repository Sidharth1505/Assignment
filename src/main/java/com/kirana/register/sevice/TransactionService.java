package com.kirana.register.sevice;

import com.kirana.register.TransactionDTO;
import com.kirana.register.entity.CreditTransaction;
import com.kirana.register.entity.DebitTransaction;
import com.kirana.register.entity.Transaction;
import com.kirana.register.repository.CreditTransactionRepository;
import com.kirana.register.repository.DebitTransactionRepository;
import com.kirana.register.repository.TransactionRepository;
import com.kirana.register.request.TransactionInput;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private DebitTransactionRepository debitTransactionRepository;

    @Autowired
    private CreditTransactionRepository creditTransactionRepository;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Transactional
    public void addTransaction(TransactionInput transactionInput) {
        Transaction transaction = new Transaction();
        double amount = transactionInput.getAmount();
        transaction.setCurrency("INR");
        transaction.setTimestamp(transactionInput.getTimestamp());
        transaction.setType(transactionInput.getType());

        // Convert amount to a common currency (e.g., INR)
        if("USD".equalsIgnoreCase(transactionInput.getCurrency())) {
            amount = currencyConversionService.convertToINR(amount);
        }

        transaction.setAmount(amount);

        if ("DEBIT".equalsIgnoreCase(transaction.getType())) {
            // Debit Transaction
            DebitTransaction debitTransaction = new DebitTransaction();
            debitTransaction.setAmount(amount);
            debitTransaction.setDescription(transactionInput.getDescription());
            debitTransaction.setTransaction(transaction);
            debitTransactionRepository.save(debitTransaction);
        } else if ("CREDIT".equalsIgnoreCase(transaction.getType())) {
            // Credit Transaction
            CreditTransaction creditTransaction = new CreditTransaction();
            creditTransaction.setAmount(amount);
            creditTransaction.setDescription(transactionInput.getDescription());
            creditTransaction.setTransaction(transaction);
            creditTransactionRepository.save(creditTransaction);
        }

        transactionRepository.save(transaction);
    }

    public List<TransactionDTO> getTransactionsByTimestamp(LocalDate timestamp) {
        List<Transaction> transactions =  transactionRepository.findByTimestamp(timestamp);
        return addDescriptionsToOutput(transactions);
    }

    public List<TransactionDTO> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByTimestampBetween(startDate, endDate);
        return addDescriptionsToOutput(transactions);
    }


    private List<TransactionDTO> addDescriptionsToOutput(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> {
                    String creditDescription = transaction.getCreditTransaction() != null ?
                            transaction.getCreditTransaction().getDescription() : null;
                    String debitDescription = transaction.getDebitTransaction() != null ?
                            transaction.getDebitTransaction().getDescription() : null;

                    return new TransactionDTO(
                            transaction.getAmount(),
                            transaction.getCurrency(),
                            transaction.getTimestamp(),
                            transaction.getType(),
                            creditDescription,
                            debitDescription
                    );
                })
                .collect(Collectors.toList());
    }
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}
