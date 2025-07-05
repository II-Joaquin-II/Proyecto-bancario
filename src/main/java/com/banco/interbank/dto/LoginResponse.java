package com.banco.interbank.dto;

import java.util.List;

public class LoginResponse {
    
    private String nombre;
    private List<String> roles;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
