package com.assignment.demo.data.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerView, String> {

    Optional<CustomerView> findByName(String name);
}
