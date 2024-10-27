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
import org.consultas.viajes_itca.entity.Destinos;
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(destinos);
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
            destinos = em.merge(destinos);
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
