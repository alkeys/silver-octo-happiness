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
import org.consultas.viajes_itca.entity.ViajesPorHacer;
import org.consultas.viajes_itca.persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author alex
 */
public class ViajesPorHacerJpaController implements Serializable {

    public ViajesPorHacerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ViajesPorHacer viajesPorHacer) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios userId = viajesPorHacer.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                viajesPorHacer.setUserId(userId);
            }
            Destinos destinoId = viajesPorHacer.getDestinoId();
            if (destinoId != null) {
                destinoId = em.getReference(destinoId.getClass(), destinoId.getDestinoId());
                viajesPorHacer.setDestinoId(destinoId);
            }
            em.persist(viajesPorHacer);
            if (userId != null) {
                userId.getViajesPorHacerCollection().add(viajesPorHacer);
                userId = em.merge(userId);
            }
            if (destinoId != null) {
                destinoId.getViajesPorHacerCollection().add(viajesPorHacer);
                destinoId = em.merge(destinoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ViajesPorHacer viajesPorHacer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ViajesPorHacer persistentViajesPorHacer = em.find(ViajesPorHacer.class, viajesPorHacer.getViajeId());
            Usuarios userIdOld = persistentViajesPorHacer.getUserId();
            Usuarios userIdNew = viajesPorHacer.getUserId();
            Destinos destinoIdOld = persistentViajesPorHacer.getDestinoId();
            Destinos destinoIdNew = viajesPorHacer.getDestinoId();
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                viajesPorHacer.setUserId(userIdNew);
            }
            if (destinoIdNew != null) {
                destinoIdNew = em.getReference(destinoIdNew.getClass(), destinoIdNew.getDestinoId());
                viajesPorHacer.setDestinoId(destinoIdNew);
            }
            viajesPorHacer = em.merge(viajesPorHacer);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getViajesPorHacerCollection().remove(viajesPorHacer);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getViajesPorHacerCollection().add(viajesPorHacer);
                userIdNew = em.merge(userIdNew);
            }
            if (destinoIdOld != null && !destinoIdOld.equals(destinoIdNew)) {
                destinoIdOld.getViajesPorHacerCollection().remove(viajesPorHacer);
                destinoIdOld = em.merge(destinoIdOld);
            }
            if (destinoIdNew != null && !destinoIdNew.equals(destinoIdOld)) {
                destinoIdNew.getViajesPorHacerCollection().add(viajesPorHacer);
                destinoIdNew = em.merge(destinoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = viajesPorHacer.getViajeId();
                if (findViajesPorHacer(id) == null) {
                    throw new NonexistentEntityException("The viajesPorHacer with id " + id + " no longer exists.");
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
            ViajesPorHacer viajesPorHacer;
            try {
                viajesPorHacer = em.getReference(ViajesPorHacer.class, id);
                viajesPorHacer.getViajeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viajesPorHacer with id " + id + " no longer exists.", enfe);
            }
            Usuarios userId = viajesPorHacer.getUserId();
            if (userId != null) {
                userId.getViajesPorHacerCollection().remove(viajesPorHacer);
                userId = em.merge(userId);
            }
            Destinos destinoId = viajesPorHacer.getDestinoId();
            if (destinoId != null) {
                destinoId.getViajesPorHacerCollection().remove(viajesPorHacer);
                destinoId = em.merge(destinoId);
            }
            em.remove(viajesPorHacer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ViajesPorHacer> findViajesPorHacerEntities() {
        return findViajesPorHacerEntities(true, -1, -1);
    }

    public List<ViajesPorHacer> findViajesPorHacerEntities(int maxResults, int firstResult) {
        return findViajesPorHacerEntities(false, maxResults, firstResult);
    }

    private List<ViajesPorHacer> findViajesPorHacerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ViajesPorHacer.class));
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

    public ViajesPorHacer findViajesPorHacer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ViajesPorHacer.class, id);
        } finally {
            em.close();
        }
    }

    public int getViajesPorHacerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ViajesPorHacer> rt = cq.from(ViajesPorHacer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
