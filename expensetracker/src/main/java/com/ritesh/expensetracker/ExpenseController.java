package com.ritesh.expensetracker;

import jakarta.validation.Valid;
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
    public Expense addExpense(@Valid @RequestBody Expense expense) {
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

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpensesById(@PathVariable Long id){
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setCategory(updated.getCategory());
                    existing.setName(updated.getName());
                    existing.setAmount(updated.getAmount());
                    existing.setDate(updated.getDate());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
