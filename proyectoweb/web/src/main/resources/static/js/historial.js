let detalleAEliminar = null;

function buscarPedidos() {
    const dniCliente = localStorage.getItem('dniCliente');
    const nombreCliente = localStorage.getItem('nombreCliente');
    const idCliente = localStorage.getItem('idCliente');

    if (!dniCliente || !nombreCliente || !idCliente) {
        console.warn('Se requiere inicio de sesión del cliente para ver los pedidos pendientes.');
        document.getElementById('listaPedidos').innerHTML = '<p>No hay pedidos pendientes.</p>';
        return;
    }

    console.log('Cliente logueado:');
    console.log('ID:', idCliente);
    console.log('Nombre:', nombreCliente);
    console.log('DNI:', dniCliente);

    fetch(`/api/pedidos/pendientes/${dniCliente}`)
        .then(res => res.json())
        .then(data => {
            console.log('Pedidos pendientes recibidos:', data);
            mostrarPedidos(data);
        })
        .catch(err => {
            console.error('Error al cargar pedidos pendientes:', err);
            document.getElementById('listaPedidos').innerHTML = '<p>Error al cargar los pedidos.</p>';
        });
}

function mostrarPedidos(detalles) {
    const cont = document.getElementById('listaPedidos');
    cont.innerHTML = '';

    if (detalles.length === 0) {
        console.log('No hay pedidos pendientes para mostrar.');
        cont.innerHTML = '<p>No hay pedidos pendientes.</p>';
        return;
    }

    let html = `
        <table class="tabla-historial">
            <thead>
                <tr>
                    <th>Foto</th>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Subtotal</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
    `;

    detalles.forEach(det => {
        html += `
            <tr>
                <td><img src="/img/producto/${det.imagenProducto}" width="60" /></td>
                <td>${det.nombreProducto}</td>
                <td>
                    <button onclick="cambiarCantidad(${det.idDetalle}, ${det.cantidad - 1})">-</button>
                    <span id="cant-${det.idDetalle}">${det.cantidad}</span>
                    <button onclick="cambiarCantidad(${det.idDetalle}, ${det.cantidad + 1})">+</button>
                </td>
                <td>S/. ${det.subtotal.toFixed(2)}</td>
                <td>
                    <button onclick="preguntarBorrar(${det.idDetalle})">X</button>
                </td>
            </tr>
        `;
    });

    html += `</tbody></table>
        <aside>
            <button class="comprar" onclick="irAFinalizarCompra()">Pagar</button>
        </aside>
    `;
    cont.innerHTML = html;
}

function cambiarCantidad(idDetalle, nuevaCantidad) {
    if (nuevaCantidad <= 0) {
        console.log(`Cantidad nueva (${nuevaCantidad}) no válida. Se solicitará eliminación del producto con ID detalle ${idDetalle}`);
        preguntarBorrar(idDetalle);
        return;
    }

    console.log(`Cambiando cantidad del detalle ${idDetalle} a ${nuevaCantidad}`);
    fetch(`/api/pedidos/detalle/${idDetalle}/cantidad?cantidad=${nuevaCantidad}`, { method: 'PUT' })
        .then(res => {
            if (!res.ok) return res.text().then(msg => { throw new Error(msg); });
            console.log(`Cantidad actualizada exitosamente para detalle ${idDetalle}`);
            buscarPedidos();
        })
        .catch(err => {
            console.error(`Error al cambiar cantidad del detalle ${idDetalle}:`, err.message);
            alert(err.message);
        });
}

function preguntarBorrar(idDetalle) {
    console.log(`Solicitando confirmación para eliminar detalle de pedido: ${idDetalle}`);
    detalleAEliminar = idDetalle;
    document.getElementById('modalBorrar').style.display = 'block';
    document.getElementById('modalBorrar-backdrop').style.display = 'block';
}

function cerrarModal() {
    detalleAEliminar = null;
    document.getElementById('modalBorrar').style.display = 'none';
    document.getElementById('modalBorrar-backdrop').style.display = 'none';
}

function confirmarBorrado() {
    if (!detalleAEliminar) {
        console.warn('confirmarBorrado() llamado sin un detalle seleccionado.');
        return;
    }

    console.log(`Confirmando eliminación del detalle ${detalleAEliminar}`);
    fetch(`/api/pedidos/detalle/${detalleAEliminar}`, { method: 'DELETE' })
        .then(() => {
            console.log(`Detalle ${detalleAEliminar} eliminado exitosamente`);
            cerrarModal();
            document.getElementById('mensaje').innerText = 'Producto eliminado correctamente';
            buscarPedidos();
        })
        .catch(err => {
            console.error('Error al eliminar detalle:', err.message);
            cerrarModal();
            alert('Error: ' + err.message);
        });
}

document.addEventListener('DOMContentLoaded', buscarPedidos);

function irAFinalizarCompra() {
    const dniCliente = localStorage.getItem('dniCliente');
    if (!dniCliente) {
        console.warn('No se puede continuar a pagar: cliente no logueado');
        const modal = document.getElementById('modalLoginCliente');
        if (modal) {
            modal.style.display = 'flex';
        }
        return;
    }

    console.log('Redirigiendo a finalizarcompra.html con DNI:', dniCliente);
    window.location.href = `/vistas/finalizarcompra.html?dni=${dniCliente}`;
}
