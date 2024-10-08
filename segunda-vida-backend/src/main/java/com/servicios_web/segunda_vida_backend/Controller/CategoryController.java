package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Category;
import com.servicios_web.segunda_vida_backend.Service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("category")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Category", description = "Methods and documentation for product categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Obtener todas las categorías
    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Found categories", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))) })
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    // Crear una nueva categoría
    @Operation(summary = "Register a category")
    @ApiResponse(responseCode = "201", description = "Category registered", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) })
    @PostMapping
    public void createCategory(@RequestBody Category category) {
        categoryService.save(category);
    }

    // Buscar categoría por ID
    @Operation(summary = "Get a category by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid category id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content) })
    @GetMapping("{id_category}")
    public ResponseEntity<Category> getByID_Category(@PathVariable Integer id_category) {
        try {
            Category category = categoryService.getByID_Category(id_category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar categoría
    @Operation(summary = "Update a category")
    @ApiResponse(responseCode = "200", description = "Category updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) })
    @PutMapping("{id_category}")
    public ResponseEntity<String> update(@RequestBody Category category, @PathVariable Integer id_category) {
        try {
            Category auxCategory = categoryService.getByID_Category(id_category);
            category.setId_category(auxCategory.getId_category());
            categoryService.save(category);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the provided id is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar categoría
    @Operation(summary = "Delete a category")
    @ApiResponse(responseCode = "204", description = "Category deleted")
    @DeleteMapping("{id_category}")
    public ResponseEntity<String> delete(@PathVariable Integer id_category) {
        try {
            categoryService.delete(id_category);
            return new ResponseEntity<>("Record deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}