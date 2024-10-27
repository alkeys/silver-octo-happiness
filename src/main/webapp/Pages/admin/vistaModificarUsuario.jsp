<%@ page import="org.consultas.viajes_itca.entity.Usuarios" %>
<%@ page import="java.util.List" %><%--
    Document   : vistaModificarUsuario
    Created on : 25 oct 2024, 22:16:16
    Author     : enocc
--%>
<%
    List<Usuarios> usuarios = (List<Usuarios>) session.getAttribute("usuarios");
    int id = Integer.parseInt(request.getParameter("userxd"));
    Usuarios usuario = null;
    for (Usuarios u : usuarios) {
        if (u.getUserId() == id) {
            usuario = u;
            break;
        }
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Usuario - Administrador</title>
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
        <h1>Modificar Usuario</h1>
    </div>

    <!-- Formulario de Modificación -->
    <div class="container my-5">
        <h2 class="mb-4">Editar Información del Usuario</h2>
        <form action="../../Modificar-User" method="post">
            <input type="hidden" name="id" value="<%=usuario.getUserId()%>">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control"  name="nombre" id="nombre" value="<%=usuario.getNombre()%>" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Correo Electrónico</label>
                <input type="email" class="form-control" name="email" id="email" value="<%=usuario.getEmail()%>" required>
            </div>
            <div class="mb-3">
                <label for="contrasena" class="form-label">Contraseña</label>
                <input type="password" class="form-control" name="password" id="contrasena" placeholder="Ingrese nueva contraseña">
            </div>
            <div class="mb-3">
                <label for="preferencia" class="form-label">Preferencias de Viaje</label>
                <select class="form-select" name="preferencia" id="preferencia">
                    <option value="" disabled selected>Seleccione una opción</option>
                    <option value="playa">Playa</option>
                    <option value="montana">Montaña</option>
                    <option value="ciudad">Ciudad</option>
                    <option value="aventura">Aventura</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success">Guardar Cambios</button>
            <a href="gestionUsuario.jsp" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

</body>
</html>