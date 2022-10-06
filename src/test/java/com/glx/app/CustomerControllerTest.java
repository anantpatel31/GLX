package com.glx.app;

import com.glx.app.dto.Customer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CustomerControllerTest extends BaseIntegrationTest{
    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer(1, "John", "password", "john", "john@gmail.com");
    }

    @Test
    public void deleteUser_returnSuccessMessage() throws Exception {
        mockMvc.perform(delete("/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$").value("Delete Successfull"));
    }
}
