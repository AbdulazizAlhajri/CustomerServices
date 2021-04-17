package com.assignment.demo.service.service;

import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.exception.NoRecordFoundException;
import com.assignment.demo.model.ServiceModel;

import java.util.List;

public interface ServiceService {

    void createService(String serviceName, String customerId) throws AlreadyExistException, NoRecordFoundException;

    void updateService(String serviceId, String serviceName, String customerId) throws NoRecordFoundException;

    List<ServiceModel> getServiceByCustomerName(String customerName) throws NoRecordFoundException;

    List<ServiceModel> getAllServices() throws NoRecordFoundException;

    void deleteService(String id) throws NoRecordFoundException;
}
