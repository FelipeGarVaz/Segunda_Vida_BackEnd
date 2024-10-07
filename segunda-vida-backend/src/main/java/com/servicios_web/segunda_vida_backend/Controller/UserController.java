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
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id_user}")
    public ResponseEntity<User> getByID_Usuario(@PathVariable Integer id_user) {
        User user = userService.getByID_User(id_user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping
    public void createUser (@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("{id_user}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id_user) {
        try {
            User auxUser = userService.getByID_User(id_user);
            user.setId_user(auxUser.getId_user());
            userService.save(user);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id_user}")
    public void delete(@PathVariable Integer id_user) {
        userService.delete(id_user);
    }
}
