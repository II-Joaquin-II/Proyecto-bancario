package com.banco.interbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminVistaControlador {

    @GetMapping("/adminOperador")
    public String cargarAdminOperador() {
        return "adminOperador";
    }

    @GetMapping("/adminAnalista")
    public String cargarAdminAnalista() {
        return "adminAnalista";
    }

}
