package org.consultas.viajes_itca.Servlet.Data;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.ViajesPorHacer;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "CargarViajes", urlPatterns = {"/CargarViajes"})
public class CargarViajes extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        if(request.getSession().getAttribute("viajes")==null) {
            Control control = new Control();
            Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
            List<ViajesPorHacer> viajes = control.obtenerViajesPorHacerList(usuario);
            request.getSession().setAttribute("viajes", viajes);
            response.sendRedirect("Pages/user/misviajes.jsp");
        }else{
            response.sendRedirect("Pages/user/misviajes.jsp");
        }
    }
}
