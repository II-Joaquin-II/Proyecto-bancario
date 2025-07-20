package com.banco.interbank.controller;

import com.banco.interbank.model.Usuario;
import com.banco.interbank.service.ClienteServicio;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> obtenerClientePorDni(@PathVariable String dni) {
        Usuario cliente = clienteServicio.findByDni(dni);
        if (cliente == null) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/actualizar-saldo")
    public ResponseEntity<?> actualizarSaldo(@RequestBody Map<String, Object> payload) {
        try {
            Long idUsuario = Long.valueOf(payload.get("idUsuario").toString());
            BigDecimal nuevoSaldo = new BigDecimal(payload.get("nuevoSaldoDisponible").toString());

            Usuario cliente = clienteServicio.findById(idUsuario);
            if (cliente == null) {
                return ResponseEntity.status(404).body("Cliente no encontrado");
            }

            cliente.setSaldo_disponible(nuevoSaldo);
            clienteServicio.save(cliente);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error en la solicitud");
        }
    }
}
