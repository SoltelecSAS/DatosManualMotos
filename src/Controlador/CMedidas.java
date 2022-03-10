/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DataBase.CConexion;
import Modelo.CmMedidas;
import Modelo.CmPruebas;
import Utilidades.CMensajes;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author
 */
public class CMedidas {

    CConexion db;
    Map<Integer, Float> mapMedidas = new HashMap<Integer, Float>();

    public CMedidas() {
        db = new CConexion();
    }

    public boolean guardarMedidas(int[] idTipoMedida, float[] valorMedida, int idPrueba) {
        boolean estado = true;
        String campos = "(" + CmMedidas.ID_TIPO_MEDIDA + "," + CmMedidas.VALOR_MEDIDA + "," + CmMedidas.ID_PRUEBA + ")";
        String valores = "";
        for (int i = 0; i < idTipoMedida.length; i++) {

            if (idTipoMedida[i] == 2056 & valorMedida[i] == 0.0 || idTipoMedida[i] == 2057 & valorMedida[i] == 0.0 || idTipoMedida[i] == 2061 & valorMedida[i] == 0.0 || idTipoMedida[i] == 2011 & valorMedida[i] == 0.0) {

            } else {
                if (i > 0){
                    valores += ",";
                }
                valores += "('" + idTipoMedida[i] + "','" + valorMedida[i] + "','" + idPrueba + "')";
                System.out.println("valores " + i + ": " + valores);
            }
        }
        if (!db.agregar(CmMedidas.TABLA, campos, valores)) {
            CMensajes.mensajeError("No se logro agregar la medida");
            estado = false;
        }
        return estado;
    }

    public Map<Integer, Float> buscarMedidas(int idHojaPruebas, int idTipoPrueba) {
        CmPruebas pruebas = null;
        String condicion = CmPruebas.ID_HOJA_PRUEBAS + " = '" + idHojaPruebas + "' AND " + CmPruebas.ID_TIPO_PRUEBA + " = '" + idTipoPrueba + "' AND " + CmPruebas.FINALIZADA + " = 'N'" + " AND " + CmPruebas.AUTORIZADA + " = 'A'";
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
        condicion = "TEST = " + pruebas.getIdPruebas();
        if (db.consultarCampo("MEASURETYPE,Valor_medida", "medidas", condicion)) {
            CachedRowSetImpl crs = db.getCrs();
            try {
                while (crs.next()) {
                    mapMedidas.put(crs.getInt("MEASURETYPE"), crs.getFloat("Valor_medida"));
                }
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        }
        return mapMedidas;
    }

    public boolean borrarMedidas(int idPrueba) {
        boolean estado = false;
        String condicion = "TEST = " + idPrueba;
        if (db.eliminarRegistro("medidas", condicion)) {
            estado = true;
        }
        return estado;
    }

    public boolean borrarDefPrueba(int idPrueba) {
        boolean estado = false;
        String condicion = "id_prueba = " + idPrueba;
        System.out.println("condicion: " + condicion);
        if (db.eliminarRegistro("defxprueba", condicion)) {
            estado = true;
        }
        return estado;
    }
}
