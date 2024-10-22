package com.servicios_web.segunda_vida_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_producto")
    private int idProduct;

    @NotNull(message = "User ID cannot be null")
    @Column(name = "id_usuario")
    @JsonProperty("id_vendedor")
    private int idUser;

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
    private int idCategorie;

    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Category categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private User user;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Shopping> purchases = new ArrayList<>();

    // Enum para la columna condicion
    public enum Condition {
        Nuevo,
        Usado,
        Dañado
    }

    //GET AND SET
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
