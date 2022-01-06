/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 *
 * @author GerenciaDesarrollo
 */

public class AuditoriaSicov implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private Integer idAuditoriaSICOV;
   
    private Integer idRevision;
    
    private String serialEquipoMedicion;
   
    private String ipEquipoMedicion;
  
    private Date fechaRegistroBD;
   
    private Date fechaEvento;  
    
    private Integer tipoOperacion;
   
    private Integer tipoEvento;   
   
    private String codigoProveedor;    
   
    private Integer idRUNTCDA;    
  
    private String TRAMA;    
   
    private String usuario;    
    
    private String IdentificacionUsuario;    
    
    private String Observacion;
    
    public AuditoriaSicov() {
    }

    public AuditoriaSicov(Integer idAuditoriaSICOV) {
        this.idAuditoriaSICOV = idAuditoriaSICOV;
    }    

    public Integer getIdAuditoriaSICOV() {
        return idAuditoriaSICOV;
    }

    public void setIdTrama(Integer IdTrama) {
        this.idAuditoriaSICOV  = IdTrama;
    }
    
    public void setIdRevision(Integer idRevision) {
        this.idRevision = idRevision;
    }

    public Integer getIdRevision() {
        return idRevision;
    }

    public void setRevision(Integer idRevision) {
        this.idRevision = idRevision;
    }

    public String getSerialEquipoMedicion() {
        return serialEquipoMedicion;
    }

    public void setSerialEquipoMedicion(String serialEquipoMedicion) {
        this.serialEquipoMedicion = serialEquipoMedicion;
    }

    public String getIpEquipoMedicion() {
        return ipEquipoMedicion;
    }

    public void setIpEquipoMedicion(String ipEquipoMedicion) {
        this.ipEquipoMedicion = ipEquipoMedicion;
    }

    public Date getFechaRegistroBD() {
        return fechaRegistroBD;
    }

    public void setFechaRegistroBD(Date fechaRegistroBD) {
        this.fechaRegistroBD = fechaRegistroBD;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Integer getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Integer tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Integer getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Integer tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public Integer getIdRUNTCDA() {
        return idRUNTCDA;
    }

    public void setIdRUNTCDA(Integer idRUNTCDA) {
        this.idRUNTCDA = idRUNTCDA;
    }

    public String getTRAMA() {
        return TRAMA;
    }

    public void setTRAMA(String TRAMA) {
        this.TRAMA = TRAMA;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdentificacionUsuario() {
        return IdentificacionUsuario;
    }

    public void setIdentificacionUsuario(String IdentificacionUsuario) {
        this.IdentificacionUsuario = IdentificacionUsuario;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuditoriaSICOV != null ? idAuditoriaSICOV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaSicov)) {
            return false;
        }
        AuditoriaSicov other = (AuditoriaSicov) object;
        return (this.idAuditoriaSICOV != null || other.idAuditoriaSICOV == null) && (this.idAuditoriaSICOV == null || this.idAuditoriaSICOV.equals(other.idAuditoriaSICOV));
    }

    @Override
    public String toString() {
        return "com.model.AuditoriaSicov[idAuditoriaSICOV=" + idAuditoriaSICOV + "]";
    }

}
