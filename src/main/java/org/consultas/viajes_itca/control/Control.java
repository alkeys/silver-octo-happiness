package org.consultas.viajes_itca.control;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;

import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.ViajesPorHacer;
import org.consultas.viajes_itca.persistencia.ControlDestinos;
import org.consultas.viajes_itca.persistencia.ControlFav;
import org.consultas.viajes_itca.persistencia.ControlUsuario;
import org.consultas.viajes_itca.persistencia.Controlviajes;
import org.consultas.viajes_itca.persistencia.exceptions.NonexistentEntityException;

import java.util.List;

public class Control {
    private final EntityManagerFactory emf;
    private ControlUsuario controlUsuario = null;
    private ControlDestinos controlDestinos = null;
    private ControlFav controlFavorite = null;
    private Controlviajes controlViajes = null;

    public Control() {
        String unidadPersistencia = "jpaEnoc";
        emf = Persistence.createEntityManagerFactory(unidadPersistencia);
        controlUsuario = new ControlUsuario(emf, Usuarios.class);
        controlDestinos = new ControlDestinos(emf, Destinos.class);
        controlFavorite = new ControlFav(emf, Favoritos.class);
        controlViajes = new Controlviajes(emf, ViajesPorHacer.class);
    }


    public boolean ValidarUsuario(String email, String pass) {
        Usuarios usuario = new Usuarios();
        usuario = controlUsuario.findUsuariosEmailPass(email, pass);
        if (usuario != null) {
            return true;
        } else {
            return false;
        }
    }

    public Usuarios getIdUsuario(Object id) {
        Usuarios usuario = new Usuarios();
        usuario = controlUsuario.find(id);
        return usuario;
    }

    public Usuarios getUsuarioEmail(String email) {
        Usuarios usuario = new Usuarios();
        usuario = controlUsuario.findUsuariosEmail(email);
        return usuario;
    }

    public Usuarios getUsuariosEmailPass(String email, String pass) {
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


    public List<Destinos> getDestinosMasValorados() {
        return controlDestinos.findDestinosMasValorados();
    }

    public List<Destinos> getDestinosMasValorados(int maxResults) {
        return controlDestinos.findDestinosMasValorados(maxResults);
    }


    public void crearUsuario(Usuarios user) {
        controlUsuario.create(user);
    }

    public void updateUsuario(Usuarios user) throws Exception {
        controlUsuario.edit(user);
    }

    public void crearDestino(Destinos destino) {
        controlDestinos.create(destino);
    }

    public void agregarFavorito(Favoritos favoritos) {
        controlFavorite.create(favoritos);

    }


    public Favoritos getFavorite(int id) {
        return controlFavorite.find(id);
    }

    public Favoritos getFavorite(Usuarios usuario) {
        return controlFavorite.findFavoritos(usuario);
    }

    public Favoritos getFavorito(int userid, int iddestino) {
        return controlFavorite.findFavoritos(userid, iddestino);
    }

    public ViajesPorHacer getViajePorHacer(Integer userId, Integer destinoId) {
        return controlViajes.findViajesPorHacer(userId, destinoId);
    }

    public List<ViajesPorHacer> getViajePorHacer() {
        return controlViajes.findAll();
    }

    public void agregarViajePorHacer(ViajesPorHacer viajesPorHacerVerificar) {
        controlViajes.create(viajesPorHacerVerificar);
    }

    public List<Favoritos> obtenerFavoritosList(Usuarios user) {
        return controlFavorite.findFavoritosList(user);
    }

    public List<Destinos> obtenerDestinosListFavoritos(List<Favoritos> favoritos) {
        return controlDestinos.findDestinosListFavoritos(favoritos);
    }

    public List<ViajesPorHacer> obtenerViajesPorHacerList(Usuarios usuario) {
        return controlViajes.findViajesPorHacerList(usuario);
    }

    public void EliminarFav(int id) {
        controlFavorite.destroy(id);
    }

    public void ElimanarViaje(int id) {
        controlViajes.destroy(id);
    }

    /**
     * este metoo devolvera la cantidad de usuarios que tienen el destino en sus viajes por hacer
     *
     * @param destinoId
     * @return
     */
    public int obtenerCantidadIdusuariosDestino(Destinos destinoId) {
        return controlViajes.findCantidadIdusuariosDestino(destinoId);
    }

    public void ActualizarDestino(Destinos destino) throws Exception {
        controlDestinos.edit(destino);
    }

    public long obtenerCantidadIdusuarios() {
        return controlUsuario.findUsuariosCount();
    }

    public List<Usuarios> getUsuarios() {
        return controlUsuario.findAll();
    }

    public int cantidadUsuarios() {
        return (int) controlUsuario.findUsuariosCount();
    }

    public int getCantidadClima(String clima) {
        return controlDestinos.findCantidadClima(clima);
    }

    public long getCantidadClimaviajes(String clima) {
        return controlViajes.findCantidadClima(clima);
    }

    public long getCantidadTipoviajes(String tipo) {
        return controlViajes.findCantidadTipo(tipo);
    }

    public void actualizarDestino(Destinos destino) throws Exception {
        controlDestinos.edit(destino);
    }


    public void eliminarDestino(int destino) {
        List<Integer> idfavoritos = controlFavorite.findDestinosFavoritos(destino);
        List<Integer> idviajes = controlViajes.findDestinosFavoritos(destino);
        try {
            if (idfavoritos != null) {
                for (int id : idfavoritos) {
                    controlFavorite.destroy(id);
                }
            }
            if (idviajes != null){
                for (int id : idviajes) {
                    controlViajes.destroy(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        controlDestinos.destroy(destino);
    }


    public Usuarios getUsuario(int id) {
        return controlUsuario.find(id);
    }

    public void EliminarUsuario(Usuarios usuario) {
        controlUsuario.destroy(usuario.getUserId());
    }

    public long getPopularidadTipo(String tipo) {
        return controlDestinos.findCantidadTipoPopularidad(tipo);
    }

    public List<Favoritos> getListFav(Usuarios usuario) {
        return controlFavorite.findFavoritosList(usuario);
    }

    public List<ViajesPorHacer> getListViajes(Usuarios usuario) {
        return controlViajes.findViajesPorHacerList(usuario);
    }

    public void EliminarViaje(Integer viajeId) {
        controlViajes.destroy(viajeId);
    }


}
