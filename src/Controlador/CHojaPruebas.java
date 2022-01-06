/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DataBase.CConexion;
import Modelo.CmHojaPruebas;
import Utilidades.CMensajes;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

/**
 *
 * @author Soltelec Ltda
 */
public class CHojaPruebas {

    private CConexion db;
    
    public CHojaPruebas() {
        db = new CConexion();
    }
    
    public CmHojaPruebas cargarHojaPruebas(int idHojaPruebas) {
        CmHojaPruebas hojaPruebas = null;
        String condicion = CmHojaPruebas.ID_HOJA_PRUEBAS + " = " + idHojaPruebas;
        
        if (db.consultarCampo("*", CmHojaPruebas.TABLA, condicion)) {
            CachedRowSetImpl crs = db.getCrs();
            try {
                if (crs.next()) {
                    hojaPruebas = new CmHojaPruebas();
                    
                    hojaPruebas.setIdVehiculo(crs.getInt(CmHojaPruebas.ID_VEHICULO));
                    hojaPruebas.setIdPropietario(crs.getLong(CmHojaPruebas.ID_PROPIETARIO));
                    hojaPruebas.setIdUsuario(crs.getInt(CmHojaPruebas.ID_USUARIO));
                    hojaPruebas.setHojaActiva(crs.getString(CmHojaPruebas.HOJA_ACTIVA));
                    hojaPruebas.setFinalizada(crs.getString(CmHojaPruebas.FINALIZADA));
                    hojaPruebas.setImpreso(crs.getString(CmHojaPruebas.IMPRESO));
                    hojaPruebas.setFechaIngresoVehiculo(crs.getDate(CmHojaPruebas.FECHA_INGRESO_VEHICULO));
                    hojaPruebas.setAnulado(crs.getString(CmHojaPruebas.ANULADO));
                    hojaPruebas.setAprobado(crs.getString(CmHojaPruebas.APROBADO));
                    hojaPruebas.setFechaExpiracionRevision(crs.getDate(CmHojaPruebas.FECHA_EXPIRACION_REVISION));
                    hojaPruebas.setConductor(crs.getString(CmHojaPruebas.CONDUCTOR));
                    hojaPruebas.setConsecutivoResolucion(crs.getString(CmHojaPruebas.CONSECUTIVO_RESOLUCION));
                    hojaPruebas.setCerrada(crs.getString(CmHojaPruebas.CERRADA));
                    hojaPruebas.setFechaExpedicionCertificados(crs.getDate(CmHojaPruebas.FECHA_EXPEDICION_CERTIFICADOS));
                    hojaPruebas.setComentariosCDA(crs.getString(CmHojaPruebas.COMENTARIOS_CDA));
                    hojaPruebas.setNombreFoto(crs.getString(CmHojaPruebas.NOMBRE_FOTO));
                    hojaPruebas.setNumeroIntentos(crs.getInt(CmHojaPruebas.NUMERO_INTENTOS));
                    hojaPruebas.setIdFotos(crs.getInt(CmHojaPruebas.ID_FOTOS));
                    hojaPruebas.setConsecutivoRunt(crs.getString(CmHojaPruebas.CONSECUTIVO_RUNT));
                    hojaPruebas.setNumeroSolicitud(crs.getString(CmHojaPruebas.NUMERO_INTENTOS));
                } else {
                    CMensajes.mensajeError("No Hoja de Pruebas con el id: " + idHojaPruebas);
                }
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        }
        
        return hojaPruebas;
    }
    
    /*
     * Metodo para consultar todos los registros que se encuentran en la tabla
     * hoja_pruebas
     * 
     * @return datosHojaPruebas
     */
    public CmHojaPruebas ultimaHojaPruebasPorId(int idVehiculo) {
        CmHojaPruebas hojaPruebas = null;
        String condicion = CmHojaPruebas.ID_VEHICULO + " = '" + idVehiculo + "' and " + CmHojaPruebas.FINALIZADA + " = 'N'";
        
        //Valida si la consulta es correcta de lo contrario retorna un valor null
        //y muestra el mensaje que se encuentra en el metodo consultar.
        if(db.consultarCampo("MAX(" + CmHojaPruebas.ID_HOJA_PRUEBAS + ") AS " + CmHojaPruebas.ID_HOJA_PRUEBAS,CmHojaPruebas.TABLA,condicion)) {
            CachedRowSetImpl crs = db.getCrs();
            try {
                //bucle while para que recorra todo el ResultSet si ya no hay mas datos
                //se sale del bucle.
                if (crs.next()) {                
                    hojaPruebas = new CmHojaPruebas();
                    
                    //Enviar todos los valores necesarios para CmHojaPruebas
                    hojaPruebas.setIdHojaPruebas(crs.getInt(CmHojaPruebas.ID_HOJA_PRUEBAS));                    
                } else {
                    CMensajes.mensajeError("No se encontro hoja de pruebas");
                }
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        }
        
        return hojaPruebas;
    }
}
