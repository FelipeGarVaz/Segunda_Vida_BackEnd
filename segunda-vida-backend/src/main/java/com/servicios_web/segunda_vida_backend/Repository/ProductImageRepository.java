package com.servicios_web.segunda_vida_backend.Repository;

import com.servicios_web.segunda_vida_backend.Model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

}
