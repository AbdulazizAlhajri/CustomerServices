package com.assignment.demo.model;

import com.assignment.demo.data.customer.CustomerView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ServiceModel implements Serializable {

    private String id;
    private String name;
    private CustomerModel customerModel;

    public ServiceModel(String id, String name, CustomerView customerView) {
        this.id = id;
        this.name = name;
        this.customerModel = new CustomerModel(customerView);
    }
}
