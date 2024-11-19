import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.*;
import controller.*;

public class CadastroThread extends Thread {
    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private Socket s1;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
public void run() {
    try {
        ObjectInputStream inputStream = new ObjectInputStream(s1.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(s1.getOutputStream());

        String login = (String) inputStream.readObject();
        String senha = (String) inputStream.readObject();

        Usuario usuario = ctrlUsu.findUsuario(login, senha);
        if (usuario == null) {
            outputStream.writeObject("Credenciais inválidas");
            s1.close();
            return;
        }

        outputStream.writeObject("Credenciais válidas. Bem-vindo!");

        String comando = (String) inputStream.readObject();

        if ("L".equals(comando)) {
            List<Produto> produtos = ctrl.findProdutoEntities();
            outputStream.writeObject(produtos);
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            s1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

}


