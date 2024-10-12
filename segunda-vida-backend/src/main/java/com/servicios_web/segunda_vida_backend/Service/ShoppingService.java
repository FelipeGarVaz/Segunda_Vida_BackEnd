package com.servicios_web.segunda_vida_backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Repository.ShoppingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingService {
    @Autowired
    private ShoppingRepository shoppingRepo;

    public List<Shopping> getAll() {
        return shoppingRepo.findAll();
    }

    public void save (Shopping shopping) {
        shoppingRepo.save(shopping);
    }

    public Shopping getByIdShopping(Integer idShopping){
        return shoppingRepo.findById(idShopping).get();
    }

    public void delete(Integer idShopping){
        shoppingRepo.deleteById(idShopping);
    }
}

