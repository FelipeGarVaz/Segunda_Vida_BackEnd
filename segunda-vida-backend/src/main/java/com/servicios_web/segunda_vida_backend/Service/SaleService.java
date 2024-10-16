package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Sale;
import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepo;

    public List<Sale> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Sale> sales = saleRepo.findAll(pageReq);
        return sales.getContent();
    }

    public List<Sale> getAll() {
        return saleRepo.findAll();
    }

    public void save (Sale sale) {
        saleRepo.save(sale);
    }

    public Sale getByID_Sale(Integer id_sale){
        return saleRepo.findById(id_sale).get();
    }
    public void delete(Integer id_sale){
        saleRepo.deleteById(id_sale);
    }
    public List<Sale> findAllBySellerId(int userId) {
        return saleRepo.findAllBySellerIdJPQL(userId);
    }
}
