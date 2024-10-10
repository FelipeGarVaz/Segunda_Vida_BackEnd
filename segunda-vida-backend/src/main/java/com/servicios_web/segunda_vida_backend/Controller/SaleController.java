package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Sale;
import com.servicios_web.segunda_vida_backend.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAll() {
        return saleService.getAll();
    }

    @PostMapping
    public void createProduct (@RequestBody Sale sale) {
        saleService.save(sale);
    }

    //Buscar por ID
    @GetMapping("{id_sale}")
    public Sale getByID_Sale(@PathVariable Integer id_sale) {
        return saleService.getByID_Sale(id_sale);
    }

    @PutMapping("{id_sale}")
    public ResponseEntity<?> Update(@RequestBody Sale sale, @PathVariable Integer id_sale) {
        try {
            Sale auxSale = saleService.getByID_Sale(id_sale);
            sale.setId_sale(auxSale.getId_sale());
            saleService.save(sale);
            return ResponseEntity.ok().body("Updated record");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id_sale}")
    public void delete(@PathVariable Integer id_sale) {
        saleService.delete(id_sale);
    }

}
