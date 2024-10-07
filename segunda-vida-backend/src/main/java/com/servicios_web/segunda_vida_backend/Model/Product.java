package com.servicios_web.segunda_vida_backend.Model;


import jakarta.persistence.*;

import java.util.concurrent.locks.Condition;

@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_producto")
    private int id_product;

    @Column(name = "id_usuario")
    private int id_user;

    @Column(name = "nombre_articulo")
    private String name;

    @Column(name = "precio")
    private double price;

    @Column(name = "id_categoria")
    private int id_categorie;

    @Column(name = "descripcion")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicion")
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
