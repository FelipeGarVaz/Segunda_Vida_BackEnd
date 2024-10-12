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
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<User> users = userRepository.findAll(pageReq);
        return users.getContent();
    }

    public void save (User user) {
        userRepository.save(user);
    }

    public User getByID_User(Integer idUser){
        return userRepository.findById(idUser).get();
    }
    public void delete(Integer idUser){
        userRepository.deleteById(idUser);
    }
}
