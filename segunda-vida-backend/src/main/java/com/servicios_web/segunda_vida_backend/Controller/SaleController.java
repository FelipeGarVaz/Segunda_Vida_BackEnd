package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Sale;
import com.servicios_web.segunda_vida_backend.Service.SaleService;
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
@RequestMapping("sales")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Sale", description = "Methods and documentation for sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // Obtener todas las ventas
    @Operation(summary = "Get all sales")
    @ApiResponse(responseCode = "200", description = "Found sales", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) })
    @GetMapping
    public List<Sale> getAll() {
        return saleService.getAll();
    }

    // Crear una nueva venta
    @Operation(summary = "Register a sale")
    @ApiResponse(responseCode = "201", description = "Sale registered", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) })
    @PostMapping
    public void createSale(@RequestBody Sale sale) {
        saleService.save(sale);
    }

    // Buscar venta por ID
    @Operation(summary = "Get a sale by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid sale id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content) })
    @GetMapping("{id_sale}")
    public ResponseEntity<Sale> getByID_Sale(@PathVariable Integer id_sale) {
        try {
            Sale sale = saleService.getByID_Sale(id_sale);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar una venta
    @Operation(summary = "Update a sale")
    @ApiResponse(responseCode = "200", description = "Sale updated", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) })
    @PutMapping("{id_sale}")
    public ResponseEntity<String> update(@RequestBody Sale sale, @PathVariable Integer id_sale) {
        try {
            Sale auxSale = saleService.getByID_Sale(id_sale);
            sale.setId_sale(auxSale.getId_sale());
            saleService.save(sale);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the provided id is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una venta
    @Operation(summary = "Delete a sale")
    @ApiResponse(responseCode = "204", description = "Sale deleted")
    @DeleteMapping("{id_sale}")
    public void delete(@PathVariable Integer id_sale) {
        saleService.delete(id_sale);
    }
}