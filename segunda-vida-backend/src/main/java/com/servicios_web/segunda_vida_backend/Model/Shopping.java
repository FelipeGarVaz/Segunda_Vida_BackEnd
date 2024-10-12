package com.servicios_web.segunda_vida_backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "compras")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_compra")
    @JsonProperty("id_compra")
    private int idShopping;

    @NotNull(message = "Buyer ID cannot be null")
    @Column(name = "id_comprador")
    @JsonProperty("id_comprador")
    private int idBuyer;

    @NotNull(message = "Product ID cannot be null")
    @Column(name = "id_producto")
    @JsonProperty("id_producto")
    private int idProduct;

    @NotNull
    @CreationTimestamp
    @Column(name = "fecha_compra", nullable = false, updatable = false)
    @JsonProperty("fecha_compra")
    private LocalDateTime dateOfPurchase;

      //GET AND SET
    public int getIdShopping() {
        return idShopping;
    }

    public void setIdShopping(int idShopping) {
        this.idShopping = idShopping;
    }

    public int getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(int idBuyer) {
        this.idBuyer = idBuyer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
