package com.testSpring.Tests.service;

import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.persistence.DAO.IncomesDAO;
import com.testSpring.Tests.persistence.DAO.ExpensesDAO;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final ExpensesDAO expensesDAO;
    private final IncomesDAO incomesDAO;

    public BalanceService(ExpensesDAO expensesDAO, IncomesDAO incomesDAO){
        this.expensesDAO = expensesDAO;
        this.incomesDAO = incomesDAO;
    }

    public double calculateBalance(Elderly elderly){
        double totalIncomes = incomesDAO.sumIncomesById(elderly);
        double totalExpenses = expensesDAO.sumExpensesById(elderly);
        return totalIncomes - totalExpenses;
    }
}