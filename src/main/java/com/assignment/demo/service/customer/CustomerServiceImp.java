package com.assignment.demo.service.customer;

import com.assignment.demo.data.customer.CustomerRepository;
import com.assignment.demo.data.customer.CustomerView;
import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.exception.NoRecordFoundException;
import com.assignment.demo.model.CustomerModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@CacheConfig(cacheNames = "customer")
public class CustomerServiceImp implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    @CacheEvict(cacheNames = "customer", allEntries = true)
    public void createCustomer(String name, String phone) throws AlreadyExistException {

        log.info("Type: service, Status: start, log-message: start creating customer");
        Optional<CustomerView> customerView = this.customerRepository.findByName(name);
        if (!customerView.isPresent()) {
            log.info("Type: service, Status: end, log-message: Customer created");
            this.customerRepository.save(new CustomerView(name, phone));
        } else {
            log.info("Type: service, Status: end with exception, log-message: Customer already exist");
            throw new AlreadyExistException();
        }
    }

    @Override
    @CacheEvict(cacheNames = "customer", allEntries = true)
    public void updateCustomer(String id, String name, String phone) throws NoRecordFoundException {

        log.info("Type: service, Status: end, log-message: start updating customer");
        Optional<CustomerView> customerView = this.customerRepository.findById(id);
        if (customerView.isPresent()) {
            customerView.get().setName(name);
            customerView.get().setPhone(phone);
            this.customerRepository.save(customerView.get());
            log.info("Type: service, Status: end, log-message: Customer updated successfully");
        } else {
            log.info("Type: service, Status: end with exception, log-message: Customer with Id: {} not found", id);
            throw new NoRecordFoundException();
        }
    }

    @Override
    @Cacheable(key = "{#methodName, #customerName}")
    public CustomerModel getCustomerByName(String customerName) throws NoRecordFoundException {

        log.info("Type: service, Status: end, log-message: start retrieving customer with name: {}", customerName);
        Optional<CustomerView> customerView = this.customerRepository.findByName(customerName);
        if (customerView.isPresent()) {
            log.info("Type: service, Status: end, log-message: customer retrieved successfully");
            return new CustomerModel(customerView.get().getCustomerId(), customerView.get().getName(), customerView.get().getPhone());
        } else {
            log.info("Type: service, Status: end, log-message: Customer not found");
            throw new NoRecordFoundException();
        }
    }

    @Override
    @Cacheable(key = "{#methodName}")
    public List<CustomerModel> getAllCustomers() throws NoRecordFoundException {

        log.info("Type: service, Status: end, log-message: Service updated successfully");
        List<CustomerView> customerViews = this.customerRepository.findAll();
        if (customerViews != null && !customerViews.isEmpty()) {
            log.info("Type: service, Status: end, log-message: customers retrieved successfully");
            return customerViews.stream().map(customerView -> new CustomerModel(customerView.getCustomerId(), customerView.getName(),
                    customerView.getPhone())).collect(Collectors.toList());
        } else {
            log.info("Type: service, Status: end, log-message: No customers at all");
            throw new NoRecordFoundException();
        }
    }

    @Override
    @CacheEvict(cacheNames = "customer", allEntries = true)
    public void deleteCustomer(String id) throws NoRecordFoundException {

        log.info("Type: service, Status: start, log-message: start deleting customer");
        try {
            this.customerRepository.deleteById(id);
        } catch (NullPointerException | EmptyResultDataAccessException e){
            log.info("Type: service, Status: end, log-message: No record found for customer with id: {}", id);
            throw new NoRecordFoundException();
        }
    }
}
