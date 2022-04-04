package com.example.sportoAiksteliuRezervacija.hibernateControllers;

import com.example.sportoAiksteliuRezervacija.ds.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ReservationHibControl {
    private EntityManagerFactory emf = null;
    public ReservationHibControl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void editReservation(Reservation reservation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(reservation);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void createReservation(Reservation reservation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reservation);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Reservation> getAllReservations() {
        return getAllReservations(false, -1, -1);
    }

    public List<Reservation> getAllReservations(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Reservation.class));
            Query q = em.createQuery(query);

            if (!all) {
                q.setMaxResults(resMax);
                q.setFirstResult(resFirst);
            }

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }
    public Reservation getReservationById(int id) {
        EntityManager em = null;
        Reservation reservation = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservation = em.getReference(Reservation.class, id);
            reservation.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such reservation by given Id");
        }
        return reservation;
    }
}
