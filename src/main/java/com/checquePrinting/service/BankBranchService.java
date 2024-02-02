package com.checquePrinting.service;

import com.checquePrinting.dao.BankBranchRepository;
import com.checquePrinting.entity.BankBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankBranchService implements CrudService<BankBranch> {
    private BankBranchRepository bankRepository;
    public BankBranchService(BankBranchRepository theBankRepository){
        bankRepository = theBankRepository;
    }

    @Override
    public Page<BankBranch> findByNameContaining(String T, Pageable pageable) {
        return null;
    }

    @Override
    public Page<BankBranch> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        bankRepository.deleteById(id);
    }

    @Override
    public BankBranch save(BankBranch bank) {
        return bankRepository.save(bank);
    }

    @Override
    public BankBranch findById(int id) {
        Optional<BankBranch> result = bankRepository.findById(id);
        BankBranch theBank = null;
        if(result.isPresent()){
            theBank = result.get();
        }
        else {
            throw new RuntimeException("Did not fine bank id - " + id);
        }

        return theBank;
    }
}
