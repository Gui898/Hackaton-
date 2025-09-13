package com.testSpring.Tests.service;

import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.persistence.DAO.ElderlyDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlyService {

    ElderlyDAO elderlyDAO;

    public ElderlyService(ElderlyDAO elderlyDAO){
        this.elderlyDAO = elderlyDAO;
    }

    public Elderly addElderly(Elderly elderly){
        elderlyDAO.add(elderly);
        return elderly;
    }

    public long deleteElderly(long id){
        elderlyDAO.delete(id);
        return id;
    }

    public Elderly updateElderly(Elderly elderly){
        elderlyDAO.update(elderly);
        return elderly;
    }

    public Elderly getElderlyById(long id){
        return elderlyDAO.selectById(id);
    }

    public List<Elderly> getAllElderlies(){
        return elderlyDAO.selectAll();
    }
}
