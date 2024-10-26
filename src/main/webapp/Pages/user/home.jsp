<%@ page import="org.consultas.viajes_itca.entity.Usuarios" %>
<%@ page import="java.util.List" %>
<%@ page import="org.consultas.viajes_itca.entity.Destinos" %>
<%@ page import="org.consultas.viajes_itca.entity.ImgDestino" %>
<%@ page import="org.consultas.viajes_itca.control.Control" %><%--
    Document   : home
    Created on : 24 oct 2024, 20:01:34
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuarios usuario = (Usuarios) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("index.jsp");
    }
    List<Destinos> destinos = (List<Destinos>) session.getAttribute("destinos");
    Control control = new Control();
    List<ImgDestino> imgDestinos = control.getImgDestinos(destinos);
%>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Inicio - Agencia de Viajes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.html">Agencia de Viajes</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="explorar.html">Explorar Destinos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="mis-viajes.html">Mis Viajes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="perfil.html">Perfil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="analisis.html">Análisis Personalizado</a>
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
        <h1>¡Explora los Mejores Destinos!</h1>
        <p class="lead">Encuentra el lugar ideal para tu próxima aventura.</p>
        <a href="explorar.html" class="btn btn-primary btn-lg">Explorar Ahora</a>
    </div>

    <!-- Barra de Búsqueda y Filtros -->
    <div class="container my-5">
        <form class="row g-3">
            <div class="col-md-4">
                <label for="tipoDestino" class="form-label">Tipo de Destino</label>
                <select class="form-select" id="tipoDestino">
                    <option selected>Selecciona un tipo...</option>
                    <option value="playa">Playa</option>
                    <option value="montaña">Montaña</option>
                    <option value="ciudad">Ciudad</option>
                    <option value="aventura">Aventura</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="pais" class="form-label">País</label>
                <input type="text" class="form-control" id="pais" placeholder="Introduce un país">
            </div>
            <div class="col-md-4">
                <label for="clima" class="form-label">Clima</label>
                <select class="form-select" id="clima">
                    <option selected>Selecciona el clima...</option>
                    <option value="tropical">Tropical</option>
                    <option value="templado">Templado</option>
                    <option value="frío">Frío</option>
                </select>
            </div>
            <div class="col-12 d-flex justify-content-center mt-3">
                <button type="submit" class="btn btn-primary">Buscar Destinos</button>
            </div>
        </form>
    </div>

    <!-- Lista de Destinos -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Destinos Populares</h2>
        <div class="row">

            <%
                int i = 0;
                for (Destinos destino : destinos) {
            %>
            <div class="col-md-4">
                <div class="card">
                    <img src="<%=imgDestinos.get(i).getUrl()%>" class="card-img-top" alt="Playa">
                    <div class="card-body">
                        <h5 class="card-title"><%=destino.getNombre()%>,<%=destino.getPais()%></h5>
                        <p class="card-text">Clima: <%=destino.getClima()%> | Popularidad: <%=destino.getPopularidad()%></p>
                        <a href="detalle-destino.html" class="btn btn-outline-primary w-100">Ver Detalles</a>
                    </div>
                </div>
            </div>
            <%
                    i++;
                }%>

            <div class="col-md-4">
                <div class="card">
                    <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Montaña">
                    <div class="card-body">
                        <h5 class="card-title">Monte Everest, Nepal</h5>
                        <p class="card-text">Clima: Frío | Popularidad: Muy Alta</p>
                        <a href="detalle-destino.html" class="btn btn-outline-primary w-100">Ver Detalles</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Ciudad">
                    <div class="card-body">
                        <h5 class="card-title">París, Francia</h5>
                        <p class="card-text">Clima: Templado | Popularidad: Muy Alta</p>
                        <a href="detalle-destino.html" class="btn btn-outline-primary w-100">Ver Detalles</a>
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
