<%@ page import="java.util.List" %>
<%@ page import="org.consultas.viajes_itca.entity.Destinos" %>
<%@ page import="org.consultas.viajes_itca.control.Control" %><%--
    Document   : gestionDestinos
    Created on : 25 oct 2024, 21:57:11
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Destinos - Administrador</title>
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
                        <a class="nav-link active" href="gestionDestinos.jsp">Gestión de Destinos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gestionUsuario.jsp">Gestión de Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="analisisReportes.jsp">Análisis y Reportes</a>
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
        <h1>Gestión de Destinos</h1>
        <p class="lead">Añade, edita o elimina destinos turísticos.</p>
    </div>

    <!-- Formulario para añadir nuevos destinos -->
    <div class="container my-5">
        <h2 class="text-center mb-4">Añadir Nuevo Destino</h2>
        <form method="post" action="../../CrearDestino"  id="formDestino" class="mb-5">
            <div class="row">
         <div class="col-md-4">
            <label for="tipoDestino" class="form-label">Tipo de Destino</label>
            <select class="form-select" id="tipoDestino" name="tipoDestino">
                <option selected>Selecciona un tipo...</option>
                <option value="playa">Playa</option>
                <option value="montaña">Montaña</option>
                <option value="ciudad">Ciudad</option>
                <option value="aventura">Aventura</option>
            </select>
        </div>
                <div class="col-md-4">
                    <label for="tipoDestino" class="form-label">Nombre del Destino</label>
                    <input type="text" class="form-control" id="nombreD" name="nombreD" required>
                </div>
                <div class="col-md-4">
                 <div class="col-md-4 mb-3">
                    <label for="clima" class="form-label">Clima</label>
                    <select class="form-select" id="clima" name="clima">
                        <option value="todos">Todos</option>
                        <option value="calido">Cálido</option>
                        <option value="frio">Frío</option>
                        <option value="templado">Templado</option>
                    </select>
                </div>
                </div>
                 <div class="col-md-4">
                    <label for="nombrepais" class="form-label">Nombre del Pais</label>
                    <input type="text" class="form-control" id="nombrepais" name="nombrepais" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label for="urlImagen" class="form-label">URL de la Imagen</label>
                    <input type="url" class="form-control" id="urlImagen" name="urlImagen" placeholder="https://ejemplo.com/imagen.jpg" required>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Añadir Destino</button>
        </form>
    </div>

    <!-- Lista editable de destinos -->
    <div class="container">
        <h2 class="text-center mb-4">Lista de Destinos</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Clima</th>
                    <th>Imagen</th>
                    <th>Acciones</th>
                </tr>
            </thead>




            <tbody id="destinosList">
            <%
                Control control = new Control();
                List<Destinos> destinos= (List<Destinos>) session.getAttribute("destinosAdmin");
                for(Destinos destino: destinos){
            %>
                <tr>
                    <td><%=destino.getDestinoId()%></td>
                    <td><%=destino.getNombre()%></td>
                    <td><%=destino.getTipoDestino()%></td>
                    <td><%=destino.getClima()%></td>
                    <td><img src="<%=destino.getUrl()%>" alt="Cancún" style="width: 100px;" /></td>
                    <td>
                        <a href="editarDestinoTuristico.jsp?id=<%=destino.getDestinoId()%>" class="btn btn-warning btn-sm">Editar</a>
                        <form class="btn btn-danger btn-sm" method="get" action="../../EliminarDestino">
                            <input type="hidden"  name="destinoId" value="<%=destino.getDestinoId()%>">
                            <button type="submit"  class="btn btn-danger btn-sm">Eliminar</button>
                        </form>

                    </td>
                </tr>
            <%
                }
            %>

            </tbody>
        </table>
    </div>






    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
