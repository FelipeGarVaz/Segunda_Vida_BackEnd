package com.servicios_web.segunda_vida_backend.Controller;
import com.servicios_web.segunda_vida_backend.Model.User;
import com.servicios_web.segunda_vida_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(originPatterns = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id_usuario}")
    public ResponseEntity<User> getByID_Usuario(@PathVariable Integer id_usuario) {
        User user = userService.getByID_Usuario(id_usuario);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping
    public void createUser (@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("{id_usuario}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id_usuario) {
        try {
            User auxUser = userService.getByID_Usuario(id_usuario);
            user.setId_usuario(auxUser.getId_usuario());
            userService.save(user);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id_usuario}")
    public void delete(@PathVariable Integer id_usuario) {
        userService.delete(id_usuario);
    }
}
