package com.assignment.demo.data.service;

import com.assignment.demo.data.customer.CustomerView;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ServiceView {

    @Id
    private String id;
    @Column(unique = true)
    private String name;
    @ManyToOne(targetEntity = CustomerView.class)
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerView customerView;

    public ServiceView() {
    }

    public ServiceView(String name, CustomerView customerView) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.customerView = customerView;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerView getCustomerView() {
        return customerView;
    }

    public void setCustomerView(CustomerView customerView) {
        this.customerView = customerView;
    }
}
