package com.servicios_web.segunda_vida_backend.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_usuario")
    private int idUser;

    @NotBlank(message = "Name cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters.")
    @Column(name = "nombre_completo")
    @JsonProperty("nombre_completo")
    private String name;

    @NotBlank(message = "Username cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 8, max = 50, message = "Username must be between 8 and 50 characters.")
    @Column(name = "nombre_usuario")
    @JsonProperty("nombre_usuario")
    private String userName;

    @NotBlank(message = "Password cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters.")
    @Column(name = "contrasena")
    @JsonProperty("contrasena")
    private String password;

    @NotBlank(message = "Phone number cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 10, max = 20, message = "Phone number must be between 10 and 20 characters.")
    @Column(name = "telefono")
    @JsonProperty("telefono")
    private String phone;

    @NotBlank(message = "Email cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 10, max = 100, message = "Email must be between 10 and 100 characters.")
    @Column(name = "correo_electronico")
    @JsonProperty("correo_electronico")
    private String email;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


