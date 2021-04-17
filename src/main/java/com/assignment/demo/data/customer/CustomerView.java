package com.assignment.demo.data.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class CustomerView {

    @Id
    private String customerId;
    @Column(unique = true)
    private String name;
    private String phone;

    public CustomerView() {
    }

    public CustomerView(String name, String phone) {
        this.customerId = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
