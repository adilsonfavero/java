/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adilson_f
 */
@Entity
@Table(name = "mensagem", catalog = "messenger", schema = "")
@NamedQueries({
    @NamedQuery(name = "Mensagem.findAll", query = "SELECT m FROM Mensagem m")
    , @NamedQuery(name = "Mensagem.findByMsUsOrigem", query = "SELECT m FROM Mensagem m WHERE m.mensagemPK.msUsOrigem = :msUsOrigem")
    , @NamedQuery(name = "Mensagem.findByMsUsDestino", query = "SELECT m FROM Mensagem m WHERE m.mensagemPK.msUsDestino = :msUsDestino")
    , @NamedQuery(name = "Mensagem.findByMsDataEntrega", query = "SELECT m FROM Mensagem m WHERE m.msDataEntrega = :msDataEntrega")
    , @NamedQuery(name = "Mensagem.findByMsDataEnvio", query = "SELECT m FROM Mensagem m WHERE m.msDataEnvio = :msDataEnvio")
    , @NamedQuery(name = "Mensagem.findByMsId", query = "SELECT m FROM Mensagem m WHERE m.mensagemPK.msId = :msId")})
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MensagemPK mensagemPK;
    @Lob
    @Column(name = "ms_mensagem", length = 65535)
    private String msMensagem;
    @Basic(optional = false)
    @Column(name = "ms_data_entrega", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date msDataEntrega;
    @Basic(optional = false)
    @Column(name = "ms_data_envio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date msDataEnvio;
    @JoinColumn(name = "ms_us_destino", referencedColumnName = "us_usuario", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "ms_us_origem", referencedColumnName = "us_usuario", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Mensagem() {
    }

    public Mensagem(MensagemPK mensagemPK) {
        this.mensagemPK = mensagemPK;
    }

    public Mensagem(MensagemPK mensagemPK, Date msDataEntrega, Date msDataEnvio) {
        this.mensagemPK = mensagemPK;
        this.msDataEntrega = msDataEntrega;
        this.msDataEnvio = msDataEnvio;
    }

    public Mensagem(String msUsOrigem, String msUsDestino, int msId) {
        this.mensagemPK = new MensagemPK(msUsOrigem, msUsDestino, msId);
    }

    public MensagemPK getMensagemPK() {
        return mensagemPK;
    }

    public void setMensagemPK(MensagemPK mensagemPK) {
        this.mensagemPK = mensagemPK;
    }

    public String getMsMensagem() {
        return msMensagem;
    }

    public void setMsMensagem(String msMensagem) {
        this.msMensagem = msMensagem;
    }

    public Date getMsDataEntrega() {
        return msDataEntrega;
    }

    public void setMsDataEntrega(Date msDataEntrega) {
        this.msDataEntrega = msDataEntrega;
    }

    public Date getMsDataEnvio() {
        return msDataEnvio;
    }

    public void setMsDataEnvio(Date msDataEnvio) {
        this.msDataEnvio = msDataEnvio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mensagemPK != null ? mensagemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.mensagemPK == null && other.mensagemPK != null) || (this.mensagemPK != null && !this.mensagemPK.equals(other.mensagemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Mensagem[ mensagemPK=" + mensagemPK + " ]";
    }
    
}
