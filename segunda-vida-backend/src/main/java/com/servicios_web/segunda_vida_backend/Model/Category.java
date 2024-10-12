package com.servicios_web.segunda_vida_backend.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_categoria")
    private int idCategory;

    @NotBlank(message = "The category name cannot be blank")
    @Size(min = 1, max = 100, message = "The category name must be between 1 and 100 characters")
    @Column(name = "nombre_categoria")
    private String nameCategoria;

    //Relación One-to-Many con Producto
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Product> productos;

    //GET AND SET
    public int getId_category() {
        return idCategory;
    }

    public void setId_category(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return nameCategoria;
    }

    public void setName(String nameCategoria) {
        this.nameCategoria = nameCategoria;
    }

}
