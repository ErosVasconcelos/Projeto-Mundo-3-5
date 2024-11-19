import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    // M�todo para buscar todos os produtos
    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    // M�todo para buscar produtos com pagina��o
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

    // Outros m�todos de persist�ncia (cria��o, atualiza��o, exclus�o) podem ser implementados aqui...
}
