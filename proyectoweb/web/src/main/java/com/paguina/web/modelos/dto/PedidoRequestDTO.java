package com.paguina.web.modelos.dto;

import java.util.List;

public class PedidoRequestDTO {
    private Integer idCliente;
    private List<DetallePedidoDTO> detalles;

    // Getters y setters
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }
}

