package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * Entidade Produto mapeada para a tabela "Produto" no banco de dados.
 * Esta classe utiliza JPA (Java Persistence API) para persistir os dados.
 * 
 * @author Eross
 */
@Entity
@Table(name = "Produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIDProduto", query = "SELECT p FROM Produto p WHERE p.iDProduto = :iDProduto"),
    @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produto.findByPrecoVenda", query = "SELECT p FROM Produto p WHERE p.precoVenda = :precoVenda"),
    @NamedQuery(name = "Produto.findByQuantidade", query = "SELECT p FROM Produto p WHERE p.quantidade = :quantidade")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Produto")
    private Integer iDProduto;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    
@NotNull
@DecimalMin(value = "0.01")  // Preço mínimo de 0.01
@DecimalMax(value = "10000.00")  // Preço máximo de 10.000.00
@Basic(optional = false)
@Column(name = "PrecoVenda")
private BigDecimal precoVenda;

    
    @NotNull
    @Basic(optional = false)
    @Column(name = "Quantidade")
    private int quantidade;

    // Construtores
    public Produto() {
    }

    public Produto(Integer iDProduto) {
        this.iDProduto = iDProduto;
    }

    public Produto(Integer iDProduto, String nome, BigDecimal precoVenda, int quantidade) {
        this.iDProduto = iDProduto;
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Integer getIDProduto() {
        return iDProduto;
    }

    public void setIDProduto(Integer iDProduto) {
        this.iDProduto = iDProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Métodos hashCode e equals
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProduto != null ? iDProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        return (this.iDProduto != null || other.iDProduto == null) && 
               (this.iDProduto == null || this.iDProduto.equals(other.iDProduto));
    }

    // Método toString melhorado
    @Override
    public String toString() {
        return String.format("Produto [ID: %d, Nome: %s, Preço: R$ %.2f, Quantidade: %d]",
                             iDProduto, nome, precoVenda, quantidade);
    }
}
