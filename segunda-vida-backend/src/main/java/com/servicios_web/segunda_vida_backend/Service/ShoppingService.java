package com.servicios_web.segunda_vida_backend.Service;

import java.util.List;
import java.util.Optional;

import com.servicios_web.segunda_vida_backend.Model.ContactInfo;
import com.servicios_web.segunda_vida_backend.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Repository.ShoppingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingService {
    @Autowired
    private ShoppingRepository shoppingRepository;

    public List<Shopping> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Shopping> fabrics = shoppingRepository.findAll(pageReq);
        return fabrics.getContent();
    }

    public List<Shopping> getAll() {
        return shoppingRepository.findAll();
    }

    public void save (Shopping shopping) {
        shoppingRepository.save(shopping);
    }

    public Shopping getByIdShopping(Integer idShopping){
        return shoppingRepository.findById(idShopping).get();
    }

    public void delete(Integer idShopping){
        shoppingRepository.deleteById(idShopping);
    }

    public List<Shopping> findAllByBuyerId(int idUser) {
        return shoppingRepository.findAllByBuyerIdJPQL(idUser);
    }

    public ContactInfo getUserContactInfo(int idShopping) {
        Optional<Shopping> shoppingOpt = shoppingRepository.findById(idShopping);
        if (shoppingOpt.isPresent()) {
            Shopping shopping = shoppingOpt.get();
            User seller = shopping.getProduct().getUser();
            if (seller != null) {
                return new ContactInfo("Puede contactar al vendedor para más detalles con el siguiente número:", seller.getPhone());
            }
        }
        return new ContactInfo("Información del vendedor no disponible.", null);
    }
}

