package com.assignment.demo.controller.dto;


import com.assignment.demo.model.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable {

    private String id;
    private String name;
    private String phone;

    public CustomerDto(CustomerModel customerModel) {
        this.id = customerModel.getId();
        this.name = customerModel.getName();
        this.phone = customerModel.getPhone();
    }
}
