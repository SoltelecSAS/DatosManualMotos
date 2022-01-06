/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DataBase.CConexion;
import Modelo.CmPruebas;
import Utilidades.CMensajes;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author
 */
public class CPruebas {

    private CConexion db;
    CPruebas cPruebas;
    private int idPruebas;
    private int idTipoPrueba;
    private int idHojaPruebas;
    private int idUsuario;

    public CPruebas() {
        db = new CConexion();
    }
    public int getIdPruebas() {
        return idPruebas;
    }

    public void setIdPruebas(int idPruebas) {
        this.idPruebas = idPruebas;
    }

    /*
     * Metodo para buscar pruebas por el id de la hoja de pruebas y el tipo de prueba
     * 
     * @param idHojaPruebas El id de la hoja de pruebas
     * @param idTipoPrueba El id del tipo de prueba
     * @return pruebas
     */
    public CmPruebas buscarPruebas(int idHojaPruebas, int idTipoPrueba) {
        CmPruebas pruebas = null;
        String condicion = CmPruebas.ID_HOJA_PRUEBAS + " = '" + idHojaPruebas + "' AND " + CmPruebas.ID_TIPO_PRUEBA + " = '" + idTipoPrueba + "' AND " + CmPruebas.FINALIZADA + " = 'N' AND " + CmPruebas.AUTORIZADA + " = 'A' ";
        if (db.consultarCampo(CmPruebas.ID_PRUEBAS, CmPruebas.TABLA, condicion)) {
            CachedRowSetImpl crs = db.getCrs();
            try {
                if (crs.next()) {
                    pruebas = new CmPruebas();
                    pruebas.setIdPruebas(crs.getInt(CmPruebas.ID_PRUEBAS));
                }
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        }
        return pruebas;
    }

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

    public String obtenerIp(int idPrueba) {
        String ipServ = db.obtenerIp(idPrueba);
        return ipServ;
    }

    public boolean finalizarPrueba(int idPrueba, String aprobada) {
        boolean estado = true;        
        String[] campos = {CmPruebas.APROBADA, CmPruebas.FINALIZADA,CmPruebas.AUTORIZADA};
        String[] valores = {aprobada,"Y","N"};
        String condicion = CmPruebas.ID_PRUEBAS + " = " + idPrueba;
        
        if (!db.actualizarRegistro(CmPruebas.TABLA, campos, valores, condicion,idPrueba)) {
            estado = false;
        }        
        return estado;
    }
    public boolean finalizarPruebaGases(int idPrueba, String aprobada) {
        boolean estado = true;        
        String[] campos = {CmPruebas.APROBADA, CmPruebas.FINALIZADA,CmPruebas.AUTORIZADA};
        String[] valores = {aprobada,"Y","N"};
        String condicion = CmPruebas.ID_PRUEBAS + " = " + idPrueba;
        
        if (!db.actualizarRegistroGases(CmPruebas.TABLA, campos, valores, condicion,idPrueba)) {
            estado = false;
        }        
        return estado;
    }

    public boolean guardarDefectos(List<Integer> defectos, int idPrueba) {
        boolean estado = true;
        String tabla = "defxprueba";
        String campos = "(id_defecto, id_prueba)";
        String valores = "";

        for (int i = 0; i < defectos.size(); i++) {
            valores += "('" + defectos.get(i) + "','" + idPrueba + "'), ";
        }

        valores = valores.substring(0, valores.length() - 2);

        if (!db.agregar(tabla, campos, valores)) {
            estado = false;
        }

        return estado;
    }
}
