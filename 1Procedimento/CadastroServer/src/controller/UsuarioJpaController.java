package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Usuario;
import java.util.List;
import javax.persistence.EntityNotFoundException;


public class UsuarioJpaController {

    private EntityManagerFactory emf;

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("CadastroServerPU"); // Nome da unidade de persist�ncia definida no persistence.xml
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // M�todo para salvar o usu�rio
    public void create(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (findUsuario(usuario.getLogin()) != null) {
                throw new RuntimeException("Usu�rio " + usuario + " j� existe.");
            }
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e; // Em caso de erro, lan�a a exce��o
        } finally {
            em.close();
        }
    }

    // M�todo para buscar um usu�rio pelo login
    public Usuario findUsuario(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, login);
        } finally {
            em.close();
        }
    }

    // M�todo para buscar um usu�rio pelo login e senha
    public Usuario findUsuario(String login, String senha) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class);
            query.setParameter("login", login);
            query.setParameter("senha", senha);

            List<Usuario> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0); // Retorna o primeiro usu�rio encontrado
            } else {
                return null; // Retorna nulo se n�o houver resultado
            }
        } finally {
            em.close();
        }
    }

    // M�todo para editar um usu�rio
    public void edit(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // M�todo para excluir um usu�rio
    public void destroy(String login) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, login);
                usuario.getLogin();  // Isso ir� lan�ar uma exce��o se o usu�rio n�o existir
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("O usu�rio com login " + login + " n�o foi encontrado.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
