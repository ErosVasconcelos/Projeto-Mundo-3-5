/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eross
 */
@Entity
@Table(name = "movimento")
@NamedQueries({
    @NamedQuery(name = "Movimento.findAll", query = "SELECT m FROM Movimento m"),
    @NamedQuery(name = "Movimento.findByIDMovimento", query = "SELECT m FROM Movimento m WHERE m.iDMovimento = :iDMovimento"),
    @NamedQuery(name = "Movimento.findByDescricao", query = "SELECT m FROM Movimento m WHERE m.descricao = :descricao"),
    @NamedQuery(name = "Movimento.findByValor", query = "SELECT m FROM Movimento m WHERE m.valor = :valor"),
    @NamedQuery(name = "Movimento.findByDataMovimento", query = "SELECT m FROM Movimento m WHERE m.dataMovimento = :dataMovimento")
})
public class Movimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Movimento")
    private Integer iDMovimento;

    @Column(name = "Descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "Valor")
    private BigDecimal valor;

    @Column(name = "Data_Movimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimento;

    @JoinColumn(name = "ID_Usuario", referencedColumnName = "ID_Usuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    // Novos atributos
    @Column(name = "Tipo")
    private String tipo;

    @Column(name = "Quantidade")
    private Integer quantidade;

    @Column(name = "Valor_Unitario")
    private BigDecimal valorUnitario;

    @Column(name = "ID_Produto")
    private Integer idProduto;

    @Column(name = "ID_Pessoa")
    private Integer idPessoa;

    // Construtores e métodos getters/setters atualizados

    public Movimento() {
    }

    public Movimento(Integer iDMovimento) {
        this.iDMovimento = iDMovimento;
    }

    public Movimento(Integer iDMovimento, BigDecimal valor) {
        this.iDMovimento = iDMovimento;
        this.valor = valor;
    }

    public Integer getIDMovimento() {
        return iDMovimento;
    }

    public void setIDMovimento(Integer iDMovimento) {
        this.iDMovimento = iDMovimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDMovimento != null ? iDMovimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Movimento)) {
            return false;
        }
        Movimento other = (Movimento) object;
        if ((this.iDMovimento == null && other.iDMovimento != null) || (this.iDMovimento != null && !this.iDMovimento.equals(other.iDMovimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Movimento[ iDMovimento=" + iDMovimento + " ]";
    }
}
