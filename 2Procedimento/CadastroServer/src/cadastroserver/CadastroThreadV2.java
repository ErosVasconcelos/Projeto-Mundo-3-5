package cadastroserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import model.*;
import controller.*;
import java.math.BigDecimal;

public class CadastroThreadV2 extends Thread {
    private ProdutoJpaController ctrlProd;
    private UsuarioJpaController ctrlUsu;
    private MovimentoJpaController ctrlMov;
    private PessoaJpaController ctrlPessoa;
    private Socket socket;

    public CadastroThreadV2(ProdutoJpaController ctrlProd, UsuarioJpaController ctrlUsu,
                            MovimentoJpaController ctrlMov, PessoaJpaController ctrlPessoa,
                            Socket socket) {
        this.ctrlProd = ctrlProd;
        this.ctrlUsu = ctrlUsu;
        this.ctrlMov = ctrlMov;
        this.ctrlPessoa = ctrlPessoa;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            String login = (String) in.readObject();
            String senha = (String) in.readObject();
            Usuario usuario = ctrlUsu.findUsuario(login, senha);

            if (usuario == null) {
                out.writeObject("Credenciais inválidas");
                return; 
            } else {
                out.writeObject("Login bem-sucedido");
            }

            while (true) {
                String comando = (String) in.readObject();

                if ("E".equals(comando) || "S".equals(comando)) {
                    Movimento movimento = new Movimento();
                    movimento.setIDUsuario(usuario); 
                    movimento.setTipo(comando); 

                    Integer idPessoa = (Integer) in.readObject();
                    Pessoa pessoa = ctrlPessoa.findPessoa(idPessoa);
                    movimento.setIdPessoa(pessoa.getIDPessoa());

                    Integer idProduto = (Integer) in.readObject();
                    Produto produto = ctrlProd.findProduto(idProduto);
                    movimento.setIdProduto(produto.getIDProduto());

                    Integer quantidade = (Integer) in.readObject();
                    movimento.setQuantidade(quantidade);

                    double valorUnitario = (double) in.readObject();
                    BigDecimal valorUnitarioBigDecimal = BigDecimal.valueOf(valorUnitario);
                    movimento.setValorUnitario(valorUnitarioBigDecimal);

                    ctrlMov.create(movimento);

                    if ("E".equals(comando)) {
                        produto.setQuantidade(produto.getQuantidade() + quantidade);
                    } else if ("S".equals(comando)) {
                        if (produto.getQuantidade() >= quantidade) {
                            produto.setQuantidade(produto.getQuantidade() - quantidade);
                        } else {
                            out.writeObject("Estoque insuficiente");
                            continue;
                        }
                    }

                    try {
                        ctrlProd.edit(produto); 
                    } catch (Exception e) {
                        out.writeObject("Erro ao atualizar produto: " + e.getMessage());
                        e.printStackTrace(); 
                        continue;
                    }

                    out.writeObject("Movimento registrado com sucesso");
                } else {
                    out.writeObject("Comando inválido");
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
    }
}
