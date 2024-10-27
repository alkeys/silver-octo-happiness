<%@ page import="org.consultas.viajes_itca.entity.Usuarios" %>


<%-- 
    Document   : misviajes
    Created on : 25 oct 2024, 21:13:24
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

 Usuarios usuario = (Usuarios) session.getAttribute("usuario");
if (usuario == null) {
        response.sendRedirect("../../index.jsp");
    }
%>


<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Viajes - Agencia de Viajes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="index.html">Agencia de Viajes</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="home.jsp">Explorar Destinos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="misviajes.jsp">Mis Viajes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="perfilUsuario.jsp">Perfil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-primary" href="../../Cerrar-Sesion">Cerrar Sesión</a>
                </li>

            </ul>
        </div>
    </div>
</nav>

    <!-- Hero Section -->
    <div class="bg-light text-center p-5">
        <h1>Mis Viajes</h1>
        <p class="lead">Gestiona tus destinos favoritos y tu lista de viajes por hacer.</p>
    </div>

    <!-- Sección de Destinos Favoritos -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Destinos Favoritos</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Playa">
                    <div class="card-body">
                        <h5 class="card-title">Cancún, México</h5>
                        <p class="card-text">Clima: Tropical | Popularidad: Alta</p>
                        <button class="btn btn-danger w-100">Eliminar de Favoritos</button>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Montaña">
                    <div class="card-body">
                        <h5 class="card-title">Monte Everest, Nepal</h5>
                        <p class="card-text">Clima: Frío | Popularidad: Muy Alta</p>
                        <button class="btn btn-danger w-100">Eliminar de Favoritos</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Sección de Viajes por Hacer -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Viajes por Hacer</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Ciudad">
                    <div class="card-body">
                        <h5 class="card-title">París, Francia</h5>
                        <p class="card-text">Clima: Templado | Popularidad: Muy Alta</p>
                        <button class="btn btn-danger w-100">Eliminar de Lista</button>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Aventura">
                    <div class="card-body">
                        <h5 class="card-title">Amazonas, Brasil</h5>
                        <p class="card-text">Clima: Tropical | Popularidad: Media</p>
                        <button class="btn btn-danger w-100">Eliminar de Lista</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
        <div>
            <a href="https://www.facebook.com" class="text-white me-3">Facebook</a>
            <a href="https://www.instagram.com" class="text-white me-3">Instagram</a>
            <a href="https://www.twitter.com" class="text-white">Twitter</a>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
