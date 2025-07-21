<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link href="css/encabezado.css" rel="stylesheet" type="text/css" />
    <link href="css/inicio.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Bootstrap para carrusel -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .hero-carousel {
            height: 60vh;
            overflow: hidden;
        }

        .hero-carousel .carousel-item {
            height: 60vh;
            background-size: cover;
            background-position: center;
            position: relative;
        }
        .video-promocional {
        padding: 40px 0;
        background-color: #f9f9f9;
        border-top: 2px solid #eee;
    }

        .hero-text {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: #fff;
            text-shadow: 2px 2px 10px #000;
            text-align: center;
        }

        .btn-custom {
            background-color: #b76e79;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
        }

        .btn-custom:hover {
            background-color: #984f58;
            color: white;
        }
        @import url("https://fonts.googleapis.com/css2?family=Oswald:wght@400;500;600;700&family=Poppins:wght@400;500;600;700;800&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  text-decoration: none;
  list-style: none;
}

body {
  font-family: "Poppins", sans-serif;
}

img {
  max-width: 100%;
}

.container {
  width: 1200px;
  margin: 0 auto;
}

.header {
  display: flex;
  align-items: center;
  min-height: 70vh;
  background-color: #f5f5f53b;
  padding: 70px 0;
}

.menu {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  align-items: center;
  display: flex;
  justify-content: space-between;
}

.logo {
  font-size: 25px;
  color: black;
  text-transform: uppercase;
  font-weight: 800;
}

.menu .navbar ul li {
  position: relative;
  float: left;
}

.menu .navbar ul li a {
  color: black;
  padding: 20px;
  font-size: 20px;
  display: block;
}

#menu {
  display: none;
}

.menu-icono {
  width: 25px;
}

.menu label {
  cursor: pointer;
  display: none;
}

.submenu {
  position: relative;
}

.submenu #carrito {
  display: none;
}

.submenu:hover #carrito {
  display: block;
  position: absolute;
  top: 100%;
  right: 0;
  backdrop-filter: blur(10px);
  z-index: 1;
  background-color: #4e4b5076;
  min-width: 400px;
  padding: 20px;
}

table {
  width: 100%;
}

th,
td {
  color: #2600ff;
}

.borrar {
  background-color: black;
  border-radius: 50%;
  padding: 5px 10px;
  text-decoration: none;
  color: blue;
  font-weight: 800;
  cursor: pointer;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex: 1;
  /* padding: 0 200px; si la imagen esta grande esto sirve para redimensionar*/
}

.header-img {
  flex-basis: 50%;
  text-align: center;
}

.header-txt {
  flex-basis: 50%;
  text-align: center;
}

.header-txt h1 {
  font-size: 90px;
  color: #111111;
  text-transform: uppercase;
  line-height: 1;
  font-family: "Oswald", sans-serif;
  margin-bottom: 15px;
}

.header-txt p {
  font-size: 20px;
  text-decoration: uppercase;
  margin-bottom: 20px;
}

.btn-1 {
  display: inline-block;
  padding: 11px 25px;
  background-color: black;
  color: white;
  border-radius: 5px;
}

.ofert {
  padding: 20px 0 0 0;
  display: flex;
  justify-content: space-between;
}

.ofert-1 {
  display: flex;
  align-items: center;
  flex-basis: calc(33.3% - 15px);
  background-color: #f5f5f5;
  padding: 35px;
}

.ofert-img {
  flex-basis: 50%;
  margin-right: 15px;
}

.ofert-img img {
  width: 250px;
}

.ofert-txt {
  flex-basis: 50%;
  text-align: center;
}

.ofert-txt h3 {
  font-size: 25px;
  color: #111111;
  text-transform: uppercase;
  margin-bottom: 15px;
  font-family: "oswald", sans-serif;
}
    </style>
</head>
<script src="//rum-static.pingdom.net/pa-687dcf72c99eee0012000024.js" async></script>
<body>
    <!-- ENCABEZADO -->
    <header class="header">
        <div class="menu container">
            <a href="/" class="logo">Manos del Peru</a>
            <input type="checkbox" id="menu" />
            <label for="menu">
                <img src="/img/iconos/menu.png" class="menu-icono" alt="menu">
            </label>
            <nav class="navbar">
                <ul>
                    <li><a href="/index.html">inicio</a></li>
                    <li><a href="/vistas/QuienesSomos.html">quienes somos?</a></li>
                    <li><a href="/vistas/sesionproveedor.html">emprende tu negocio</a></li>
                </ul>
            </nav>
            <div>
                <ul style="display: flex; align-items: center; gap: 18px;">
                    <li class="submenu">
                        <a class="carro" href="/vistas/historial.html">
                            <img src="/img/iconos/car.svg" id="img-carrito" alt="carrito">
                        </a>
                        <div id="carrito">
                            <table id="lista-carrito">
                                <thead>
                                    <tr>
                                        <th>Imagen</th>
                                        <th>Nombre</th>
                                        <th>Precio</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody> </tbody>
                            </table>
                        </div>
                    </li>
                    <li style="position: relative;">
                        <button id="abrirModalLogin" style="background:none;border:none;cursor:pointer;">
                            <img src="/img/iconos/usuario.png" alt="login cliente"
                                style="width:50px;vertical-align:middle;">
                        </button>
                        <div id="nombreClienteLabel" style="font-size:13px; text-align:center;"></div>
                        <div id="usuarioMenu"
                            style="display:none; position:absolute; top:55px; left:0; background:#fff; border:1px solid #ccc; border-radius:8px; min-width:160px; box-shadow:0 2px 8px #0002; z-index:1000;">
                            <div id="nombreClienteMenu" style="padding:10px; font-weight:bold; text-align:center;">
                            </div>
                            <button id="cerrarSesionBtn"
                                style="width:100%; border:none; background:#f44336; color:#fff; padding:10px; border-radius:0 0 8px 8px; cursor:pointer;">Cerrar
                                sesión</button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="chatbot-flotante" style="position:fixed; bottom:30px; right:30px; z-index:9999; display:none;">
            <div style="background:#fff; border-radius:10px; box-shadow:0 0 10px rgba(0,0,0,0.2); padding:15px; max-width:250px;">
                <p style="margin:0 0 10px;">¿A qué esperas para emprender? 👀<br>¡Dale click y comienza!</p>
                <button onclick="window.location.href='/vistas/sesionproveedor.html'" style="background:#ff6600; color:#fff; border:none; padding:8px 12px; border-radius:5px; cursor:pointer;">Emprender ahora</button>
            </div>
        </div>
        <div class="header-content container">
            <div class="header-img">
                <img src="/img/iconos/prcpal.png" alt="rrr">
            </div>
            <div class="header-txt">
                <h1>Ofertas Especiales</h1>
                <p>Aprovecha las mejores artesanias </p>
                <a href="#" class="btn-1">Información</a>
            </div>
        </div>
    </header>

    <div id="anuncioIzquierdo" style="position: fixed;top: 100px;left: 15px;width: 180px;background: #fff;border: 2px solid #ff6600;border-radius: 8px;padding: 10px;box-shadow: 0 4px 10px rgba(0,0,0,0.1);z-index: 999;">
    <h6 style="margin-top:0;">🔔 Oferta especial</h6>
    <img src="/img/producto/GodzillaArtesanal.png" alt="oferta" style="width:100%; border-radius:5px;">
    <p style="font-size: 13px;">Hasta 50% en productos artesanales.</p>
    <a href="index.html" class="btn-2" style="font-size:12px;">Ver más</a>
    </div>

    <!-- CARRUSEL HERO -->
    <div id="heroCarousel" class="carousel slide hero-carousel" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active" style="background-image: url('/img/producto/1.webp');">
                <div class="hero-text">
                    <h1 class="display-4">Bienvenido a Manos del Perú</h1>
                    <p class="lead">Artesanías únicas hechas con tradición</p>
                    <a href="/vistas/QuienesSomos.html" class="btn btn-custom mt-3">Ver más</a>
                </div>
            </div>
            <div class="carousel-item" style="background-image: url('/img/producto/2.avif');">
                <div class="hero-text">
                    <h1 class="display-4">Hecho a mano con amor</h1>
                    <p class="lead">Calidad y autenticidad en cada pieza</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#heroCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon bg-dark rounded-circle" aria-hidden="true"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#heroCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon bg-dark rounded-circle" aria-hidden="true"></span>
        </button>
    </div>
    <!-- PRODUCTOS -->
    <div class="titulo-seccion">PRODUCTOS</div>
    <div class="grid-productos">
        <div th:each="producto : ${productos}" class="producto">
            <img th:src="@{/img/producto/{img}(img=${producto.imagenProducto})}" alt="imagen producto" />
            <div class="info">
                <h3 th:text="${producto.nombreProducto}">Nombre</h3>
                <p>Precio: S/. <span th:text="${producto.precio}">0.00</span></p>
                <p>Stock: <span th:text="${producto.stock}">0</span></p>
            </div>
            <div class="botones">
                <button type="button" class="btn-info" th:onclick="'mostrarInfo(' + ${producto.idProducto} + ')'"
                    th:attr="data-id=${producto.idProducto}">Mostrar información</button>
                <button type="button" class="btn-comprar"
                    th:onclick="'comprarProducto(' + ${producto.idProducto} + ')'"
                    th:attr="data-id=${producto.idProducto}">Comprar</button>
            </div>
        </div>
    </div>

    <!-- Modal Login Cliente -->
    <div id="modalLoginCliente" class="modal-centro" style="display:none;">
        <div class="modal-contenido">
            <span class="cerrar" id="cerrarLogin">&times;</span>
            <h2>Iniciar Sesión</h2>
            <form id="formLoginCliente" autocomplete="off">
                <input type="text" id="loginCorreo" placeholder="Correo" required>
                <input type="password" id="loginPass" placeholder="Contraseña" required>
                <div style="margin-top:18px;">
                    <button type="submit" class="btn-2">Iniciar sesión</button>
                    <button type="button" id="abrirModalRegistro" class="btn-2"
                        style="margin-left:10px;">Registrarse</button>
                </div>
            </form>
            <div id="loginClienteError" style="color:red; margin-top:8px; display:none;"></div>
        </div>
    </div>

    <!-- Modal Registro Cliente -->
    <div id="modalRegistroCliente" class="modal-centro" style="display:none;">
        <div class="modal-contenido">
            <span class="cerrar" id="cerrarRegistro">&times;</span>
            <h2>Registrarse</h2>
            <form id="formRegistroCliente" autocomplete="off">
                <input type="text" id="regDni" placeholder="DNI" required>
                <input type="text" id="regNombre" placeholder="Nombres" required>
                <input type="text" id="regApellido" placeholder="Apellidos" required>
                <input type="email" id="regCorreo" placeholder="Correo" required>
                <input type="text" id="regDireccion" placeholder="Dirección" required>
                <input type="text" id="regContacto" placeholder="Celular" required>
                <input type="password" id="regPass" placeholder="Contraseña" required>
                <div style="margin-top:18px;">
                    <button type="submit" class="btn-2">Registrarme</button>
                </div>
            </form>
            <div id="registroClienteError" style="color:red; margin-top:8px; display:none;"></div>
        </div>
    </div>
    <!-- VIDEOS PROMOCIONALES EN GRID 3x2 -->
        <section class="container mt-5 mb-5">
            <h2 class="text-center mb-4">Videos Promocionales de los jovenes emprendedores</h2>
            <div class="row g-4">
                <div class="col-md-4">
                    <video controls class="w-100 rounded">
                        <source src="https://www.youtube.com/watch?v=d6YrfZxKsvU" type="video/mp4">
                        Tu navegador no soporta el video.
                    </video>
                </div>
                <div class="col-md-4">
                    <video controls class="w-100 rounded">
                        <source src="https://www.youtube.com/watch?v=d6YrfZxKsvU" type="video/mp4">
                        Tu navegador no soporta el video.
                    </video>
                </div>
                <div class="col-md-4">
                    <video controls class="w-100 rounded">
                        <source src="https://www.youtube.com/watch?v=d6YrfZxKsvU" type="video/mp4">
                        Tu navegador no soporta el video.
                    </video>
                </div>
                <div class="col-md-4">
                    <video controls class="w-100 rounded">
                        <source src="https://www.youtube.com/watch?v=d6YrfZxKsvU" type="video/mp4">
                        Tu navegador no soporta el video.
                    </video>
                </div>
                <div class="col-md-4">
                    <video controls class="w-100 rounded">
                        <source src="https://www.youtube.com/watch?v=d6YrfZxKsvU" type="video/mp4">
                        Tu navegador no soporta el video.
                    </video>
                </div>
                <div class="col-md-4">
                    <video controls class="w-100 rounded">
                        <source src="https://www.youtube.com/watch?v=d6YrfZxKsvU" type="video/mp4">
                        Tu navegador no soporta el video.
                    </video>
                </div>
            </div>
        </section>
        <footer class="bg-dark text-white pt-4 pb-2 mt-5">
  <div class="container text-center text-md-start">
    <div class="row">
      <!-- Sección de información -->
      <div class="col-md-4 mb-3">
        <h5>Manos del Perú</h5>
        <p>Productos artesanales hechos con amor por manos peruanas.</p>
      </div>

      <!-- Enlaces útiles -->
      <div class="col-md-4 mb-3">
        <h5>Enlaces</h5>
        <ul class="list-unstyled">
          <li><a href="/" class="text-white text-decoration-none">Inicio</a></li>
          <li><a href="/vistas/historial.html" class="text-white text-decoration-none">Tus Compras</a></li>
          <li><a href="/vistas/QuienesSomos.html" class="text-white text-decoration-none">Contacto</a></li>
        </ul>
      </div>

      <!-- Redes sociales -->
      <div class="col-md-4 mb-3">
        <h5>Síguenos</h5>
        <a href="#" class="text-white me-2"><i class="bi bi-facebook"></i></a>
        <a href="#" class="text-white me-2"><i class="bi bi-instagram"></i></a>
        <a href="#" class="text-white"><i class="bi bi-whatsapp"></i></a>
      </div>
    </div>
    <hr class="bg-light" />
    <div class="text-center">
      <p class="mb-0">&copy; 2025 Manos del Perú. Todos los derechos reservados.</p>
    </div>
  </div>
</footer>

    <script src="/js/index.js"></script>
    <script src="/js/usuarios.js"></script>
    <script src="/js/chatbot.js"></script>
</body>

</html>
