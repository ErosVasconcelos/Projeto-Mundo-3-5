import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import controller.*;

public class ServidorSocket {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");
        
        EntityManager em = emf.createEntityManager();
        
        ProdutoJpaController produtoCtrl = new ProdutoJpaController(); 
        UsuarioJpaController usuarioCtrl = new UsuarioJpaController(); 
        
        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor aguardando conexões na porta 4321...");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + socket.getInetAddress());

                CadastroThread cadastroThread = new CadastroThread(produtoCtrl, usuarioCtrl, socket);
                
                cadastroThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
