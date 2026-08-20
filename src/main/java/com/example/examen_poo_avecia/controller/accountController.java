package com.example.examen_poo_avecia.controller;

import com.example.examen_poo_avecia.model.transaction;
import com.example.examen_poo_avecia.service.transactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

public class accountController {
    private final accountController accountService;
    private final transactionService transactionService;

    public accountController(accountController accountService, transactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}/transactions")
    public List<transaction> getTransactions(
            @PathVariable String id
    ) {

        return accountService.getTransactions(id);
    }

    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(
            @PathVariable String id
    ) {

        return accountService.getBalance(id);
    }

    @PostMapping("/{id}/transactions")
    public transaction createTransaction(
            @PathVariable String id,
            @RequestBody transaction transaction
    ) {

        return transactionService.createtransaction(
                id,
                transaction
        );
    }
}
