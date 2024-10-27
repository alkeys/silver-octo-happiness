package org.consultas.viajes_itca.Servlet.AgregarData;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;

import java.io.IOException;

@WebServlet(name = "AgregarFav", urlPatterns = {"/AgregarFav"})
public class AgregarFav extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Control control = new Control();
        int id = Integer.parseInt(request.getParameter("destinoId"));
        Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
        Destinos destino = control.getDestino(id);
        System.out.printf("Usuario: %s\n", usuario.getNombre());
        System.out.printf("Destino: %s\n", destino.getNombre());

        Favoritos favoritosVerificar = control.getFavorito(usuario.getUserId(), destino.getDestinoId());
        if (favoritosVerificar != null) {
            response.sendRedirect("Pages/user/home.jsp");
        }else {
            Favoritos favoritos = new Favoritos();
            favoritos.setDestinoId(destino);
            favoritos.setUserId(usuario);
            control.agregarFavorito(favoritos);
            request.getSession().setAttribute("favoritos", control.obtenerFavoritosList(usuario));
            response.sendRedirect("Pages/user/misfavoritos.jsp");
        }



    }
}
