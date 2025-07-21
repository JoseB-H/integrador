package com.paguina.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paguina.web.repositorios.ProductosRepository;

@Controller
public class vistas {

    @Autowired
    private ProductosRepository productosRepository;

    @GetMapping("/vistas/historial.html")
    public String historial() {
        return "vistas/historial";
    }

    @GetMapping("/vistas/finalizarcompra.html")
    public String finalizarcompra() {
        return "vistas/finalizarcompra";
    }

    @GetMapping("/vistas/QuienesSomos.html")
    public String QuienesSomos() {
        return "vistas/QuienesSomos";
    }

    @GetMapping("/index.html")
    public String mostrarIndex(Model model) {
        model.addAttribute("productos", productosRepository.findAll());
        return "index";
    }

    @GetMapping("/vistas/sesionproveedor.html")
    public String sesion() {
        return "vistas/sesionproveedor";
    }

    @GetMapping("/vistas/boleta.html")
    public String boleta() {
        return "vistas/boleta";
    }
    // Puedes agregar otros mappings para otras vistas si lo necesitas

    @GetMapping("/vistas/productoadd.html")
    public String productoAdd() {
        return "vistas/productoadd";
    }

    @GetMapping("/vistas/registroproveedor.html")
    public String registro() {
        return "vistas/registroproveedor";
    }

    @GetMapping("/index1.html")
    public String mostrarIndex1(Model model) {
        model.addAttribute("productos", productosRepository.findAll());
        return "index1";
    }
}
