package com.redz.financeportfolio.repository;

import com.redz.financeportfolio.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
