package com.paguina.web.controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paguina.web.modelos.Productos;
import com.paguina.web.modelos.Proveedores;
import com.paguina.web.modelos.dto.ProductoDTO;
import com.paguina.web.modelos.dto.ProductoDetalleDTO;
import com.paguina.web.repositorios.ProductosRepository;
import com.paguina.web.repositorios.ProveedoresRepository;
import com.paguina.web.servicios.ProductoService;

@Controller
public class ProductoController {
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private ProveedoresRepository proveedoresRepository;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String verProductos(Model model) {
          try {
        List<ProductoDTO> productos = productoService.obtenerTodos();
        model.addAttribute("productos", productos);
        return "index";
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}

    @GetMapping("/producto/detalle/{id}")
    @ResponseBody
    public ProductoDetalleDTO obtenerProductoPorId(@PathVariable("id") Integer idProducto) {
        return productoService.obtenerDetallePorId(idProducto);
    }

    @PostMapping("/api/productos/registrar")
@ResponseBody
public ResponseEntity<?> registrarProducto(
        @RequestParam("id_proveedor") Integer idProveedor,
        @RequestParam("nombre") String nombre,
        @RequestParam("imagen") MultipartFile imagen,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("precio") Double precio,
        @RequestParam("stock") Integer stock) {
    try {
        // 1. Crear la carpeta si no existe
        String nombreArchivo = imagen.getOriginalFilename();
        Path carpeta = Paths.get("web/src/main/resources/static/img/producto/");
        if (!Files.exists(carpeta)) {
            Files.createDirectories(carpeta);
        }
        Path ruta = carpeta.resolve(nombreArchivo);

        // 2. Guardar la imagen (sobrescribe si ya existe)
        Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

        // 3. Buscar proveedor
        Proveedores proveedor = proveedoresRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return ResponseEntity.badRequest().body("Proveedor no encontrado");
        }

        // 4. Crear y guardar el producto
        Productos producto = new Productos();
        producto.setProveedor(proveedor);
        producto.setNombreProducto(nombre);
        producto.setImagenProducto(nombreArchivo); // Solo el nombre
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        productosRepository.save(producto);

        return ResponseEntity.ok("Producto registrado correctamente");
    } catch (IOException e) {
        return ResponseEntity.status(500).body("Error al registrar producto: " + e.getMessage());
    }
}
}