package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Product;
import com.servicios_web.segunda_vida_backend.Model.Sale;
import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Repository.ProductRepository;
import com.servicios_web.segunda_vida_backend.Repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepo;
    private ProductRepository productRepository;

    // Inyección de dependencias a través del constructor
    @Autowired
    public SaleService(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepo = saleRepository;
    }

    public List<Sale> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Sale> sales = saleRepo.findAll(pageReq);
        return sales.getContent();
    }

    public List<Sale> getAll() {
        return saleRepo.findAll();
    }

    public void save (Sale sale) {
        Product product = productRepository.findById(sale.getIdProduct())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStatus() == Product.ProductStatus.Vendido) {
            throw new RuntimeException("The product is already sold");
        }

        product.setStatus(Product.ProductStatus.Vendido);
        productRepository.save(product);
        saleRepo.save(sale);
    }

    public Sale getByIdSale(Integer idSale){
        return saleRepo.findById(idSale).get();
    }
    public void delete(Integer idSale){
        Sale sale = saleRepo.findById(idSale)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        Product product = productRepository.findById(sale.getIdProduct())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus(Product.ProductStatus.Disponible);
        productRepository.save(product);
        saleRepo.deleteById(idSale);
    }

    // Encontrar todas las ventas realizadas por un usuario específico
    public List<Sale> findAllBySellerId(int idUser) {
        return saleRepo.findAllBySellerIdJPQL(idUser);
    }

    // Encontrar una venta realizada por fecha en formato ejemplo (2024-12-31)
    public List<Sale> findByDate(LocalDate date) {
        return saleRepo.findByDateJPQL(date);
    }

    // Encontrar una venta realizada por fecha en formato ejemplo (2024-12-31T12:07:30)
    public List<Sale> findByDateComplete(LocalDateTime date) {
        return saleRepo.findByDateCompleteJPQL(date);
    }

    // Encontrar una venta por un producto específico
    public List<Sale> findByProduct(int idProduct) {
        return saleRepo.findByProductJPQL(idProduct);
    }
}
