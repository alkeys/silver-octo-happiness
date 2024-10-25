/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.consultas.viajes_itca.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Usuarios;

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Control control = new Control();
        PrintWriter out = response.getWriter();
        if (control.ValidarUsuario(email, password)) {
            Usuarios usuario = control.getUsuarioEmail(email);
            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
              if (usuario.getNombre().equalsIgnoreCase("admin")) {
                    response.sendRedirect("admin.jsp");
                } else {
                    response.sendRedirect("Pages/home.jsp");
                }
            }

        } else {
            out.println("<html><body>");
            out.println("<h1>Usuario Invalido</h1>");
            out.println("</body></html>");
        }



    }

    public void destroy() {
    }
}
