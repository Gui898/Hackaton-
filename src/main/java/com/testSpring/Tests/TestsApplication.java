package com.testSpring.Tests;

import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.persistence.DAO.ElderlyDAO;
import com.testSpring.Tests.persistence.MySqlConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestsApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestsApplication.class, args);

        Elderly eld = new Elderly("Felipe", "felipe@gmail.com", "1277", "00987654321", "2009", "M", "99111111111");
        ElderlyDAO eDAO = new ElderlyDAO(new MySqlConnection());
        eld.setIdElderly(3);
        eDAO.update(eld);
	}

}
