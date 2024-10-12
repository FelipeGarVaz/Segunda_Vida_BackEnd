package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Category;
import com.servicios_web.segunda_vida_backend.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //Listar todas las categorias
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    //Guardar una categoria
    public void save (Category category) {
        categoryRepository.save(category);
    }

    //Obtener una categoria por su id
    public Category getByIDCategory(Integer idCategory) {
        return categoryRepository.findById(idCategory).get();
    }

    //Eliminar una categoria por su id
    public void delete(Integer idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}
