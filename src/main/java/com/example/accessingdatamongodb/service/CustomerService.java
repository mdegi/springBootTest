package com.example.accessingdatamongodb.service;

import com.example.accessingdatamongodb.repository.CustomerRepository;
import com.example.accessingdatamongodb.model.Customer;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CustomerService {

    private final int INIT_ACCESS_NUMBER = 79632190;
    private final double INIT_CREDIT_LIMIT = 1000;

    private final int MAX_RECORDS = 1000;

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    private String getAccessNUmber(int number) {
        return String.valueOf(INIT_ACCESS_NUMBER + number);
    }

    private double getCreditLimit(int number) {

        if ((number % (INIT_CREDIT_LIMIT / 10)) == 0) {
            return INIT_CREDIT_LIMIT;
        } else {
            return (INIT_CREDIT_LIMIT + number);
        }
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findByFirstName(String name) {
        return repository.findByFirstName(name);
    }

    public void initDB() {
        repository.deleteAll();

        // save a couple of customers
        System.out.println();
        long startCurrentMillis = System.currentTimeMillis();
        System.out.println("Start Populating Customers: [" + startCurrentMillis + "] .....");
        IntStream.range(0, MAX_RECORDS)
                .parallel()
                .forEach(
                        loopNumber -> {
                            Customer customer = new Customer("Name - " + loopNumber, "Surname - " + loopNumber,
                                    getAccessNUmber(loopNumber), getCreditLimit(loopNumber));
                            repository.save(customer);
                        }
                );

        long endCurrentMillis = System.currentTimeMillis();
        System.out.format("End Populating Customers Duration in minutes, seconds, millliseconds: [%d] - [%s]" + " - [" + (endCurrentMillis - startCurrentMillis + "]"),
                TimeUnit.MILLISECONDS.toMinutes(endCurrentMillis - startCurrentMillis),
                TimeUnit.MILLISECONDS.toSeconds(endCurrentMillis - startCurrentMillis)
        );
        System.out.println();

        System.out.println();
        System.out.println("Customers found with creditLimit: " + INIT_CREDIT_LIMIT);
        System.out.println("--------------------------------");
        repository.findAllByCreditLimit(INIT_CREDIT_LIMIT).forEach(System.out::println);

        System.out.println();
        double creditLinitToCheck = ((MAX_RECORDS * 2) - 10);
        System.out.println("Customers having CreditLimit >= " + creditLinitToCheck);
        System.out.println("--------------------------------");
        repository.findCustomerByCreditLimitGreaterThanEqual(creditLinitToCheck).forEach(System.out::println);
        System.out.println();

    }

}
