package com.servicios_web.segunda_vida_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios_web.segunda_vida_backend.Model.Shopping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  ShoppingRepository extends JpaRepository<Shopping, Integer> {

    //Encontrar todas las compras realizadas por un usuario específico
    @Query("SELECT s FROM Shopping s WHERE s.buyer.idUser = :userId")
    List<Shopping> findAllByBuyerIdJPQL(@Param("userId") int userId);

}
