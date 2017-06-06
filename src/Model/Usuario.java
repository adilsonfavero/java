/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adilson_f
 */
@Entity
@Table(name = "usuario", catalog = "messenger", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByUsUsuario", query = "SELECT u FROM Usuario u WHERE u.usUsuario = :usUsuario")
    , @NamedQuery(name = "Usuario.findByUsNome", query = "SELECT u FROM Usuario u WHERE u.usNome = :usNome")
    , @NamedQuery(name = "Usuario.findByUsSobrenome", query = "SELECT u FROM Usuario u WHERE u.usSobrenome = :usSobrenome")
    , @NamedQuery(name = "Usuario.findByUsSenha", query = "SELECT u FROM Usuario u WHERE u.usSenha = :usSenha")
    , @NamedQuery(name = "Usuario.findByUsAdm", query = "SELECT u FROM Usuario u WHERE u.usAdm = :usAdm")
    , @NamedQuery(name = "Usuario.findByUsEmail", query = "SELECT u FROM Usuario u WHERE u.usEmail = :usEmail")
    , @NamedQuery(name = "Usuario.findByUsDataCriacao", query = "SELECT u FROM Usuario u WHERE u.usDataCriacao = :usDataCriacao")
    , @NamedQuery(name = "Usuario.findByUsAtivo", query = "SELECT u FROM Usuario u WHERE u.usAtivo = :usAtivo")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "us_usuario", nullable = false, length = 60)
    private String usUsuario;
    @Basic(optional = false)
    @Column(name = "us_nome", nullable = false, length = 60)
    private String usNome;
    @Basic(optional = false)
    @Column(name = "us_sobrenome", nullable = false, length = 60)
    private String usSobrenome;
    @Basic(optional = false)
    @Column(name = "us_senha", nullable = false, length = 255)
    private String usSenha;
    @Basic(optional = false)
    @Column(name = "us_adm", nullable = false)
    private boolean usAdm;
    @Basic(optional = false)
    @Column(name = "us_email", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date usEmail;
    @Basic(optional = false)
    @Column(name = "us_data_criacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date usDataCriacao;
    @Column(name = "us_ativo")
    private Boolean usAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Mensagem> mensagemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private Collection<Mensagem> mensagemCollection1;

    public Usuario() {
    }

    public Usuario(String usUsuario) {
        this.usUsuario = usUsuario;
    }

    public Usuario(String usUsuario, String usNome, String usSobrenome, String usSenha, boolean usAdm, Date usEmail, Date usDataCriacao) {
        this.usUsuario = usUsuario;
        this.usNome = usNome;
        this.usSobrenome = usSobrenome;
        this.usSenha = usSenha;
        this.usAdm = usAdm;
        this.usEmail = usEmail;
        this.usDataCriacao = usDataCriacao;
    }

    public String getUsUsuario() {
        return usUsuario;
    }

    public void setUsUsuario(String usUsuario) {
        this.usUsuario = usUsuario;
    }

    public String getUsNome() {
        return usNome;
    }

    public void setUsNome(String usNome) {
        this.usNome = usNome;
    }

    public String getUsSobrenome() {
        return usSobrenome;
    }

    public void setUsSobrenome(String usSobrenome) {
        this.usSobrenome = usSobrenome;
    }

    public String getUsSenha() {
        return usSenha;
    }

    public void setUsSenha(String usSenha) {
        this.usSenha = usSenha;
    }

    public boolean getUsAdm() {
        return usAdm;
    }

    public void setUsAdm(boolean usAdm) {
        this.usAdm = usAdm;
    }

    public Date getUsEmail() {
        return usEmail;
    }

    public void setUsEmail(Date usEmail) {
        this.usEmail = usEmail;
    }

    public Date getUsDataCriacao() {
        return usDataCriacao;
    }

    public void setUsDataCriacao(Date usDataCriacao) {
        this.usDataCriacao = usDataCriacao;
    }

    public Boolean getUsAtivo() {
        return usAtivo;
    }

    public void setUsAtivo(Boolean usAtivo) {
        this.usAtivo = usAtivo;
    }

    public Collection<Mensagem> getMensagemCollection() {
        return mensagemCollection;
    }

    public void setMensagemCollection(Collection<Mensagem> mensagemCollection) {
        this.mensagemCollection = mensagemCollection;
    }

    public Collection<Mensagem> getMensagemCollection1() {
        return mensagemCollection1;
    }

    public void setMensagemCollection1(Collection<Mensagem> mensagemCollection1) {
        this.mensagemCollection1 = mensagemCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usUsuario != null ? usUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usUsuario == null && other.usUsuario != null) || (this.usUsuario != null && !this.usUsuario.equals(other.usUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Usuario[ usUsuario=" + usUsuario + " ]";
    }
    
}
