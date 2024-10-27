package org.consultas.viajes_itca.Servlet.Data;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;

import java.io.IOException;

@WebServlet(name = "CargarFav", urlPatterns = {"/CargarFav"})
public class CargarFav extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        if(request.getSession().getAttribute("favoritos")==null) {
            Control control = new Control();
            Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
            request.getSession().setAttribute("favoritos", control.obtenerFavoritosList(usuario));
            response.sendRedirect("Pages/user/misfavoritos.jsp");
        }else{
            response.sendRedirect("Pages/user/misfavoritos.jsp");
        }
    }

}
