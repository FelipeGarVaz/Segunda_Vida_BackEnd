package com.servicios_web.segunda_vida_backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_compra")
    private int id_shopping;

    @Column(name = "id_comprador")
    private int id_buyer;

    @Column(name = "id_producto")
    private int id_product;

    @Column(name = "fecha_compra")
    private String date_of_purchase;

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

    public String get_Date_of_purchase() {
        return date_of_purchase;
    }

    public void set__Date_of_purchase(String date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }
}
