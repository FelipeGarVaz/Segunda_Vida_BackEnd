package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Service.ShoppingService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("shopping")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Shopping", description = "methods and documentation of purchases")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;
    
    //Endpoint que maneja solicitudes HHTP GET y devuelve una lista de compras
    @Operation(summary = "Get all shoppings")
    @ApiResponse(responseCode = "200", description = "Found shoppings", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))) })
    @GetMapping
    public List<Shopping> getAll() {
        return shoppingService.getAll();
    }

    //Crear compra
    @Operation(summary = "Register a shopping")
    @ApiResponse(responseCode = "201", description = "Register a review", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))) })
    @PostMapping
    public void createShopping (@RequestBody Shopping shopping) {
        shoppingService.save(shopping);
    }

    //Buscar pod ID
    @Operation(summary = "Get a fabric type by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shopping  found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Shopping.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid shopping  id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Shopping not found", content = @Content) })
    @GetMapping("{id_shopping}")
    public ResponseEntity<Shopping> getByID_Shopping(@PathVariable Integer id_shopping) {
        Shopping shopping = shoppingService.getByID_Shopping(id_shopping);
        return new ResponseEntity<Shopping>(shopping, HttpStatus.OK);
    }

    //Actualizar ventas
    @Operation(summary = "Update a shopping")
    @ApiResponse(responseCode = "200", description = "Updated shopping", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))) })
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
