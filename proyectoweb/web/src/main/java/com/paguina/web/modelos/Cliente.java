package com.paguina.web.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(unique = true)
    private String dniCliente;

     @Column(name = "Nombre_cliente")
    private String nombreCliente;

    @Column(name = "Apellido_cliente")
    private String apellidoCliente;

    @Column(name = "Correo_cliente")
    private String correoCliente;

    @Column(name = "Contraseña_cliente")
    private String contraseñaCliente;

    @Column(name = "Direccion_cliente")
    private String direccionCliente;

    @Column(name = "Contacto_cliente")
    private String contactoCliente;

    @Column(name = "rol")
    private String rol; 
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    
public String getContraseñaCliente() {
    return contraseñaCliente;
}
public void setContraseñaCliente(String contraseñaCliente) {
    this.contraseñaCliente = contraseñaCliente;
}

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getContactoCliente() {
        return contactoCliente;
    }

    public void setContactoCliente(String contactoCliente) {
        this.contactoCliente = contactoCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

  
}