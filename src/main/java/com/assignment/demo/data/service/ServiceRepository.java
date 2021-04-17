package com.assignment.demo.data.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceView, String> {

    Optional<ServiceView> findByName(String name);

    List<ServiceView> findAllByCustomerView_Name(String name);
}
