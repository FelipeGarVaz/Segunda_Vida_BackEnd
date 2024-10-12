package com.servicios_web.segunda_vida_backend.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.servicios_web.segunda_vida_backend.Model.Product;
import com.servicios_web.segunda_vida_backend.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("{idProduct}")
    public ResponseEntity<Product> getByID_Product(@PathVariable Integer idProduct) {
        try {
            Product product = productService.getByID_Product(idProduct);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar producto
    @Operation(summary = "Update a product")
    @ApiResponse(responseCode = "200", description = "Product updated", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class))) })
    @PutMapping("{idProduct}")
    public ResponseEntity<String> update(@RequestBody Product product, @PathVariable Integer idProduct) {
        try {
            Product auxProduct = productService.getByID_Product(idProduct);
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
    @DeleteMapping("{idProduct}")
    public void delete(@PathVariable Integer idProduct) {productService.delete(idProduct);
    }
}
