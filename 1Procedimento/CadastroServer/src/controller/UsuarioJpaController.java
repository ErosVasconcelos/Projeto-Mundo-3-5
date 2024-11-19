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
        this.emf = Persistence.createEntityManagerFactory("CadastroServerPU"); // Nome da unidade de persistência definida no persistence.xml
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para salvar o usuário
    public void create(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (findUsuario(usuario.getLogin()) != null) {
                throw new RuntimeException("Usuário " + usuario + " já existe.");
            }
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e; // Em caso de erro, lança a exceção
        } finally {
            em.close();
        }
    }

    // Método para buscar um usuário pelo login
    public Usuario findUsuario(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, login);
        } finally {
            em.close();
        }
    }

    // Método para buscar um usuário pelo login e senha
    public Usuario findUsuario(String login, String senha) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class);
            query.setParameter("login", login);
            query.setParameter("senha", senha);

            List<Usuario> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0); // Retorna o primeiro usuário encontrado
            } else {
                return null; // Retorna nulo se não houver resultado
            }
        } finally {
            em.close();
        }
    }

    // Método para editar um usuário
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

    // Método para excluir um usuário
    public void destroy(String login) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, login);
                usuario.getLogin();  // Isso irá lançar uma exceção se o usuário não existir
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("O usuário com login " + login + " não foi encontrado.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
