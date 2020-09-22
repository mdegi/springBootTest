package com.example.accessingdatamongodb.controller;

import com.example.accessingdatamongodb.model.Customer;
import com.example.accessingdatamongodb.repository.CustomerRepository;
import com.example.accessingdatamongodb.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApplicationController implements CommandLineRunner {

	private final CustomerRepository repository;

	private static final String ALL_CUSTOMERS = "ALL";

	private CustomerService dbCustomer;

	public ApplicationController(CustomerRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/customer")
	public List<String> hello(@RequestParam(value = "name", defaultValue = ALL_CUSTOMERS) String name) {
		List<String> customers = new ArrayList<>();
		if (name.equals(ALL_CUSTOMERS) || name.equals(ALL_CUSTOMERS.toLowerCase())) {
			 return dbCustomer.findAll().stream().map(Customer::toString).collect(Collectors.toList());
		} else {
			Customer customerRec = dbCustomer.findByFirstName(name);
			if (customerRec != null) {
				customers.add(dbCustomer.findByFirstName(name).toString());
			}
			return customers;
		}
	}

	@Override
	public void run(String... args) throws Exception {
		dbCustomer = new CustomerService(repository);
		dbCustomer.initDB();
	}

}