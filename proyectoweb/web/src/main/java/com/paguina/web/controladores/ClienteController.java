package com.paguina.web.controladores;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paguina.web.modelos.Cliente;
import com.paguina.web.modelos.dto.ClienteLoginDTO;
import com.paguina.web.modelos.dto.ClienteRegistroDTO;
import com.paguina.web.servicios.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody ClienteRegistroDTO dto) {
        try {
            Cliente cliente = clienteService.registrar(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("idCliente", cliente.getIdCliente());
            response.put("nombre", cliente.getNombreCliente());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClienteLoginDTO dto) {
        Optional<Cliente> clienteOpt = clienteService.login(dto);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            Map<String, Object> response = new HashMap<>();
           response.put("idCliente", cliente.getIdCliente());
            response.put("dniCliente", cliente.getDniCliente()); // Añadir dniCliente
            response.put("nombre", cliente.getNombreCliente());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> getClienteByDni(@PathVariable String dni) {
        System.out.println("Buscando cliente con DNI: " + dni);
        Optional<Cliente> clienteOpt = clienteService.findByDni(dni);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            Map<String, Object> response = new HashMap<>();
            response.put("nombreCliente", cliente.getNombreCliente());
            response.put("apellidoCliente", cliente.getApellidoCliente());
            response.put("correoCliente", cliente.getCorreoCliente());
            response.put("direccionCliente", cliente.getDireccionCliente());
            response.put("contactoCliente", cliente.getContactoCliente());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body("Cliente no encontrado");
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> updateCliente(@PathVariable String dni, @RequestBody Map<String, String> datos) {
        System.out.println("Actualizando cliente con DNI: " + dni);
        try {
            Cliente cliente = clienteService.updateCliente(dni, datos);
            Map<String, Object> response = new HashMap<>();
            response.put("nombreCliente", cliente.getNombreCliente());
            response.put("apellidoCliente", cliente.getApellidoCliente());
            response.put("correoCliente", cliente.getCorreoCliente());
            response.put("direccionCliente", cliente.getDireccionCliente());
            response.put("contactoCliente", cliente.getContactoCliente());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error interno: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno al actualizar cliente");
        }
    }
}