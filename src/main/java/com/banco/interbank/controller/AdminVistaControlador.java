package com.banco.interbank.controller;

import com.banco.interbank.dto.LoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminVistaControlador {

    @GetMapping("/adminOperador")
    public String adminOperador(HttpSession session) {
        LoginResponse usuario = (LoginResponse) session.getAttribute("usuario");
        if (usuario == null || !usuario.getRoles().contains("ROLE_OPERADOR")) {
            return "redirect:/login"; // no autorizado o no logueado
        }
        return "adminOperador"; // la vista Thymeleaf
    }

    @GetMapping("/adminAnalista")
    public String adminAnalista(HttpSession session) {
        LoginResponse usuario = (LoginResponse) session.getAttribute("usuario");
        if (usuario == null || !usuario.getRoles().contains("ROLE_ANALISTA")) {
            return "redirect:/login";
        }
        return "adminAnalista";
    }

}
