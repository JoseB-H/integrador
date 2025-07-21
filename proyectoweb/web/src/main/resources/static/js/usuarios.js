document.addEventListener('DOMContentLoaded', () => {
    const abrirModalLogin = document.getElementById('abrirModalLogin');
    const modalLoginCliente = document.getElementById('modalLoginCliente');
    const cerrarLogin = document.getElementById('cerrarLogin');
    const abrirModalRegistro = document.getElementById('abrirModalRegistro');
    const modalRegistroCliente = document.getElementById('modalRegistroCliente');
    const cerrarRegistro = document.getElementById('cerrarRegistro');
    const usuarioMenu = document.getElementById('usuarioMenu');
    const nombreClienteLabel = document.getElementById('nombreClienteLabel');
    const nombreClienteMenu = document.getElementById('nombreClienteMenu');

    // Función para verificar si el usuario está logueado
    function usuarioLogueado() {
        return !!localStorage.getItem('idCliente') && 
               !!localStorage.getItem('dniCliente') && 
               !!localStorage.getItem('nombreCliente');
    }

    // Inicializar UI según estado de autenticación
    if (usuarioLogueado()) {
        const nombre = localStorage.getItem('nombreCliente');
        nombreClienteLabel.innerText = nombre;
        nombreClienteMenu.innerText = nombre;
        usuarioMenu.style.display = 'none'; // Ocultar menú por defecto
    } else {
        nombreClienteLabel.innerText = '';
        nombreClienteMenu.innerText = '';
        usuarioMenu.style.display = 'none';
    }

    // Manejar clic en el ícono de usuario
    if (abrirModalLogin) {
        abrirModalLogin.addEventListener('click', () => {
            if (usuarioLogueado()) {
                // Si está logueado, alternar visibilidad del menú de usuario
                usuarioMenu.style.display = usuarioMenu.style.display === 'block' ? 'none' : 'block';
                if (modalLoginCliente) modalLoginCliente.style.display = 'none';
                if (modalRegistroCliente) modalRegistroCliente.style.display = 'none';
            } else {
                // Si no está logueado, mostrar modal de login
                if (modalLoginCliente) {
                    modalLoginCliente.style.display = 'flex';
                    if (modalRegistroCliente) modalRegistroCliente.style.display = 'none';
                } else {
                    console.error('Elemento #modalLoginCliente no encontrado');
                }
            }
        });
    } else {
        console.error('Elemento #abrirModalLogin no encontrado');
    }

    // Cerrar modal de login
    if (cerrarLogin) {
        cerrarLogin.addEventListener('click', () => {
            modalLoginCliente.style.display = 'none';
        });
    }

    // Abrir modal de registro
    if (abrirModalRegistro && modalRegistroCliente) {
        abrirModalRegistro.addEventListener('click', () => {
            modalRegistroCliente.style.display = 'flex';
            if (modalLoginCliente) modalLoginCliente.style.display = 'none';
        });
    } else {
        console.error('Elementos #abrirModalRegistro o #modalRegistroCliente no encontrados');
    }

    // Cerrar modal de registro
    if (cerrarRegistro) {
        cerrarRegistro.addEventListener('click', () => {
            modalRegistroCliente.style.display = 'none';
        });
    }

    // Manejar submit del formulario de login
    document.getElementById('formLoginCliente').onsubmit = function(e) {
        e.preventDefault();
        document.getElementById('loginClienteError').style.display = 'none';
        const correo = document.getElementById('loginCorreo').value;
        const contraseña = document.getElementById('loginPass').value;

        fetch('/api/clientes/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ correoCliente: correo, contraseñaCliente: contraseña })
        })
            .then(res => {
                if (res.ok) return res.json();
                return res.text().then(msg => { throw new Error(msg || 'Correo o contraseña incorrectos'); });
            })
            .then(data => {
                localStorage.setItem('idCliente', data.idCliente);
                localStorage.setItem('dniCliente', data.dniCliente);
                localStorage.setItem('nombreCliente', data.nombre);
                document.getElementById('modalLoginCliente').style.display = 'none';
                nombreClienteLabel.innerText = data.nombre;
                nombreClienteMenu.innerText = data.nombre;
                usuarioMenu.style.display = 'none'; // Ocultar menú tras login
                mostrarModalCentrado(`¡Bienvenido, ${data.nombre}!`);
                console.log('Login exitoso:', data);
            })
            .catch(err => {
                document.getElementById('loginClienteError').textContent = err.message;
                document.getElementById('loginClienteError').style.display = 'block';
                console.error('Error en login:', err.message);
            });
    };

    // Manejar submit del formulario de registro
    document.getElementById('formRegistroCliente').onsubmit = function(e) {
        e.preventDefault();
        document.getElementById('registroClienteError').style.display = 'none';
        const dni = document.getElementById('regDni').value;
        const nombre = document.getElementById('regNombre').value;
        const apellido = document.getElementById('regApellido').value;
        const correo = document.getElementById('regCorreo').value;
        const direccion = document.getElementById('regDireccion').value;
        const contacto = document.getElementById('regContacto').value;
        const contraseña = document.getElementById('regPass').value;

        const registroData = {
            dniCliente: dni,
            nombreCliente: nombre,
            apellidoCliente: apellido,
            correoCliente: correo,
            direccionCliente: direccion,
            contactoCliente: contacto,
            contraseñaCliente: contraseña
        };

        fetch('/api/clientes/registro', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(registroData)
        })
            .then(res => {
                if (res.ok) return res.json();
                return res.text().then(msg => { throw new Error(msg || 'Error al registrar cliente'); });
            })
            .then(data => {
                localStorage.setItem('idCliente', data.idCliente);
                localStorage.setItem('dniCliente', data.dniCliente);
                localStorage.setItem('nombreCliente', data.nombre);
                document.getElementById('modalRegistroCliente').style.display = 'none';
                nombreClienteLabel.innerText = data.nombre;
                nombreClienteMenu.innerText = data.nombre;
                usuarioMenu.style.display = 'none'; // Ocultar menú tras registro
                mostrarModalCentrado(`¡Bienvenido, ${data.nombre}! Cliente registrado con éxito.`);
                console.log('Cliente registrado:', data);
            })
            .catch(err => {
                document.getElementById('registroClienteError').textContent = err.message;
                document.getElementById('registroClienteError').style.display = 'block';
                console.error('Error al registrar:', err.message);
            });
    };

    // Cerrar sesión
    document.getElementById('cerrarSesionBtn').onclick = () => {
        localStorage.removeItem('idCliente');
        localStorage.removeItem('dniCliente');
        localStorage.removeItem('nombreCliente');
        nombreClienteLabel.innerText = '';
        nombreClienteMenu.innerText = '';
        usuarioMenu.style.display = 'none';
        mostrarModalCentrado('Sesión cerrada');
    };
});