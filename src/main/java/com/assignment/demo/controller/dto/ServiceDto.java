package com.assignment.demo.controller.dto;

import com.assignment.demo.model.CustomerModel;
import com.assignment.demo.model.ServiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ServiceDto implements Serializable {

    private String id;
    private String name;
    private CustomerModel customerModel;

    public ServiceDto(ServiceModel serviceModel) {
        this.id = serviceModel.getId();
        this.name = serviceModel.getName();
        this.customerModel = serviceModel.getCustomerModel();
    }
}
