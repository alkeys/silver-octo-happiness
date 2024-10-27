<%@ page import="org.consultas.viajes_itca.entity.Usuarios" %>

<%-- 
    Document   : perfilUsuario
    Created on : 25 oct 2024, 21:22:43
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
    <title>Perfil de Usuario - Agencia de Viajes</title>
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
        <h1>Perfil de Usuario</h1>
        <p class="lead">Administra tu información personal y preferencias de viaje.</p>
    </div>

    <!-- Formulario de Información Personal -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Actualizar Información Personal</h2>
        <form action="../../Modificar-User" method="post">
            <div class="mb-3">
                <label for="nombre" class="form-label"  >Nombre Completo</label>
                <input type="text" class="form-control" name="nombre" id="nombre" placeholder="<%=usuario.getNombre()%>" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Correo Electrónico</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="<%=usuario.getEmail()%>" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Nueva Contraseña</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="Introduce una nueva contraseña">
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirmar Nueva Contraseña</label>
                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Repite la nueva contraseña">
            </div>

            <div class="mb-3">
                <label for="preferencias" class="form-label">Preferencia de Viaje</label>
                <select class="form-select" name="preferencia" id="preferencias">
                    <option selected>Selecciona una preferencia  <%=usuario.getPreferencias()%>...</option>
                    <option value="playa">Playa</option>
                    <option value="montaña">Montaña</option>
                    <option value="ciudad">Ciudad</option>
                    <option value="aventura">Aventura</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100">Guardar Cambios</button>
        </form>
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
