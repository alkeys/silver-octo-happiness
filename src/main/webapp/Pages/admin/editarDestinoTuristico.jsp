<%-- 
    Document   : editarDestinoTuristico
    Created on : 26 oct 2024, 22:11:15
    Author     : enocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Destino - Administrador</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <!-- Hero Section -->
    <div class="bg-light text-center p-5">
        <h1>Editar Destino Turístico</h1>
    </div>

    <!-- Formulario de Edición de Destino -->
    <div class="container my-5">
        <h2 class="mb-4">Modificar Información del Destino</h2>
        <form>
            <div class="mb-3">
                <label for="nombreDestino" class="form-label">Nombre del Destino</label>
                <input type="text" class="form-control" id="nombreDestino" value="Playa El Tunco" required>
            </div>
            <div class="mb-3">
                <label for="tipoDestino" class="form-label">Tipo de Destino</label>
                <select class="form-select" id="tipoDestino">
                    <option value="playa" selected>Playa</option>
                    <option value="montana">Montaña</option>
                    <option value="ciudad">Ciudad</option>
                    <option value="aventura">Aventura</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="clima" class="form-label">Clima</label>
                <input type="text" class="form-control" id="clima" value="Cálido y soleado" required>
            </div>
            <div class="mb-3">
                <label for="imagenURL" class="form-label">Imagen (URL)</label>
                <input type="url" class="form-control" id="imagenURL" value="https://example.com/tunco.jpg" required>
            </div>
            <button type="submit" class="btn btn-success">Guardar Cambios</button>
            <a href="gestionDestinos.jsp" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Agencia de Viajes. Todos los derechos reservados.</p>
    </footer>

</body>
</html>
