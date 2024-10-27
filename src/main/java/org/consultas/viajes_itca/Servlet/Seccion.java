/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.consultas.viajes_itca.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.ViajesPorHacer;

/**
 *
 * @author alex
 */
@WebServlet(name = "Seccion", urlPatterns = {"/Seccion"})
public class Seccion extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Que haces aqui papu</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Control control = new Control();
         String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        if (control.ValidarUsuario(email, password)) {
            Usuarios usuario = control.getUsuarioEmail(email);
            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
                List<Destinos> destinos = control.getDestinosMasValorados(3);
                List<Destinos> destinos2 = control.getDestinos(50,0);
                request.getSession().setAttribute("destinos2", destinos2);
                request.getSession().setAttribute("destinos", destinos);
              if (usuario.getNombre().equalsIgnoreCase("admin")&& usuario.getEmail().equalsIgnoreCase("admin@admin.com")) {
                    List<Usuarios> usuarios = control.getUsuarios();
                    List<Destinos> destinosAdmin = control.getDestinos();
                    List<Destinos> destinosMasValorados = control.getDestinosMasValorados(2);
                    List<ViajesPorHacer> viajes = control.getViajePorHacer();
                    request.getSession().setAttribute("viajes", viajes);
                    request.getSession().setAttribute("destinosMasValorados", destinosMasValorados);
                    request.getSession().setAttribute("usuarios", usuarios);
                    request.getSession().setAttribute("destinosAdmin", destinosAdmin);


                    response.sendRedirect("Pages/admin/panelAdmin.jsp");
                } else {
                    response.sendRedirect("Pages/user/home.jsp");
                }
            }

        } else {
          response.sendRedirect("Pages/usuarioinvalido.jsp");
        }



    }

    public void destroy() {
    }
}
