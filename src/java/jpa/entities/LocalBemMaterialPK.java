/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JVictor
 */
@Embeddable
public class LocalBemMaterialPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idBemMaterial")
    private int idBemMaterial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLocal")
    private int idLocal;

    public LocalBemMaterialPK() {
    }

    public LocalBemMaterialPK(int idBemMaterial, int idLocal) {
        this.idBemMaterial = idBemMaterial;
        this.idLocal = idLocal;
    }

    public int getIdBemMaterial() {
        return idBemMaterial;
    }

    public void setIdBemMaterial(int idBemMaterial) {
        this.idBemMaterial = idBemMaterial;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idBemMaterial;
        hash += (int) idLocal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalBemMaterialPK)) {
            return false;
        }
        LocalBemMaterialPK other = (LocalBemMaterialPK) object;
        if (this.idBemMaterial != other.idBemMaterial) {
            return false;
        }
        if (this.idLocal != other.idLocal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.LocalBemMaterialPK[ idBemMaterial=" + idBemMaterial + ", idLocal=" + idLocal + " ]";
    }
    
}
