package com.servicios_web.segunda_vida_backend.Controller;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SaleController saleController;

    @Test
    void contextLoads() throws Exception {
        assertThat(saleController).isNotNull();
    }

    // Pruebas de obtención (GET)
    @Test
    public void getAllPaginatedTest() throws Exception {
        mvc.perform(get("/sales/pagination")
                        .param("page", "0")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getByIdTest() throws Exception {
        mvc.perform(get("/sales/5").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSale", CoreMatchers.is(5)));
    }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        mvc.perform(get("/sales/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }

    // Pruebas de creación (POST)
    @Test
    public void createSaleTest() throws Exception {
        String newSale = "{ \"id_vendedor\": \"3\", \"id_producto\": \"22\" }";
        mvc.perform(post("/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newSale))
                .andExpect(status().isCreated());
    }

    // Pruebas de actualización (PUT)
    @Test
    public void updateSaleTest() throws Exception {
        String updatedSale = "{ \"id_vendedor\": \"9\", \"id_producto\": \"18\" }";
        mvc.perform(put("/sales/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedSale))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated record")));
    }

    // Pruebas de eliminación (DELETE)
    @Test
    public void deleteSaleTest() throws Exception {
        mvc.perform(delete("/sales/19"))
                .andExpect(status().isNoContent());
    }
}
