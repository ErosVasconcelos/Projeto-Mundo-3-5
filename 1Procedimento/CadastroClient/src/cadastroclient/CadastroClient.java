package cadastroclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List; 
import model.Produto;

public class CadastroClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4321);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            outputStream.writeObject("op1");
            outputStream.writeObject("op1");

            String response = (String) inputStream.readObject();
            System.out.println(response);

            if ("Credenciais válidas. Bem-vindo!".equals(response)) {
                outputStream.writeObject("L");

                List<Produto> produtos = (List<Produto>) inputStream.readObject();
                System.out.println("Produtos recebidos:");

                for (Produto produto : produtos) {
                    System.out.println(produto.getNome());
                }
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

