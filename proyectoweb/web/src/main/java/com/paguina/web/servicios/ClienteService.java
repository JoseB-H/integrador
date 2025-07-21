package com.paguina.web.servicios;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.paguina.web.modelos.Cliente;
import com.paguina.web.modelos.dto.ClienteLoginDTO;
import com.paguina.web.modelos.dto.ClienteRegistroDTO;
import com.paguina.web.repositorios.ClienteRepository;

@Service
public class ClienteService {
 @Autowired
    private ClienteRepository clienteRepository;

    public Cliente registrar(ClienteRegistroDTO dto) {
        if (clienteRepository.findByDniCliente(dto.getDniCliente()).isPresent()) {
            throw new IllegalArgumentException("DNI ya registrado");
        }
        if (clienteRepository.findByCorreoCliente(dto.getCorreoCliente()).isPresent()) {
            throw new IllegalArgumentException("Correo ya registrado");
        }
        Cliente cliente = new Cliente();
        cliente.setDniCliente(dto.getDniCliente());
        cliente.setNombreCliente(dto.getNombreCliente());
        cliente.setApellidoCliente(dto.getApellidoCliente());
        cliente.setCorreoCliente(dto.getCorreoCliente());
        cliente.setDireccionCliente(dto.getDireccionCliente());
        cliente.setContactoCliente(dto.getContactoCliente());
        cliente.setContraseñaCliente(BCrypt.hashpw(dto.getContraseñaCliente(), BCrypt.gensalt()));
        return clienteRepository.save(cliente);
    }

 public Optional<Cliente> login(ClienteLoginDTO dto) {
    Optional<Cliente> clienteOpt = clienteRepository.findByCorreoCliente(dto.getCorreoCliente());
    if (clienteOpt.isPresent()) {
        Cliente cliente = clienteOpt.get();
        if (BCrypt.checkpw(dto.getContraseñaCliente(), cliente.getContraseñaCliente())) {
            return Optional.of(cliente);
        }
    }
    return Optional.empty();
}
  public Optional<Cliente> findByDni(String dni) {
        return clienteRepository.findByDniCliente(dni);
    }

    public Cliente updateCliente(String dni, Map<String, String> datos) {
        Optional<Cliente> clienteOpt = clienteRepository.findByDniCliente(dni);
        if (!clienteOpt.isPresent()) {
            throw new IllegalArgumentException("Cliente no encontrado con DNI: " + dni);
        }
        Cliente cliente = clienteOpt.get();
        cliente.setNombreCliente(datos.getOrDefault("nombreCliente", cliente.getNombreCliente()));
        cliente.setApellidoCliente(datos.getOrDefault("apellidoCliente", cliente.getApellidoCliente()));
        cliente.setCorreoCliente(datos.getOrDefault("correoCliente", cliente.getCorreoCliente()));
        cliente.setDireccionCliente(datos.getOrDefault("direccionCliente", cliente.getDireccionCliente()));
        cliente.setContactoCliente(datos.getOrDefault("contactoCliente", cliente.getContactoCliente()));
        return clienteRepository.save(cliente);
    }
    public Cliente guardar(Cliente cliente) {
    return clienteRepository.save(cliente);
}
}