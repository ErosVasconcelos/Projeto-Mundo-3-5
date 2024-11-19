/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
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

/**
 *
 * @author Eross
 */
@Entity
@Table(name = "PessoaJuridica")
@NamedQueries({
    @NamedQuery(name = "PessoaJuridica.findAll", query = "SELECT p FROM PessoaJuridica p"),
    @NamedQuery(name = "PessoaJuridica.findByIDPessoaJuridica", query = "SELECT p FROM PessoaJuridica p WHERE p.iDPessoaJuridica = :iDPessoaJuridica"),
    @NamedQuery(name = "PessoaJuridica.findByCnpj", query = "SELECT p FROM PessoaJuridica p WHERE p.cnpj = :cnpj")})
public class PessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PessoaJuridica")
    private Integer iDPessoaJuridica;
    @Basic(optional = false)
    @Column(name = "CNPJ")
    private String cnpj;
    @JoinColumn(name = "ID_Pessoa", referencedColumnName = "ID_Pessoa")
    @ManyToOne(optional = false)
    private Pessoa iDPessoa;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Integer iDPessoaJuridica) {
        this.iDPessoaJuridica = iDPessoaJuridica;
    }

    public PessoaJuridica(Integer iDPessoaJuridica, String cnpj) {
        this.iDPessoaJuridica = iDPessoaJuridica;
        this.cnpj = cnpj;
    }

    public Integer getIDPessoaJuridica() {
        return iDPessoaJuridica;
    }

    public void setIDPessoaJuridica(Integer iDPessoaJuridica) {
        this.iDPessoaJuridica = iDPessoaJuridica;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Pessoa getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Pessoa iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPessoaJuridica != null ? iDPessoaJuridica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaJuridica)) {
            return false;
        }
        PessoaJuridica other = (PessoaJuridica) object;
        if ((this.iDPessoaJuridica == null && other.iDPessoaJuridica != null) || (this.iDPessoaJuridica != null && !this.iDPessoaJuridica.equals(other.iDPessoaJuridica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PessoaJuridica[ iDPessoaJuridica=" + iDPessoaJuridica + " ]";
    }
    
}
