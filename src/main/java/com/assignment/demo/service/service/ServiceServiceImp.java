package com.assignment.demo.service.service;

import com.assignment.demo.data.customer.CustomerRepository;
import com.assignment.demo.data.customer.CustomerView;
import com.assignment.demo.data.service.ServiceRepository;
import com.assignment.demo.data.service.ServiceView;
import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.exception.NoRecordFoundException;
import com.assignment.demo.model.CustomerModel;
import com.assignment.demo.model.ServiceModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@CacheConfig(cacheNames = "service")
public class ServiceServiceImp implements ServiceService {

    private CustomerRepository customerRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImp (CustomerRepository customerRepository, ServiceRepository serviceRepository) {

        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
    }


    @Override
    @CacheEvict(cacheNames = "service", allEntries = true)
    public void createService(String serviceName, String customerId) throws AlreadyExistException, NoRecordFoundException {

        log.info("Type: service, Status: start, log-message: Start creating service for customer with customerId: {}", customerId);
        Optional<ServiceView> serviceView = this.serviceRepository.findByName(serviceName);
        if (!serviceView.isPresent()) {
            Optional<CustomerView> customerView = this.customerRepository.findById(customerId);
            if (customerView.isPresent()) {
                log.info("Type: service, Status: end, log-message: Service created");
                this.serviceRepository.save(new ServiceView(serviceName, customerView.get()));
            } else {
                log.info("Type: service, Status: end with exception, log-message: Customer not found");
                throw new NoRecordFoundException();
            }
        } else {
            log.info("Type: service, Status: end with exception, log-message: Service already exist");
            throw new AlreadyExistException();
        }
    }

    @Override
    @CacheEvict(cacheNames = "service", allEntries = true)
    public void updateService(String serviceId, String serviceName, String customerId) throws NoRecordFoundException {

        log.info("Type: service, Status: start, log-message: Start updating service");
        Optional<ServiceView> serviceView = this.serviceRepository.findById(serviceId);
        if (serviceView.isPresent()) {
            serviceView.get().setName(serviceName);
            Optional<CustomerView> customerView = this.customerRepository.findById(customerId);
            if (customerView.isPresent()) {
                serviceView.get().setCustomerView(customerView.get());
            } else {
                log.info("Type: service, Status: end with exception, log-message: Customer with id: {} not exist", customerId);
                throw new NoRecordFoundException();
            }
            this.serviceRepository.save(serviceView.get());
            log.info("Type: service, Status: end, log-message: Service updated successfully");
        } else {
            log.info("Type: service, Status: end with exception, log-message: Service with id: {} not exist", serviceId);
            throw new NoRecordFoundException();
        }
    }

    @Override
    @Cacheable(key = "{#methodName, #customerName}")
    public List<ServiceModel> getServiceByCustomerName(String customerName) throws NoRecordFoundException {

        log.info("Type: service, Status: end, log-message: start getting the service with name: {}", customerName);
        List<ServiceView> serviceViews = this.serviceRepository.findAllByCustomerView_Name(customerName);
        if (serviceViews != null && !serviceViews.isEmpty()) {
            log.info("Type: service, Status: end, log-message: Service retrieved successfully");
            return serviceViews.stream().map(serviceView -> new ServiceModel(serviceView.getId(), serviceView.getName(), serviceView.getCustomerView()
                    )).collect(Collectors.toList());
        } else {
            log.info("Type: service, Status: end with exception, log-message: Service with name: {} not exist", customerName);
            throw new NoRecordFoundException();
        }
    }

    @Override
    @Cacheable(key = "{#methodName}")
    public List<ServiceModel> getAllServices() throws NoRecordFoundException {

        log.info("Type: service, Status: end, log-message: start retrieving all services");
        List<ServiceView> serviceViews = this.serviceRepository.findAll();
        if (serviceViews != null && !serviceViews.isEmpty()) {
            log.info("Type: service, Status: end, log-message: Services retrieved successfully");
            return serviceViews.stream().map(serviceView -> new ServiceModel(serviceView.getId(), serviceView.getName(),
                    new CustomerModel(serviceView.getCustomerView()))).collect(Collectors.toList());
        } else {
            log.info("Type: service, Status: end with exception, log-message: No services at all");
            throw new NoRecordFoundException();
        }
    }

    @Override
    @CacheEvict(cacheNames = "service", allEntries = true)
    public void deleteService(String id) throws NoRecordFoundException {

        log.info("Type: service, Status: end, log-message: start deleting service with id: {}", id);
        try {
            this.serviceRepository.deleteById(id);
        } catch (NullPointerException e) {
            log.info("Type: service, Status: end with exception, log-message: Service Not exist");
            throw new NoRecordFoundException();
        }
    }
}
