package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.ProductImage;
import com.servicios_web.segunda_vida_backend.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productImage")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    // Endpoint para obtener todas las imágenes de productos
    @GetMapping
    public List<ProductImage> getAll() {
        return productImageService.getAll();
    }

    // Endpoint para crear una nueva imagen de producto
    @PostMapping
    public void createProductImage(@RequestBody ProductImage productImage) {
        productImageService.save(productImage);
    }

    // Endpoint para obtener una imagen de producto por su ID
    @GetMapping("{id_image}")
    public ProductImage getByIdImage(@PathVariable Integer id_image) {
        return productImageService.getByIdImage(id_image);
    }

    // Endpoint para actualizar una imagen de producto
    @PutMapping("{id_image}")
    public ResponseEntity<?> updateProductImage(@RequestBody ProductImage productImage, @PathVariable Integer id_image) {
        try {
            ProductImage auxProductImage = productImageService.getByIdImage(id_image);
            productImage.setId_Imagen(auxProductImage.getId_Imagen());
            productImageService.save(productImage);
            return ResponseEntity.ok().body("Updated record");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar una imagen de producto
    @DeleteMapping("{id_image}")
    public void deleteProductImage(@PathVariable Integer id_image) {
        productImageService.delete(id_image);
    }
}
