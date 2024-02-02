package com.checquePrinting.rest;

import com.checquePrinting.entity.Bank;
import com.checquePrinting.model.Pagination;
import com.checquePrinting.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BankRestController {

    private CrudService<Bank> bankService;
    @Autowired
    public BankRestController(CrudService<Bank> theBankService) {
        bankService = theBankService;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Map<String, Object>> getTestData(@RequestBody Pagination pagination) {
        //sorted fields and order
        List<Order> orders = new ArrayList<Order>();
        if (pagination.getSort().size() > 0) {
            for (String sortOrder : pagination.getSort().keySet()) {
                orders.add(new Order(getSortDirection(pagination.getSort().get(sortOrder)), sortOrder));
            }
        } else {
            orders.add(new Order(getSortDirection("desc"), "id"));
        }

        List<Bank> banks = new ArrayList<Bank>();
        Pageable pagingSort = PageRequest.of(pagination.getPageNo(), pagination.getPageSize(), Sort.by(orders));

        Page<Bank> pageBank;
        if (pagination.getSearchText() == null || pagination.getSearchText() == ""){
            pageBank = bankService.findAll(pagingSort);
        }
        else {
            pageBank = bankService.findByNameContaining(pagination.getSearchText(), pagingSort);
        }

        banks = pageBank.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("banks", banks);
        response.put("currentPage", pageBank.getNumber());
        response.put("totalItems", pageBank.getTotalElements());
        response.put("totalPages", pageBank.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bank/{bankId}")
    public Bank getBankById(@PathVariable int bankId) {
        Bank bank = (Bank) bankService.findById(bankId);
        return bank;
    }

    @PostMapping("/bank")
    public Bank addBank(@RequestBody Bank theBank) {
        theBank.setId(0);
        Bank dbBank = (Bank) bankService.save(theBank);
        return dbBank;
    }

    @PutMapping("/bank")
    public Bank updateBank(@RequestBody Bank theBank) {
        Bank dbBank = (Bank) bankService.save(theBank);
        return dbBank;
    }

    @DeleteMapping("/bank/{bankId}")
    public String deleteBank(@PathVariable int bankId) {
        Bank tempBank = (Bank) bankService.findById(bankId);

        if (tempBank == null) {
            throw new RuntimeException("Bank id not found - " + bankId);
        }
        bankService.deleteById(bankId);
        return "Deleted Bank id - " + bankId;
    }

}
