package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Product;
import com.servicios_web.segunda_vida_backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@CrossOrigin(originPatterns = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class ProductController {
    @Autowired
    private ProductService productService;

    //Endpoint que maneja solicitudes HHTP GET y devuelve una lista de productos
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    //Crear producto
    @PostMapping
    public void createProduct (@RequestBody Product product) {
        productService.save(product);
    }

    //Buscar por ID
    @GetMapping("{id_product}")
    public Product getByID_Product(@PathVariable Integer id_product) {
        return productService.getByID_Product(id_product);
    }


    //Actualizar producto
    @PutMapping("{id_product}")
    public ResponseEntity<?> Update(@RequestBody Product product, @PathVariable Integer id_product) {
        try {
            Product auxProduct = productService.getByID_Product(id_product);
            product.setId_product(auxProduct.getId_product());
            productService.save(product);
            return ResponseEntity.ok().body("Updated record");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar producto
    @DeleteMapping("{id_product}")
    public void delete(@PathVariable Integer id_product) {
        productService.delete(id_product);
    }
}
