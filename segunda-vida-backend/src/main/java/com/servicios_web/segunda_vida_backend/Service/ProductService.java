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
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void save (Product product) {
        productRepository.save(product);
    }

    public Product getByIDProduct(Integer idProduct){
        return productRepository.findById(idProduct).get();
    }

    public void delete(Integer idProduct){
        productRepository.deleteById(idProduct);
    }
}
