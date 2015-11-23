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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JVictor
 */
@Entity
@Table(name = "patrimonio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patrimonio.findAll", query = "SELECT p FROM Patrimonio p"),
    @NamedQuery(name = "Patrimonio.findById", query = "SELECT p FROM Patrimonio p WHERE p.id = :id"),
    @NamedQuery(name = "Patrimonio.findByNumeroPatrimonio", query = "SELECT p FROM Patrimonio p WHERE p.numeroPatrimonio = :numeroPatrimonio"),
    @NamedQuery(name = "Patrimonio.findByCodigoBarras", query = "SELECT p FROM Patrimonio p WHERE p.codigoBarras = :codigoBarras")})
public class Patrimonio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroPatrimonio")
    private int numeroPatrimonio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoBarras")
    private int codigoBarras;
    @OneToMany(mappedBy = "patrimonio")
    private Collection<BemMaterial> bemMaterialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patrimonio")
    private Collection<EstadoConservacaoPatrimonio> estadoConservacaoPatrimonioCollection;

    public Patrimonio() {
    }

    public Patrimonio(Integer id) {
        this.id = id;
    }

    public Patrimonio(Integer id, int numeroPatrimonio, int codigoBarras) {
        this.id = id;
        this.numeroPatrimonio = numeroPatrimonio;
        this.codigoBarras = codigoBarras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(int numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @XmlTransient
    public Collection<BemMaterial> getBemMaterialCollection() {
        return bemMaterialCollection;
    }

    public void setBemMaterialCollection(Collection<BemMaterial> bemMaterialCollection) {
        this.bemMaterialCollection = bemMaterialCollection;
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
        if (!(object instanceof Patrimonio)) {
            return false;
        }
        Patrimonio other = (Patrimonio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Patrimonio[ id=" + id + " ]";
    }
    
}
