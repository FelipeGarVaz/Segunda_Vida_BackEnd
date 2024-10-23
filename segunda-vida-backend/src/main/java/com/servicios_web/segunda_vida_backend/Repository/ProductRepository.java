package com.servicios_web.segunda_vida_backend.Repository;

import com.servicios_web.segunda_vida_backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Encontrar un producto por su nombre
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findByNameJPQL(@Param("name") String name);

    //Encontrar un producto por su precio
    @Query("SELECT p FROM Product p WHERE p.price = :price")
    List<Product> findByPriceJPQL(@Param("price") Double price);

    //Encontrar por palabra clave
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> findByKeywordJPQL(@Param("keyword") String keyword);

}
