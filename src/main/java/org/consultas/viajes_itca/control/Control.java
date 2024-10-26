package org.consultas.viajes_itca.control;

import javax.persistence.Persistence;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.ImgDestino;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.persistencia.ControlDestinos;
import org.consultas.viajes_itca.persistencia.ControlImg;
import org.consultas.viajes_itca.persistencia.ControlUsuario;

import java.util.List;

public  class Control {
    private final EntityManagerFactory emf;
    private ControlUsuario controlUsuario=null;
     private ControlDestinos controlDestinos=null;
     private ControlImg controlImgDestinos=null;

    public Control() {
        String unidadPersistencia = "jpaEnoc";
       emf=Persistence.createEntityManagerFactory(unidadPersistencia);
       controlUsuario =new ControlUsuario(emf, Usuarios.class);
         controlDestinos =new ControlDestinos(emf, Destinos.class);
         controlImgDestinos =new ControlImg(emf, ImgDestino.class);
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


    public Destinos getDestino(int i) {
       return controlDestinos.find(i);
    }

    public List<Destinos> getDestinos() {
        return controlDestinos.findAll();
    }

    public List<Destinos> getDestinos(int maxResults, int firstResult) {
        return controlDestinos.findEntities(maxResults, firstResult);
    }

    public List<ImgDestino> getImgDestinos(List<Destinos> destinos) {
        return controlImgDestinos.findImgDestinos(destinos);
    }

    public List<Destinos> getDestinosMasValorados() {
        return controlDestinos.findDestinosMasValorados();
    }

    public List<Destinos> getDestinosMasValorados(int maxResults) {
        return controlDestinos.findDestinosMasValorados(maxResults);
    }


    public void crearUsuario(Usuarios user) {
        controlUsuario.create(user);
    }
}
