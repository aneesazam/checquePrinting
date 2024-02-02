package com.checquePrinting.service;

import com.checquePrinting.dao.BankRepository;
import com.checquePrinting.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService implements CrudService<Bank> {
    private BankRepository bankRepository;

    public BankService(BankRepository theBankRepository) {
        bankRepository = theBankRepository;
    }

    @Override
    public void deleteById(int id) {
        bankRepository.deleteById(id);
    }

    @Override
    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Bank findById(int id) {
        Optional<Bank> result = bankRepository.findById(id);
        Bank theBank = null;
        if (result.isPresent()) {
            theBank = result.get();
        } else {
            throw new RuntimeException("Did not fine bank id - " + id);
        }

        return theBank;
    }

    @Override
    public Page<Bank> findByNameContaining(String name, Pageable pageable) {
        Page<Bank> banks = bankRepository.findByNameContaining(name, pageable);
        return banks;
    }

    @Override
    public Page<Bank> findAll(Pageable pageable) {
        Page<Bank> banks = bankRepository.findAll(pageable);
        return banks;
    }
}
