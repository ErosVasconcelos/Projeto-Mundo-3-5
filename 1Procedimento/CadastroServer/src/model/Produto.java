/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
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
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PrecoVenda")
    private BigDecimal precoVenda;
    @Basic(optional = false)
    @Column(name = "Quantidade")
    private int quantidade;

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProduto != null ? iDProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.iDProduto == null && other.iDProduto != null) || (this.iDProduto != null && !this.iDProduto.equals(other.iDProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Produto[ iDProduto=" + iDProduto + " ]";
    }
    
}
