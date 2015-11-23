/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JVictor
 */
@Entity
@Table(name = "notafiscal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotaFiscal.findAll", query = "SELECT n FROM NotaFiscal n"),
    @NamedQuery(name = "NotaFiscal.findById", query = "SELECT n FROM NotaFiscal n WHERE n.id = :id"),
    @NamedQuery(name = "NotaFiscal.findByNumero", query = "SELECT n FROM NotaFiscal n WHERE n.numero = :numero"),
    @NamedQuery(name = "NotaFiscal.findByDataEmissao", query = "SELECT n FROM NotaFiscal n WHERE n.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "NotaFiscal.findByValorTotal", query = "SELECT n FROM NotaFiscal n WHERE n.valorTotal = :valorTotal")})
public class NotaFiscal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataEmissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "midia")
    private byte[] midia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
    @JoinColumn(name = "idFornecedor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notaFiscal")
    private Collection<BemMaterial> bemMaterialCollection;

    public NotaFiscal() {
    }

    public NotaFiscal(Integer id) {
        this.id = id;
    }

    public NotaFiscal(Integer id, int numero, Date dataEmissao, byte[] midia, BigDecimal valorTotal) {
        this.id = id;
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.midia = midia;
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public byte[] getMidia() {
        return midia;
    }

    public void setMidia(byte[] midia) {
        this.midia = midia;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @XmlTransient
    public Collection<BemMaterial> getBemMaterialCollection() {
        return bemMaterialCollection;
    }

    public void setBemMaterialCollection(Collection<BemMaterial> bemMaterialCollection) {
        this.bemMaterialCollection = bemMaterialCollection;
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
        if (!(object instanceof NotaFiscal)) {
            return false;
        }
        NotaFiscal other = (NotaFiscal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.NotaFiscal[ id=" + id + " ]";
    }
    
}
