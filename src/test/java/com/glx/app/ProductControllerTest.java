package com.glx.app;

import com.glx.app.dto.Product;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class ProductControllerTest extends BaseIntegrationTest {
    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product(1, "product", "description", 10.0);
    }

    @Test
    @SneakyThrows
    public void givenProductObject_whenSave_returnSuccessMessage() {
        mockMvc.perform(post("/product")
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$").value("Product Added to Database Successfully."));
    }

    @Test
    @SneakyThrows
    public void givenNonExistentProductId_whenGetProductById_returnNotFoundMessage() {
        Integer nonExistent = 2;

        mockMvc.perform(get("/product/2")
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$").value("Id Not found"));
    }

    @Test
    @SneakyThrows
    public void givenNonExistentProductName_whenGetProductByName_returnNoMatchingMessage() {
        String nonExistentName = "name";

        mockMvc.perform(get("/product?name=" + nonExistentName)
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$").value("No Product matching the given name : " + nonExistentName));
    }

    @Test
    @SneakyThrows
    public void givenListOfProduct_whenGetAllProduct_returnSuccess() {
        Product product2 =  new Product(2, "product2", "description2", 10.0);

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);

         mockMvc.perform(get("/products")
                .content(objectMapper.writeValueAsString(products))
                .contentType(MediaType.APPLICATION_JSON)
        )   .andExpect(jsonPath("$.size()", is(2)));

    }
}
