package com.servicios_web.segunda_vida_backend.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.servicios_web.segunda_vida_backend.Model.Product;
import com.servicios_web.segunda_vida_backend.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Product", description = "Methods and documentation for products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "Found products", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class))) })
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    // Crear un nuevo producto
    @Operation(summary = "Register a product")
    @ApiResponse(responseCode = "201", description = "Product registered", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class))) })
    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productService.save(product);
    }

    // Buscar producto por ID
    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid product id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @GetMapping("{id_product}")
    public ResponseEntity<Product> getByID_Product(@PathVariable Integer id_product) {
        try {
            Product product = productService.getByID_Product(id_product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar producto
    @Operation(summary = "Update a product")
    @ApiResponse(responseCode = "200", description = "Product updated", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class))) })
    @PutMapping("{id_product}")
    public ResponseEntity<String> update(@RequestBody Product product, @PathVariable Integer id_product) {
        try {
            Product auxProduct = productService.getByID_Product(id_product);
            product.setId_product(auxProduct.getId_product());
            productService.save(product);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the provided id is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar producto
    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "204", description = "Product deleted")
    @DeleteMapping("{id_product}")
    public void delete(@PathVariable Integer id_product) {
        productService.delete(id_product);
    }
}
