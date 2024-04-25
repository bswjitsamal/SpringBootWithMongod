package com.programming.techie.expensetracker.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("expense")
public class Expense {
	@Id
	private String id;
	@Field("name")
	@Indexed(unique = true)
	private String expenseName;
	@Field("category")
	private ExpenseCategory expenseCategory;
	@Field("amount")
	private BigDecimal expenseAmount;
}
