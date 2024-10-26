<%-- 
    Document   : correoYaRegistrado
    Created on : 26 oct 2024, 00:05:12
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Correo Electrónico Ya Registrado</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <!-- Hero Section -->
    <div class="bg-light text-center p-5">
        <h1>Correo Electrónico Ya Registrado</h1>
    </div>

    <!-- Mensaje de confirmación -->
    <div class="container my-5 text-center">
        <div class="alert alert-warning" role="alert">
            El correo electrónico ingresado ya está registrado. Por favor, intenta con otro correo o inicia sesión.
        </div>
        <a href="Registrase.jsp" class="btn btn-primary">Volver al Registro</a>
        <a href="iniciaSession.jsp" class="btn btn-secondary">Iniciar Sesión</a>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

</body>
</html>
