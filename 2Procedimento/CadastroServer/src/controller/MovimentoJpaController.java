package controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import model.Movimento;

/**
 *
 * @author Eross
 */
public class MovimentoJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public MovimentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimento movimento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movimento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimento movimento) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            movimento = em.merge(movimento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Erro ao editar o movimento: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Movimento movimento;
            try {
                movimento = em.getReference(Movimento.class, id);
                movimento.getIDMovimento();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("Movimento não encontrado.");
            }
            em.remove(movimento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Movimento findMovimento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimento.class, id);
        } finally {
            em.close();
        }
    }

    public List<Movimento> findMovimentoEntities() {
        return findMovimentoEntities(true, -1, -1);
    }

    public List<Movimento> findMovimentoEntities(int maxResults, int firstResult) {
        return findMovimentoEntities(false, maxResults, firstResult);
    }

    private List<Movimento> findMovimentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM Movimento m");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getMovimentoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(m) FROM Movimento m");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
