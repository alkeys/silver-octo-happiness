/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.consultas.viajes_itca.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.consultas.viajes_itca.entity.Destinos;
import org.consultas.viajes_itca.entity.ImgDestino;
import org.consultas.viajes_itca.persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author alex
 */
public class ImgDestinoJpaController implements Serializable {

    public ImgDestinoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ImgDestino imgDestino) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Destinos idDestino = imgDestino.getIdDestino();
            if (idDestino != null) {
                idDestino = em.getReference(idDestino.getClass(), idDestino.getDestinoId());
                imgDestino.setIdDestino(idDestino);
            }
            em.persist(imgDestino);
            if (idDestino != null) {
                idDestino.getImgDestinoCollection().add(imgDestino);
                idDestino = em.merge(idDestino);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ImgDestino imgDestino) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ImgDestino persistentImgDestino = em.find(ImgDestino.class, imgDestino.getId());
            Destinos idDestinoOld = persistentImgDestino.getIdDestino();
            Destinos idDestinoNew = imgDestino.getIdDestino();
            if (idDestinoNew != null) {
                idDestinoNew = em.getReference(idDestinoNew.getClass(), idDestinoNew.getDestinoId());
                imgDestino.setIdDestino(idDestinoNew);
            }
            imgDestino = em.merge(imgDestino);
            if (idDestinoOld != null && !idDestinoOld.equals(idDestinoNew)) {
                idDestinoOld.getImgDestinoCollection().remove(imgDestino);
                idDestinoOld = em.merge(idDestinoOld);
            }
            if (idDestinoNew != null && !idDestinoNew.equals(idDestinoOld)) {
                idDestinoNew.getImgDestinoCollection().add(imgDestino);
                idDestinoNew = em.merge(idDestinoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = imgDestino.getId();
                if (findImgDestino(id) == null) {
                    throw new NonexistentEntityException("The imgDestino with id " + id + " no longer exists.");
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
            ImgDestino imgDestino;
            try {
                imgDestino = em.getReference(ImgDestino.class, id);
                imgDestino.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imgDestino with id " + id + " no longer exists.", enfe);
            }
            Destinos idDestino = imgDestino.getIdDestino();
            if (idDestino != null) {
                idDestino.getImgDestinoCollection().remove(imgDestino);
                idDestino = em.merge(idDestino);
            }
            em.remove(imgDestino);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ImgDestino> findImgDestinoEntities() {
        return findImgDestinoEntities(true, -1, -1);
    }

    public List<ImgDestino> findImgDestinoEntities(int maxResults, int firstResult) {
        return findImgDestinoEntities(false, maxResults, firstResult);
    }

    private List<ImgDestino> findImgDestinoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ImgDestino.class));
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

    public ImgDestino findImgDestino(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ImgDestino.class, id);
        } finally {
            em.close();
        }
    }


    public ImgDestino findImgDestino(int idDestino) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM ImgDestino i WHERE i.idDestino.destinoId = :idDestino");
            q.setParameter("idDestino", idDestino);
            try {
                return (ImgDestino) q.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        } finally {
            em.close();
        }
    }



    public int getImgDestinoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ImgDestino> rt = cq.from(ImgDestino.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
