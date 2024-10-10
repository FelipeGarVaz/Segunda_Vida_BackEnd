package com.servicios_web.segunda_vida_backend.Repository;

import com.servicios_web.segunda_vida_backend.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
