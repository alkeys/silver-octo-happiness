<%-- 
    Document   : analisisReportes
    Created on : 25 oct 2024, 22:27:01
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Análisis y Reportes - Administrador</title>
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
                        <a class="nav-link" href="panelAdmin.jsp">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gestionDestinos.jsp">Gestión de Destinos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gestionUsuario.jsp">Gestión de Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="analisisReportes.jsp">Análisis y Reportes</a>
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
        <h1>Análisis y Reportes</h1>
    </div>

    <!-- Filtros Avanzados -->
    <div class="container my-5">
        <h2 class="mb-4">Filtros Avanzados</h2>
        <form>
            <div class="row">
                <div class="col-md-4 mb-3">
                    <label for="tipoDestino" class="form-label">Tipo de Destino</label>
                    <select class="form-select" id="tipoDestino">
                        <option value="todos">Todos</option>
                        <option value="playa">Playa</option>
                        <option value="montana">Montaña</option>
                        <option value="ciudad">Ciudad</option>
                        <option value="aventura">Aventura</option>
                    </select>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="pais" class="form-label">País</label>
                    <input type="text" class="form-control" id="pais" placeholder="Ingrese el país">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="clima" class="form-label">Clima</label>
                    <select class="form-select" id="clima">
                        <option value="todos">Todos</option>
                        <option value="calido">Cálido</option>
                        <option value="frio">Frío</option>
                        <option value="templado">Templado</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Aplicar Filtros</button>
        </form>
    </div>

    <!-- Gráficos -->
    <div class="container my-5">
        <h2 class="mb-4">Gráficos de Análisis</h2>
        <div class="row">
            <div class="col-md-6 mb-4">
                <h5>Gráfico de Barras: Número de Usuarios por Tipo de Destino</h5>
                <div class="card">
                    <div class="card-body">
                        <!-- Aquí se puede integrar un gráfico de barras -->
                        <div style="height: 300px; background-color: #f8f9fa; text-align: center; line-height: 300px;">
                            Gráfico de Barras
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-4">
                <h5>Gráfico de Pastel: Proporción de Tipos de Destinos Seleccionados</h5>
                <div class="card">
                    <div class="card-body">
                        <!-- Aquí se puede integrar un gráfico de pastel -->
                        <div style="height: 300px; background-color: #f8f9fa; text-align: center; line-height: 300px;">
                            Gráfico de Pastel
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12 mb-4">
                <h5>Gráfico de Dispersión: Relación entre Popularidad, Clima y Tipo de Viaje</h5>
                <div class="card">
                    <div class="card-body">
                        <!-- Aquí se puede integrar un gráfico de dispersión -->
                        <div style="height: 300px; background-color: #f8f9fa; text-align: center; line-height: 300px;">
                            Gráfico de Dispersión
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

</body>
</html>
