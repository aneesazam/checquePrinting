package com.checquePrinting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T> {
    Page<T> findByNameContaining(String T, Pageable pageable);

    Page<T> findAll(Pageable pageable);

    void deleteById(int id);

    T save(T bank);

    T findById(int id);
}
