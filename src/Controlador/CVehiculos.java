/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DataBase.CConexion;
import Modelo.CmVehiculos;
import Utilidades.CMensajes;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

/**
 *
 * @author Soltelec Ltda
 */
public class CVehiculos {
    
    CConexion db;

    public CVehiculos() {
        db = new CConexion();
    }
    
    public CmVehiculos cargarVehiculo(int id) {
        CmVehiculos vehiculos = null;
        String condicion = CmVehiculos.ID_VEHICULO + " = '" + id + "'";
        
        if (db.consultarCampo("*", CmVehiculos.TABLA, condicion)) {
            CachedRowSetImpl crs = db.getCrs();
            try {
                if (crs.next()) {
                  vehiculos = new CmVehiculos();
                  vehiculos.setIdVehiculo(crs.getInt(CmVehiculos.ID_VEHICULO));
                  vehiculos.setNumeroPlaca(crs.getString(CmVehiculos.NUMERO_PLACA));
                  vehiculos.setIdLineaVehiculo(crs.getInt(CmVehiculos.ID_LINEA_VEHICULO));
                  vehiculos.setIdMarcaVehiculo(crs.getInt(CmVehiculos.ID_MARCA_VEHICULO));
                  vehiculos.setModelo(crs.getInt(CmVehiculos.MODELO));
                  vehiculos.setCilindraje(crs.getInt(CmVehiculos.CILINDRAJE));
                  vehiculos.setIdServicios(crs.getInt(CmVehiculos.ID_SERVICIOS));
                  vehiculos.setIdClaseVehiculo(crs.getInt(CmVehiculos.ID_CLASE_VEHICULO));
                  vehiculos.setNumeroLicencia(crs.getString(CmVehiculos.NUMERO_LICENCIA));
                  vehiculos.setNumeroEjes(crs.getInt(CmVehiculos.NUMERO_EJES));
                  vehiculos.setIdTipoVehiculo(crs.getInt(CmVehiculos.ID_TIPO_VEHICULO));
                  vehiculos.setFechaCreacion(crs.getDate(CmVehiculos.FECHA_CREACION));
                  vehiculos.setIdUsuario(crs.getInt(CmVehiculos.ID_USUARIO));
                  vehiculos.setIdPropietario(crs.getLong(CmVehiculos.ID_PROPIETARIO));
                  vehiculos.setNumeroExostos(crs.getInt(CmVehiculos.NUMERO_EXOSTOS));
                  vehiculos.setDiametro(crs.getInt(CmVehiculos.DIAMETRO));
                  vehiculos.setIdTipoCombustible(crs.getInt(CmVehiculos.ID_TIPO_COMBUSTIBLE));
                  vehiculos.setTiemposMotor(crs.getInt(CmVehiculos.TIEMPOS_MOTOR));
                  vehiculos.setVelocidad(crs.getInt(CmVehiculos.VELOCIDAD));
                  vehiculos.setIdColor(crs.getInt(CmVehiculos.ID_COLOR));
                  vehiculos.setNumeroSOAT(crs.getString(CmVehiculos.NUMERO_SOAT));
                  vehiculos.setIdAseguradora(crs.getInt(CmVehiculos.ID_ASEGURADORA));
                  vehiculos.setFechaSOAT(crs.getDate(CmVehiculos.FECHA_SOAT));
                  vehiculos.setFechaExpSOAT(crs.getDate(CmVehiculos.FECHA_EXPIRACION_SOAT));
                  vehiculos.setIdLlantas(crs.getInt(CmVehiculos.ID_LLANTAS));
                  vehiculos.setNacionalidad(crs.getString(CmVehiculos.NACIONALIDAD));
                  vehiculos.setSpService(crs.getInt(CmVehiculos.SP_SERVICE));
                  vehiculos.setNumeroMotor(crs.getString(CmVehiculos.NUMERO_MOTOR));
                  vehiculos.setVin(crs.getString(CmVehiculos.VIN));
                  vehiculos.setFechaRegistro(crs.getDate(CmVehiculos.FECHA_REGISTRO));
                  vehiculos.setIdPais(crs.getInt(CmVehiculos.ID_PAIS));
                  vehiculos.setKilometraje(crs.getInt(CmVehiculos.KILOMETRAJE));
                  vehiculos.setNumeroSillas(crs.getInt(CmVehiculos.NUMERO_SILLAS));
                  vehiculos.setVidriosPolarizados(crs.getString(CmVehiculos.VIDRIOS_POLARIZADOS));
                  vehiculos.setBlindaje(crs.getString(CmVehiculos.BLINDAJE));
                  vehiculos.setNumeroChasis(crs.getString(CmVehiculos.NUMERO_CHASIS));
                } else {
                    CMensajes.mensajeError("No se encontro vehiculo con id: " + id);
                }        
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        } else {
            CMensajes.mensajeError("No se encontro vehiculo con id: " + id);
        }
        
        return vehiculos;
    }
    
    public CmVehiculos idPorPlaca(String placa) {
        CmVehiculos vehiculos = null;
        String condicion = CmVehiculos.NUMERO_PLACA + " = '" + placa + "'";
        
        if (db.consultarCampo(CmVehiculos.ID_VEHICULO, CmVehiculos.TABLA, condicion)) {
            CachedRowSetImpl crs = db.getCrs();
            try {
                if (crs.next()) {
                  vehiculos = new CmVehiculos();
                  vehiculos.setIdVehiculo(crs.getInt(CmVehiculos.ID_VEHICULO));  
                } else {
                    CMensajes.mensajeError("No se encontro vehiculo con placa: " + placa);
                }        
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        } else {
            CMensajes.mensajeError("No se encontro vehiculo con placa: " + placa);
        }
        
        return vehiculos;
    }
}
