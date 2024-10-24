/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.consultas.viajes_itca.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author alex
 */
public class FavoritosJpaController implements Serializable {

    public FavoritosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Favoritos favoritos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios userId = favoritos.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                favoritos.setUserId(userId);
            }
            Destinos destinoId = favoritos.getDestinoId();
            if (destinoId != null) {
                destinoId = em.getReference(destinoId.getClass(), destinoId.getDestinoId());
                favoritos.setDestinoId(destinoId);
            }
            em.persist(favoritos);
            if (userId != null) {
                userId.getFavoritosCollection().add(favoritos);
                userId = em.merge(userId);
            }
            if (destinoId != null) {
                destinoId.getFavoritosCollection().add(favoritos);
                destinoId = em.merge(destinoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Favoritos favoritos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos persistentFavoritos = em.find(Favoritos.class, favoritos.getFavoritoId());
            Usuarios userIdOld = persistentFavoritos.getUserId();
            Usuarios userIdNew = favoritos.getUserId();
            Destinos destinoIdOld = persistentFavoritos.getDestinoId();
            Destinos destinoIdNew = favoritos.getDestinoId();
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                favoritos.setUserId(userIdNew);
            }
            if (destinoIdNew != null) {
                destinoIdNew = em.getReference(destinoIdNew.getClass(), destinoIdNew.getDestinoId());
                favoritos.setDestinoId(destinoIdNew);
            }
            favoritos = em.merge(favoritos);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getFavoritosCollection().remove(favoritos);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getFavoritosCollection().add(favoritos);
                userIdNew = em.merge(userIdNew);
            }
            if (destinoIdOld != null && !destinoIdOld.equals(destinoIdNew)) {
                destinoIdOld.getFavoritosCollection().remove(favoritos);
                destinoIdOld = em.merge(destinoIdOld);
            }
            if (destinoIdNew != null && !destinoIdNew.equals(destinoIdOld)) {
                destinoIdNew.getFavoritosCollection().add(favoritos);
                destinoIdNew = em.merge(destinoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = favoritos.getFavoritoId();
                if (findFavoritos(id) == null) {
                    throw new NonexistentEntityException("The favoritos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos favoritos;
            try {
                favoritos = em.getReference(Favoritos.class, id);
                favoritos.getFavoritoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The favoritos with id " + id + " no longer exists.", enfe);
            }
            Usuarios userId = favoritos.getUserId();
            if (userId != null) {
                userId.getFavoritosCollection().remove(favoritos);
                userId = em.merge(userId);
            }
            Destinos destinoId = favoritos.getDestinoId();
            if (destinoId != null) {
                destinoId.getFavoritosCollection().remove(favoritos);
                destinoId = em.merge(destinoId);
            }
            em.remove(favoritos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Favoritos> findFavoritosEntities() {
        return findFavoritosEntities(true, -1, -1);
    }

    public List<Favoritos> findFavoritosEntities(int maxResults, int firstResult) {
        return findFavoritosEntities(false, maxResults, firstResult);
    }

    private List<Favoritos> findFavoritosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Favoritos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Favoritos findFavoritos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Favoritos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFavoritosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Favoritos> rt = cq.from(Favoritos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
