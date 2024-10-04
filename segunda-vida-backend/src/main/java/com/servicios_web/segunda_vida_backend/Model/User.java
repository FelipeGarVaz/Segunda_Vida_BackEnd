package com.servicios_web.segunda_vida_backend.Model;


import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_usuario")
    private int id_user;
    @Column(name = "nombre_completo")
    private String name;
    @Column(name = "nombre_usuario")
    private String userName;
    @Column(name = "contrasena")
    private String password;
    @Column(name = "telefono")
    private String phone;
    @Column(name = "correo_electronico")
    private String email;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
}

