package org.consultas.viajes_itca.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.consultas.viajes_itca.entity.ViajesPorHacer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.NoResultException;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.persistencia.exceptions.NonexistentEntityException;


public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getViajesPorHacerCollection() == null) {
            usuarios.setViajesPorHacerCollection(new ArrayList<ViajesPorHacer>());
        }
        if (usuarios.getFavoritosCollection() == null) {
            usuarios.setFavoritosCollection(new ArrayList<Favoritos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ViajesPorHacer> attachedViajesPorHacerCollection = new ArrayList<ViajesPorHacer>();
            for (ViajesPorHacer viajesPorHacerCollectionViajesPorHacerToAttach : usuarios.getViajesPorHacerCollection()) {
                viajesPorHacerCollectionViajesPorHacerToAttach = em.getReference(viajesPorHacerCollectionViajesPorHacerToAttach.getClass(), viajesPorHacerCollectionViajesPorHacerToAttach.getViajeId());
                attachedViajesPorHacerCollection.add(viajesPorHacerCollectionViajesPorHacerToAttach);
            }
            usuarios.setViajesPorHacerCollection(attachedViajesPorHacerCollection);
            Collection<Favoritos> attachedFavoritosCollection = new ArrayList<Favoritos>();
            for (Favoritos favoritosCollectionFavoritosToAttach : usuarios.getFavoritosCollection()) {
                favoritosCollectionFavoritosToAttach = em.getReference(favoritosCollectionFavoritosToAttach.getClass(), favoritosCollectionFavoritosToAttach.getFavoritoId());
                attachedFavoritosCollection.add(favoritosCollectionFavoritosToAttach);
            }
            usuarios.setFavoritosCollection(attachedFavoritosCollection);
            em.persist(usuarios);
            for (ViajesPorHacer viajesPorHacerCollectionViajesPorHacer : usuarios.getViajesPorHacerCollection()) {
                Usuarios oldUserIdOfViajesPorHacerCollectionViajesPorHacer = viajesPorHacerCollectionViajesPorHacer.getUserId();
                viajesPorHacerCollectionViajesPorHacer.setUserId(usuarios);
                viajesPorHacerCollectionViajesPorHacer = em.merge(viajesPorHacerCollectionViajesPorHacer);
                if (oldUserIdOfViajesPorHacerCollectionViajesPorHacer != null) {
                    oldUserIdOfViajesPorHacerCollectionViajesPorHacer.getViajesPorHacerCollection().remove(viajesPorHacerCollectionViajesPorHacer);
                    oldUserIdOfViajesPorHacerCollectionViajesPorHacer = em.merge(oldUserIdOfViajesPorHacerCollectionViajesPorHacer);
                }
            }
            for (Favoritos favoritosCollectionFavoritos : usuarios.getFavoritosCollection()) {
                Usuarios oldUserIdOfFavoritosCollectionFavoritos = favoritosCollectionFavoritos.getUserId();
                favoritosCollectionFavoritos.setUserId(usuarios);
                favoritosCollectionFavoritos = em.merge(favoritosCollectionFavoritos);
                if (oldUserIdOfFavoritosCollectionFavoritos != null) {
                    oldUserIdOfFavoritosCollectionFavoritos.getFavoritosCollection().remove(favoritosCollectionFavoritos);
                    oldUserIdOfFavoritosCollectionFavoritos = em.merge(oldUserIdOfFavoritosCollectionFavoritos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getUserId());
            Collection<ViajesPorHacer> viajesPorHacerCollectionOld = persistentUsuarios.getViajesPorHacerCollection();
            Collection<ViajesPorHacer> viajesPorHacerCollectionNew = usuarios.getViajesPorHacerCollection();
            Collection<Favoritos> favoritosCollectionOld = persistentUsuarios.getFavoritosCollection();
            Collection<Favoritos> favoritosCollectionNew = usuarios.getFavoritosCollection();
            Collection<ViajesPorHacer> attachedViajesPorHacerCollectionNew = new ArrayList<ViajesPorHacer>();
            for (ViajesPorHacer viajesPorHacerCollectionNewViajesPorHacerToAttach : viajesPorHacerCollectionNew) {
                viajesPorHacerCollectionNewViajesPorHacerToAttach = em.getReference(viajesPorHacerCollectionNewViajesPorHacerToAttach.getClass(), viajesPorHacerCollectionNewViajesPorHacerToAttach.getViajeId());
                attachedViajesPorHacerCollectionNew.add(viajesPorHacerCollectionNewViajesPorHacerToAttach);
            }
            viajesPorHacerCollectionNew = attachedViajesPorHacerCollectionNew;
            usuarios.setViajesPorHacerCollection(viajesPorHacerCollectionNew);
            Collection<Favoritos> attachedFavoritosCollectionNew = new ArrayList<Favoritos>();
            for (Favoritos favoritosCollectionNewFavoritosToAttach : favoritosCollectionNew) {
                favoritosCollectionNewFavoritosToAttach = em.getReference(favoritosCollectionNewFavoritosToAttach.getClass(), favoritosCollectionNewFavoritosToAttach.getFavoritoId());
                attachedFavoritosCollectionNew.add(favoritosCollectionNewFavoritosToAttach);
            }
            favoritosCollectionNew = attachedFavoritosCollectionNew;
            usuarios.setFavoritosCollection(favoritosCollectionNew);
            usuarios = em.merge(usuarios);
            for (ViajesPorHacer viajesPorHacerCollectionOldViajesPorHacer : viajesPorHacerCollectionOld) {
                if (!viajesPorHacerCollectionNew.contains(viajesPorHacerCollectionOldViajesPorHacer)) {
                    viajesPorHacerCollectionOldViajesPorHacer.setUserId(null);
                    viajesPorHacerCollectionOldViajesPorHacer = em.merge(viajesPorHacerCollectionOldViajesPorHacer);
                }
            }
            for (ViajesPorHacer viajesPorHacerCollectionNewViajesPorHacer : viajesPorHacerCollectionNew) {
                if (!viajesPorHacerCollectionOld.contains(viajesPorHacerCollectionNewViajesPorHacer)) {
                    Usuarios oldUserIdOfViajesPorHacerCollectionNewViajesPorHacer = viajesPorHacerCollectionNewViajesPorHacer.getUserId();
                    viajesPorHacerCollectionNewViajesPorHacer.setUserId(usuarios);
                    viajesPorHacerCollectionNewViajesPorHacer = em.merge(viajesPorHacerCollectionNewViajesPorHacer);
                    if (oldUserIdOfViajesPorHacerCollectionNewViajesPorHacer != null && !oldUserIdOfViajesPorHacerCollectionNewViajesPorHacer.equals(usuarios)) {
                        oldUserIdOfViajesPorHacerCollectionNewViajesPorHacer.getViajesPorHacerCollection().remove(viajesPorHacerCollectionNewViajesPorHacer);
                        oldUserIdOfViajesPorHacerCollectionNewViajesPorHacer = em.merge(oldUserIdOfViajesPorHacerCollectionNewViajesPorHacer);
                    }
                }
            }
            for (Favoritos favoritosCollectionOldFavoritos : favoritosCollectionOld) {
                if (!favoritosCollectionNew.contains(favoritosCollectionOldFavoritos)) {
                    favoritosCollectionOldFavoritos.setUserId(null);
                    favoritosCollectionOldFavoritos = em.merge(favoritosCollectionOldFavoritos);
                }
            }
            for (Favoritos favoritosCollectionNewFavoritos : favoritosCollectionNew) {
                if (!favoritosCollectionOld.contains(favoritosCollectionNewFavoritos)) {
                    Usuarios oldUserIdOfFavoritosCollectionNewFavoritos = favoritosCollectionNewFavoritos.getUserId();
                    favoritosCollectionNewFavoritos.setUserId(usuarios);
                    favoritosCollectionNewFavoritos = em.merge(favoritosCollectionNewFavoritos);
                    if (oldUserIdOfFavoritosCollectionNewFavoritos != null && !oldUserIdOfFavoritosCollectionNewFavoritos.equals(usuarios)) {
                        oldUserIdOfFavoritosCollectionNewFavoritos.getFavoritosCollection().remove(favoritosCollectionNewFavoritos);
                        oldUserIdOfFavoritosCollectionNewFavoritos = em.merge(oldUserIdOfFavoritosCollectionNewFavoritos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getUserId();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            Collection<ViajesPorHacer> viajesPorHacerCollection = usuarios.getViajesPorHacerCollection();
            for (ViajesPorHacer viajesPorHacerCollectionViajesPorHacer : viajesPorHacerCollection) {
                viajesPorHacerCollectionViajesPorHacer.setUserId(null);
                viajesPorHacerCollectionViajesPorHacer = em.merge(viajesPorHacerCollectionViajesPorHacer);
            }
            Collection<Favoritos> favoritosCollection = usuarios.getFavoritosCollection();
            for (Favoritos favoritosCollectionFavoritos : favoritosCollection) {
                favoritosCollectionFavoritos.setUserId(null);
                favoritosCollectionFavoritos = em.merge(favoritosCollectionFavoritos);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    
    /*con sonsulta jql para ver el de que ide es el correo*/
    public Usuarios findUsuarios(String email) {
        javax.persistence.EntityManager em = getEntityManager();
        try {
            javax.persistence.Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email");
            query.setParameter("email", email);
            try {
                return (Usuarios) query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } finally {
            em.close();
        }
    }
    
    

    public Usuarios finUsuarios(String email, String pass) {
        javax.persistence.EntityManager em = getEntityManager();
        try {
            javax.persistence.Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email AND u.password = :pass");
            query.setParameter("email", email);
            query.setParameter("pass", pass);
            try {
                return (Usuarios) query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } finally {
            em.close();
        }
    }



    public boolean existeUsuario(String email) {
        javax.persistence.EntityManager em = getEntityManager();
        try {
            javax.persistence.Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email");
            query.setParameter("email", email);
            return query.getResultList().size() > 0;
        } finally {
            em.close();
        }
    }
}
