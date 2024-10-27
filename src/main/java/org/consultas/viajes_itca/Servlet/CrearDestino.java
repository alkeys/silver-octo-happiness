package org.consultas.viajes_itca.Servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;

import java.io.IOException;

@WebServlet(name = "CrearDestino", urlPatterns = {"/CrearDestino"})
public class CrearDestino extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Control control = new Control();
        Destinos destino = new Destinos();

        String tipoDestino = request.getParameter("tipoDestino");
        String nombreDestino = request.getParameter("nombreD");
        String climaDestino = request.getParameter("clima");
        String paisDestino = request.getParameter("nombrepais");
        String urlImagen = request.getParameter("urlImagen");

        destino.setTipoDestino(tipoDestino);
        destino.setNombre(nombreDestino);
        destino.setClima(climaDestino);
        destino.setPais(paisDestino);
        destino.setUrl(urlImagen);


        control.crearDestino(destino);
        response.sendRedirect("Pages/admin/gestionDestinos.jsp");
    }

}
