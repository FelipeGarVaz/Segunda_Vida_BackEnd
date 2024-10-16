package com.servicios_web.segunda_vida_backend.Repository;

import com.servicios_web.segunda_vida_backend.Model.Sale;
import com.servicios_web.segunda_vida_backend.Model.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    //Encontrar todas las ventas realizadas por un usuario específico
    @Query("SELECT s FROM Sale s WHERE s.seller.idUser = :userId")
    List<Sale> findAllBySellerIdJPQL(@Param("userId") int userId);
}
