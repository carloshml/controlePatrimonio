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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JVictor
 */
@Entity
@Table(name = "bemmaterial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BemMaterial.findAll", query = "SELECT b FROM BemMaterial b"),
    @NamedQuery(name = "BemMaterial.findById", query = "SELECT b FROM BemMaterial b WHERE b.id = :id"),
    @NamedQuery(name = "BemMaterial.findByCodigoBarra", query = "SELECT b FROM BemMaterial b WHERE b.codigoBarra = :codigoBarra"),
    @NamedQuery(name = "BemMaterial.findByNumeroSerie", query = "SELECT b FROM BemMaterial b WHERE b.numeroSerie = :numeroSerie"),
    @NamedQuery(name = "BemMaterial.findByDataCadastro", query = "SELECT b FROM BemMaterial b WHERE b.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "BemMaterial.findByValorCompra", query = "SELECT b FROM BemMaterial b WHERE b.valorCompra = :valorCompra"),
    @NamedQuery(name = "BemMaterial.findByValorAtual", query = "SELECT b FROM BemMaterial b WHERE b.valorAtual = :valorAtual"),
    @NamedQuery(name = "BemMaterial.findByObservacao", query = "SELECT b FROM BemMaterial b WHERE b.observacao = :observacao")})
public class BemMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigoBarra")
    private String codigoBarra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "numeroSerie")
    private String numeroSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorCompra")
    private BigDecimal valorCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorAtual")
    private BigDecimal valorAtual;
    @Size(max = 200)
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bemMaterial")
    private Collection<LocalBemMaterial> localBemMaterialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bemMaterial")
    private Collection<Documento> documentoCollection;
    @JoinColumn(name = "idPatrimonio", referencedColumnName = "id")
    @ManyToOne
    private Patrimonio patrimonio;
    @JoinColumn(name = "idProduto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "idNotaFiscal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private NotaFiscal notaFiscal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bemMaterial")
    private Collection<Foto> fotoCollection;

    public BemMaterial() {
    }

    public BemMaterial(Integer id) {
        this.id = id;
    }

    public BemMaterial(Integer id, String codigoBarra, String numeroSerie, Date dataCadastro, BigDecimal valorCompra, BigDecimal valorAtual) {
        this.id = id;
        this.codigoBarra = codigoBarra;
        this.numeroSerie = numeroSerie;
        this.dataCadastro = dataCadastro;
        this.valorCompra = valorCompra;
        this.valorAtual = valorAtual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @XmlTransient
    public Collection<LocalBemMaterial> getLocalBemMaterialCollection() {
        return localBemMaterialCollection;
    }

    public void setLocalBemMaterialCollection(Collection<LocalBemMaterial> localBemMaterialCollection) {
        this.localBemMaterialCollection = localBemMaterialCollection;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    @XmlTransient
    public Collection<Foto> getFotoCollection() {
        return fotoCollection;
    }

    public void setFotoCollection(Collection<Foto> fotoCollection) {
        this.fotoCollection = fotoCollection;
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
        if (!(object instanceof BemMaterial)) {
            return false;
        }
        BemMaterial other = (BemMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.BemMaterial[ id=" + id + " ]";
    }
    
}
