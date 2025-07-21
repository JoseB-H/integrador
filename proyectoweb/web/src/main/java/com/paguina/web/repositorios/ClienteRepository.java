package com.paguina.web.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.paguina.web.modelos.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findById(Integer idCliente);
    Optional<Cliente> findByCorreoCliente(String correo);
    Optional<Cliente> findByDniCliente(String dniCliente);
    Optional<Cliente> findByCorreoClienteAndContraseñaCliente(String correo, String contraseña);
}