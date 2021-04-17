package com.assignment.demo.configuration;


import com.assignment.demo.data.customer.CustomerRepository;
import com.assignment.demo.data.service.ServiceRepository;
import com.assignment.demo.service.customer.CustomerService;
import com.assignment.demo.service.customer.CustomerServiceImp;
import com.assignment.demo.service.service.ServiceService;
import com.assignment.demo.service.service.ServiceServiceImp;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories("com.assignment.demo.data")
public class Config {

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository) {

        return new CustomerServiceImp(customerRepository);
    }

    @Bean
    public ServiceService serviceService(CustomerRepository customerRepository, ServiceRepository serviceRepository) {
        return new ServiceServiceImp(customerRepository, serviceRepository);
    }
}
