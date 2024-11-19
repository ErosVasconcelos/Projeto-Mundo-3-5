package controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Pessoa;

public class PessoaJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public PessoaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("CadastroServerPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Pessoa findPessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }
}
