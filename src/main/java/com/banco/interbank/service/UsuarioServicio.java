package com.banco.interbank.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banco.interbank.dto.LoginRequest;
import com.banco.interbank.dto.LoginResponse;
import com.banco.interbank.repository.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Optional<LoginResponse> login(LoginRequest request) {
        return usuarioRepositorio.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .map(usuario -> {
                    LoginResponse response = new LoginResponse();
                    response.setNombre(usuario.getNombre() + " " + usuario.getApellidos());
                    response.setRoles(
                        usuario.getRoles()
                               .stream()
                               .map(rol -> rol.getNombre())
                               .collect(Collectors.toList())
                    );
                    return response;
                });
    }
}
