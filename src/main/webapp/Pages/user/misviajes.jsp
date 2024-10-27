<%@ page import="org.consultas.viajes_itca.entity.Usuarios" %>
<%@ page import="org.consultas.viajes_itca.entity.ViajesPorHacer" %>
<%@ page import="java.util.List" %>


<%-- 
    Document   : misviajes
    Created on : 25 oct 2024, 21:13:24
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%

    Usuarios usuario = (Usuarios) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../../index.jsp");
    } else {
        List<ViajesPorHacer>  viajesPorHacer = (List<ViajesPorHacer>) session.getAttribute("viajes");
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
<div class="bg-light text-center p-5">
    <h1>Mis Viajes</h1>
    <p class="lead">Gestiona tus destinos favoritos y tu lista de viajes por hacer.</p>
</div>


<!-- Sección de Viajes por Hacer -->
<div class="container my-5">
    <h2 class="text-center mb-4">Viajes por Hacer</h2>
    <div class="row">

        <%
            for (ViajesPorHacer viaje : viajesPorHacer) {
        %>
        <div class="col-md-4">
            <div class="card">
                <img src="<%=viaje.getDestinoId().getUrl()%>" class="card-img-top" alt="Ciudad">
                <div class="card-body">
                    <h5 class="card-title"><%=viaje.getDestinoId().getNombre()%>, <%=viaje.getDestinoId().getPais()%></h5>
                    <p class="card-text">Clima: <%=viaje.getDestinoId().getClima()%> | Popularidad: <%=viaje.getDestinoId().getPopularidad()%></p>
                    <a href="../../EliminarViajes?id=<%=viaje.getViajeId()%>" class="btn btn-danger w-100">Eliminar de Viajes</a>
                </div>
            </div>
        </div>
        <%
            }
        %>


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
<%
    }
%>