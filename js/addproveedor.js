document.getElementById('abrirModal').onclick = function() {
    document.getElementById('modalSesion').style.display = 'flex';
};
document.getElementById('cerrarModal').onclick = function() {
    document.getElementById('modalSesion').style.display = 'none';
};


window.onclick = function(event) {
    if (event.target == document.getElementById('modalSesion')) {
        document.getElementById('modalSesion').style.display = 'none';
    }
};

document.getElementById('abrirModal').onclick = function() {
    document.getElementById('modalSesion').style.display = 'flex';
};
document.getElementById('cerrarModal').onclick = function() {
    document.getElementById('modalSesion').style.display = 'none';
};
window.onclick = function(event) {
    if (event.target == document.getElementById('modalSesion')) {
        document.getElementById('modalSesion').style.display = 'none';
    }
};

// Registro AJAX
document.querySelector('.formulario-registro').onsubmit = async function(e) {
    e.preventDefault();
    const form = e.target;
    const data = new FormData(form);

    try {
        const resp = await fetch('/api/proveedores/registro', {
            method: 'POST',
            body: data
        });
        if (resp.ok) {
            mostrarModal('¡Registro exitoso!', true);
        } else {
            // Intenta leer como texto, si no es texto, muestra error genérico
            let msg = '';
            try {
                msg = await resp.text();
            } catch {
                msg = 'Error en el registro';
            }
            mostrarModal(
                msg.includes('correo') || msg.includes('DNI') ? msg : 'Error en el registro',
                false
            );
        }
    } catch (err) {
        mostrarModal('Error de conexión', false);
    }
};

function mostrarModal(mensaje, exito) {
    let modal = document.createElement('div');
    modal.style.position = 'fixed';
    modal.style.top = '0'; modal.style.left = '0';
    modal.style.width = '100vw'; modal.style.height = '100vh';
    modal.style.background = 'rgba(0,0,0,0.5)';
    modal.style.display = 'flex'; modal.style.alignItems = 'center'; modal.style.justifyContent = 'center';
    modal.innerHTML = `<div style="background:#fff;padding:30px 40px;border-radius:10px;text-align:center;">
        <h2>${mensaje}</h2>
        <button id="cerrarModalExito">OK</button>
    </div>`;
    document.body.appendChild(modal);
    document.getElementById('cerrarModalExito').onclick = function() {
        document.body.removeChild(modal);
        if (exito) window.location.href = '/vistas/sesionproveedor.html';
    };
}