package com.paguina.web.modelos.dto;

public class ClienteLoginDTO {
    private String correoCliente;
    private String contraseñaCliente;

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getContraseñaCliente() {
        return contraseñaCliente;
    }

    public void setContraseñaCliente(String contraseñaCliente) {
        this.contraseñaCliente = contraseñaCliente;
    }
}
