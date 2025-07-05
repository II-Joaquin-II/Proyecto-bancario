package com.banco.interbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginVistaControlador {

    @GetMapping("/login")
    public String cargarLogin() {
        return "login";
    }

}
