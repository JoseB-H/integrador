package com.paguina.web.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paguina.web.modelos.Proveedores;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {
    Optional<Proveedores> findByCorreoProveedorAndContraseñaProveedor(String correo, String contraseña);

    Optional<Proveedores> findByCorreoProveedor(String correoProveedor);

    Optional<Proveedores> findByDniProveedor(String dniProveedor);

    boolean existsByCorreoProveedor(String correoProveedor);

    boolean existsByDniProveedor(String dniProveedor);
}
