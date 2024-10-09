package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Category;
import com.servicios_web.segunda_vida_backend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@CrossOrigin(originPatterns = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //Endpoint que maneja solicitudes HHTP GET y devuelve una lista de categorias
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    //Crear categoria
    @PostMapping
    public void createCategory (@RequestBody Category category) {
        categoryService.save(category);
    }

    //Buscar por ID
    @GetMapping("{id_category}")
    public Category getByID_Category(@PathVariable Integer id_category) {
        return categoryService.getByID_Category(id_category);
    }

    //Actualizar categoria
    @PutMapping("{id_category}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Integer id_category) {
        try {
            Category auxCategory = categoryService.getByID_Category(id_category);
            category.setId_category(auxCategory.getId_category());
            categoryService.save(category);
            return ResponseEntity.ok().body("Updated record");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar categoria
    @DeleteMapping("{id_category}")
    public ResponseEntity<?> delete(@PathVariable Integer id_category) {
        try {
            categoryService.delete(id_category);
            return ResponseEntity.ok().body("Record deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
