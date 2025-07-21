package com.paguina.web.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paguina.web.modelos.DetallePedido;
import com.paguina.web.modelos.Pedido;
import com.paguina.web.modelos.dto.PedidoRequestDTO;
import com.paguina.web.repositorios.DetallePedidoRepository;
import com.paguina.web.repositorios.PedidoRepository;
import com.paguina.web.servicios.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired private PedidoService pedidoService;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private DetallePedidoRepository detallePedidoRepository;

    @PostMapping("/registrar-pedido")
    public ResponseEntity<?> registrar(@RequestBody PedidoRequestDTO dto) {
        try {
            pedidoService.registrarPedido(dto);
            return ResponseEntity.ok("Pedido registrado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno al registrar pedido: " + e.getMessage());
        }
    }

    @GetMapping("/pendientes/{dniCliente}")
    public List<Map<String, Object>> obtenerPedidosPendientes(@PathVariable String dniCliente) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findByDniClienteAndEstado(dniCliente, "pendiente");
        if (pedidoOpt.isEmpty()) return Collections.emptyList();
        Pedido pedido = pedidoOpt.get();
        List<Map<String, Object>> detalles = new ArrayList<>();
        for (DetallePedido det : pedido.getDetalles()) {
            Map<String, Object> map = new HashMap<>();
            map.put("idDetalle", det.getIdDetalle());
            map.put("idProducto", det.getProducto().getIdProducto());
            map.put("nombreProducto", det.getProducto().getNombreProducto());
            map.put("imagenProducto", det.getProducto().getImagenProducto());
            map.put("cantidad", det.getCantidad());
            map.put("precioUnitario", det.getPrecioUnitario());
            map.put("subtotal", det.getSubtotal());
            detalles.add(map);
        }
        return detalles;
    }

    @PutMapping("/detalle/{idDetalle}/cantidad")
    public ResponseEntity<?> actualizarCantidad(@PathVariable Integer idDetalle, @RequestParam Integer cantidad) {
        Optional<DetallePedido> detOpt = detallePedidoRepository.findById(idDetalle);
        if (detOpt.isEmpty()) return ResponseEntity.notFound().build();
        DetallePedido det = detOpt.get();
        if (cantidad <= 0) return ResponseEntity.badRequest().body("Cantidad inválida");
        if (cantidad > det.getProducto().getStock()) return ResponseEntity.badRequest().body("Stock insuficiente");
        det.setCantidad(cantidad);
        det.setSubtotal(det.getPrecioUnitario() * cantidad);
        detallePedidoRepository.save(det);
        Pedido pedido = det.getPedido();
        double nuevoTotal = pedido.getDetalles().stream().mapToDouble(DetallePedido::getSubtotal).sum();
        pedido.setTotal(nuevoTotal);
        pedidoRepository.save(pedido);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/detalle/{idDetalle}")
    public ResponseEntity<?> borrarDetalle(@PathVariable Integer idDetalle) {
        Optional<DetallePedido> detOpt = detallePedidoRepository.findById(idDetalle);
        if (detOpt.isEmpty()) return ResponseEntity.notFound().build();
        Pedido pedido = detOpt.get().getPedido();
        detallePedidoRepository.deleteById(idDetalle);
        double nuevoTotal = pedido.getDetalles().stream()
                .filter(d -> !d.getIdDetalle().equals(idDetalle))
                .mapToDouble(DetallePedido::getSubtotal).sum();
        pedido.setTotal(nuevoTotal);
        pedidoRepository.save(pedido);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/pagar/{dniCliente}")
    public ResponseEntity<?> pagarPedido(@PathVariable String dniCliente) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findByDniClienteAndEstado(dniCliente, "pendiente");
        if (pedidoOpt.isEmpty()) return ResponseEntity.notFound().build();
        Pedido pedido = pedidoOpt.get();
        pedido.setEstado("pagado");
        pedidoRepository.save(pedido);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ultimo-pagado/{dniCliente}")
    public List<Map<String, Object>> obtenerUltimoPedidoPagado(@PathVariable String dniCliente) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findTopByDniClienteAndEstadoOrderByFechaPedidoDesc(dniCliente, "pagado");
        if (pedidoOpt.isEmpty()) return Collections.emptyList();
        Pedido pedido = pedidoOpt.get();
        List<Map<String, Object>> detalles = new ArrayList<>();
        for (DetallePedido det : pedido.getDetalles()) {
            Map<String, Object> map = new HashMap<>();
            map.put("idDetalle", det.getIdDetalle());
            map.put("idProducto", det.getProducto().getIdProducto());
            map.put("nombreProducto", det.getProducto().getNombreProducto());
            map.put("imagenProducto", det.getProducto().getImagenProducto());
            map.put("cantidad", det.getCantidad());
            map.put("precioUnitario", det.getPrecioUnitario());
            map.put("subtotal", det.getSubtotal());
            detalles.add(map);
        }
        return detalles;
    }
}