package com.ritesh.expensetracker;

import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenseController {

    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/expenses")
    public Expense addExpense(@RequestBody Expense expense) {
        return repository.save(expense);
    }
}
