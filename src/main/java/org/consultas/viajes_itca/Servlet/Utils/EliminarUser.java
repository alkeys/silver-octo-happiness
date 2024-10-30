package org.consultas.viajes_itca.Servlet.Utils;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.consultas.viajes_itca.control.Control;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.ViajesPorHacer;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "EliminarUser", urlPatterns = {"/EliminarUser"})
public class EliminarUser  extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            int id=Integer.parseInt(request.getParameter("id"));
            Control control = new Control();
            Usuarios usuario = control.getUsuario(id);
            List<Favoritos> fav = control.getListFav(usuario);
            List< ViajesPorHacer> viajes = control.getListViajes(usuario);
            try {
                if(fav != null){
                    for (Favoritos f : fav) {
                        control.EliminarFav(f.getFavoritoId());
                    }
                }
                if(viajes != null){
                    for (ViajesPorHacer v : viajes) {
                        control.EliminarViaje(v.getViajeId());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            control.EliminarUsuario(usuario);
            List<Usuarios> usuarios = control.getUsuarios();
            request.getSession().setAttribute("usuarios", usuarios);
            response.sendRedirect("Pages/admin/gestionUsuario.jsp");
        }

}
