package com.example.customerdemowithoutdb.ServiceTest;


import com.example.customerdemowithoutdb.entity.Customer;
import com.example.customerdemowithoutdb.service.CustomerService;
import com.example.customerdemowithoutdb.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(); // fresh in-memory list each time
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    void testGetCustomerByIdFound() {
        Customer customer = customerService.getCustomerById(1L);
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void testGetCustomerByIdNotFound() {
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(99L));
    }

    @Test
    void testCreateCustomer() {
        Customer newCustomer = new Customer(null, "Alice", "alice@example.com", "Austin", "TX", "USA");
        Customer saved = customerService.createCustomer(newCustomer);
        assertNotNull(saved.getId());
        assertEquals("Alice", saved.getName());
        assertEquals(3, customerService.getAllCustomers().size());
    }

    @Test
    void testUpdateCustomerFound() {
        Customer updated = new Customer(null, "John Updated", "john.new@example.com", "Boston", "MA", "USA");
        Customer result = customerService.updateCustomer(1L, updated);
        assertEquals("John Updated", result.getName());
        assertEquals("Boston", result.getCity());
    }

    @Test
    void testUpdateCustomerNotFound() {
        Customer updated = new Customer(null, "No One", "noone@example.com", "City", "ST", "Country");
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(99L, updated));
    }

    @Test
    void testDeleteCustomerFound() {
        customerService.deleteCustomer(1L);
        assertEquals(1, customerService.getAllCustomers().size());
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
    }

    @Test
    void testDeleteCustomerNotFound() {
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(99L));
    }
}

