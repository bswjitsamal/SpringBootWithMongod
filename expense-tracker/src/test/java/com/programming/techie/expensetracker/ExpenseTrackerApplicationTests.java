package com.programming.techie.expensetracker;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.techie.expensetracker.dto.ExpenseDto;
import com.programming.techie.expensetracker.model.ExpenseCategory;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ExpenseTrackerApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Should Create Expense, and verify the expense by GET")
	void shouldCreateExpenseAndGetTheExpense() throws Exception {
		ExpenseDto expenseDto = ExpenseDto.builder().expenseCategory(ExpenseCategory.ENTERTAINMENT)
				.expenseName("Movies").expenseAmount(BigDecimal.TEN).build();
		MvcResult mvcResult = mockMvc
				.perform(post("/api/expense").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(expenseDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION)).andReturn();

		String expenseUrl = mvcResult.getResponse().getHeaderValue(HttpHeaders.LOCATION).toString();

		mockMvc.perform(get(new URI(expenseUrl))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenseAmount").value(BigDecimal.TEN))
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenseCategory")
						.value(ExpenseCategory.ENTERTAINMENT.toString()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenseName").value("Movies"));
	}

}
