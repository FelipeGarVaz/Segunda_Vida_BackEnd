package com.servicios_web.segunda_vida_backend.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ImagenesProducto")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotNull
    @Column(name = "id_imagen")
    private int idImage;

    @Column(name = "url_imagen")
    private String urlImage;

    //Creamos la relación con la tabla Product
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product producto;

    //GET AND SET
    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
