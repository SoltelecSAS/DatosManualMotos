/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CHojaPruebas;
import java.sql.Date;

/**
 *
 * @author 
 */
public class CmHojaPruebas {
    
    CHojaPruebas cHojaPruebas;
    
    private int idHojaPruebas;
    private int idVehiculo;
    private Long idPropietario;
    private int idUsuario;
    private String hojaActiva;
    private String finalizada;
    private String impreso;
    private Date fechaIngresoVehiculo;
    private String anulado;
    private String aprobado;
    private Date fechaExpiracionRevision;
    private String conductor;
    private String consecutivoResolucion;
    private String cerrada;
    private Date fechaExpedicionCertificados;
    private String comentariosCDA;
    private String nombreFoto;
    private int numeroIntentos;
    private int idFotos;
    private String consecutivoRunt;
    private String numeroSolicitud;
    
    //constantes que guardaran la informacion de los nombres de las tablas
    //cada constante tendra el nombre de la variable a la cual va ligada
    //la tabla.
    public static final String TABLA = "hoja_pruebas";
    public static final String ID_HOJA_PRUEBAS = "TESTSHEET";
    public static final String ID_VEHICULO = "Vehiculo_for";
    public static final String ID_PROPIETARIO = "Propietario_for";
    public static final String ID_USUARIO = "Usuario_for";
    public static final String HOJA_ACTIVA = "Hoja_activa_activeflag";
    public static final String FINALIZADA = "Finalizada";
    public static final String IMPRESO = "Impreso";
    public static final String FECHA_INGRESO_VEHICULO = "Fecha_ingreso_vehiculo";
    public static final String ANULADO = "Anulado";
    public static final String APROBADO = "Aprobado";
    public static final String FECHA_EXPIRACION_REVISION = "Fecha_expiracion_revision";
    public static final String CONDUCTOR = "Conductor";
    public static final String CONSECUTIVO_RESOLUCION = "Consecutivo_resolucion";
    public static final String CERRADA = "Cerrada";
    public static final String FECHA_EXPEDICION_CERTIFICADOS = "Fecha_expedicion_certificados";
    public static final String COMENTARIOS_CDA = "Comentarios_cda";
    public static final String NOMBRE_FOTO = "Nombre_foto";
    public static final String NUMERO_INTENTOS = "Numero_intentos";
    public static final String ID_FOTOS = "id_fotos_for";
    public static final String CONSECUTIVO_RUNT = "consecutivo_runt";
    public static final String NUMERO_SOLICITUD = "numero_solicitud";

    public int getIdHojaPruebas() {
        return idHojaPruebas;
    }

    public void setIdHojaPruebas(int idHojaPruebas) {
        this.idHojaPruebas = idHojaPruebas;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Long getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Long idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getHojaActiva() {
        return hojaActiva;
    }

    public void setHojaActiva(String hojaActiva) {
        this.hojaActiva = hojaActiva;
    }

    public String getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(String finalizada) {
        this.finalizada = finalizada;
    }

    public String getImpreso() {
        return impreso;
    }

    public void setImpreso(String impreso) {
        this.impreso = impreso;
    }

    public Date getFechaIngresoVehiculo() {
        return fechaIngresoVehiculo;
    }

    public void setFechaIngresoVehiculo(Date fechaIngresoVehiculo) {
        this.fechaIngresoVehiculo = fechaIngresoVehiculo;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public Date getFechaExpiracionRevision() {
        return fechaExpiracionRevision;
    }

    public void setFechaExpiracionRevision(Date fechaExpiracionRevision) {
        this.fechaExpiracionRevision = fechaExpiracionRevision;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getConsecutivoResolucion() {
        return consecutivoResolucion;
    }

    public void setConsecutivoResolucion(String consecutivoResolucion) {
        this.consecutivoResolucion = consecutivoResolucion;
    }

    public String getCerrada() {
        return cerrada;
    }

    public void setCerrada(String cerrada) {
        this.cerrada = cerrada;
    }

    public Date getFechaExpedicionCertificados() {
        return fechaExpedicionCertificados;
    }

    public void setFechaExpedicionCertificados(Date fechaExpedicionCertificados) {
        this.fechaExpedicionCertificados = fechaExpedicionCertificados;
    }

    public String getComentariosCDA() {
        return comentariosCDA;
    }

    public void setComentariosCDA(String comentariosCDA) {
        this.comentariosCDA = comentariosCDA;
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public int getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(int numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public int getIdFotos() {
        return idFotos;
    }

    public void setIdFotos(int idFotos) {
        this.idFotos = idFotos;
    }

    public String getConsecutivoRunt() {
        return consecutivoRunt;
    }

    public void setConsecutivoRunt(String consecutivoRunt) {
        this.consecutivoRunt = consecutivoRunt;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public CmHojaPruebas() {
        cHojaPruebas = new CHojaPruebas();
    }

    public CmHojaPruebas(int idHojaPruebas, int idVehiculo, Long idPropietario, int idUsuario, String hojaActiva, String finalizada, String impreso, Date fechaIngresoVehiculo, String anulado, String aprobado, Date fechaExpiracionRevision, String conductor, String consecutivoResolucion, String cerrada, Date fechaExpedicionCertificados, String comentariosCDA, String nombreFoto, int numeroIntentos, int idFotos, String consecutivoRunt, String numeroSolicitud) {
        this.idHojaPruebas = idHojaPruebas;
        this.idVehiculo = idVehiculo;
        this.idPropietario = idPropietario;
        this.idUsuario = idUsuario;
        this.hojaActiva = hojaActiva;
        this.finalizada = finalizada;
        this.impreso = impreso;
        this.fechaIngresoVehiculo = fechaIngresoVehiculo;
        this.anulado = anulado;
        this.aprobado = aprobado;
        this.fechaExpiracionRevision = fechaExpiracionRevision;
        this.conductor = conductor;
        this.consecutivoResolucion = consecutivoResolucion;
        this.cerrada = cerrada;
        this.fechaExpedicionCertificados = fechaExpedicionCertificados;
        this.comentariosCDA = comentariosCDA;
        this.nombreFoto = nombreFoto;
        this.numeroIntentos = numeroIntentos;
        this.idFotos = idFotos;
        this.consecutivoRunt = consecutivoRunt;
        this.numeroSolicitud = numeroSolicitud;
        cHojaPruebas = new CHojaPruebas();
    }
    
    public boolean cargarHojaPruebas(int idHojaPruebas) {
        boolean estado = true;
        CmHojaPruebas hojaPruebas = cHojaPruebas.cargarHojaPruebas(idHojaPruebas);
        if (hojaPruebas != null) {
            this.setIdVehiculo(hojaPruebas.getIdVehiculo());
            this.setIdPropietario(hojaPruebas.getIdPropietario());
            this.setIdUsuario(hojaPruebas.getIdUsuario());
            this.setHojaActiva(hojaPruebas.getHojaActiva());
            this.setFinalizada(hojaPruebas.getFinalizada());
            this.setImpreso(hojaPruebas.getImpreso());
            this.setFechaIngresoVehiculo(hojaPruebas.getFechaIngresoVehiculo());
            this.setAnulado(hojaPruebas.getAnulado());
            this.setAprobado(hojaPruebas.getAprobado());
            this.setFechaExpiracionRevision(hojaPruebas.getFechaExpiracionRevision());
            this.setConductor(hojaPruebas.getConductor());
            this.setConsecutivoResolucion(hojaPruebas.getConsecutivoResolucion());
            this.setCerrada(hojaPruebas.getCerrada());
            this.setFechaExpedicionCertificados(hojaPruebas.getFechaExpedicionCertificados());
            this.setComentariosCDA(hojaPruebas.getComentariosCDA());
            this.setNombreFoto(hojaPruebas.getNombreFoto());
            this.setNumeroIntentos(hojaPruebas.getNumeroIntentos());
            this.setIdFotos(hojaPruebas.getIdFotos());
            this.setConsecutivoRunt(hojaPruebas.getConsecutivoRunt());
            this.setNumeroSolicitud(hojaPruebas.getNumeroSolicitud());
        } else {
            estado = false;
        }
        
        return estado;
    }
    
    public boolean ultimaHojaPruebasPorVehiculo(int idVehiculo) {
        boolean estado = true;
        
        CmHojaPruebas hojaPruebas = cHojaPruebas.ultimaHojaPruebasPorId(idVehiculo);
        if (hojaPruebas != null) {
            this.setIdHojaPruebas(hojaPruebas.getIdHojaPruebas());
        } else {
            estado = false;
        }
        
        return estado;
    }
    
}
