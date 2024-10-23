package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.ProductImage;
import com.servicios_web.segunda_vida_backend.Repository.ProductImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    //paginacion
    public List<ProductImage> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page <ProductImage> productImages = productImageRepository.findAll(pageReq);
        return productImages.getContent();
    }

    // Guardar una nueva imagen de producto
    public void save(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    // Obtener una imagen de producto por su ID
    public ProductImage getByIdImage(Integer idImage) {
        return productImageRepository.findById(idImage).orElse(null);
    }

    // Eliminar una imagen de producto por su ID
    public void delete(Integer idImage) {
        productImageRepository.deleteById(idImage);
    }

}
