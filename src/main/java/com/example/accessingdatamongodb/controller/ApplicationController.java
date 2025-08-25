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

    //Testing PR removed new setup
    private final CustomerRepository repository;

    private static final String ALL_VALUES = "ALL";

    private final CustomerService customerService;

    /**
     * This is the constructor of the Application. 
     * @param repository - customer reporitory object
     * @param customerService - customer service object
     */
    public ApplicationController(final CustomerRepository customerRepository, 
                                  final CustomerService customerService) {
        this.repository = customerRepository;
        this.customerService = customerService;
    }

    /**
     * This method has been done to test different options in requestMapping annotation.
     * Examples can be as follows:
     * http://localhost:8080/customerName/msisdn:35679632198?fields=DOB
     * http://localhost:8080/customerName/msisdn:35679632198
     * @param identifierType Identifier Type
     * @param serviceId Service Id
     * @param params Parameters
     * @return String
     */
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

    /**
     * This method has been done to get Customer details by name.
     * @param name Customer Name
     * @return List
     */
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

    /**
     * Method to run the application.
     * @param args Arguments
     */
    @Override
    public void run(String... args) {
        customerService.setRepository(repository);
        customerService.initDB();
    }

}

