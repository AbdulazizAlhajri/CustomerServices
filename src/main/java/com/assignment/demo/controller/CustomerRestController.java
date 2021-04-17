package com.assignment.demo.controller;

import com.assignment.demo.controller.dto.CustomerDto;
import com.assignment.demo.controller.dto.CustomerRequest;
import com.assignment.demo.controller.dto.ResponseMessageDto;
import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.exception.NoRecordFoundException;
import com.assignment.demo.service.customer.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping(value = "customers")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<ResponseMessageDto> createCustomer(@RequestBody CustomerRequest customerRequest) throws AlreadyExistException {

        log.info("Type: rest, Status: start, log-message: start creating customer");
        this.customerService.createCustomer(customerRequest.getName(), customerRequest.getPhone());
        return new ResponseEntity<>(new ResponseMessageDto("Customer created"), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<ResponseMessageDto> updateCustomer(@PathVariable String id, @RequestBody CustomerRequest customerRequest) throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start updating customer");
        this.customerService.updateCustomer(id, customerRequest.getName(), customerRequest.getPhone());
        return new ResponseEntity<>(new ResponseMessageDto("Customer updated"), HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<CustomerDto> getCustomerByName(@PathVariable String name) throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start retrieving customer");
        return new ResponseEntity<>(new CustomerDto(this.customerService.getCustomerByName(name)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start retrieving customers");
        return new ResponseEntity<>(this.customerService.getAllCustomers().stream().map(customerModel -> new CustomerDto(customerModel.getId(),
                customerModel.getName(), customerModel.getPhone())).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<ResponseMessageDto> deleteCustomer(@PathVariable String id) throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start deleting customer");
        this.customerService.deleteCustomer(id);
        return new ResponseEntity<>(new ResponseMessageDto("Customer deleted"), HttpStatus.OK);
    }
}
