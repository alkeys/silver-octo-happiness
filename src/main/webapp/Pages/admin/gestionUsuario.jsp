<%@ page import="java.util.List" %>
<%@ page import="org.consultas.viajes_itca.entity.Usuarios" %><%--
    Document   : gestionUsuario
    Created on : 25 oct 2024, 22:10:22
    Author     : enocc
--%>

<%
    List<Usuarios> usuarios = (List<Usuarios>) session.getAttribute("usuarios");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios - Administrador</title>
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
                        <a class="nav-link active" href="gestionUsuario.jsp">Gestión de Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="analisisReportes.jsp">Análisis y Reportes</a>
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
        <h1>Gestión de Usuarios</h1>
    </div>

    <!-- Tabla de Usuarios -->
    <div class="container my-5">
        <h2 class="mb-4">Lista de Usuarios Registrados</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Correo Electrónico</th>
                    <th>Rol</th>
                </tr>
            </thead>
            <tbody>

                <%
                    for (Usuarios usuario : usuarios) {
                %>
                        <tr>
                    <td><%=usuario.getUserId()%></td>
                    <td><%=usuario.getNombre()%></td>
                    <td><%=usuario.getEmail()%></td>
                    <td>
                        <a href="detalleUsuario.jsp?userxd=<%=usuario.getUserId()%>" class="btn btn-info btn-sm">Ver Detalle</a>
                        <a href="vistaModificarUsuario.jsp?userxd=<%=usuario.getUserId()%>" class="btn btn-warning btn-sm">Modificar</a>
                        <form  class="btn btn-danger btn-sm" action="../../EliminarUser" method="get">
                            <input type="hidden" name="id" value="<%=usuario.getUserId()%>">
                            <button  type="submit" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#eliminarModal">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                %>

            </tbody>
        </table>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function confirmDelete() {
            const url = document.querySelector('.btn-danger[data-bs-target="#confirmDeleteModal"]').getAttribute('href');
            window.location.href = url;
        }
    </script>

    <!-- Modal de Confirmación de Eliminación -->
    <div class="modal fade" id="eliminarModal" tabindex="-1" aria-labelledby="eliminarModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="eliminarModalLabel">Confirmar Eliminación</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ¿Estás seguro de que deseas eliminar a este usuario?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button"  class="btn btn-danger">Eliminar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
