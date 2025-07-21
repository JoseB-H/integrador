document.getElementById('abrirModal').onclick = () => {
  console.log('Abriendo modal de sesión');
  document.getElementById('modalSesion').style.display = 'flex';
};

document.getElementById('cerrarModal').onclick = () => {
  console.log('Cerrando modal de sesión manualmente');
  document.getElementById('modalSesion').style.display = 'none';
};

window.onclick = e => {
  if (e.target === document.getElementById('modalSesion')) {
    console.log('Cerrando modal de sesión por clic fuera del contenido');
    document.getElementById('modalSesion').style.display = 'none';
  }
};

// Validación AJAX para login
document.getElementById('formLogin').onsubmit = function(e) {
  e.preventDefault();
  console.log('Enviando formulario de login');
  document.getElementById('loginError').style.display = 'none';

  fetch('/api/proveedores/login', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({
      correo: document.getElementById('loginCorreo').value,
      contraseña: document.getElementById('loginPass').value
    })
  })
  .then(res => {
    if (res.ok) {
      console.log('Login exitoso, respuesta del servidor OK');
      return res.json();
    } else {
      throw new Error("Correo o contraseña incorrectos");
    }
  })
  .then(data => {
    // Guardar datos del proveedor en localStorage
    localStorage.setItem('proveedorId', data.idProveedor);
    localStorage.setItem('proveedorNombre', data.nombreProveedor);
    localStorage.setItem('proveedorApellido', data.apellidoProveedor);
    localStorage.setItem('proveedorImg', data.imgProveedor);
    console.log('Datos del proveedor guardados en localStorage');

    document.getElementById('modalSesion').style.display = 'none';
    console.log('Modal de sesión ocultado');

    // Mostrar modal centrado de bienvenida
    mostrarModalBienvenida(data.nombreProveedor);
    console.log('Modal de bienvenida mostrado');

    // Redirigir después de 2 segundos
    setTimeout(() => {
      console.log('Redirigiendo a /vistas/productoadd.html');
      window.location.href = '/vistas/productoadd.html';
    }, 2000);
  })
  .catch(err => {
    console.error('Error en el login:', err.message);
    document.getElementById('loginError').textContent = err.message;
    document.getElementById('loginError').style.display = 'block';
  });
};

// Función para mostrar el modal centrado de bienvenida
function mostrarModalBienvenida(nombre) {
  let modal = document.createElement('div');
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
    <div style="background:#fff;padding:40px 30px;border-radius:18px;text-align:center;box-shadow:0 4px 24px #0002;">
      <h2>¡Bienvenido, ${nombre}!</h2>
      <p>Redirigiendo para registrar producto...</p>
    </div>
  `;
  document.body.appendChild(modal);
  console.log('Modal de bienvenida creado y añadido al DOM');
}
