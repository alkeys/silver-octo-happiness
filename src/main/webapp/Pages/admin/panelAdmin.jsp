<%-- 
    Document   : panelAdmin
    Created on : 25 oct 2024, 21:35:02
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Administrador</title>
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
                        <a class="nav-link active" href="dashboard.html">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gestion-destinos.html">Gestión de Destinos</a>
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
        <h1>Panel de Administración</h1>
        <p class="lead">Monitorea y gestiona el sistema desde este panel.</p>
    </div>

    <!-- Resumen del Sistema -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Resumen General</h2>
        <div class="row text-center">
            <div class="col-md-4">
                <div class="card bg-info text-white mb-4">
                    <div class="card-body">
                        <h3>Cantidad de Usuarios</h3>
                        <p class="display-4">150</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card bg-warning text-white mb-4">
                    <div class="card-body">
                        <h3>Destinos Disponibles</h3>
                        <p class="display-4">45</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card bg-success text-white mb-4">
                    <div class="card-body">
                        <h3>Destinos Populares</h3>
                        <p class="display-4">Cancún, París</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Gráficos de Preferencias -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Análisis de Preferencias Generales</h2>
        <div class="row">
            <div class="col-md-6">
                <canvas id="graficoTiposViaje"></canvas>
            </div>
            <div class="col-md-6">
                <canvas id="graficoClima"></canvas>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <script>
        // Gráfico de tipos de viaje
        const ctx1 = document.getElementById('graficoTiposViaje').getContext('2d');
        new Chart(ctx1, {
            type: 'bar',
            data: {
                labels: ['Playa', 'Montaña', 'Ciudad', 'Aventura'],
                datasets: [{
                    label: 'Usuarios por Tipo de Viaje',
                    data: [50, 30, 40, 20],
                    backgroundColor: ['#3498db', '#2ecc71', '#e74c3c', '#9b59b6']
                }]
            }
        });

        // Gráfico de clima preferido
        const ctx2 = document.getElementById('graficoClima').getContext('2d');
        new Chart(ctx2, {
            type: 'pie',
            data: {
                labels: ['Tropical', 'Templado', 'Frío'],
                datasets: [{
                    data: [60, 25, 15],
                    backgroundColor: ['#f1c40f', '#3498db', '#95a5a6']
                }]
            }
        });
    </script>

</body>
</html>