package com.example.accessingdatamongodb.controller;

import com.example.accessingdatamongodb.model.Customer;
import com.example.accessingdatamongodb.repository.CustomerRepository;
import com.example.accessingdatamongodb.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApplicationController implements CommandLineRunner {


	//Testing PR and Linter
	private final CustomerRepository repository;

	private static final String ALL_VALUES = "ALL";

	private final CustomerService customerService;

	public ApplicationController(CustomerRepository repository, CustomerService customerService) {
		this.repository = repository;
		this.customerService = customerService;
	}

	//This method has been done to test different options in requestMapping annotation
	//examples can be as follows:
	//http://localhost:8080/customerName/msisdn:35679632198?fields=DOB
	//http://localhost:8080/customerName/msisdn:35679632198
	@RequestMapping(value = "/customerName/{identifierType}:{serviceId}")
	@ResponseBody
	public String getCustomer(
			@PathVariable String identifierType,
			@PathVariable String serviceId,
			@RequestParam(value = "fields", required = false) String params)
	{
		return "The selected parameters are identifierType / serviceId / params : >>"
				+ identifierType + "<< >>" + serviceId + "<< >>>" + params ;
	}

	//@GetMapping("/customer")
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public List<String> getDetailsByName(
			@RequestParam(value = "name", defaultValue = ALL_VALUES)
			String name)
	{

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
