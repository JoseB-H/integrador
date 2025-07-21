package com.paguina.web.controladores;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class BoletaController {
    @GetMapping(value = "/api/boleta/{dni}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> generarBoleta(@PathVariable String dni) {
        // Aquí deberías obtener los datos del cliente y los productos desde tu servicio/repositorio
        // Ejemplo de datos simulados:
        String nombreCliente = "Juan Pérez";
        String correo = "juan@mail.com";
        String direccion = "Av. Perú 123";
        List<String[]> productos = List.of(
            new String[]{"Producto 1", "2", "10.00", "20.00"},
            new String[]{"Producto 2", "1", "15.00", "15.00"}
        );
        double total = 35.00;

        // Construir el HTML con StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Boleta</title></head><body>");
        sb.append("<h1>Boleta de Compra</h1>");
        sb.append("<p><b>Cliente:</b> ").append(nombreCliente).append("</p>");
        sb.append("<p><b>Correo:</b> ").append(correo).append("</p>");
        sb.append("<p><b>Dirección:</b> ").append(direccion).append("</p>");
        sb.append("<table border='1'><tr><th>Producto</th><th>Cantidad</th><th>Precio Unitario</th><th>Subtotal</th></tr>");
        for (String[] prod : productos) {
            sb.append("<tr>");
            for (String campo : prod) {
                sb.append("<td>").append(campo).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("<tr><td colspan='3'><b>Total</b></td><td><b>").append(total).append("</b></td></tr>");
        sb.append("</table>");
        sb.append("</body></html>");

        return ResponseEntity.ok(sb.toString());
    }
}
