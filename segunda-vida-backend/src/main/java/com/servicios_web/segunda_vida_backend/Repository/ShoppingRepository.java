package com.servicios_web.segunda_vida_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios_web.segunda_vida_backend.Model.Shopping;

public interface  ShoppingRepository extends JpaRepository<Shopping, Integer> {
    
}
