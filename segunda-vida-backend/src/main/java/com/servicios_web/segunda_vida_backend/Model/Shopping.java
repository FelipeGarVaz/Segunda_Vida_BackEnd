package com.servicios_web.segunda_vida_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_comprador", insertable = false, updatable = false)
    private User buyer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Product product;

    @OneToMany(mappedBy = "shopping", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

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

    public Product getProduct() {
        return product;
    }


}
