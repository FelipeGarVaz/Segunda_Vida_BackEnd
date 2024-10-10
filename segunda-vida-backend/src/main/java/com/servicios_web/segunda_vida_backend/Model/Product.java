package com.servicios_web.segunda_vida_backend.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.concurrent.locks.Condition;

@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_producto")
    private int id_product;

    @NotNull(message = "User ID cannot be null")
    @Column(name = "id_usuario")
    @JsonProperty("id_vendedor")
    private int id_user;

    @NotBlank(message = "Name cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters.")
    @Column(name = "nombre_articulo")
    @JsonProperty("nombre_articulo")
    private String name;

    @NotBlank(message = "Price cannot be null or empty and must contain at least one non-whitespace character.")
    @Column(name = "precio")
    @JsonProperty("precio")
    private double price;

    @NotNull(message = "Category ID cannot be null")
    @Column(name = "id_categoria")
    @JsonProperty("id_categoria")
    private int id_categorie;

    @NotBlank(message = "Description cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters.")
    @Column(name = "descripcion")
    @JsonProperty("descripcion")
    private String description;

    @NotBlank(message = "Condition cannot be null or empty and must contain at least one non-whitespace character.")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "condicion")
    @JsonProperty("condicion")
    private Condition condition;

    // Enum para la columna condicion
    public enum Condition {
        Nuevo,
        Usado,
        Dañado
    }

    //GET AND SET
    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
