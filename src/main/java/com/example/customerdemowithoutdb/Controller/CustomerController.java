package com.example.customerdemowithoutdb.Controller;


import com.example.customerdemowithoutdb.entity.Customer;
import com.example.customerdemowithoutdb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ✅ Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // ✅ Get customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // ✅ Create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerService.createCustomer(newCustomer);
    }

    // ✅ Update an existing customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }

    // ✅ Delete a customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    // ✅ Optional: Search customers by city/state/country
    @GetMapping("/search")
    public List<Customer> searchCustomers(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country) {

        return customerService.getAllCustomers().stream()
                .filter(c -> (city == null || c.getCity().equalsIgnoreCase(city)))
                .filter(c -> (state == null || c.getState().equalsIgnoreCase(state)))
                .filter(c -> (country == null || c.getCountry().equalsIgnoreCase(country)))
                .collect(Collectors.toList());
    }
}
