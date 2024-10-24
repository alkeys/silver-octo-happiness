package org.consultas.viajes_itca.control;

import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.persistencia.ControljpaUasuario;

public  class Control {
    private ControljpaUasuario controljpaUasuario = null;

    public Control() {
        String unidadPersistencia = "jpaEnoc";
        controljpaUasuario = new ControljpaUasuario(unidadPersistencia);
    }


    public  void  CreateUser(String nombre, String email, String password,String Preferencias){
        Usuarios usuario = new Usuarios();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setPreferencias(Preferencias);
        controljpaUasuario.CrearUsuario(usuario);
    }


    public Usuarios BuscarUsuario(int id){
        Usuarios usuario = controljpaUasuario.BuscarUsuario(id);
        return usuario;
    }

    public Usuarios BuscarUsuario(String email){
        Usuarios usuario = controljpaUasuario.BuscarUsuario(email);
        return usuario;
    }





}
