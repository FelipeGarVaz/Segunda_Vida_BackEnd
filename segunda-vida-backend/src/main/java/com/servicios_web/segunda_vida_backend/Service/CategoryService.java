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
    private CategoryRepository categoryRepo;

    //Listar todas las categorias
    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    //Guardar una categoria
    public void save (Category category) {
        categoryRepo.save(category);
    }

    //Obtener una categoria por su id
    public Category getByID_Category(Integer id_category) {
        return categoryRepo.findById(id_category).get();
    }

    //Eliminar una categoria por su id
    public void delete(Integer id_category) {
        categoryRepo.deleteById(id_category);
    }
}
