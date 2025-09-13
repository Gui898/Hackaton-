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

	}

}
