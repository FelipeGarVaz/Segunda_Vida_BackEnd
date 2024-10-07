package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Product;
import com.servicios_web.segunda_vida_backend.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public void save (Product product) {
        productRepo.save(product);
    }

    public Product getByID_Product(Integer id_product){
        return productRepo.findById(id_product).get();
    }

    public void delete(Integer id_product){
        productRepo.deleteById(id_product);
    }
}
