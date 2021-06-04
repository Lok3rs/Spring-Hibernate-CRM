package com.codecool.service;

import com.codecool.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers(int sortField);

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomers(String searchPhrase);
}
