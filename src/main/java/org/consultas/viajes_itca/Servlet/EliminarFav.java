package org.consultas.viajes_itca.Servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EliminarFav", value = "/EliminarFav")
public class EliminarFav  extends HttpServlet  {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
       int id=Integer.parseInt(request.getParameter("id"));
        Control control = new Control();
        control.EliminarFav(id);
        Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
        List<Favoritos> favoritos = control.obtenerFavoritosList(usuario);
        request.getSession().setAttribute("favoritos", favoritos);
        response.sendRedirect("Pages/user/misfavoritos.jsp");
    }

}
