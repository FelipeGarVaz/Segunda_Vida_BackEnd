package com.servicios_web.segunda_vida_backend.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "ImagenesProducto")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Imagen;

    @Column(name = "url_imagen")
    private String url_Imagen;

    //Creamos la relación con la tabla Product
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product producto;

    //GET AND SET
    public int getId_Imagen() {
        return id_Imagen;
    }

    public void setId_Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
    }

    public String getUrl_Imagen() {
        return url_Imagen;
    }

    public void setUrl_Imagen(String url_Imagen) {
        this.url_Imagen = url_Imagen;
    }
}
