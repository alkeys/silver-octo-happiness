<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agencia de Viajes - Bienvenida</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        /* Estilo para la imagen de fondo */
        .hero {
            background-image: url('https://via.placeholder.com/1920x1080?text=Bienvenido+a+tu+próxima+aventura'); 
            background-size: cover;
            background-position: center;
            height: 100vh;
            color: white;
        }

        .overlay {
            background-color: rgba(0, 0, 0, 0.6); /* Oscurece la imagen para mejor legibilidad del texto */
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Agencia de Viajes</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="Pages/iniciaSession.jsp">Iniciar Sesión</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn btn-primary text-white ms-2" href="Pages/Registrase.jsp">Registrarse</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Hero Section con Imagen de Fondo -->
    <div class="hero">
        <div class="overlay text-center">
            <h1 class="display-4">¡Descubre el Mundo con Nosotros!</h1>
            <p class="lead">Organiza tu próxima aventura y vive experiencias inolvidables.</p>
            <a href="explorar.html" class="btn btn-outline-light btn-lg mt-3">Explorar Destinos</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
