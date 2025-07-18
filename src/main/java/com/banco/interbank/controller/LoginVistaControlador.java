package com.banco.interbank.controller;

import com.banco.interbank.dto.LoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginVistaControlador {

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            // Si ya está logueado redirige según rol (ejemplo simple)
            LoginResponse usuario = (LoginResponse) session.getAttribute("usuario");
            if (usuario.getRoles().contains("ROLE_OPERADOR")) {
                return "redirect:/adminOperador";
            } else if (usuario.getRoles().contains("ROLE_ANALISTA")) {
                return "redirect:/adminAnalista";
            }
        }
        return "login"; // mostrar login si no hay sesión
    }

}
