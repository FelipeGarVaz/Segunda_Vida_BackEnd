package com.servicios_web.segunda_vida_backend.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ImagenesProducto")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImagen;

    @Column(name = "url_imagen")
    private String urlImagen;

    //Creamos la relación con la tabla Product
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product producto;

    //GET AND SET
    public int getId_Imagen() {
        return idImagen;
    }

    public void setId_Imagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getUrl_Imagen() {
        return urlImagen;
    }

    public void setUrl_Imagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
