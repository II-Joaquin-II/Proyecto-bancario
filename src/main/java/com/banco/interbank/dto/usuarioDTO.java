package com.banco.interbank.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.banco.interbank.model.Usuario;

public class usuarioDTO {

    private long id_usuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private BigDecimal saldo_disponible;
    private BigDecimal saldo_contable;
    private List<String> roles;

    public usuarioDTO(Usuario usuarioEntidad) {
        this.id_usuario = usuarioEntidad.getId_usuario();
        this.nombre = usuarioEntidad.getNombre();
        this.apellidos = usuarioEntidad.getApellidos();
        this.dni = usuarioEntidad.getDni();
        this.email = usuarioEntidad.getEmail();
        this.saldo_contable = usuarioEntidad.getSaldo_contable();
        this.saldo_disponible = usuarioEntidad.getSaldo_disponible();
        this.roles = usuarioEntidad.getRoles().stream().map(rol -> rol.getNombre()).collect(Collectors.toList());
    }

}
