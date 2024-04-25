package com.programming.techie.expensetracker.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.programming.techie.expensetracker.model.Expense;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {

}
