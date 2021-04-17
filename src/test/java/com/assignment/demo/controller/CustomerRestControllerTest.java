package com.assignment.demo.controller;

import com.assignment.demo.exception.AlreadyExistException;
import com.assignment.demo.model.CustomerModel;
import com.assignment.demo.service.customer.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomerRestControllerTest {

    @InjectMocks
    private CustomerRestController customerRestController;

    private MockMvc mockMvc;

    @Before
    public void  setup(){

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerRestController)
                .setHandlerExceptionResolvers(TestUtil.createExceptionResolver(ExceptionHandlerController.class))
                .build();
    }

    @Test
    public void testCreateCustomer() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(customerModelMok());
        TestUtil.perform(post("/customers", customerModelMok()).contentType(MediaType.APPLICATION_JSON).content(requestJson), mockMvc)
                .andExpect(status().isAccepted())
                .andExpect(result -> result.getResponse().equals("Customer created"));
    }

    @Test(expected = AlreadyExistException.class)
    public void whenCreateCustomerThenThrowAlreadyExist() throws Exception {

        CustomerService customerServiceImp = mock(CustomerService.class);
        doThrow(AlreadyExistException.class).when(customerServiceImp).createCustomer("a", "123");
        customerServiceImp.createCustomer("a", "123");
    }

    private CustomerModel customerModelMok() {
        return new CustomerModel("123abc", "test", "0567");
    }
}
