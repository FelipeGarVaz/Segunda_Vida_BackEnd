package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Exceptions.UsernameNotFoundException;
import com.servicios_web.segunda_vida_backend.Model.User;
import com.servicios_web.segunda_vida_backend.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User getUserByUserName(String userName) {
        // Eliminar espacios en blanco al inicio y al final del nombre de usuario
        userName = userName.trim();
        // Buscar usuario en la base de datos
        Optional<User> userOptional = userRepository.findByUserNameJPQL(userName);
        // Verificar si el usuario fue encontrado
        if (userOptional.isPresent()) {
            return userOptional.get(); // Retornar el usuario encontrado
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
    }

    public User getUserByEmail(String email) {
        // Eliminar espacios en blanco alrededor del correo electrónico
        email = email.trim();
        // Buscar usuario en la base de datos
        Optional<User> userOptional = userRepository.findByEmailJPQL(email);
        // Verificar si el usuario fue encontrado
        if (userOptional.isPresent()) {
            return userOptional.get(); // Retornar el usuario encontrado
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public User getUserByName(String name) {
        // Buscar usuario en la base de datos
        Optional<User> userOptional = userRepository.findByNameJPQL(name);
        // Verificar si el usuario fue encontrado
        if (userOptional.isPresent()) {
            return userOptional.get(); // Retornar el usuario encontrado
        } else {
            throw new UsernameNotFoundException("User not found with name: " + name);
        }
    }
}
