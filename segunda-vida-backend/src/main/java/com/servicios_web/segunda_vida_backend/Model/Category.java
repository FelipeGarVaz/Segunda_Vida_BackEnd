package com.servicios_web.segunda_vida_backend.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_categoria")
    private int id_category;

    @Column(name = "nombre_categoria")
    private String name;

    //Relación One-to-Many con Producto
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Product> productos;

    //GET AND SET
    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
