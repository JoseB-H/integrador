package com.paguina.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paguina.web.modelos.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {
}
