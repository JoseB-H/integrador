package com.paguina.web.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paguina.web.modelos.Productos;
import com.paguina.web.modelos.dto.ProductoDTO;
import com.paguina.web.modelos.dto.ProductoDetalleDTO;
import com.paguina.web.repositorios.ProductosRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductosRepository productosRepository;

    public List<ProductoDTO> obtenerTodos() {
        List<Productos> productos = productosRepository.findAll();
        return productos.stream().map(p -> new ProductoDTO(
            p.getIdProducto(),
            p.getNombreProducto(),
            p.getImagenProducto(),
            p.getPrecio(),
            p.getStock(),
            p.getProveedor().getNombreProveedor()
                )).collect(Collectors.toList());
    }
@GetMapping("/producto/detalle/{id}")
@ResponseBody
    public ProductoDetalleDTO obtenerDetallePorId(Integer idProducto) {
        return productosRepository.findById(idProducto).map(p -> {
            ProductoDetalleDTO dto = new ProductoDetalleDTO();
            dto.setNombreProducto(p.getNombreProducto());
            dto.setImagenProducto(p.getImagenProducto());
            dto.setPrecio(p.getPrecio());
            dto.setStock(p.getStock());
            dto.setDescripcion(p.getDescripcion());
            dto.setNombreProveedor(p.getProveedor().getNombreProveedor());
            return dto;
        }).orElse(null);
    }

}
