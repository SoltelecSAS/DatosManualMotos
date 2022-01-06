/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CPruebas;
import java.sql.Date;

/**
 *
 * @author
 */
public class CmPruebas {
    
    CPruebas cPruebas;
    
    private int idPruebas;
    private Date fechaPrueba;
    private int idTipoPrueba;
    private int idHojaPruebas;
    private int idUsuario;
    private String autorizada;
    private String aprobada;
    private String finalizada;
    private String abortada;
    private String fechaAborto;
    private String comentarioAborto;
    private int pista;
    
    //constantes que guardaran la informacion de los nombres de las tablas
    //cada constante tendra el nombre de la variable a la cual va ligada
    //la tabla.
    public static final String TABLA = "pruebas";
    public static final String ID_PRUEBAS = "Id_Pruebas";
    public static final String FECHA_PRUEBA = "Fecha_prueba";
    public static final String ID_TIPO_PRUEBA = "Tipo_prueba_for";
    public static final String ID_HOJA_PRUEBAS = "hoja_pruebas_for";
    public static final String ID_USUARIO = "usuario_for";
    public static final String AUTORIZADA = "Autorizada";
    public static final String APROBADA = "Aprobada";
    public static final String FINALIZADA = "Finalizada";
    public static final String ABORTADA = "Abortada";
    public static final String FECHA_ABORTO = "Fecha_aborto";
    public static final String COMENTARIO_ABORTO = "Comentario_aborto";
    public static final String PISTA = "Pista";

    public int getIdPruebas() {
        return idPruebas;
    }

    public void setIdPruebas(int idPruebas) {
        this.idPruebas = idPruebas;
    }

    public Date getFechaPrueba() {
        return fechaPrueba;
    }

    public void setFechaPrueba(Date fechaPrueba) {
        this.fechaPrueba = fechaPrueba;
    }

    public int getIdTipoPrueba() {
        return idTipoPrueba;
    }

    public void setIdTipoPrueba(int idTipoPrueba) {
        this.idTipoPrueba = idTipoPrueba;
    }

    public int getIdHojaPruebas() {
        return idHojaPruebas;
    }

    public void setIdHojaPruebas(int idHojaPruebas) {
        this.idHojaPruebas = idHojaPruebas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(String autorizada) {
        this.autorizada = autorizada;
    }

    public String getAprobada() {
        return aprobada;
    }

    public void setAprobada(String aprobada) {
        this.aprobada = aprobada;
    }

    public String getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(String finalizada) {
        this.finalizada = finalizada;
    }

    public String getAbortada() {
        return abortada;
    }

    public void setAbortada(String abortada) {
        this.abortada = abortada;
    }

    public String getFechaAborto() {
        return fechaAborto;
    }

    public void setFechaAborto(String fechaAborto) {
        this.fechaAborto = fechaAborto;
    }

    public String getComentarioAborto() {
        return comentarioAborto;
    }

    public void setComentarioAborto(String comentarioAborto) {
        this.comentarioAborto = comentarioAborto;
    }

    public int getPista() {
        return pista;
    }

    public void setPista(int pista) {
        this.pista = pista;
    }

    public CmPruebas() {
        cPruebas = new CPruebas();
    }

    public CmPruebas(int idPruebas, Date fechaPrueba, int idTipoPrueba, int idHojaPruebas, int idUsuario, String autorizada, String aprobada, String finalizada, String abortada, String fechaAborto, String comentarioAborto, int pista) {
        this.idPruebas = idPruebas;
        this.fechaPrueba = fechaPrueba;
        this.idTipoPrueba = idTipoPrueba;
        this.idHojaPruebas = idHojaPruebas;
        this.idUsuario = idUsuario;
        this.autorizada = autorizada;
        this.aprobada = aprobada;
        this.finalizada = finalizada;
        this.abortada = abortada;
        this.fechaAborto = fechaAborto;
        this.comentarioAborto = comentarioAborto;
        this.pista = pista;
        cPruebas = new CPruebas();
    }
    
    /*
     * Metodo que verifica si el tipo de prueba esta autorizada para el vehiculo 
     * al cual le pertenece la hoja de pruebas
     * 
     * @param idHojaPruebas es el id de la hoja de pruebas del vehiculo
     * @param idTipoPrueba es el id del tipo de prueba la cual va a ser verificada
     * @return estado
     */
    public boolean buscarPrueba(int idHojaPruebas, int idTipoPrueba) {
        boolean estado = true;
        CmPruebas pruebas = cPruebas.buscarPruebas(idHojaPruebas, idTipoPrueba);
        
        if (pruebas != null) {
            this.setIdPruebas(pruebas.getIdPruebas());
        } else {
            estado = false;
        }
        
        return estado;
    }
}
