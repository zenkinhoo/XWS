package com.example.ConnectionService;

import com.example.ConnectionService.Model.Message;
import com.example.ConnectionService.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableMongoRepositories
public class ConnectionServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(ConnectionServiceApplication.class, args);
	}

}