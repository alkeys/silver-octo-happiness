<%@ page import="java.util.List" %>
<%@ page import="org.consultas.viajes_itca.entity.Favoritos" %>
<%--
    Document   : misfavoritos
    Created on : 26 oct 2024, 23:48:21
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Favoritos - Agencia de Viajes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .card {
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .hero-section {
            background: url('https://via.placeholder.com/1200x400') no-repeat center center/cover;
            height: 300px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
        }

        footer a {
            text-decoration: none;
        }

        .btn-remove {
            position: absolute;
            top: 10px;
            right: 10px;
        }
    </style>
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
                    <a class="nav-link" href="../../CargarViajes">Mis Viajes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="../../CargarFav" >Mis Favoritos</a>
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
<div class="hero-section text-center">
    <h1>Mis Favoritos</h1>
</div>

<%
    // Código Java para obtener los destinos favoritos del usuario
    List<Favoritos> favoritos = (List<Favoritos>) session.getAttribute("favoritos");
%>


<!-- Sección de Favoritos -->
<div class="container my-5">
    <h2 class="text-center mb-4">Tus Destinos Guardados</h2>
    <div class="row g-4">
        <!-- Card 1 -->
        <%
            for (Favoritos favorito : favoritos) {
        %>
        <div class="col-md-4 position-relative">
            <div class="card">
                <img src="<%=favorito.getDestinoId().getUrl()%>" class="card-img-top" alt="Bora Bora">
                <div class="card-body">
                    <h5 class="card-title"><%=favorito.getDestinoId().getNombre()%>, <%=favorito.getDestinoId().getPais()%></h5>
                    <p class="card-text">Clima: <%=favorito.getDestinoId().getClima()%> | Popularidad: <%=favorito.getDestinoId().getPopularidad()%></p>
                </div>
                <a href="../../EliminarFav?id=<%=favorito.getFavoritoId()%>" class="btn btn-danger btn-sm btn-remove">Eliminar</a>

            </div>
        </div>
        <%
            }
        %>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-3 mt-5">
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
