<%-- 
    Document   : detalleUsuario
    Created on : 25 oct 2024, 22:20:19
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles del Usuario - Administrador</title>
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
                        <a class="nav-link" href="gestion-destinos.html">Gestión de Destinos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="gestion-usuarios.html">Gestión de Usuarios</a>
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
        <h1>Detalles del Usuario</h1>
    </div>

    <!-- Detalles del Usuario -->
    <div class="container my-5">
        <h2 class="mb-4">Información del Usuario</h2>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Nombre: Juan Pérez</h5>
                <p class="card-text">Correo Electrónico: juan.perez@example.com</p>
                <p class="card-text">Contraseña: ********</p>
                <p class="card-text">Preferencias de Viaje: 
                    <select class="form-select" disabled>
                        <option value="playa" selected>Playa</option>
                        <option value="montana">Montaña</option>
                        <option value="ciudad">Ciudad</option>
                        <option value="aventura">Aventura</option>
                    </select>
                </p>
                <a href="gestion-usuarios.html" class="btn btn-primary">Regresar a la Lista</a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

</body>
</html>
