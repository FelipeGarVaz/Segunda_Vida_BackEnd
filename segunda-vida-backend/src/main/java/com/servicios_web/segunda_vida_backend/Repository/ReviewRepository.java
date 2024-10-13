package com.servicios_web.segunda_vida_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios_web.segunda_vida_backend.Model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository <Review, Integer> {

    //Obtener todas las reseñas realizadas por un usuario
    @Query("SELECT r FROM Review r WHERE r.shopping.buyer.idUser = :userId")
    List<Review> findAllByUserIdJPQL(@Param("userId") int userId);


}



