package cadastroclient;

import java.net.Socket;
import java.io.*;
import javax.swing.*;
import view.SaidaFrame;

public class CadastroClientV2 {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4321);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Conectado ao servidor.");

            SaidaFrame janela = new SaidaFrame(null);
            janela.setVisible(true); 
            JTextArea textArea = janela.texto; 

            ThreadClient thread = new ThreadClient(in, textArea);
            thread.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String comando;

            while (true) {
                System.out.print("Escolha um comando: L - Listar, X - Finalizar, E - Entrada, S - Saída: ");
                comando = reader.readLine();
                out.writeObject(comando);
                out.flush();

                if ("E".equals(comando)) {
                    System.out.print("ID da pessoa: ");
                    int idPessoa = Integer.parseInt(reader.readLine());
                    System.out.print("ID do produto: ");
                    int idProduto = Integer.parseInt(reader.readLine());
                    System.out.print("Quantidade: ");
                    int quantidade = Integer.parseInt(reader.readLine());
                    System.out.print("Valor Unitário: ");
                    double valorUnitario = Double.parseDouble(reader.readLine());

                    out.writeInt(idPessoa);
                    out.writeInt(idProduto);
                    out.writeInt(quantidade);
                    out.writeDouble(valorUnitario);
                    out.flush();
                } else if ("X".equals(comando)) {
                    System.out.println("Finalizando...");

                    thread.interrupt();

                    janela.fechar();
                    break; 
                } else if (!"L".equals(comando)) {
                    System.out.println("Comando inválido.");
                }
            }

            socket.close();
            out.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
