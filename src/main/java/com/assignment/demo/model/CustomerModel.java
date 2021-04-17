package com.assignment.demo.model;


import com.assignment.demo.data.customer.CustomerView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel implements Serializable {

    private String id;
    private String name;
    private String phone;

    public CustomerModel(CustomerView customerView) {
        this.id = customerView.getCustomerId();
        this.name = customerView.getName();
        this.phone = customerView.getPhone();
    }
}
