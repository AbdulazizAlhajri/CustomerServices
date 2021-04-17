package com.assignment.demo.service.customer;

import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.exception.NoRecordFoundException;
import com.assignment.demo.model.CustomerModel;

import java.util.List;

public interface CustomerService {

    void createCustomer(String name, String phone) throws AlreadyExistException;

    void updateCustomer(String id, String name, String phone) throws NoRecordFoundException;

    CustomerModel getCustomerByName(String customerName) throws NoRecordFoundException;

    List<CustomerModel> getAllCustomers() throws NoRecordFoundException;

    void deleteCustomer(String id) throws NoRecordFoundException;
}
