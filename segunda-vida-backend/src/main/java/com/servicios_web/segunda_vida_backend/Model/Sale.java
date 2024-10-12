package com.servicios_web.segunda_vida_backend.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_venta")
    @JsonProperty("id_venta")
    private int idSale;

    @NotNull(message = "Seller ID cannot be null")
    @Column(name = "id_vendedor")
    @JsonProperty("id_vendedor")
    private int idSeller;

    @NotNull(message = "Product ID cannot be null")
    @Column(name = "id_producto")
    @JsonProperty("id_producto")
    private int idProduct;

    @NotNull
    @CreationTimestamp
    @Column(name = "fecha_venta", nullable = false, updatable = false)
    @JsonProperty("fecha_venta")
    private LocalDateTime saleDate;

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    @NotNull(message = "Seller ID cannot be null")
    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(@NotNull(message = "Seller ID cannot be null") int idSeller) {
        this.idSeller = idSeller;
    }

    @NotNull(message = "Product ID cannot be null")
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(@NotNull(message = "Product ID cannot be null") int idProduct) {
        this.idProduct = idProduct;
    }

    public @NotNull LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(@NotNull LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", idSeller=" + idSeller +
                ", idProduct=" + idProduct +
                ", saleDate=" + saleDate +
                '}';
    }
}
