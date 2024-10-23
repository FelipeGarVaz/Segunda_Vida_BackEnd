package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.ProductImage;
import com.servicios_web.segunda_vida_backend.Service.ProductImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("productImage")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    //Paginacion
    @Operation(summary = "Get all product images with pagination")
    @GetMapping(value = "pagination", params = {"page", "pageSize"})
    public List<ProductImage> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<ProductImage> productImages = productImageService.getAll(page, pageSize);
        return productImages;
    }


    //Crear una nueva imagen de producto
    @PostMapping
    public void createProductImage(@RequestBody ProductImage productImage) {
        productImageService.save(productImage);
    }

    //Obtener una imagen de producto por su ID
    @GetMapping("{idImagen}")
    public ProductImage getByIdImage(@PathVariable Integer idImage) {
        return productImageService.getByIdImage(idImage);
    }

    //Actualizar una imagen de producto
    @PutMapping("{idImagen}")
    public ResponseEntity<?> updateProductImage(@RequestBody ProductImage productImage, @PathVariable Integer idImagen) {
        try {
            ProductImage auxProductImage = productImageService.getByIdImage(idImagen);
            productImage.setIdImage(auxProductImage.getIdImage());
            productImageService.save(productImage);
            return ResponseEntity.ok().body("Updated record");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar una imagen de producto
    @DeleteMapping("{idImage}")
    public void deleteProductImage(@PathVariable Integer idImage) {
        productImageService.delete(idImage);
    }


}
