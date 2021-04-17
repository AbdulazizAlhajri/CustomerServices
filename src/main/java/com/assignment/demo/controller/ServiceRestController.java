package com.assignment.demo.controller;


import com.assignment.demo.controller.dto.ResponseMessageDto;
import com.assignment.demo.controller.dto.ServiceDto;
import com.assignment.demo.controller.dto.ServiceRequest;
import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.exception.NoRecordFoundException;
import com.assignment.demo.service.service.ServiceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping(value = "services")
public class ServiceRestController {

    private final ServiceService serviceService;

    public ServiceRestController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    @PostMapping
    public ResponseEntity<ResponseMessageDto> createService(@RequestBody ServiceRequest serviceRequest) throws AlreadyExistException, NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start creating service");
        this.serviceService.createService(serviceRequest.getName(), serviceRequest.getCustomerId());
        return new ResponseEntity<>(new ResponseMessageDto("Service created"), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<ResponseMessageDto> updateService(@PathVariable String id, @RequestBody ServiceRequest serviceRequest) throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start updating service");
        this.serviceService.updateService(id, serviceRequest.getName(), serviceRequest.getCustomerId());
        return new ResponseEntity<>(new ResponseMessageDto("Service updated"), HttpStatus.OK);
    }

    @GetMapping(value = "/customerName/{name}")
    public ResponseEntity<List<ServiceDto>> getServiceByCustomerName(@PathVariable String name) throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start retrieving service");
        return new ResponseEntity<>(this.serviceService.getServiceByCustomerName(name).stream().map(
                serviceModel -> new ServiceDto(serviceModel.getId(), serviceModel.getName(), serviceModel.getCustomerModel())
        ).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ServiceDto>> getAllServices() throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start retrieving services");
        return new ResponseEntity<>(this.serviceService.getAllServices().stream().map(serviceModel -> new ServiceDto(
                serviceModel.getId(), serviceModel.getName(), serviceModel.getCustomerModel()
        )).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<ResponseMessageDto> deleteService(@PathVariable String id) throws NoRecordFoundException {

        log.info("Type: rest, Status: start, log-message: start deleting service");
        this.serviceService.deleteService(id);
        return new ResponseEntity<>(new ResponseMessageDto("Service deleted"), HttpStatus.OK);
    }
}
