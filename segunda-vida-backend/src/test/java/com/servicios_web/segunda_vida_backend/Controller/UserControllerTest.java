package com.servicios_web.segunda_vida_backend.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

//    @Test
//    public void getAllTest() throws Exception {
//        mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
//    }


    //Pruebas de obtencion GET
    @Test
    public void getAllPaginatedTest() throws Exception {
        mvc.perform(get("/users/pagination")
                        .param("page", "0")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getByIdTest() throws Exception {
        mvc.perform(get("/users/5").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idUser", is(5)));
    }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        mvc.perform(get("/users/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                   .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }

    //Pruebas de creación POST
    //verificar que nombre de usuario y correo electronico no este duplicado en la base de datos
    @Test
    public void createUserTest() throws Exception {
        String newUser = "{ \"nombre_completo\": \"Carlitos Condecito\", \"nombre_usuario\": \"carlitos2024\", \"contrasena\": \"password123\", \"telefono\": \"5551289080\", \"correo_electronico\": \"condeCar@example.com\" }";
        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUser))
                .andExpect(status().isCreated());
    }

    //Pruebas de actualización PUT
    //verificar que nombre de usuario no este duplicado en la base de datos
    @Test
    public void updateUserTest() throws Exception {
        String updatedUser = "{ \"nombre_completo\": \"Pablo Baltazar\", \"nombre_usuario\": \"baltazar1515\", \"contrasena\": \"newpassword123\", \"telefono\": \"5551289080\", \"correo_electronico\": \"rey@example.com\" }";

        mvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUser))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated record")));
    }

    //Pruebas de eliminación DELETE
    @Test
    public void deleteUserTest() throws Exception {
        mvc.perform(delete("/users/14"))
                .andExpect(status().isNoContent());
    }
}
