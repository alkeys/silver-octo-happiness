package org.consultas.viajes_itca.Servlet.Utils;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "EliminarDestino", urlPatterns = {"/EliminarDestino"})
public class EliminarDestino extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        int id = Integer.parseInt(request.getParameter("destinoId"));
        Control control = new Control();
        Destinos destino = control.getDestino(id);
        control.eliminarDestino(destino.getDestinoId());
        List<Destinos> destinosAdmin = control.getDestinos();
        request.getSession().setAttribute("destinosAdmin", destinosAdmin);
        response.sendRedirect("Pages/admin/gestionDestinos.jsp");

    }
}
