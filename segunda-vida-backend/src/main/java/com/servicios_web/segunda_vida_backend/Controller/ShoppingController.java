package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.ContactInfo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("shoppings")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Shopping", description = "methods and documentation of purchases")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    //devuelve una lista de compras
    @Operation(summary = "Get all shoppings")
    @ApiResponse(responseCode = "200", description = "Found shoppings", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))) })
    @GetMapping
    public List<Shopping> getAll() {
        return shoppingService.getAll();
    }

    @Operation(summary = "Get all shoppings with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Shopping> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Shopping> shoppings = shoppingService.getAll(page, pageSize);
        return shoppings;
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
    @Operation(summary = "Get a shopping by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shopping  found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Shopping.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid shopping  id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Shopping not found", content = @Content) })
    @GetMapping("{idShopping}")
    public ResponseEntity<Shopping> getByID_Shopping(@PathVariable Integer idShopping) {
        Shopping shopping = shoppingService.getByIdShopping(idShopping);
        return new ResponseEntity<Shopping>(shopping, HttpStatus.OK);
    }

    //Actualizar ventas
    @Operation(summary = "Update a shopping")
    @ApiResponse(responseCode = "200", description = "Updated shopping", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))) })
    @PutMapping("{idShopping}")
    public ResponseEntity<?> update(@RequestBody Shopping shopping, @PathVariable Integer idShopping) {
        try {
            Shopping auxShopping = shoppingService.getByIdShopping(idShopping);
            shopping.setIdShopping(auxShopping.getIdShopping());
            shoppingService.save(shopping);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    //Eliminar venta
    @Operation(summary = "Delete a shopping")
    @ApiResponse(responseCode = "204", description = "Deleted shopping", content = @Content)
    @DeleteMapping("{idShopping}")
    public void delete(@PathVariable Integer idShopping) {
        shoppingService.delete(idShopping);
    }

    //Encontrar todas las compras realizadas por un usuario específico
    @Operation(summary = "Get all shoppings by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found shoppings for the user", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))) }),
            @ApiResponse(responseCode = "404", description = "User not found or no purchases made", content = @Content) })
    @GetMapping("/user/{idUser}")
    public List<Shopping> findAllByBuyerId(@PathVariable Integer idUser) {
        return shoppingService.findAllByBuyerId(idUser);
    }

    @Operation(summary = "Get contact information for the seller of a shopping item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact information found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ContactInfo.class)) }),
            @ApiResponse(responseCode = "404", description = "Shopping not found or contact information unavailable", content = @Content)
    })
    @GetMapping("/{idShopping}/contact-info")
    public ResponseEntity<ContactInfo> getUserContactInfo(@PathVariable int idShopping) {
        ContactInfo contactInfo = shoppingService.getUserContactInfo(idShopping);
        return ResponseEntity.ok(contactInfo);
    }
}
