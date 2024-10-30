package org.consultas.viajes_itca.Servlet.Utils;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Usuarios;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AutalizarDestino", urlPatterns = {"/AutalizarDestino"})
public class AutalizarDestino extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Control control = new Control();
        int id = Integer.parseInt(request.getParameter("id"));
        Destinos destino = control.getDestino(id);
        String nombre = request.getParameter("nombreDestino");
        String TipodeDestino = request.getParameter("tipoDestino");
        String clima = request.getParameter("clima");
        String pais = request.getParameter("pais");
        String url = request.getParameter("urlimg");

        if (nombre != null) {
            destino.setNombre(nombre);
        }
        if (TipodeDestino != null) {
            destino.setTipoDestino(TipodeDestino);
        }
        if (clima != null) {
            destino.setClima(clima);
        }
        if (pais != null) {
            destino.setPais(pais);
        }
        if (url != null) {
            destino.setUrl(url);
        }

        try {

            control.actualizarDestino(destino);
            List<Destinos> destinosAdmin = control.getDestinos();
            request.getSession().setAttribute("destinosAdmin", destinosAdmin);
            List<Destinos> destinosMasValorados = control.getDestinosMasValorados(2);
            request.getSession().setAttribute("destinosMasValorados", destinosMasValorados);
            response.sendRedirect("Pages/admin/gestionDestinos.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
