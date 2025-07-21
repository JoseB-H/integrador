package com.paguina.web.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paguina.web.modelos.Cliente;
import com.paguina.web.modelos.DetallePedido;
import com.paguina.web.modelos.Pedido;
import com.paguina.web.modelos.Productos;
import com.paguina.web.modelos.dto.DetallePedidoDTO;
import com.paguina.web.modelos.dto.PedidoRequestDTO;
import com.paguina.web.repositorios.ClienteRepository;
import com.paguina.web.repositorios.DetallePedidoRepository;
import com.paguina.web.repositorios.PedidoRepository;
import com.paguina.web.repositorios.ProductosRepository;
@Service
public class PedidoService {
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private DetallePedidoRepository detallePedidoRepo;
    @Autowired private ProductosRepository productosRepo;

    @Transactional
    public void registrarPedido(PedidoRequestDTO dto) {
        if (dto.getIdCliente() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        Cliente cliente = clienteRepo.findById(dto.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + dto.getIdCliente()));
        if (dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La lista de detalles no puede estar vacía");
        }
        Pedido pedido = pedidoRepo.findByDniClienteAndEstado(cliente.getDniCliente(), "pendiente")
                .orElseGet(() -> {
                    Pedido nuevo = new Pedido();
                    nuevo.setDniCliente(cliente.getDniCliente());
                    nuevo.setFechaPedido(LocalDateTime.now());
                    nuevo.setEstado("pendiente");
                    nuevo.setTotal(0.0);
                    return nuevo;
                });
        if (pedido.getDetalles() == null) {
            pedido.setDetalles(new ArrayList<>());
        }
        for (DetallePedidoDTO detalleDTO : dto.getDetalles()) {
            if (detalleDTO.getIdProducto() == null || detalleDTO.getCantidad() == null) {
                throw new IllegalArgumentException("ID de producto o cantidad no pueden ser nulos");
            }
            Productos producto = productosRepo.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + detalleDTO.getIdProducto()));
            if (detalleDTO.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
            }
            if (detalleDTO.getCantidad() > producto.getStock()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombreProducto());
            }
            DetallePedido detalleExistente = null;
            for (DetallePedido d : pedido.getDetalles()) {
                if (d.getProducto().getIdProducto().equals(producto.getIdProducto())) {
                    detalleExistente = d;
                    break;
                }
            }
            if (detalleExistente != null) {
                int nuevaCantidad = detalleExistente.getCantidad() + detalleDTO.getCantidad();
                detalleExistente.setCantidad(nuevaCantidad);
                detalleExistente.setSubtotal(nuevaCantidad * detalleExistente.getPrecioUnitario());
            } else {
                DetallePedido nuevoDetalle = new DetallePedido();
                nuevoDetalle.setProducto(producto);
                nuevoDetalle.setCantidad(detalleDTO.getCantidad());
                nuevoDetalle.setPrecioUnitario(producto.getPrecio());
                nuevoDetalle.setSubtotal(detalleDTO.getCantidad() * producto.getPrecio());
                nuevoDetalle.setPedido(pedido);
                pedido.getDetalles().add(nuevoDetalle);
            }
            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productosRepo.save(producto);
        }
        double total = pedido.getDetalles().stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
        pedido.setTotal(total);
        pedidoRepo.save(pedido);
    }
}