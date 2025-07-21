document.addEventListener("DOMContentLoaded", function() {
    let dni = localStorage.getItem('dniHistorial') || '';
    if (!dni) {
        document.body.innerHTML = "<h2>No se encontró información de la compra.</h2>";
        return;
    }

    console.log("🧾 Mostrando boleta para DNI:", dni);

    // Datos del cliente
    fetch(`/api/clientes/${dni}`)
        .then(res => res.json())
        .then(cliente => {
            document.getElementById('clienteNombre').textContent = cliente.nombreCliente + " " + cliente.apellidoCliente;
            document.getElementById('clienteDni').textContent = cliente.dniCliente;
            document.getElementById('clienteCorreo').textContent = cliente.correoCliente;
            document.getElementById('clienteDireccion').textContent = cliente.direccionCliente;

            console.log(`👤 Cliente: ${cliente.nombreCliente} ${cliente.apellidoCliente} | Correo: ${cliente.correoCliente} | Dirección: ${cliente.direccionCliente}`);
        });

    // Productos del pedido pagado
    fetch(`/api/pedidos/ultimo-pagado/${dni}`)
        .then(res => res.json())
        .then(detalles => {
            let tbody = document.getElementById('detalleProductos');
            tbody.innerHTML = "";
            let total = 0;

            console.log("🛒 Productos:");
            detalles.forEach(det => {
                let subtotal = det.cantidad * det.precioUnitario;
                console.log(`- ${det.nombreProducto} | Cantidad: ${det.cantidad} | Precio: S/ ${det.precioUnitario.toFixed(2)} | Subtotal: S/ ${subtotal.toFixed(2)}`);

                let tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>
                        <img src="/img/producto/${det.imagenProducto}" alt="${det.nombreProducto}" style="width:60px;vertical-align:middle;margin-right:8px;">
                        ${det.nombreProducto}
                    </td>
                    <td>${det.cantidad}</td>
                    <td>S/ ${det.precioUnitario.toFixed(2)}</td>
                    <td>S/ ${subtotal.toFixed(2)}</td>
                `;
                tbody.appendChild(tr);
                total += subtotal;
            });

            document.getElementById('totalFinal').textContent = "S/ " + total.toFixed(2);
            console.log(`💰 Total: S/ ${total.toFixed(2)}`);
        });
});
