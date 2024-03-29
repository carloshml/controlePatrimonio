/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JVictor
 */
@Entity
@Table(name = "estadoconservacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoConservacao.findAll", query = "SELECT e FROM EstadoConservacao e"),
    @NamedQuery(name = "EstadoConservacao.findById", query = "SELECT e FROM EstadoConservacao e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoConservacao.findByDescricao", query = "SELECT e FROM EstadoConservacao e WHERE e.descricao = :descricao")})
public class EstadoConservacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoConservacao")
    private Collection<EstadoConservacaoPatrimonio> estadoConservacaoPatrimonioCollection;

    public EstadoConservacao() {
    }

    public EstadoConservacao(Integer id) {
        this.id = id;
    }

    public EstadoConservacao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<EstadoConservacaoPatrimonio> getEstadoConservacaoPatrimonioCollection() {
        return estadoConservacaoPatrimonioCollection;
    }

    public void setEstadoConservacaoPatrimonioCollection(Collection<EstadoConservacaoPatrimonio> estadoConservacaoPatrimonioCollection) {
        this.estadoConservacaoPatrimonioCollection = estadoConservacaoPatrimonioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoConservacao)) {
            return false;
        }
        EstadoConservacao other = (EstadoConservacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EstadoConservacao[ id=" + id + " ]";
    }
    
}
