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
public class EstadoConservacaoPatrimonioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPatrimonio")
    private int idPatrimonio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoConservacao")
    private int idEstadoConservacao;

    public EstadoConservacaoPatrimonioPK() {
    }

    public EstadoConservacaoPatrimonioPK(int idPatrimonio, int idEstadoConservacao) {
        this.idPatrimonio = idPatrimonio;
        this.idEstadoConservacao = idEstadoConservacao;
    }

    public int getIdPatrimonio() {
        return idPatrimonio;
    }

    public void setIdPatrimonio(int idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public int getIdEstadoConservacao() {
        return idEstadoConservacao;
    }

    public void setIdEstadoConservacao(int idEstadoConservacao) {
        this.idEstadoConservacao = idEstadoConservacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPatrimonio;
        hash += (int) idEstadoConservacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoConservacaoPatrimonioPK)) {
            return false;
        }
        EstadoConservacaoPatrimonioPK other = (EstadoConservacaoPatrimonioPK) object;
        if (this.idPatrimonio != other.idPatrimonio) {
            return false;
        }
        if (this.idEstadoConservacao != other.idEstadoConservacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EstadoConservacaoPatrimonioPK[ idPatrimonio=" + idPatrimonio + ", idEstadoConservacao=" + idEstadoConservacao + " ]";
    }
    
}
