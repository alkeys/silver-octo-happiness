package org.consultas.viajes_itca.control;

import javax.persistence.Persistence;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.persistencia.ControlUsuario;

public  class Control {
    private final EntityManagerFactory emf;
    private ControlUsuario controlUsuario=null;

    public Control() {
        String unidadPersistencia = "jpaEnoc";
       emf=Persistence.createEntityManagerFactory(unidadPersistencia);
       controlUsuario =new ControlUsuario(emf, Usuarios.class);
    }


    public boolean ValidarUsuario(String email, String pass){
        Usuarios usuario = new Usuarios();
        usuario=controlUsuario.findUsuariosEmailPass(email, pass);
        if(usuario!=null){
            return true;
        }else{
            return false;
        }
    }

    public Usuarios getIdUsuario(Object id){
        Usuarios usuario = new Usuarios();
        usuario=controlUsuario.find(id);
        return usuario;
    }

    public Usuarios getUsuarioEmail(String email){
        Usuarios usuario = new Usuarios();
        usuario=controlUsuario.findUsuariosEmail(email);
        return usuario;
    }
    
    public Usuarios getUsuariosEmailPass(String email,String pass){
        return controlUsuario.findUsuariosEmailPass(email, pass);
    }




}
