package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import model.Produto;

public class ProdutoJpaController {

    private EntityManagerFactory emf;

    public ProdutoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("CadastroServerPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Produto p");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Produto findProduto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public void edit(Produto produto) throws Exception {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(produto); 
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback(); 
            }
            throw new Exception("Erro ao editar o produto: " + ex.getMessage(), ex);
        } finally {
            em.close();
        }
    }

    public void create(Produto produto) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(produto);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback(); 
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Produto produto = em.find(Produto.class, id);
            if (produto != null) {
                em.remove(produto); 
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback(); 
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
}
