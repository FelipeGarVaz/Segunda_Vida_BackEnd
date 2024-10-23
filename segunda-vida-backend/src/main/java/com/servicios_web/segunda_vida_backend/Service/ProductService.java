package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Product;
import com.servicios_web.segunda_vida_backend.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //paginacion
    public List<Product> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Product> products = productRepository.findAll(pageReq);
        return products.getContent();
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

    //Encontrar un producto por su nombre
    public List<Product> findByName(String name){
        return productRepository.findByNameJPQL(name);
    }

    //Encontrar un producto por su precio
    public List<Product> findByPrice(Double price){
        return productRepository.findByPriceJPQL(price);
    }

    //Encontrar por palabra clave
    public List<Product> findByKeyword(String keyword){
        return productRepository.findByKeywordJPQL(keyword);
    }
}
