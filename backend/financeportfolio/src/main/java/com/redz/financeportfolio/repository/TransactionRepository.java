package com.redz.financeportfolio.repository;

import com.redz.financeportfolio.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
