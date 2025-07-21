package com.paguina.web.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.paguina.web.modelos.Proveedores;
import com.paguina.web.repositorios.ProveedoresRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedoresRepository proveedoresRepository;

    public Optional<Proveedores> login(String correo, String contraseña) {
    Optional<Proveedores> proveedorOpt = proveedoresRepository.findByCorreoProveedor(correo);
    if (proveedorOpt.isPresent()) {
        Proveedores proveedor = proveedorOpt.get();
        if (BCrypt.checkpw(contraseña, proveedor.getContraseñaProveedor())) {
            return Optional.of(proveedor);
        }
    }
    return Optional.empty();
}

    public Proveedores registrar(Proveedores proveedor) {
    if (proveedoresRepository.findByCorreoProveedor(proveedor.getCorreoProveedor()).isPresent()) {
        throw new IllegalArgumentException("El correo ya está registrado");
    }
    if (proveedoresRepository.findByDniProveedor(proveedor.getDniProveedor()).isPresent()) {
        throw new IllegalArgumentException("El DNI ya está registrado");
    }
   proveedor.setContraseñaProveedor(BCrypt.hashpw(proveedor.getContraseñaProveedor(), BCrypt.gensalt()));
    return proveedoresRepository.save(proveedor);
}
}
