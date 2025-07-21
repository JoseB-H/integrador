package com.paguina.web.controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.paguina.web.modelos.Proveedores;
import com.paguina.web.modelos.dto.ProveedorLoginDTO;
import com.paguina.web.servicios.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ProveedorLoginDTO dto) {
        return proveedorService.login(dto.getCorreo(), dto.getContraseña())
                .<ResponseEntity<?>>map(proveedor -> ResponseEntity.ok(proveedor))
                .orElse(ResponseEntity.status(401).body("Credenciales incorrectas"));
    }

    // Endpoint para registro
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(
            @RequestParam("dniProveedor") String dniProveedor,
            @RequestParam("nombreProveedor") String nombreProveedor,
            @RequestParam("apellidoProveedor") String apellidoProveedor,
            @RequestParam("correoProveedor") String correoProveedor,
            @RequestParam("contraseñaProveedor") String contraseñaProveedor,
            @RequestParam("direccionProveedor") String direccionProveedor,
            @RequestParam("contactoProveedor") String contactoProveedor,
            @RequestParam("imagen") MultipartFile imagen) {
        try {
            // 1. Guardar la imagen en la carpeta
            String nombreArchivo = imagen.getOriginalFilename();
            Path carpeta = Paths.get("web/src/main/resources/static/img/perfil_proveedor/");
            if (!Files.exists(carpeta)) {
                Files.createDirectories(carpeta);
            }
            Path ruta = carpeta.resolve(nombreArchivo);
            Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            // 2. Crear el proveedor y guardar el nombre de la imagen
            Proveedores proveedor = new Proveedores();
            proveedor.setDniProveedor(dniProveedor);
            proveedor.setNombreProveedor(nombreProveedor);
            proveedor.setApellidoProveedor(apellidoProveedor);
            proveedor.setCorreoProveedor(correoProveedor);
            proveedor.setContraseñaProveedor(contraseñaProveedor);
            proveedor.setDireccionProveedor(direccionProveedor);
            proveedor.setContactoProveedor(contactoProveedor);
            proveedor.setImgProveedor(nombreArchivo); // Guarda solo el nombre
            Proveedores nuevo = proveedorService.registrar(proveedor);
            return ResponseEntity.ok("¡registro exitoso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al registrar proveedor: " + e.getMessage());
        }
    }

}