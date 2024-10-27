<%-- 
    Document   : usuarioinvalido
    Created on : 26 oct 2024, 23:02:35
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error de inicio de sesión</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

    <!-- Contenedor principal -->
    <div class="container d-flex align-items-center justify-content-center" style="min-height: 100vh;">
        <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
            <div class="card-body text-center">
                <h1 class="display-6 text-danger">¡Error!</h1>
                <p class="lead mb-4">Usuario no encontrado o correo inválido.</p>
                <img src="https://as1.ftcdn.net/v2/jpg/02/23/61/60/1000_F_223616064_samvdS9HEZdQT7AaUPFErbwrZZP5RXWA.jpg" alt="Error" class="mb-3">

                <a href="iniciaSession.jsp" class="btn btn-primary btn-block mb-2 w-100">Volver al Login</a>
                <a href="Registrase.jsp" class="btn btn-outline-secondary btn-block w-100">Registrarse</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
