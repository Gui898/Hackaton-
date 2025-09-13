package com.testSpring.Tests.service;

import org.springframework.stereotype.Service;

import com.testSpring.Tests.model.Expenses;
import com.testSpring.Tests.persistence.DAO.ExpensesDAO;

@Service
public class ExpensesService {

    ExpensesDAO expensesDAO;

    public ExpensesService(ExpensesDAO expensesDAO){
        this.expensesDAO = expensesDAO;
    }

    public Expenses addExpenses(Expenses expenses){
        expensesDAO.add(expenses);
        return expenses;
    }

    public long deleteExpenses(long id){
        expensesDAO.delete(id);
        return id;
    }

    public Expenses updateExpenses(Expenses expenses){
        expensesDAO.update(expenses);
        return expenses;
    }

    public Expenses getExpensesById(long id){
        return expensesDAO.selectById(id);
    }

    public java.util.List<Expenses> getAllExpenses(){
        return expensesDAO.selectAll();
    }
}
