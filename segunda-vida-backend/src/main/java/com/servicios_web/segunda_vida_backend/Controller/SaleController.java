package com.servicios_web.segunda_vida_backend.Controller;
import com.servicios_web.segunda_vida_backend.Model.Sale;
import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("sales")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Sale", description = "Methods and documentation for sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    //paginación
    @Operation(summary = "Get all sales with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Sale> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Sale> sales = saleService.getAll(page, pageSize);
        return sales;
    }

    // Obtener todas las ventas
   /* @Operation(summary = "Get all sales")
    @ApiResponse(responseCode = "200", description = "Found sales", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) })
    @GetMapping
    public List<Sale> getAll() {
        return saleService.getAll();
    }   */

    // Crear una nueva venta
    @Operation(summary = "Register a sale")
    @ApiResponse(responseCode = "201", description = "Sale registered", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) })
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        // Asigna la fecha actual si saleDate es nulo
        if (sale.getSaleDate() == null) {
            sale.setSaleDate(LocalDateTime.now());
        }
        saleService.save(sale);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    // Buscar venta por ID
    @Operation(summary = "Get a sale by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid sale id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content) })
    @GetMapping("{idSale}")
    public ResponseEntity<Sale> getByID_Sale(@PathVariable Integer idSale) {
        Sale sale = saleService.getByID_Sale(idSale);
        if (sale != null) {
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar una venta
    @Operation(summary = "Update a sale")
    @ApiResponse(responseCode = "200", description = "Sale updated", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) })
    @PutMapping("{idSale}")
    public ResponseEntity<String> update(@RequestBody Sale sale, @PathVariable Integer idSale) {
        try {
            Sale auxSale = saleService.getByID_Sale(idSale);
            sale.setIdSale(auxSale.getIdSale());
            // Reuse the existing sale date to prevent it from being updated
            sale.setSaleDate(auxSale.getSaleDate());
            saleService.save(sale);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the provided id is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a sale")
    @ApiResponse(responseCode = "204", description = "Sale deleted")
    @DeleteMapping("{idSale}")
    public ResponseEntity<Void> delete(@PathVariable Integer idSale) {
        try {
            saleService.delete(idSale);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Encontrar todas las compras realizadas por un usuario específico
    @Operation(summary = "Get all sales by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found sales for the user", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) }),
            @ApiResponse(responseCode = "404", description = "User not found or no purchases made", content = @Content) })
    @GetMapping("/user/{userId}")
    public List<Sale> findAllBySellerId(@PathVariable Integer userId) {
        return saleService.findAllBySellerId(userId);
    }

    //Encontrar una venta realizada por fecha en formato ejemplo (2024-12-31)
    @Operation(summary = "Get a sale by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found sales for the date", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) }),
            @ApiResponse(responseCode = "404", description = "No sales found for the date", content = @Content) })
    @GetMapping("/date/{date}")
    public List<Sale> findByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return saleService.findByDate(date);
    }

    //Encontrar una venta realizada por fecha en formato ejemplo (2024-12-31T12:07:30)
    @Operation(summary = "Get a sale by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found sales for the date", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) }),
            @ApiResponse(responseCode = "404", description = "No sales found for the date", content = @Content) })
    @GetMapping("/datecomplete/{date}")
    public List<Sale> findByDateComplete(@PathVariable LocalDateTime date) {
        return saleService.findByDateComplete(date);
    }

    //Encontrar una venta por un producto específico (Id)
    @Operation(summary = "Get a sale by product ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found sales for the product", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sale.class))) }),
            @ApiResponse(responseCode = "404", description = "No sales found for the product", content = @Content) })
    @GetMapping("/product/{productId}")
    public List<Sale> findByProduct(@PathVariable Integer productId) {
        return saleService.findByProduct(productId);
    }
}