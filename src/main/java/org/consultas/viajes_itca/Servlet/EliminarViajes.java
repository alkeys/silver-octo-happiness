package org.consultas.viajes_itca.Servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.ViajesPorHacer;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "EliminarViajes", urlPatterns = {"/EliminarViajes"})
public class EliminarViajes extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        int id=Integer.parseInt(request.getParameter("id"));
        Control control = new Control();
        control.ElimanarViaje(id);
        Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
        List<ViajesPorHacer> viajes = control.obtenerViajesPorHacerList(usuario);
        request.getSession().setAttribute("viajes", viajes);
        response.sendRedirect("Pages/user/misviajes.jsp");
    }
}
