document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('formCliente').style.display = 'none';
    document.getElementById('formPago').style.display = 'none';

    const urlParams = new URLSearchParams(window.location.search);
    let dni = urlParams.get('dni') || localStorage.getItem('dniCliente') || '';
    document.getElementById('dniFinalizar').value = dni;

    if (dni) {
        cargarResumenPedido(dni);
        validarCliente(dni);
    }

    document.getElementById('btnValidarDni').onclick = function() {
        let dniInput = document.getElementById('dniFinalizar').value.trim();
        if (!dniInput) {
            mostrarModal("Ingrese un DNI válido");
            return;
        }
        localStorage.setItem('dniHistorial', dniInput);
        cargarResumenPedido(dniInput);
        validarCliente(dniInput);
    };
});

function cargarResumenPedido(dni) {
    fetch(`/api/pedidos/pendientes/${dni}`)
        .then(res => res.json())
        .then(detalles => {
            if (!detalles || detalles.length === 0) {
                document.getElementById('resumenPedido').innerHTML = "<p style='color:red'>No hay productos pendientes para este DNI.</p>";
                return;
            }

            // 🟢 Mostrar en consola lo que el usuario está comprando
            console.log("🛒 Pedido cargado para el usuario con DNI:", dni);
            console.table(detalles);

            let html = `<table>
                <thead>
                    <tr><th>Imagen</th><th>Producto</th><th>Cantidad</th><th>Precio</th><th>Subtotal</th></tr>
                </thead><tbody>`;
            let total = 0;
            detalles.forEach(det => {
                html += `<tr>
                    <td><img src="/img/producto/${det.imagenProducto}" alt="${det.nombreProducto}" width="60"/></td>
                    <td>${det.nombreProducto}</td>
                    <td>${det.cantidad}</td>
                    <td>S/ ${det.precioUnitario.toFixed(2)}</td>
                    <td>S/ ${(det.cantidad * det.precioUnitario).toFixed(2)}</td>
                </tr>`;
                total += det.cantidad * det.precioUnitario;
            });
            html += `<tr style="font-weight:bold;">
                        <td colspan="4">TOTAL</td>
                        <td>S/ ${total.toFixed(2)}</td>
                    </tr>`;
            html += `</tbody></table>`;
            document.getElementById('resumenPedido').innerHTML = html;
        })
        .catch(err => {
            document.getElementById('resumenPedido').innerHTML = "<p style='color:red'>Error al cargar los productos.</p>";
            console.error(err);
        });
}

function validarCliente(dni) {
    fetch(`/api/clientes/${dni}`)
        .then(res => {
            if (!res.ok) throw new Error('Cliente no encontrado');
            return res.json();
        })
        .then(cliente => {
            mostrarFormularioCliente(cliente, dni);
        })
        .catch(err => {
            mostrarModal("Cliente no encontrado. Por favor, regístrese.", () => {
                document.getElementById('modalRegistroCliente').style.display = 'flex';
            });
        });
}

function mostrarFormularioCliente(cliente, dni) {
    let form = `
        <h3>Por favor confirma tus datos</h3>
        <label>Nombres:</label>
        <input type="text" id="nombreCliente" value="${cliente.nombreCliente || ''}" required>
        <label>Apellidos:</label>
        <input type="text" id="apellidoCliente" value="${cliente.apellidoCliente || ''}" required>
        <label>Correo:</label>
        <input type="email" id="correoCliente" value="${cliente.correoCliente || ''}" required>
        <label>Dirección:</label>
        <input type="text" id="direccionCliente" value="${cliente.direccionCliente || ''}" required>
        <label>Celular:</label>
        <input type="text" id="contactoCliente" value="${cliente.contactoCliente || ''}" required>
        <button id="btnConfirmarDatos">Confirmar Datos</button>
    `;
    document.getElementById('formCliente').innerHTML = form;
    document.getElementById('formCliente').style.display = 'block';
    document.getElementById('formPago').style.display = 'none';

    document.getElementById('btnConfirmarDatos').onclick = function() {
        confirmarDatos(dni);
    };
}

function confirmarDatos(dni) {
    let datos = {
        nombreCliente: document.getElementById('nombreCliente').value,
        apellidoCliente: document.getElementById('apellidoCliente').value,
        correoCliente: document.getElementById('correoCliente').value,
        direccionCliente: document.getElementById('direccionCliente').value,
        contactoCliente: document.getElementById('contactoCliente').value
    };
    fetch(`/api/clientes/${dni}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datos)
    })
        .then(res => {
            if (!res.ok) throw new Error("No se pudo actualizar el cliente");
            return res.json();
        })
        .then(cliente => {
            mostrarModal("Datos actualizados correctamente", mostrarFormularioPago);
        })
        .catch(err => {
            mostrarModal("Error al actualizar datos: " + err.message);
        });
}

function mostrarFormularioPago() {
    let form = `
        <h3>Procesar Pago</h3>
        <label>Número de Tarjeta:</label>
        <input type="text" id="tarjeta" maxlength="16" required>
        <label>Fecha de Vencimiento:</label>
        <input type="month" id="fechaVencimiento" required>
        <label>CVV:</label>
        <input type="text" id="cvv" maxlength="4" required>
        <button id="btnPagar">Pagar</button>
    `;
    document.getElementById('formPago').innerHTML = form;
    document.getElementById('formPago').style.display = 'block';
    document.getElementById('formCliente').style.display = 'none';

    document.getElementById('btnPagar').onclick = function() {
        pagar();
    };
}

function pagar() {
    let dni = document.getElementById('dniFinalizar').value;
    fetch(`/api/pedidos/pagar/${dni}`, {
        method: 'PUT'
    })
        .then(res => {
            if (res.ok) {
                mostrarModalCreandoBoleta(() => {
                    window.location.href = '/vistas/boleta.html';
                });
            } else {
                return res.text().then(msg => { throw new Error(msg || 'Error al procesar el pago'); });
            }
        })
        .catch(err => {
            mostrarModal('Error al procesar el pago: ' + err.message);
        });
}

function mostrarModalCreandoBoleta(callback) {
    let modalExistente = document.getElementById('modalPago');
    if (modalExistente) modalExistente.remove();

    let modal = document.createElement('div');
    modal.id = 'modalPago';
    modal.className = 'modal-centro';
    modal.style.display = 'flex';
    modal.innerHTML = `
        <div class="modal-contenido">
            <p>Creando boleta, espere...</p>
        </div>
    `;
    document.body.appendChild(modal);

    setTimeout(() => {
        modal.remove();
        if (typeof callback === "function") callback();
    }, 1500);
}

function mostrarModal(mensaje, callback) {
    let modalExistente = document.getElementById('modalGenerico');
    if (modalExistente) modalExistente.remove();

    let modal = document.createElement('div');
    modal.id = 'modalGenerico';
    modal.className = 'modal-centro';
    modal.style.display = 'flex';
    modal.innerHTML = `
        <div class="modal-contenido">
            <p style="font-size:1.1em;margin-bottom:18px;">${mensaje}</p>
            <button id="cerrarModalGenerico" style="padding:10px 32px;border:none;background:#6c6cff;color:#fff;border-radius:8px;font-size:16px;cursor:pointer;">Aceptar</button>
        </div>
    `;
    document.body.appendChild(modal);

    document.getElementById('cerrarModalGenerico').onclick = function() {
        modal.remove();
        if (typeof callback === "function") callback();
    };
}
