package ServidorSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import controller.ProdutoJpaController;
import model.Produto;
import java.util.List;

public class ServidorSocket {

    public static void main(String[] args) {
        ProdutoJpaController produtoCtrl = new ProdutoJpaController();

        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor aguardando conexões na porta 4321...");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + socket.getInetAddress());


                new Thread(() -> {
                    try (
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
                    ) {
                        String command;

                        while ((command = (String) in.readObject()) != null) {
                            if ("L".equals(command)) {
                                List<Produto> produtos = produtoCtrl.findProdutoEntities();
                                out.writeObject(produtos);
                                out.flush();
                            } else if ("E".equals(command) || "S".equals(command)) {
                                int idPessoa = in.readInt();
                                int idProduto = in.readInt();
                                int quantidade = in.readInt();
                                double valorUnitario = in.readDouble();

                                out.writeObject("Comando executado com sucesso.");
                                out.flush(); 
                            } else if ("X".equals(command)) {
                                out.writeObject("Conexão encerrada.");
                                out.flush(); 
                                break; 
                            }
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close(); 
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
