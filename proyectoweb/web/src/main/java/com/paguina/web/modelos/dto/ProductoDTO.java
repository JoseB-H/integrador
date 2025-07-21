package com.paguina.web.modelos.dto;

public class ProductoDTO {
    private Integer idProducto;
    private String nombreProducto;
    private String imagenProducto;
    private Double precio;
    private Integer stock;
        private String nombreProveedor;
    public ProductoDTO() {
    }

    public ProductoDTO(Integer idProducto, String nombreProducto, String imagenProducto, Double precio, Integer stock, String nombreProveedor) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.imagenProducto = imagenProducto;
        this.precio = precio;
        this.stock = stock;
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

}