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


    public Usuarios BuscarIdUsuario(Object id){
        Usuarios usuario = new Usuarios();
        usuario=controlUsuario.find(id);
        return usuario;
    }

    public Usuarios BuscarUsuarioEmail(String email){
        Usuarios usuario = new Usuarios();
        usuario=controlUsuario.findUsuariosEmail(email);
        return usuario;
    }
    
    public Usuarios BuscarUsuariosEmailPass(String email,String pass){
        return controlUsuario.findUsuariosEmailPass(email, pass);
    }




}
