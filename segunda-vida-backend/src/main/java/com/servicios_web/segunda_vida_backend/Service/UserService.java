package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.User;
import com.servicios_web.segunda_vida_backend.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public List<User> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<User> users = userRepository.findAll(pageReq);
        return users.getContent();
    }

    public void save (User user) {
        userRepo.save(user);
    }

    public User getByID_User(Integer id_user){
        return userRepo.findById(id_user).get();
    }
    public void delete(Integer id_user){
        userRepo.deleteById(id_user);
    }
}
