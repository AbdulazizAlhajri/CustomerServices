package com.assignment.demo.service;

import com.assignment.demo.data.customer.CustomerRepository;
import com.assignment.demo.data.customer.CustomerView;
import com.assignment.demo.exception.NoRecordFoundException;
import com.assignment.demo.model.CustomerModel;
import com.assignment.demo.service.customer.CustomerServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImp customerServiceImp;

    @Before
    public void initMocks() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void whenRetrievingAllCustomers_thenAllCustomersRetrieved() {

        List<CustomerView> customerViews = customerViewsMock();
        when(customerRepository.findAll()).thenReturn(customerViews);
        try {
            List<CustomerModel> result = customerServiceImp.getAllCustomers();
            assertEquals(customerViews.get(0).getCustomerId(), result.get(0).getId());
            assertEquals(customerViews.get(0).getName(), result.get(0).getName());
            assertEquals(customerViews.get(0).getPhone(), result.get(0).getPhone());
            assertThat(customerViews.size()).isEqualTo(result.size());
        } catch (NoRecordFoundException e) {
            Assert.fail("NoRecordFoundException");
        }
    }

    @Test(expected = NoRecordFoundException.class)
    public void whenRetrievingAllCustomers_thenThrowNoRecordFoundException() throws NoRecordFoundException {

        customerServiceImp.getAllCustomers();
    }

    private List<CustomerView> customerViewsMock() {

        List<CustomerView> customerModels = new ArrayList<>();
        customerModels.add(new CustomerView("a", "056234"));
        customerModels.add(new CustomerView("a", "056234"));
        return customerModels;
    }
}
