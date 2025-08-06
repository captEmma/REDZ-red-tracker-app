package com.redz.financeportfolio.repository;

import com.redz.financeportfolio.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

//This gives us save(), remove(), etc. methods that have to do with persistency.
public interface PortfolioRepository extends JpaRepository<PortfolioItem, Integer> {
}
