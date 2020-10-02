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

	private static final String ALL_VALUES = "ALL";

	private final CustomerService customerService;

	public ApplicationController(CustomerRepository repository, CustomerService customerService) {
		this.repository = repository;
		this.customerService = customerService;
	}

	@GetMapping("/customer")
	public List<String> getDetailsByName(@RequestParam(value = "name", defaultValue = ALL_VALUES) String name) {
		List<String> customers = new ArrayList<>();
		if (name.equals(ALL_VALUES) || name.equals(ALL_VALUES.toLowerCase())) {
			 return customerService.findAll().stream().map(Customer::toString).collect(Collectors.toList());
		} else {
			Customer customerRec = customerService.findByFirstName(name);
			if (customerRec != null) {
				customers.add(customerService.findByFirstName(name).toString());
			}
			return customers;
		}
	}

	@Override
	public void run(String... args) {
		customerService.setRepository(repository);
		customerService.initDB();
	}

}