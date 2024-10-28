package com.chernikov.DeliveryGO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressAPITest {
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc;

    @Test
    public void addressLoadingWithoutParamTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-address/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addressLoadingWrongParamTest() throws Exception {
        String addressId = "one";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-address/" + addressId))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addressLoadingNotFoundTest() throws Exception {
        long addressId = 1000000L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-address/" + addressId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addressLoadingTest() throws Exception {
        long addressId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-address/" + addressId))
                .andExpect(status().isOk());
    }
}
