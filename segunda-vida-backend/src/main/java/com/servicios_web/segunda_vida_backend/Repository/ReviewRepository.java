package com.servicios_web.segunda_vida_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios_web.segunda_vida_backend.Model.Review;

public interface ReviewRepository extends JpaRepository <Review, Integer> {
}
