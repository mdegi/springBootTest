package com.example.accessingdatamongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class AccessingDataMongodbRestApplication implements CommandLineRunner {

	private final CustomerRepository repository;

	private static final String ALL_CUSTOMERS = "ALL";

	private MongoDBCustomer dbCustomer;

	public AccessingDataMongodbRestApplication(CustomerRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMongodbRestApplication.class, args);
	}

	@GetMapping("/customer")
	public List<String> hello(@RequestParam(value = "name", defaultValue = ALL_CUSTOMERS) String name) {
		List<String> customers = new ArrayList<>();
		if (name.equals(ALL_CUSTOMERS) || name.equals(ALL_CUSTOMERS.toLowerCase())) {
			 return dbCustomer.findAll().stream().map(Customer::toString).collect(Collectors.toList());
		} else {
			customers.add(dbCustomer.findByFirstName(name).toString());
			return customers;
		}
	}

	@Override
	public void run(String... args) throws Exception {
		dbCustomer = new MongoDBCustomer(repository);
		dbCustomer.initDB();
	}

}