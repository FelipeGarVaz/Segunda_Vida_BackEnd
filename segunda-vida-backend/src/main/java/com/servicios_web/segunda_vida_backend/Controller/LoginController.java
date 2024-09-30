package com.servicios_web.segunda_vida_backend.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        // Lógica de autenticación
        boolean isAuthenticated = authenticateUser(username, password);

        if (isAuthenticated) {
            return "redirect:/home"; // Redirige a la página principal si es exitoso
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login"; // Devuelve a la página de login si hay error
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Aquí iría la lógica para verificar el usuario y contraseña
        return username.equals("admin") && password.equals("admin"); // Ejemplo de validación
    }
}