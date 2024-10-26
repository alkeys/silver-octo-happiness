<%-- 
    Document   : gestionDestinos
    Created on : 25 oct 2024, 21:57:11
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Destinos - Administrador</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html">Panel de Administración</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="dashboard.html">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="gestion-destinos.html">Gestión de Destinos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gestion-usuarios.html">Gestión de Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="analisis.html">Análisis y Reportes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn btn-outline-primary" href="../Cerrar-Sesion">Cerrar Sesión</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Hero Section -->
    <div class="bg-light text-center p-5">
        <h1>Gestión de Destinos</h1>
        <p class="lead">Añade, edita o elimina destinos turísticos.</p>
    </div>

    <!-- Formulario para añadir nuevos destinos -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Añadir Nuevo Destino</h2>
        <form id="formDestino" class="mb-5">
            <div class="row">
                <div class="col-md-4">
                    <label for="nombreDestino" class="form-label">Nombre del Destino</label>
                    <input type="text" class="form-control" id="nombreDestino" required>
                </div>
                <div class="col-md-4">
                    <label for="tipoDestino" class="form-label">Tipo de Destino</label>
                    <input type="text" class="form-control" id="tipoDestino" required>
                </div>
                <div class="col-md-4">
                    <label for="climaDestino" class="form-label">Clima</label>
                    <input type="text" class="form-control" id="climaDestino" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label for="urlImagen" class="form-label">URL de la Imagen</label>
                    <input type="url" class="form-control" id="urlImagen" placeholder="https://ejemplo.com/imagen.jpg" required>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Añadir Destino</button>
        </form>
    </div>

    <!-- Lista editable de destinos -->
    <div class="container">
        <h2 class="text-center mb-4">Lista de Destinos</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Clima</th>
                    <th>Imagen</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody id="destinosList">
                <tr>
                    <td>Cancún</td>
                    <td>Playa</td>
                    <td>Tropical</td>
                    <td><img src="ruta/a/la/imagen.jpg" alt="Cancún" style="width: 100px;" /></td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="editarDestino(this)">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="eliminarDestino(this)">Eliminar</button>
                    </td>
                </tr>
                <tr>
                    <td>París</td>
                    <td>Ciudad</td>
                    <td>Templado</td>
                    <td><img src="ruta/a/la/imagen.jpg" alt="París" style="width: 100px;" /></td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="editarDestino(this)">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="eliminarDestino(this)">Eliminar</button>
                    </td>
                </tr>
                <!-- Más destinos se pueden agregar aquí -->
            </tbody>
        </table>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Función para añadir destinos
        document.getElementById('formDestino').addEventListener('submit', function(event) {
            event.preventDefault();
            const nombre = document.getElementById('nombreDestino').value;
            const tipo = document.getElementById('tipoDestino').value;
            const clima = document.getElementById('climaDestino').value;
            const urlImagen = document.getElementById('urlImagen').value;

            const nuevaFila = `<tr>
                <td>${nombre}</td>
                <td>${tipo}</td>
                <td>${clima}</td>
                <td><img src="${urlImagen}" alt="${nombre}" style="width: 100px;" /></td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editarDestino(this)">Editar</button>
                    <button class="btn btn-danger btn-sm" onclick="eliminarDestino(this)">Eliminar</button>
                </td>
            </tr>`;
            document.getElementById('destinosList').insertAdjacentHTML('beforeend', nuevaFila);
            this.reset(); // Reiniciar el formulario
        });

        // Función para editar destinos
        function editarDestino(btn) {
            const fila = btn.closest('tr');
            const nombre = fila.cells[0].innerText;
            const tipo = fila.cells[1].innerText;
            const clima = fila.cells[2].innerText;
            const imagen = fila.cells[3].querySelector('img').src;

            document.getElementById('nombreDestino').value = nombre;
            document.getElementById('tipoDestino').value = tipo;
            document.getElementById('climaDestino').value = clima;
            document.getElementById('urlImagen').value = imagen; // Usar la URL de la imagen

            fila.remove(); // Eliminar la fila actual para ser editada
        }

        // Función para eliminar destinos
        function eliminarDestino(btn) {
            const fila = btn.closest('tr');
            fila.remove();
        }
    </script>

</body>
</html>
