package org.consultas.viajes_itca.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.consultas.viajes_itca.entity.Usuarios;

import java.util.List;

public class ControljpaUasuario {
    private EntityManagerFactory emf = null;
    private  UsuariosJpaController usuariosJpaController = null;

    public ControljpaUasuario(String unidadPersistencia){
        this.emf = Persistence.createEntityManagerFactory(unidadPersistencia);
    }


    public void CrearUsuario(Usuarios usuario){
        usuariosJpaController = new UsuariosJpaController(emf);
        usuariosJpaController.create(usuario);
    }


    public void ActualizarUsuario(Usuarios usuario){
        usuariosJpaController = new UsuariosJpaController(emf);
        try {
            usuariosJpaController.edit(usuario);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public Usuarios BuscarUsuario(int id){
        usuariosJpaController = new UsuariosJpaController(emf);
        Usuarios usuario = usuariosJpaController.findUsuarios(id);
        if(usuario != null){
            System.out.println("Usuario encontrado: "+usuario.getNombre());
            return usuario;
        }else{
            System.out.println("Usuario no encontrado");
            return null;
        }
    }

    public Usuarios BuscarUsuario(String email){
        usuariosJpaController = new UsuariosJpaController(emf);
        Usuarios usuario = usuariosJpaController.findUsuarios(email);
        if(usuario != null){
            System.out.println("Usuario encontrado: "+usuario.getNombre());
            return usuario;
        }else{
            System.out.println("Usuario no encontrado");
            return null;
        }
    }


    public void borraUsuario(int id){
        usuariosJpaController = new UsuariosJpaController(emf);
        try {
            usuariosJpaController.destroy(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<Usuarios> listarUsuarios(){
        usuariosJpaController = new UsuariosJpaController(emf);
        List<Usuarios> usuarios = usuariosJpaController.findUsuariosEntities();
        if(usuarios != null){
            System.out.println("Usuarios encontrados: "+usuarios.size());
            return usuarios;
        }else{
            System.out.println("Usuarios no encontrados");
            return null;
        }
    }


    public List<Usuarios> listarUsuarios(int maxResults, int firstResult){
        usuariosJpaController = new UsuariosJpaController(emf);
        List<Usuarios> usuarios = usuariosJpaController.findUsuariosEntities(maxResults, firstResult);
        if(usuarios != null){
            System.out.println("Usuarios encontrados: "+usuarios.size());
            return usuarios;
        }else{
            System.out.println("Usuarios no encontrados");
            return null;
        }
    }

}
