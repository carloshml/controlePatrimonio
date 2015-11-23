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
@Table(name = "estadoconservacaopatrimonio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoConservacaoPatrimonio.findAll", query = "SELECT e FROM EstadoConservacaoPatrimonio e"),
    @NamedQuery(name = "EstadoConservacaoPatrimonio.findByIdPatrimonio", query = "SELECT e FROM EstadoConservacaoPatrimonio e WHERE e.estadoConservacaoPatrimonioPK.idPatrimonio = :idPatrimonio"),
    @NamedQuery(name = "EstadoConservacaoPatrimonio.findByIdEstadoConservacao", query = "SELECT e FROM EstadoConservacaoPatrimonio e WHERE e.estadoConservacaoPatrimonioPK.idEstadoConservacao = :idEstadoConservacao"),
    @NamedQuery(name = "EstadoConservacaoPatrimonio.findByDataInicial", query = "SELECT e FROM EstadoConservacaoPatrimonio e WHERE e.dataInicial = :dataInicial"),
    @NamedQuery(name = "EstadoConservacaoPatrimonio.findByDataFinal", query = "SELECT e FROM EstadoConservacaoPatrimonio e WHERE e.dataFinal = :dataFinal")})
public class EstadoConservacaoPatrimonio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstadoConservacaoPatrimonioPK estadoConservacaoPatrimonioPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicial")
    @Temporal(TemporalType.DATE)
    private Date dataInicial;
    @Column(name = "dataFinal")
    @Temporal(TemporalType.DATE)
    private Date dataFinal;
    @JoinColumn(name = "idPatrimonio", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patrimonio patrimonio;
    @JoinColumn(name = "idEstadoConservacao", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EstadoConservacao estadoConservacao;

    public EstadoConservacaoPatrimonio() {
    }

    public EstadoConservacaoPatrimonio(EstadoConservacaoPatrimonioPK estadoConservacaoPatrimonioPK) {
        this.estadoConservacaoPatrimonioPK = estadoConservacaoPatrimonioPK;
    }

    public EstadoConservacaoPatrimonio(EstadoConservacaoPatrimonioPK estadoConservacaoPatrimonioPK, Date dataInicial) {
        this.estadoConservacaoPatrimonioPK = estadoConservacaoPatrimonioPK;
        this.dataInicial = dataInicial;
    }

    public EstadoConservacaoPatrimonio(int idPatrimonio, int idEstadoConservacao) {
        this.estadoConservacaoPatrimonioPK = new EstadoConservacaoPatrimonioPK(idPatrimonio, idEstadoConservacao);
    }

    public EstadoConservacaoPatrimonioPK getEstadoConservacaoPatrimonioPK() {
        return estadoConservacaoPatrimonioPK;
    }

    public void setEstadoConservacaoPatrimonioPK(EstadoConservacaoPatrimonioPK estadoConservacaoPatrimonioPK) {
        this.estadoConservacaoPatrimonioPK = estadoConservacaoPatrimonioPK;
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

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoConservacaoPatrimonioPK != null ? estadoConservacaoPatrimonioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoConservacaoPatrimonio)) {
            return false;
        }
        EstadoConservacaoPatrimonio other = (EstadoConservacaoPatrimonio) object;
        if ((this.estadoConservacaoPatrimonioPK == null && other.estadoConservacaoPatrimonioPK != null) || (this.estadoConservacaoPatrimonioPK != null && !this.estadoConservacaoPatrimonioPK.equals(other.estadoConservacaoPatrimonioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EstadoConservacaoPatrimonio[ estadoConservacaoPatrimonioPK=" + estadoConservacaoPatrimonioPK + " ]";
    }
    
}
