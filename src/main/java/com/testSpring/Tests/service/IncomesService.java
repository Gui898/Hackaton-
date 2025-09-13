package com.testSpring.Tests.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testSpring.Tests.model.Incomes;
import com.testSpring.Tests.persistence.DAO.IncomesDAO;

@Service
public class IncomesService {

    IncomesDAO incomesDAO;

    public IncomesService(IncomesDAO incomesDAO){
        this.incomesDAO = incomesDAO;
    }

    public Incomes addIncomes(Incomes incomes){
        incomesDAO.add(incomes);
        return incomes;
    }

    public long deleteIncomes(long id){
        incomesDAO.delete(id);
        return id;
    }

    public Incomes updateIncomes(Incomes incomes){
        incomesDAO.update(incomes);
        return incomes;
    }

    public Incomes getIncomesById(long id){
        return incomesDAO.selectById(id);
    }

    public List<Incomes> getAllIncomes(){
        return incomesDAO.selectAll();
    }

}
