package org.consultas.viajes_itca;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Usuarios;

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

        String email = request.getParameter("correo");
        String pass = request.getParameter("pass");

        usuario = control.getUsuariosEmailPass(email, pass);

        if(usuario==null){
            usuario = new Usuarios();
            usuario.setNombre("Usuario no encontrado");
            usuario.setEmail("Usuario no encontrado");
            usuario.setPreferencias("Usuario no encontrado");
        }


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + usuario.getNombre()+"\t"+usuario.getEmail()+"\t"+usuario.getPreferencias() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}