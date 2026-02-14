package com.example.accessingdatamongodb.service;

import com.example.accessingdatamongodb.model.Customer;
import com.example.accessingdatamongodb.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    //comment
    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void findAll() {
        Customer customer1 = new Customer("John", "Doe", "123", 1000.0);
        Customer customer2 = new Customer("Jane", "Doe", "456", 2000.0);
        when(repository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> result = customerService.findAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByFirstName() {
        Customer customer = new Customer("John", "Doe", "123", 1000.0);
        when(repository.findByFirstName("John")).thenReturn(customer);

        Customer result = customerService.findByFirstName("John");

        assertEquals("John", result.firstName);
        verify(repository, times(1)).findByFirstName("John");
    }

    @Test
    void initDB() {
        customerService.initDB();

        verify(repository, times(1)).deleteAll();
        // MAX_RECORDS is 1000 in CustomerService
        verify(repository, times(1000)).save(any(Customer.class));
        verify(repository, times(1)).findAllByCreditLimit(anyDouble());
        verify(repository, times(1)).findCustomerByCreditLimitGreaterThanEqual(anyDouble());
    }
}
