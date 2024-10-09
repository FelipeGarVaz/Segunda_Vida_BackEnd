package com.servicios_web.segunda_vida_backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "compras")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotNull
    @Column(name = "id_compra")
    private int id_shopping;

    @NotNull
    @Column(name = "id_comprador")
    private int id_buyer;

    @NotNull
    @Column(name = "id_producto")
    private int id_product;

    @NotNull
    @CreationTimestamp
    @Column(name = "fecha_compra", nullable = false, updatable = false)
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date_of_purchase;

      //GET AND SET
    public int getId_shopping() {
        return id_shopping;
    }

    public void setId_shopping(int id_shopping) {
        this.id_shopping = id_shopping;
    }

    public int getId_buyer() {
        return id_buyer;
    }

    public void setId_buyer(int id_buyer) {
        this.id_buyer = id_buyer;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public LocalDateTime get_Date_of_purchase() {
        return date_of_purchase;
    }

    public void set__Date_of_purchase(LocalDateTime date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }
}
