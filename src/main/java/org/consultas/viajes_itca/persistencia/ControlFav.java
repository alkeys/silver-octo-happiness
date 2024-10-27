package org.consultas.viajes_itca.persistencia;

import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ControlFav extends AbstractJpaController<Favoritos> {
    private EntityManagerFactory emf = null;
    private FavoritosJpaController controlFav = null;

    public ControlFav(EntityManagerFactory emf, Class<Favoritos> entityClass) {
        super(emf, entityClass);
        this.emf = emf;
    }


    @Override
    protected Object getId(Favoritos entity) {
        return null;
    }

    public void agregarFavorito(Usuarios usuario, String id) {
        controlFav = new FavoritosJpaController(emf);

    }

    public Favoritos findFavoritos(Usuarios usuario) {
       controlFav = new FavoritosJpaController(emf);
        return controlFav.findFavoritos(usuario);
    }

    public List<Favoritos> findFavoritosList(Usuarios usuario) {
        controlFav = new FavoritosJpaController(emf);
        return controlFav.findFavoritosList(usuario);
    }

    public Favoritos findFavoritos(int userid, int destinoId) {
        controlFav = new FavoritosJpaController(emf);
        return controlFav.getFavorito(userid, destinoId);
    }
}
