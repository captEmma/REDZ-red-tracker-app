package com.redz.financeportfolio.repository;

import com.redz.financeportfolio.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<PortfolioItem, String> {
}
