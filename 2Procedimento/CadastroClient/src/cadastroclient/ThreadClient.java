package cadastroclient;

import java.io.*;
import javax.swing.*;
import java.util.List;
import model.Produto;

public class ThreadClient extends Thread {
    private ObjectInputStream input;
    private JTextArea textArea;

    public ThreadClient(ObjectInputStream in, JTextArea textArea) {
        this.input = in;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Object obj = input.readObject();

                if (obj instanceof String) {
                    String message = (String) obj;
                    SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
                } else if (obj instanceof List<?>) {
                    List<Produto> produtos = (List<Produto>) obj;
                    for (Produto produto : produtos) {
                        String message = "Produto: " + produto.getNome() + ", Preço: " + produto.getPrecoVenda() + ", Estoque: " + produto.getQuantidade();
                        SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if (!Thread.currentThread().isInterrupted()) { 
                e.printStackTrace();
            }
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
