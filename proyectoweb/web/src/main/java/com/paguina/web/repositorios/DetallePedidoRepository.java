package com.paguina.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paguina.web.modelos.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {}