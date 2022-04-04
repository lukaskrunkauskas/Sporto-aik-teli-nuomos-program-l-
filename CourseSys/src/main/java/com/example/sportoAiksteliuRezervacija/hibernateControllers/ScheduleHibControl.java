package com.example.sportoAiksteliuRezervacija.hibernateControllers;

import com.example.sportoAiksteliuRezervacija.ds.Schedule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ScheduleHibControl {
    private EntityManagerFactory emf = null;
    public ScheduleHibControl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void editSchedule(Schedule schedule) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(schedule);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void createSchedule(Schedule schedule) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(schedule);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Schedule> getAllSchedules() {
        return getAllSchedules(false, -1, -1);
    }

    public List<Schedule> getAllSchedules(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Schedule.class));
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
    public Schedule getScheduleById(int id) {
        EntityManager em = null;
        Schedule schedule = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            schedule = em.getReference(Schedule.class, id);
            schedule.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such schedule by given Id");
        }
        return schedule;
    }
}
