package com.paguina.web.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paguina.web.modelos.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByDniClienteAndEstado(String dniCliente, String estado);
    Optional<Pedido> findTopByDniClienteAndEstadoOrderByFechaPedidoDesc(String dniCliente, String estado);
}