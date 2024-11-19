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
@Table(name = "PessoaFisica")
@NamedQueries({
    @NamedQuery(name = "PessoaFisica.findAll", query = "SELECT p FROM PessoaFisica p"),
    @NamedQuery(name = "PessoaFisica.findByIDPessoaFisica", query = "SELECT p FROM PessoaFisica p WHERE p.iDPessoaFisica = :iDPessoaFisica"),
    @NamedQuery(name = "PessoaFisica.findByCpf", query = "SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf")})
public class PessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PessoaFisica")
    private Integer iDPessoaFisica;
    @Basic(optional = false)
    @Column(name = "CPF")
    private String cpf;
    @JoinColumn(name = "ID_Pessoa", referencedColumnName = "ID_Pessoa")
    @ManyToOne(optional = false)
    private Pessoa iDPessoa;

    public PessoaFisica() {
    }

    public PessoaFisica(Integer iDPessoaFisica) {
        this.iDPessoaFisica = iDPessoaFisica;
    }

    public PessoaFisica(Integer iDPessoaFisica, String cpf) {
        this.iDPessoaFisica = iDPessoaFisica;
        this.cpf = cpf;
    }

    public Integer getIDPessoaFisica() {
        return iDPessoaFisica;
    }

    public void setIDPessoaFisica(Integer iDPessoaFisica) {
        this.iDPessoaFisica = iDPessoaFisica;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        hash += (iDPessoaFisica != null ? iDPessoaFisica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaFisica)) {
            return false;
        }
        PessoaFisica other = (PessoaFisica) object;
        if ((this.iDPessoaFisica == null && other.iDPessoaFisica != null) || (this.iDPessoaFisica != null && !this.iDPessoaFisica.equals(other.iDPessoaFisica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PessoaFisica[ iDPessoaFisica=" + iDPessoaFisica + " ]";
    }
    
}
