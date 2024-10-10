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
    private int id_sale;

    @NotNull(message = "Seller ID cannot be null")
    @Column(name = "id_vendedor")
    @JsonProperty("id_vendedor")
    private int id_seller;

    @NotNull(message = "Product ID cannot be null")
    @Column(name = "id_producto")
    @JsonProperty("id_producto")
    private int id_product;

    @NotNull
    @CreationTimestamp
    @Column(name = "fecha_venta", nullable = false, updatable = false)
    @JsonProperty("fecha_venta")
    private LocalDateTime sale_date;

    public int getId_sale() {
        return id_sale;
    }

    public void setId_sale(int id_sale) {
        this.id_sale = id_sale;
    }

    public int getId_seller() {
        return id_seller;
    }

    public void setId_seller(int id_seller) {
        this.id_seller = id_seller;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public LocalDateTime getSale_date() {
        return sale_date;
    }

    public void setSale_date(LocalDateTime sale_date) {
        this.sale_date = sale_date;
    }
}
