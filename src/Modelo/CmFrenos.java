/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CPruebas;
import DataBase.CConexion;
import Utilidades.CMensajes;
import Utilidades.Utilidades;
import Vista.frmFrenos;
import Vista.frmMenuPrincipal;
import com.sun.rowset.CachedRowSetImpl;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class CmFrenos {

    private float pesoDelantero;
    private float pesoTrasero;
    private float fuerzaDelantera;
    private float fuerzaTrasera;
    private float eficacia;

    private float[] valorMedidas;
    private int[] tipoMedidas;
    private List<Integer> defectos;
    private String aprobada;

    //Permisible para eficacia de frenado
    private static final int MINIMO_EFICACIA_A = 30;

    public void setPesoDelantero(float pesoDelantero) {
        this.pesoDelantero = pesoDelantero;
    }

    public void setPesoTrasero(float pesoTrasero) {
        this.pesoTrasero = pesoTrasero;
    }

    public void setFuerzaDelantera(float fuerzaDelantera) {
        this.fuerzaDelantera = fuerzaDelantera;
    }

    public void setFuerzaTrasera(float fuerzaTrasera) {
        this.fuerzaTrasera = fuerzaTrasera;
    }

    private void calcularEficacia() {

        float sumaPeso = pesoDelantero + pesoTrasero;
        float sumaFuerza = fuerzaDelantera + fuerzaTrasera;

        eficacia = (sumaFuerza / sumaPeso) * 100;

        if (eficacia > 100) {
            eficacia = 100;
        }
    }

    private String llenarValorMedidas(int idPrueba) {
        valorMedidas = new float[5];
        valorMedidas[0] = pesoDelantero;
        valorMedidas[1] = pesoTrasero;
        valorMedidas[2] = fuerzaDelantera;
        valorMedidas[3] = fuerzaTrasera;
        valorMedidas[4] = eficacia;
        String tramaAuditoria = "{\"eficaciaTotal\":\"".concat(String.valueOf(eficacia)).concat("\",").concat("\"eficaciaAuxiliar\":\"").concat(String.valueOf(" ")).concat("\",");

        tramaAuditoria = tramaAuditoria.concat("\"fuerzaEje").concat(String.valueOf(1)).concat("Izquierdo\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"pesoEje").concat(String.valueOf(1)).concat("Izquierdo\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"fuerzaEje").concat(String.valueOf(1)).concat("Derecho\":\"").concat(String.valueOf(fuerzaDelantera)).concat("\",").concat("\"pesoEje").concat(String.valueOf(1)).concat("Derecho\":\"").concat(String.valueOf(pesoDelantero)).concat("\",").concat("\"eje").concat(String.valueOf(1)).concat("Desequilibrio\":\"").concat(String.valueOf(" ")).concat("\",");
        tramaAuditoria = tramaAuditoria.concat("\"fuerzaEje").concat(String.valueOf(1)).concat("Izquierdo\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"pesoEje").concat(String.valueOf(1)).concat("Izquierdo\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"fuerzaEje").concat(String.valueOf(1)).concat("Derecho\":\"").concat(String.valueOf(fuerzaTrasera)).concat("\",").concat("\"pesoEje").concat(String.valueOf(1)).concat("Derecho\":\"").concat(String.valueOf(pesoTrasero)).concat("\",").concat("\"eje").concat(String.valueOf(1)).concat("Desequilibrio\":\"").concat(String.valueOf(" ")).concat("\",");
        for (int k = 2 + 1; k < 6; k++) {
            tramaAuditoria = tramaAuditoria.concat("\"fuerzaEje").concat(String.valueOf(k)).concat("Izquierdo\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"pesoEje").concat(String.valueOf(k)).concat("Izquierdo\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"fuerzaEje").concat(String.valueOf(k)).concat("Derecho\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"pesoEje").concat(String.valueOf(k)).concat("Derecho\":\"").concat(String.valueOf(" ")).concat("\",").concat("\"eje").concat(String.valueOf(k)).concat("Desequilibrio\":\"").concat(String.valueOf(" ")).concat("\",");
        }
        tramaAuditoria = tramaAuditoria.concat("\"tablaAfectada\":\"medidas\",\"idRegistro\":\"").concat(String.valueOf(idPrueba)).concat("\"}");
        return tramaAuditoria;

    }

    private void llenarTipoMedidas() {
        tipoMedidas = new int[5];
        tipoMedidas[0] = 5000;
        tipoMedidas[1] = 5001;
        tipoMedidas[2] = 5008;
        tipoMedidas[3] = 5009;
        tipoMedidas[4] = 5024;
    }

    private boolean verificarDefectos() {
        boolean estado = true;
        defectos = new ArrayList<>();
        aprobada = "Y";
        String estadoAprobacion = "";

        if (eficacia < MINIMO_EFICACIA_A) {
            aprobada = "N";
            defectos.add(54010);
            CMensajes.mensajeAdvertencia("La Eficacia de frenado es inferior al 30%");
        }

        switch (aprobada) {
            case "Y":
                estadoAprobacion = "Aprobada";
                break;
            case "N":
                estadoAprobacion = "No Aprobada";
                break;
        }

        if (!CMensajes.mensajePregunta("Estado de la Prueba: " + estadoAprobacion + ". ¿Desea Continuar?")) {
            estado = false;
        }

        return estado;
    }

    public boolean guardarDatos(int idPrueba) {
        boolean estado = true;

        calcularEficacia();
        String tramaAuditoria = llenarValorMedidas(idPrueba);
        llenarTipoMedidas();

        if (!verificarDefectos()) {
            return false;
        }
       boolean guardo;
         if (frmFrenos.aplicFupa == true) {
              CmMedidas medidas = new CmMedidas(tipoMedidas, valorMedidas, idPrueba);
            guardo = medidas.borrarMedidas();
            guardo = medidas.guardarMedidas();
        } else {
            guardo = true;
        }
        if (guardo == true) {
            CPruebas cPruebas = new CPruebas();
            String trama = cPruebas.obtenerIp(idPrueba);
            if (!cPruebas.finalizarPrueba(idPrueba, aprobada)) {
                CMensajes.mensajeCorrecto("Disculpe la Operacion no se Ejecuto Complentamente; Consulte al SOPORTE TECNICO (1) ");
                return false;
            }

            if (!defectos.isEmpty()) {
                if (!cPruebas.guardarDefectos(defectos, idPrueba)) {
                     CMensajes.mensajeCorrecto("Disculpe la Operacion no se Ejecuto Complentamente; Consulte al SOPORTE TECNICO (2)");
                    return false;
                }
            }
            if(trama!=null) {
               String[] trm = trama.split(";");
              // regTblAuditoriaSicov(tramaAuditoria, 1, 4, idPrueba, trm[0], Integer.parseInt(trm[1]));
            }     
            int e = 0;
            Boolean reg;
            if (frmFrenos.aplicFupa == true) {
                reg = regCuponTest();
                e = 1;
            } else {
                reg = true;
            }
            if (reg == true) {
                if (e == 0) {
                    CMensajes.mensajeCorrecto("Se Agrego Correctamente sin Consumo FUPA");
                } else {
                    CMensajes.mensajeCorrecto("Se Agrego Correctamente con Consumo FUPA");
                }                    
            } else {
                estado = false;
                CMensajes.mensajeCorrecto("Disculpe la Operacion no se Ejecuto Complentamente; Consulte al SOPORTE TECNICO (4)");
            }
        } else {
            estado = false;
        }

        return estado;
    }

    private void regTblAuditoriaSicov(String tramaAuditoria, Integer evento, Integer operacion, Integer indPrueba, String ipServ, int idTrama) {
        try {
            Statement stm;
            ResultSet rs;
            CachedRowSetImpl crs;
            PreparedStatement pstm;
            Connection conn = CConexion.conectarBd();
            String sql = "SELECT * FROM Pruebas  WHERE Id_Pruebas= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, indPrueba);
            rs = ps.executeQuery();
            rs.next();
            AuditoriaSicov auditoriaSicov = new AuditoriaSicov();
            auditoriaSicov.setIdRevision(rs.getInt("hoja_pruebas_for"));
            auditoriaSicov.setIpEquipoMedicion(ipServ);
            auditoriaSicov.setSerialEquipoMedicion(rs.getString("serialEquipo"));
            auditoriaSicov.setCodigoProveedor("858");
            auditoriaSicov.setTipoEvento(evento);
            auditoriaSicov.setTipoOperacion(operacion);
            auditoriaSicov.setTRAMA(tramaAuditoria);
            String obs = rs.getString("observaciones");
            if (obs == null) {
                auditoriaSicov.setObservacion(" ");
            } else {
                auditoriaSicov.setObservacion(rs.getString("observaciones"));
            }
            auditoriaSicov.setFechaEvento(rs.getTimestamp("Fecha_final"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(auditoriaSicov.getFechaEvento()); // Configuramos la fecha que se recibe
            calendar.add(Calendar.MILLISECOND, 9500);
            auditoriaSicov.setFechaRegistroBD(calendar.getTime());
            System.out.println("Esta es la fecha que toma la tbl Auditoria " + auditoriaSicov.getFechaEvento());
            sql = "SELECT * FROM usuarios  WHERE  GEUSER = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rs.getInt("usuario_for"));
            ResultSet ru = ps.executeQuery();
            ru.next();
            auditoriaSicov.setUsuario(ru.getString("Nombre_usuario"));
            auditoriaSicov.setIdentificacionUsuario(String.valueOf(ru.getInt("cedula")));
            auditoriaSicov.setIdTrama(idTrama);
            sql = "SELECT * FROM cda  WHERE  id_cda = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ResultSet rc = ps.executeQuery();
            rc.next();
            auditoriaSicov.setIdRUNTCDA(rc.getInt("id_runt"));
            sql = "INSERT auditoria_sicov (ID_REVISION,SERIAL_EQUIPO_MEDICION,IP_EQUIPO_MEDICION,FECHA_EVENTO,TIPO_OPERACION,TIPO_EVENTO,CODIGO_PROVEEDOR,ID_RUNT_CDA,TRAMA,USUARIO,IDENTIFICACION_USUARIO,OBSERVACION,ID_AUDITORIA_SICOV,FECHA_REGISTRO_BD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, auditoriaSicov.getIdRevision());
            ps.setString(2, auditoriaSicov.getSerialEquipoMedicion());
            ps.setString(3, auditoriaSicov.getIpEquipoMedicion());
            Timestamp timestamp = new Timestamp(auditoriaSicov.getFechaEvento().getTime());
            ps.setTimestamp(4, timestamp);
            ps.setInt(5, auditoriaSicov.getTipoEvento());
            ps.setInt(6, auditoriaSicov.getTipoOperacion());
            ps.setString(7, auditoriaSicov.getCodigoProveedor());
            ps.setInt(8, auditoriaSicov.getIdRUNTCDA());
            ps.setString(9, auditoriaSicov.getTRAMA());
            ps.setString(10, auditoriaSicov.getUsuario());
            ps.setString(11, auditoriaSicov.getIdentificacionUsuario());
            ps.setString(12, auditoriaSicov.getObservacion());
            ps.setInt(13, auditoriaSicov.getIdAuditoriaSICOV());
            timestamp = new Timestamp(auditoriaSicov.getFechaRegistroBD().getTime());
            ps.setTimestamp(14, timestamp);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CmFrenos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean regCuponTest() {
        CConexion.contadorCupo = CConexion.contadorCupo - 1;
       CConexion c = new CConexion();
       c.updateA2(CConexion.contadorCupo);
       frmMenuPrincipal.cupos=CConexion.contadorCupo;
       if (CConexion.contadorCupo < 1) {
                CMensajes.mensajeAdvertencia("DISCULPE, SE HA VENCIDO EL LIMITE DE CUPOS DISPONIBLES ..!");                 
                System.exit(0);
                return false;
       }
       return true;
    }
}
