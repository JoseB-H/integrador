document.addEventListener('DOMContentLoaded', function () {
    // Cargar datos del proveedor en encabezado y formulario
    const nombre = localStorage.getItem('proveedorNombre') || '';
    const apellido = localStorage.getItem('proveedorApellido') || '';
    const img = localStorage.getItem('proveedorImg');
    const id = localStorage.getItem('proveedorId') || '';

    console.log('Datos del proveedor cargados desde localStorage:');
    console.log('ID:', id);
    console.log('Nombre:', nombre);
    console.log('Apellido:', apellido);
    console.log('Imagen:', img);

    // Mostrar nombre
    document.getElementById('bienvenidaProveedor').textContent = 'BIENVENIDO: ' + nombre + ' ' + apellido;

    // Mostrar foto (solo UNA vez, con validación)
    const fotoProveedor = document.getElementById('fotoProveedor');
    if (img && img !== 'null' && img.trim() !== '') {
        fotoProveedor.src = '/img/perfil_proveedor/' + img;
        console.log('Foto personalizada asignada:', fotoProveedor.src);
    } else {
        fotoProveedor.src = '/img/perfil_proveedor/default.png';
        console.log('Foto por defecto asignada');
    }
    fotoProveedor.alt = nombre + ' ' + apellido;

    // Poner id en el formulario
    document.getElementById('id_proveedor').value = id;

    // Menú desplegable
    fotoProveedor.onclick = function (e) {
        e.stopPropagation();
        const menu = document.getElementById('menuProveedor');
        menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
        console.log('Menú desplegable toggled:', menu.style.display);
    };

    document.addEventListener('click', function (e) {
        const menu = document.getElementById('menuProveedor');
        if (menu && menu.style.display === 'block') {
            menu.style.display = 'none';
            console.log('Menú desplegable cerrado al hacer clic fuera');
        }
    });

    // Cerrar sesión
    document.getElementById('cerrarSesionProveedor').onclick = function (e) {
        e.preventDefault();
        console.log('Cerrando sesión, limpiando localStorage...');
        localStorage.removeItem('proveedorId');
        localStorage.removeItem('proveedorNombre');
        localStorage.removeItem('proveedorApellido');
        localStorage.removeItem('proveedorImg');
        console.log('Redirigiendo a /vistas/sesionproveedor.html');
        window.location.href = '/vistas/sesionproveedor.html';
    };

    // Interceptar el submit del formulario
    const form = document.querySelector('form[action="/api/productos/registrar"]');
    if (form) {
        form.onsubmit = function (e) {
            e.preventDefault();
            const formData = new FormData(form);
            console.log('Formulario de producto enviado. Datos:');
            for (let [key, value] of formData.entries()) {
                console.log(`${key}: ${value}`);
            }

            fetch('/api/productos/registrar', {
                method: 'POST',
                body: formData
            })
                .then(res => res.ok ? res.text() : res.text().then(msg => { throw new Error(msg); }))
                .then(msg => {
                    console.log('Producto registrado correctamente. Respuesta del servidor:', msg);
                    mostrarModalProducto(msg || "Producto registrado correctamente");
                    form.reset();
                })
                .catch(err => {
                    console.error('Error al registrar el producto:', err.message);
                    mostrarModalProducto("Error: " + err.message);
                });
        };
    }
});

// Modal simple para mostrar mensajes
function mostrarModalProducto(mensaje) {
    let modalExistente = document.getElementById('modalProducto');
    if (modalExistente) modalExistente.remove();

    let modal = document.createElement('div');
    modal.id = 'modalProducto';
    modal.style.position = 'fixed';
    modal.style.top = 0;
    modal.style.left = 0;
    modal.style.width = '100vw';
    modal.style.height = '100vh';
    modal.style.background = 'rgba(0,0,0,0.4)';
    modal.style.display = 'flex';
    modal.style.alignItems = 'center';
    modal.style.justifyContent = 'center';
    modal.style.zIndex = 9999;
    modal.innerHTML = `
        <div style="background:#fff;padding:32px 24px;border-radius:16px;text-align:center;box-shadow:0 4px 24px #0002;min-width:260px;">
            <p style="font-size:1.1em;margin-bottom:18px;">${mensaje}</p>
            <button id="cerrarModalProducto" style="padding:10px 32px;border:none;background:#6c6cff;color:#fff;border-radius:8px;font-size:16px;cursor:pointer;">Aceptar</button>
        </div>
    `;
    document.body.appendChild(modal);
    console.log('Modal de mensaje mostrado:', mensaje);

    document.getElementById('cerrarModalProducto').onclick = function () {
        modal.remove();
        console.log('Modal de mensaje cerrado');
    };
}
