package com.bank.bankmod.models;

import com.bank.bankmod.models.data.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findAllByAccountId(String accountId);
}
