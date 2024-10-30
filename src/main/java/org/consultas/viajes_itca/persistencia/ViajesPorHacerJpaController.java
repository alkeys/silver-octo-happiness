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
import org.consultas.viajes_itca.entity.Usuarios;
import org.consultas.viajes_itca.entity.ViajesPorHacer;
import org.consultas.viajes_itca.persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author enoc
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
            em.persist(viajesPorHacer);
            if (userId != null) {
                userId.getViajesPorHacerCollection().add(viajesPorHacer);
                userId = em.merge(userId);
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
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                viajesPorHacer.setUserId(userIdNew);
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

    public ViajesPorHacer findViajesPorHacer(Integer userId, Integer destinoId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT v FROM ViajesPorHacer v WHERE v.userId.userId = :userId AND v.destinoId.destinoId = :destinoId");
            q.setParameter("userId", userId);
            q.setParameter("destinoId", destinoId);
          try {
            return (ViajesPorHacer) q.getSingleResult();
          }catch (Exception e){
              return null;
          }
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

    public List<ViajesPorHacer> findViajesPorHacerList(Usuarios usuario) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT v FROM ViajesPorHacer v WHERE v.userId.userId = :userId");
            q.setParameter("userId", usuario.getUserId());
            try {
                return q.getResultList();
            } catch (Exception e) {
                return null;
            }
        } finally {
            em.close();
        }
    }

    /**
     * este metoo devolvera la cantidad de usuarios que tienen el destino en sus viajes por hacer sin jquery
     * @param destinoId
     * @return
     */

    public long findCantidadIdusuariosDestino(Destinos destinoId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(v) FROM ViajesPorHacer v WHERE v.destinoId.destinoId = :destinoId");
            q.setParameter("destinoId", destinoId.getDestinoId());
            System.out.printf("Cantidad de usuarios que tienen el destino en sus viajes por hacer: %s\n", q.getSingleResult());
            return (long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public long findCantidadClima(String clima) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(distinct v.userId) FROM ViajesPorHacer v WHERE v.destinoId.clima = :clima");
            q.setParameter("clima", clima);
            return (long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public long findCantidadTipo(String tipo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(distinct v.userId) FROM ViajesPorHacer v WHERE v.destinoId.tipoDestino = :tipo");
            q.setParameter("tipo", tipo);
            return (long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void destroyDestino(Integer destinoId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("DELETE FROM ViajesPorHacer v WHERE v.destinoId.destinoId = :destinoId");
            q.setParameter("destinoId", destinoId);
            q.executeUpdate();
        } finally {
            em.close();
        }
    }

    public List<Integer> findDestinosFavoritos(int destino) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT v.viajeId FROM ViajesPorHacer v WHERE v.destinoId.destinoId = :destinoId");
            q.setParameter("destinoId", destino);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
