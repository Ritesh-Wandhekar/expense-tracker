package com.ritesh.expensetracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {
        return  repository.findAll();
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpensesById(@PathVariable Long id) {
        return repository.findById(id)
                .map(expense -> ResponseEntity.ok(expense))
                .orElse(ResponseEntity.notFound().build());
    }
}
