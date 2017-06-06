/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author adilson_f
 */
@Embeddable
public class MensagemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ms_us_origem", nullable = false, length = 60)
    private String msUsOrigem;
    @Basic(optional = false)
    @Column(name = "ms_us_destino", nullable = false, length = 60)
    private String msUsDestino;
    @Basic(optional = false)
    @Column(name = "ms_id", nullable = false)
    private int msId;

    public MensagemPK() {
    }

    public MensagemPK(String msUsOrigem, String msUsDestino, int msId) {
        this.msUsOrigem = msUsOrigem;
        this.msUsDestino = msUsDestino;
        this.msId = msId;
    }

    public String getMsUsOrigem() {
        return msUsOrigem;
    }

    public void setMsUsOrigem(String msUsOrigem) {
        this.msUsOrigem = msUsOrigem;
    }

    public String getMsUsDestino() {
        return msUsDestino;
    }

    public void setMsUsDestino(String msUsDestino) {
        this.msUsDestino = msUsDestino;
    }

    public int getMsId() {
        return msId;
    }

    public void setMsId(int msId) {
        this.msId = msId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msUsOrigem != null ? msUsOrigem.hashCode() : 0);
        hash += (msUsDestino != null ? msUsDestino.hashCode() : 0);
        hash += (int) msId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensagemPK)) {
            return false;
        }
        MensagemPK other = (MensagemPK) object;
        if ((this.msUsOrigem == null && other.msUsOrigem != null) || (this.msUsOrigem != null && !this.msUsOrigem.equals(other.msUsOrigem))) {
            return false;
        }
        if ((this.msUsDestino == null && other.msUsDestino != null) || (this.msUsDestino != null && !this.msUsDestino.equals(other.msUsDestino))) {
            return false;
        }
        if (this.msId != other.msId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.MensagemPK[ msUsOrigem=" + msUsOrigem + ", msUsDestino=" + msUsDestino + ", msId=" + msId + " ]";
    }
    
}
