/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JVictor
 */
@Entity
@Table(name = "localbemmaterial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalBemMaterial.findAll", query = "SELECT l FROM LocalBemMaterial l"),
    @NamedQuery(name = "LocalBemMaterial.findByIdBemMaterial", query = "SELECT l FROM LocalBemMaterial l WHERE l.localBemMaterialPK.idBemMaterial = :idBemMaterial"),
    @NamedQuery(name = "LocalBemMaterial.findByIdLocal", query = "SELECT l FROM LocalBemMaterial l WHERE l.localBemMaterialPK.idLocal = :idLocal"),
    @NamedQuery(name = "LocalBemMaterial.findByDataInicial", query = "SELECT l FROM LocalBemMaterial l WHERE l.dataInicial = :dataInicial"),
    @NamedQuery(name = "LocalBemMaterial.findByDataFinal", query = "SELECT l FROM LocalBemMaterial l WHERE l.dataFinal = :dataFinal")})
public class LocalBemMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocalBemMaterialPK localBemMaterialPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicial")
    @Temporal(TemporalType.DATE)
    private Date dataInicial;
    @Column(name = "dataFinal")
    @Temporal(TemporalType.DATE)
    private Date dataFinal;
    @JoinColumn(name = "idBemMaterial", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BemMaterial bemMaterial;
    @JoinColumn(name = "idLocal", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Local local;

    public LocalBemMaterial() {
    }

    public LocalBemMaterial(LocalBemMaterialPK localBemMaterialPK) {
        this.localBemMaterialPK = localBemMaterialPK;
    }

    public LocalBemMaterial(LocalBemMaterialPK localBemMaterialPK, Date dataInicial) {
        this.localBemMaterialPK = localBemMaterialPK;
        this.dataInicial = dataInicial;
    }

    public LocalBemMaterial(int idBemMaterial, int idLocal) {
        this.localBemMaterialPK = new LocalBemMaterialPK(idBemMaterial, idLocal);
    }

    public LocalBemMaterialPK getLocalBemMaterialPK() {
        return localBemMaterialPK;
    }

    public void setLocalBemMaterialPK(LocalBemMaterialPK localBemMaterialPK) {
        this.localBemMaterialPK = localBemMaterialPK;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public BemMaterial getBemMaterial() {
        return bemMaterial;
    }

    public void setBemMaterial(BemMaterial bemMaterial) {
        this.bemMaterial = bemMaterial;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localBemMaterialPK != null ? localBemMaterialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalBemMaterial)) {
            return false;
        }
        LocalBemMaterial other = (LocalBemMaterial) object;
        if ((this.localBemMaterialPK == null && other.localBemMaterialPK != null) || (this.localBemMaterialPK != null && !this.localBemMaterialPK.equals(other.localBemMaterialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.LocalBemMaterial[ localBemMaterialPK=" + localBemMaterialPK + " ]";
    }
    
}
