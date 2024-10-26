package org.consultas.viajes_itca.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CrearUser", urlPatterns = {"/CrearUser"})
public class CrearUser extends HelloServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String preferencias = request.getParameter("preferencia");
        Usuarios user = new Usuarios();
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(password);
        user.setPreferencias(preferencias);
        Control control = new Control();
        control.crearUsuario(user);
        response.sendRedirect("index.jsp");

    }

}
