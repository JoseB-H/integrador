package com.paguina.web.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Proveedores")
public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_proveedor")
    private Integer idProveedor;

    @Column(name = "Dni_proveedor", length = 20, unique = true, nullable = false)
    private String dniProveedor;

    @Column(name = "Img_proveedor", nullable = false)
    private String imgProveedor;

    @Column(name = "Nombre_proveedor", length = 100)
    private String nombreProveedor;

    @Column(name = "Apellido_proveedor", length = 100)
    private String apellidoProveedor;

    @Column(name = "Correo_proveedor", length = 100, nullable = false, unique = true)
    private String correoProveedor;

    @Column(name = "Contraseña_proveedor", nullable = false)
    private String contraseñaProveedor;

    @Column(name = "Direccion_proveedor")
    private String direccionProveedor;

    @Column(name = "Contacto_proveedor", length = 20)
    private String contactoProveedor;

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getDniProveedor() {
        return dniProveedor;
    }

    public void setDniProveedor(String dniProveedor) {
        this.dniProveedor = dniProveedor;
    }

    public String getImgProveedor() {
        return imgProveedor;
    }

    public void setImgProveedor(String imgProveedor) {
        this.imgProveedor = imgProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getApellidoProveedor() {
        return apellidoProveedor;
    }

    public void setApellidoProveedor(String apellidoProveedor) {
        this.apellidoProveedor = apellidoProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }

    public String getContraseñaProveedor() {
        return contraseñaProveedor;
    }

    public void setContraseñaProveedor(String contraseñaProveedor) {
        this.contraseñaProveedor = contraseñaProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getContactoProveedor() {
        return contactoProveedor;
    }

    public void setContactoProveedor(String contactoProveedor) {
        this.contactoProveedor = contactoProveedor;
    }

    
}