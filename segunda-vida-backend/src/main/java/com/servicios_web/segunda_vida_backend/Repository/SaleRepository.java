package com.servicios_web.segunda_vida_backend.Repository;
import com.servicios_web.segunda_vida_backend.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    //Encontrar todas las ventas realizadas por un usuario específico
    @Query("SELECT s FROM Sale s WHERE s.seller.idUser = :userId")
    List<Sale> findAllBySellerIdJPQL(@Param("userId") int userId);

    //Encontrar una venta realizada por fecha en formato ejemplo (2024-12-31)
    @Query("SELECT s FROM Sale s WHERE DATE(s.saleDate) = :date")
    List<Sale> findByDateJPQL(@Param("date") LocalDate date);

    //Encontrar una venta realizada por fecha en formato completo ejemplo (2024-12-31T12:07:30)
    @Query("SELECT s FROM Sale s WHERE s.saleDate = :date")
    List<Sale> findByDateCompleteJPQL(@Param("date") LocalDateTime date);

    //Encontrar una venta por un producto específico
    @Query("SELECT s FROM Sale s WHERE s.product.idProduct = :productId")
    List<Sale> findByProductJPQL(@Param("productId") int productId);
}
