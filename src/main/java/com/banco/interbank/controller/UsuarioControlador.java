package com.banco.interbank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banco.interbank.dto.LoginRequest;
import com.banco.interbank.dto.LoginResponse;
import com.banco.interbank.service.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        Optional<LoginResponse> loginResponse = usuarioServicio.login(request);
        if (loginResponse.isPresent()) {
            // Guardar usuario en sesión
            session.setAttribute("usuario", loginResponse.get());
            return ResponseEntity.ok(loginResponse.get());
        } else {
            return ResponseEntity
                .status(401)
                .body("Datos incorrectos");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
    
    
    @GetMapping("/session-status")
public ResponseEntity<?> sessionStatus(HttpSession session) {
    if (session.getAttribute("usuario") != null) {
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.status(401).build();
    }
}
}
