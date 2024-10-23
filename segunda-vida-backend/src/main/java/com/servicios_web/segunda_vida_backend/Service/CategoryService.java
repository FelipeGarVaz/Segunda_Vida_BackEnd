package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Category;
import com.servicios_web.segunda_vida_backend.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //paginacion
    public List<Category> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page <Category> categories = categoryRepository.findAll(pageReq);
        return categories.getContent();
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

    //Buscar una categoria por su nombre
    public List<Category> findByNameJPQL(String categoryName) {
        return categoryRepository.findByNameJPQL(categoryName);
    }


}
