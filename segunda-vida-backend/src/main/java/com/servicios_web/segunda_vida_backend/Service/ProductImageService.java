package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.ProductImage;
import com.servicios_web.segunda_vida_backend.Repository.ProductImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepo;

    // Obtener todas las imágenes de producto
    public List<ProductImage> getAll() {
        return productImageRepo.findAll();
    }

    // Guardar una nueva imagen de producto
    public void save(ProductImage productImage) {
        productImageRepo.save(productImage);
    }

    // Obtener una imagen de producto por su ID
    public ProductImage getByIdImage(Integer idImagen) {
        return productImageRepo.findById(idImagen).orElse(null);
    }

    // Eliminar una imagen de producto por su ID
    public void delete(Integer idImagen) {
        productImageRepo.deleteById(idImagen);
    }
}
