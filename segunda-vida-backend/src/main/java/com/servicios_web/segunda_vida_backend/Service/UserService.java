package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.User;
import com.servicios_web.segunda_vida_backend.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public void save (User user) {
        userRepo.save(user);
    }

    public User getByID_Usuario(Integer id_usuario){
        return userRepo.findById(id_usuario).get();
    }
    public void delete(Integer id_usuario){
        userRepo.deleteById(id_usuario);
    }
}
