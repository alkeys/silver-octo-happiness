package org.consultas.viajes_itca;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.persistencia.ControljpaUasuario;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Usuarios usuario = new Usuarios();
        Control control = new Control();
       usuario= control.BuscarUsuario("carlos744@gmail.com");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + usuario.getNombre()+usuario.getEmail() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}