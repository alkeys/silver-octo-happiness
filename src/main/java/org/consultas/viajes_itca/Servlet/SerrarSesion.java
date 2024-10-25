package org.consultas.viajes_itca.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CerrarSesion", urlPatterns = {"/Cerrar-Sesion"})
public class SerrarSesion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

}