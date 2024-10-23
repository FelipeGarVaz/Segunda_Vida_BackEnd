package com.servicios_web.segunda_vida_backend.Repository;

import com.servicios_web.segunda_vida_backend.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Integer> {

    // Buscar categoría por nombre
    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
    List<Category> findByNameJPQL(@Param("categoryName") String categoryName);
}
