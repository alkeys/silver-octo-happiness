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
import org.consultas.viajes_itca.entity.ViajesPorHacer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.Favoritos;
import org.consultas.viajes_itca.entity.ImgDestino;
import org.consultas.viajes_itca.persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author alex
 */
public class DestinosJpaController implements Serializable {

    public DestinosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Destinos destinos) {
        if (destinos.getViajesPorHacerCollection() == null) {
            destinos.setViajesPorHacerCollection(new ArrayList<ViajesPorHacer>());
        }
        if (destinos.getFavoritosCollection() == null) {
            destinos.setFavoritosCollection(new ArrayList<Favoritos>());
        }
        if (destinos.getImgDestinoCollection() == null) {
            destinos.setImgDestinoCollection(new ArrayList<ImgDestino>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ViajesPorHacer> attachedViajesPorHacerCollection = new ArrayList<ViajesPorHacer>();
            for (ViajesPorHacer viajesPorHacerCollectionViajesPorHacerToAttach : destinos.getViajesPorHacerCollection()) {
                viajesPorHacerCollectionViajesPorHacerToAttach = em.getReference(viajesPorHacerCollectionViajesPorHacerToAttach.getClass(), viajesPorHacerCollectionViajesPorHacerToAttach.getViajeId());
                attachedViajesPorHacerCollection.add(viajesPorHacerCollectionViajesPorHacerToAttach);
            }
            destinos.setViajesPorHacerCollection(attachedViajesPorHacerCollection);
            Collection<Favoritos> attachedFavoritosCollection = new ArrayList<Favoritos>();
            for (Favoritos favoritosCollectionFavoritosToAttach : destinos.getFavoritosCollection()) {
                favoritosCollectionFavoritosToAttach = em.getReference(favoritosCollectionFavoritosToAttach.getClass(), favoritosCollectionFavoritosToAttach.getFavoritoId());
                attachedFavoritosCollection.add(favoritosCollectionFavoritosToAttach);
            }
            destinos.setFavoritosCollection(attachedFavoritosCollection);
            Collection<ImgDestino> attachedImgDestinoCollection = new ArrayList<ImgDestino>();
            for (ImgDestino imgDestinoCollectionImgDestinoToAttach : destinos.getImgDestinoCollection()) {
                imgDestinoCollectionImgDestinoToAttach = em.getReference(imgDestinoCollectionImgDestinoToAttach.getClass(), imgDestinoCollectionImgDestinoToAttach.getId());
                attachedImgDestinoCollection.add(imgDestinoCollectionImgDestinoToAttach);
            }
            destinos.setImgDestinoCollection(attachedImgDestinoCollection);
            em.persist(destinos);
            for (ViajesPorHacer viajesPorHacerCollectionViajesPorHacer : destinos.getViajesPorHacerCollection()) {
                Destinos oldDestinoIdOfViajesPorHacerCollectionViajesPorHacer = viajesPorHacerCollectionViajesPorHacer.getDestinoId();
                viajesPorHacerCollectionViajesPorHacer.setDestinoId(destinos);
                viajesPorHacerCollectionViajesPorHacer = em.merge(viajesPorHacerCollectionViajesPorHacer);
                if (oldDestinoIdOfViajesPorHacerCollectionViajesPorHacer != null) {
                    oldDestinoIdOfViajesPorHacerCollectionViajesPorHacer.getViajesPorHacerCollection().remove(viajesPorHacerCollectionViajesPorHacer);
                    oldDestinoIdOfViajesPorHacerCollectionViajesPorHacer = em.merge(oldDestinoIdOfViajesPorHacerCollectionViajesPorHacer);
                }
            }
            for (Favoritos favoritosCollectionFavoritos : destinos.getFavoritosCollection()) {
                Destinos oldDestinoIdOfFavoritosCollectionFavoritos = favoritosCollectionFavoritos.getDestinoId();
                favoritosCollectionFavoritos.setDestinoId(destinos);
                favoritosCollectionFavoritos = em.merge(favoritosCollectionFavoritos);
                if (oldDestinoIdOfFavoritosCollectionFavoritos != null) {
                    oldDestinoIdOfFavoritosCollectionFavoritos.getFavoritosCollection().remove(favoritosCollectionFavoritos);
                    oldDestinoIdOfFavoritosCollectionFavoritos = em.merge(oldDestinoIdOfFavoritosCollectionFavoritos);
                }
            }
            for (ImgDestino imgDestinoCollectionImgDestino : destinos.getImgDestinoCollection()) {
                Destinos oldIdDestinoOfImgDestinoCollectionImgDestino = imgDestinoCollectionImgDestino.getIdDestino();
                imgDestinoCollectionImgDestino.setIdDestino(destinos);
                imgDestinoCollectionImgDestino = em.merge(imgDestinoCollectionImgDestino);
                if (oldIdDestinoOfImgDestinoCollectionImgDestino != null) {
                    oldIdDestinoOfImgDestinoCollectionImgDestino.getImgDestinoCollection().remove(imgDestinoCollectionImgDestino);
                    oldIdDestinoOfImgDestinoCollectionImgDestino = em.merge(oldIdDestinoOfImgDestinoCollectionImgDestino);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Destinos destinos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Destinos persistentDestinos = em.find(Destinos.class, destinos.getDestinoId());
            Collection<ViajesPorHacer> viajesPorHacerCollectionOld = persistentDestinos.getViajesPorHacerCollection();
            Collection<ViajesPorHacer> viajesPorHacerCollectionNew = destinos.getViajesPorHacerCollection();
            Collection<Favoritos> favoritosCollectionOld = persistentDestinos.getFavoritosCollection();
            Collection<Favoritos> favoritosCollectionNew = destinos.getFavoritosCollection();
            Collection<ImgDestino> imgDestinoCollectionOld = persistentDestinos.getImgDestinoCollection();
            Collection<ImgDestino> imgDestinoCollectionNew = destinos.getImgDestinoCollection();
            Collection<ViajesPorHacer> attachedViajesPorHacerCollectionNew = new ArrayList<ViajesPorHacer>();
            for (ViajesPorHacer viajesPorHacerCollectionNewViajesPorHacerToAttach : viajesPorHacerCollectionNew) {
                viajesPorHacerCollectionNewViajesPorHacerToAttach = em.getReference(viajesPorHacerCollectionNewViajesPorHacerToAttach.getClass(), viajesPorHacerCollectionNewViajesPorHacerToAttach.getViajeId());
                attachedViajesPorHacerCollectionNew.add(viajesPorHacerCollectionNewViajesPorHacerToAttach);
            }
            viajesPorHacerCollectionNew = attachedViajesPorHacerCollectionNew;
            destinos.setViajesPorHacerCollection(viajesPorHacerCollectionNew);
            Collection<Favoritos> attachedFavoritosCollectionNew = new ArrayList<Favoritos>();
            for (Favoritos favoritosCollectionNewFavoritosToAttach : favoritosCollectionNew) {
                favoritosCollectionNewFavoritosToAttach = em.getReference(favoritosCollectionNewFavoritosToAttach.getClass(), favoritosCollectionNewFavoritosToAttach.getFavoritoId());
                attachedFavoritosCollectionNew.add(favoritosCollectionNewFavoritosToAttach);
            }
            favoritosCollectionNew = attachedFavoritosCollectionNew;
            destinos.setFavoritosCollection(favoritosCollectionNew);
            Collection<ImgDestino> attachedImgDestinoCollectionNew = new ArrayList<ImgDestino>();
            for (ImgDestino imgDestinoCollectionNewImgDestinoToAttach : imgDestinoCollectionNew) {
                imgDestinoCollectionNewImgDestinoToAttach = em.getReference(imgDestinoCollectionNewImgDestinoToAttach.getClass(), imgDestinoCollectionNewImgDestinoToAttach.getId());
                attachedImgDestinoCollectionNew.add(imgDestinoCollectionNewImgDestinoToAttach);
            }
            imgDestinoCollectionNew = attachedImgDestinoCollectionNew;
            destinos.setImgDestinoCollection(imgDestinoCollectionNew);
            destinos = em.merge(destinos);
            for (ViajesPorHacer viajesPorHacerCollectionOldViajesPorHacer : viajesPorHacerCollectionOld) {
                if (!viajesPorHacerCollectionNew.contains(viajesPorHacerCollectionOldViajesPorHacer)) {
                    viajesPorHacerCollectionOldViajesPorHacer.setDestinoId(null);
                    viajesPorHacerCollectionOldViajesPorHacer = em.merge(viajesPorHacerCollectionOldViajesPorHacer);
                }
            }
            for (ViajesPorHacer viajesPorHacerCollectionNewViajesPorHacer : viajesPorHacerCollectionNew) {
                if (!viajesPorHacerCollectionOld.contains(viajesPorHacerCollectionNewViajesPorHacer)) {
                    Destinos oldDestinoIdOfViajesPorHacerCollectionNewViajesPorHacer = viajesPorHacerCollectionNewViajesPorHacer.getDestinoId();
                    viajesPorHacerCollectionNewViajesPorHacer.setDestinoId(destinos);
                    viajesPorHacerCollectionNewViajesPorHacer = em.merge(viajesPorHacerCollectionNewViajesPorHacer);
                    if (oldDestinoIdOfViajesPorHacerCollectionNewViajesPorHacer != null && !oldDestinoIdOfViajesPorHacerCollectionNewViajesPorHacer.equals(destinos)) {
                        oldDestinoIdOfViajesPorHacerCollectionNewViajesPorHacer.getViajesPorHacerCollection().remove(viajesPorHacerCollectionNewViajesPorHacer);
                        oldDestinoIdOfViajesPorHacerCollectionNewViajesPorHacer = em.merge(oldDestinoIdOfViajesPorHacerCollectionNewViajesPorHacer);
                    }
                }
            }
            for (Favoritos favoritosCollectionOldFavoritos : favoritosCollectionOld) {
                if (!favoritosCollectionNew.contains(favoritosCollectionOldFavoritos)) {
                    favoritosCollectionOldFavoritos.setDestinoId(null);
                    favoritosCollectionOldFavoritos = em.merge(favoritosCollectionOldFavoritos);
                }
            }
            for (Favoritos favoritosCollectionNewFavoritos : favoritosCollectionNew) {
                if (!favoritosCollectionOld.contains(favoritosCollectionNewFavoritos)) {
                    Destinos oldDestinoIdOfFavoritosCollectionNewFavoritos = favoritosCollectionNewFavoritos.getDestinoId();
                    favoritosCollectionNewFavoritos.setDestinoId(destinos);
                    favoritosCollectionNewFavoritos = em.merge(favoritosCollectionNewFavoritos);
                    if (oldDestinoIdOfFavoritosCollectionNewFavoritos != null && !oldDestinoIdOfFavoritosCollectionNewFavoritos.equals(destinos)) {
                        oldDestinoIdOfFavoritosCollectionNewFavoritos.getFavoritosCollection().remove(favoritosCollectionNewFavoritos);
                        oldDestinoIdOfFavoritosCollectionNewFavoritos = em.merge(oldDestinoIdOfFavoritosCollectionNewFavoritos);
                    }
                }
            }
            for (ImgDestino imgDestinoCollectionOldImgDestino : imgDestinoCollectionOld) {
                if (!imgDestinoCollectionNew.contains(imgDestinoCollectionOldImgDestino)) {
                    imgDestinoCollectionOldImgDestino.setIdDestino(null);
                    imgDestinoCollectionOldImgDestino = em.merge(imgDestinoCollectionOldImgDestino);
                }
            }
            for (ImgDestino imgDestinoCollectionNewImgDestino : imgDestinoCollectionNew) {
                if (!imgDestinoCollectionOld.contains(imgDestinoCollectionNewImgDestino)) {
                    Destinos oldIdDestinoOfImgDestinoCollectionNewImgDestino = imgDestinoCollectionNewImgDestino.getIdDestino();
                    imgDestinoCollectionNewImgDestino.setIdDestino(destinos);
                    imgDestinoCollectionNewImgDestino = em.merge(imgDestinoCollectionNewImgDestino);
                    if (oldIdDestinoOfImgDestinoCollectionNewImgDestino != null && !oldIdDestinoOfImgDestinoCollectionNewImgDestino.equals(destinos)) {
                        oldIdDestinoOfImgDestinoCollectionNewImgDestino.getImgDestinoCollection().remove(imgDestinoCollectionNewImgDestino);
                        oldIdDestinoOfImgDestinoCollectionNewImgDestino = em.merge(oldIdDestinoOfImgDestinoCollectionNewImgDestino);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = destinos.getDestinoId();
                if (findDestinos(id) == null) {
                    throw new NonexistentEntityException("The destinos with id " + id + " no longer exists.");
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
            Destinos destinos;
            try {
                destinos = em.getReference(Destinos.class, id);
                destinos.getDestinoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The destinos with id " + id + " no longer exists.", enfe);
            }
            Collection<ViajesPorHacer> viajesPorHacerCollection = destinos.getViajesPorHacerCollection();
            for (ViajesPorHacer viajesPorHacerCollectionViajesPorHacer : viajesPorHacerCollection) {
                viajesPorHacerCollectionViajesPorHacer.setDestinoId(null);
                viajesPorHacerCollectionViajesPorHacer = em.merge(viajesPorHacerCollectionViajesPorHacer);
            }
            Collection<Favoritos> favoritosCollection = destinos.getFavoritosCollection();
            for (Favoritos favoritosCollectionFavoritos : favoritosCollection) {
                favoritosCollectionFavoritos.setDestinoId(null);
                favoritosCollectionFavoritos = em.merge(favoritosCollectionFavoritos);
            }
            Collection<ImgDestino> imgDestinoCollection = destinos.getImgDestinoCollection();
            for (ImgDestino imgDestinoCollectionImgDestino : imgDestinoCollection) {
                imgDestinoCollectionImgDestino.setIdDestino(null);
                imgDestinoCollectionImgDestino = em.merge(imgDestinoCollectionImgDestino);
            }
            em.remove(destinos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Destinos> findDestinosEntities() {
        return findDestinosEntities(true, -1, -1);
    }

    public List<Destinos> findDestinosEntities(int maxResults, int firstResult) {
        return findDestinosEntities(false, maxResults, firstResult);
    }

    private List<Destinos> findDestinosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Destinos.class));
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

    public Destinos findDestinos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Destinos.class, id);
        } finally {
            em.close();
        }
    }




    public int getDestinosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Destinos> rt = cq.from(Destinos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Destinos> findDestinosMasValorados() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT d FROM Destinos d ORDER BY d.popularidad DESC");
            query.setMaxResults(5);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Destinos> findDestinosMasValorados(int i) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT d FROM Destinos d ORDER BY d.popularidad DESC");
            query.setMaxResults(i);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
