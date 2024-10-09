package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Service.ShoppingService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("shopping")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;
    
    //Endpoint que maneja solicitudes HHTP GET y devuelve una lista de productos
    @GetMapping
    public List<Shopping> getAll() {
        return shoppingService.getAll();
    }

    //Crear compra
    @PostMapping
    public void createShopping (@RequestBody Shopping shopping) {
        shoppingService.save(shopping);
    }

    //Buscar pod ID
    @GetMapping("{id_shopping}")
    public ResponseEntity<Shopping> getByID_Shopping(@PathVariable Integer id_shopping) {
        Shopping shopping = shoppingService.getByID_Shopping(id_shopping);
        return new ResponseEntity<Shopping>(shopping, HttpStatus.OK);
    }

    //Actualizar ventas
    @PutMapping("{id_shopping}")
    public ResponseEntity<?> update(@RequestBody Shopping shopping, @PathVariable Integer id_shopping) {
        try {
            Shopping auxShopping = shoppingService.getByID_Shopping(id_shopping);
            shopping.setId_shopping(auxShopping.getId_shopping());
            shoppingService.save(shopping);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    //Eliminar venta
    @DeleteMapping("{id_shopping}")
    public void delete(@PathVariable Integer id_shopping) {
        shoppingService.delete(id_shopping);
    }
}
