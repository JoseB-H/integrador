function usuarioLogueado() {
    return !!localStorage.getItem('nombreCliente') && !!localStorage.getItem('idCliente') && !!localStorage.getItem('dniCliente');
}

// Modal centrado reutilizable
function mostrarModalCentrado(mensaje) {
    let modal = document.createElement('div');
    modal.className = 'modal-centro';
    modal.style.display = 'flex';
    modal.innerHTML = `
        <div class="modal-contenido">
            <h2>${mensaje}</h2>
            <button class="btn-2" onclick="this.closest('.modal-centro').remove()">Aceptar</button>
        </div>
    `;
    document.body.appendChild(modal);
}

// Mostrar solo información del producto
function mostrarInfo(idProducto) {
    fetch(`/producto/detalle/${idProducto}`)
        .then(res => {
            if (!res.ok) throw new Error('No se pudo cargar el producto');
            return res.json();
        })
        .then(producto => {
            let modal = document.createElement('div');
            modal.className = 'modal-centro';
            modal.style.display = 'flex';
            modal.innerHTML = `
                <div class="modal-contenido" style="max-width:400px; padding:32px 24px; border-radius:16px; box-shadow:0 8px 32px rgba(44,62,80,0.18); background:#fff; text-align:center;">
                    <img src="/img/producto/${producto.imagenProducto}" alt="" style="width:180px;height:180px;object-fit:contain;margin-bottom:18px;box-shadow:0 2px 8px rgba(0,0,0,0.10);border-radius:8px;">
                    <h2 style="margin-bottom:10px; font-size:1.3em; font-family:'Oswald',sans-serif; font-weight:700; text-transform:uppercase; letter-spacing:1px; color:#111;">${producto.nombreProducto}</h2>
                    <p style="color:#222; margin-bottom:10px;">${producto.descripcion}</p>
                    <div style="margin-bottom:8px;">
                        <span style="font-weight:bold; color:#111;">Precio:</span>
                        <span style="font-size:1.1em;">S/. ${producto.precio}</span>
                    </div>
                    <div style="margin-bottom:18px;">
                        <span style="font-weight:bold; color:#111;">Proveedor:</span>
                        <span>${producto.nombreProveedor}</span>
                    </div>
                    <button class="btn-2" style="padding:10px 22px; border-radius:6px; font-size:1em; font-weight:600;" onclick="this.closest('.modal-centro').remove()">Cerrar</button>
                </div>
            `;
            document.body.appendChild(modal);
        })
        .catch(() => {
            console.error('Error al cargar producto:', idProducto);
            mostrarModalCentrado("No se pudo cargar la información del producto.");
        });
}

// Comprar producto
function comprarProducto(idProducto) {
    if (!usuarioLogueado()) {
        console.warn('Intento de compra sin sesión iniciada');
        mostrarModalCentrado('No estás logeado, inicie sesión para comprar.');
        const modalLogin = document.getElementById('modalLoginCliente');
        if (modalLogin) {
            modalLogin.style.display = 'flex';
        } else {
            console.error('Modal de login no encontrado en el DOM');
        }
        return;
    }

    const idCliente = localStorage.getItem('idCliente');
    if (!idCliente) {
        console.error('ID del cliente no encontrado en localStorage');
        mostrarModalCentrado('Error: No se encontró el ID del cliente.');
        return;
    }

    // Verificar que el producto existe antes de registrar el pedido
    fetch(`/producto/detalle/${idProducto}`)
        .then(res => {
            if (!res.ok) throw new Error('Producto no encontrado');
            return res.json();
        })
        .then(producto => {
            const pedidoRequest = {
                idCliente: parseInt(idCliente),
                detalles: [{ idProducto: idProducto, cantidad: 1 }]
            };
            return fetch('/api/pedidos/registrar-pedido', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(pedidoRequest)
            })
                .then(res => {
                    if (!res.ok) {
                        return res.text().then(msg => { throw new Error(msg || 'Error al registrar el pedido'); });
                    }
                    return { producto, response: res };
                });
        })
        .then(({ producto }) => {
            mostrarModalCentrado('¡Compra agregada!');
            agregarAlCarritoVisual(producto);
            console.log('Compra exitosa:');
            console.log('Producto:', {
                id: producto.idProducto,
                nombre: producto.nombreProducto,
                precio: producto.precio
            });
            console.log('Cliente:', {
                id: localStorage.getItem('idCliente'),
                nombre: localStorage.getItem('nombreCliente'),
                dni: localStorage.getItem('dniCliente')
            });
        })
        .catch(err => {
            console.error('Error al registrar pedido:', err);
            mostrarModalCentrado("No se pudo agregar el producto al carrito: " + err.message);
        });
}

// Agrega el producto a la tabla #lista-carrito
function agregarAlCarritoVisual(producto) {
    const lista = document.querySelector("#lista-carrito tbody");
    if (!lista) {
        console.error('Tabla #lista-carrito no encontrada');
        return;
    }
    const row = document.createElement("tr");
    row.innerHTML = `
        <td><img src="/img/producto/${producto.imagenProducto}" width="60"></td>
        <td>${producto.nombreProducto}</td>
        <td>S/. ${producto.precio}</td>
    `;
    lista.appendChild(row);
}
