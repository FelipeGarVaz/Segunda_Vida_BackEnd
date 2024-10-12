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
    @GetMapping("{idCategory}")
    public ResponseEntity<Category> getByIDCategory(@PathVariable Integer idCategory) {
        try {
            Category category = categoryService.getByIDCategory(idCategory);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar categoría
    @Operation(summary = "Update a category")
    @ApiResponse(responseCode = "200", description = "Category updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) })
    @PutMapping("{idCategory}")
    public ResponseEntity<String> update(@RequestBody Category category, @PathVariable Integer idCategory) {
        try {
            Category auxCategory = categoryService.getByIDCategory(idCategory);
            category.setIdCategory(auxCategory.getIdCategory());
            categoryService.save(category);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the provided id is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar categoría
    @Operation(summary = "Delete a category")
    @ApiResponse(responseCode = "204", description = "Category deleted")
    @DeleteMapping("{idCategory}")
    public ResponseEntity<String> delete(@PathVariable Integer idCategory) {
        try {
            categoryService.delete(idCategory);
            return new ResponseEntity<>("Record deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}