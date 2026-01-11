package com.example.accessingdatamongodb.repository;

import java.util.List;

import com.example.accessingdatamongodb.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);

    public List<Customer> findByLastName(String lastName);

    public List<Customer> findAllByCreditLimit(double creditLimit);

    public List<Customer> findCustomerByCreditLimitGreaterThanEqual(double creditLimit);


}
