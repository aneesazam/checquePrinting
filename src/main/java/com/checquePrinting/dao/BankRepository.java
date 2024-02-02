package com.checquePrinting.dao;

import com.checquePrinting.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface BankRepository extends JpaRepository<Bank, Integer> {
  Page<Bank> findByNameContaining(String name, Pageable pageable);
}

