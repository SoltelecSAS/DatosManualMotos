/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CPruebas;
import DataBase.CConexion;
import Utilidades.CMensajes;
import Utilidades.Utilidades;
import Vista.frmGases;
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
public class CmGases {

    private float hc;
    private float co;
    private float co2;
    private float o2;
    private float rpm;
    private float temperatura;
    private float temperaturaAmbiente;
    private float humedadRelativa;

    private int[] tipoMedidas;
    private float[] valorMedidas;
    private int modeloVehiculo;
    private int tiemposMotor;
    private String aprobada;
    private List<Integer> defectos;

    //Permisibles maximos
    private static double HC_MAXIMO_2_TIEMPOS;
    private static final double CO_MAXIMO_2_TIEMPOS = 4.5;
    private static final double HC_MAXIMO_4_TIEMPOS = 2000;
    private static final double CO_MAXIMO_4_TIEMPOS = 4.5;
    private static final double O2_MAXIMO_2_TIEMPOS = 11;
    private static final double O2_MAXIMO_4_TIEMPOS = 6;

    public void setHc(float hc) {
        this.hc = hc;
    }

    public void setCo(float co) {
        this.co = co;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public void setO2(float o2) {
        this.o2 = o2;
    }

    public void setRpm(float rpm) {
        this.rpm = rpm;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setTemperaturaAmbiente(float temperaturaAmbiente) {
        this.temperaturaAmbiente = temperaturaAmbiente;
    }

    public void setHumedadRelativa(float humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public CmGases() {
    }

    //llena un array tipo float con el valor de las medidas este orden debe ser
    //igual al guardar los tipos de medidas en el metodo llenarTipoMedidas.
    private void llenarValorMedidas() {
        valorMedidas = new float[8];
        valorMedidas[0] = hc;
        valorMedidas[1] = co;
        valorMedidas[2] = co2;
        valorMedidas[3] = o2;
        valorMedidas[4] = rpm;
        valorMedidas[5] = temperatura;
        valorMedidas[6] = temperaturaAmbiente;
        valorMedidas[7] = humedadRelativa;
    }

    //Llena los tipos de medida en el orden en el cual estan en el metodo llenarValorMedidas
    //asi puede asignar el id correspondiente a la medida.
    private void llenarTipoMedidas() {
        tipoMedidas = new int[8];

        if (tiemposMotor == 2) {
            tipoMedidas[0] = 8018;
            tipoMedidas[1] = 8020;
            tipoMedidas[2] = 8019;
            tipoMedidas[3] = 8021;
            tipoMedidas[4] = 8028;
            tipoMedidas[5] = 8022;
        } else if (tiemposMotor == 4) {
            tipoMedidas[0] = 8001;
            tipoMedidas[1] = 8002;
            tipoMedidas[2] = 8003;
            tipoMedidas[3] = 8004;
            tipoMedidas[4] = 8005;
            tipoMedidas[5] = 8006;
        }

        tipoMedidas[6] = 8031;
        tipoMedidas[7] = 8032;
    }

    //Verifica los posibles defectos devuelve false si el usuario no desea continuar
    //con el proceso y true si desea continuar
    private boolean verificarDefectos() {
        boolean estado = true;
        defectos = new ArrayList<>();
        aprobada = "Y";
        String estadoAprobacion = "";

        //Validamos de cuantos tiempos es el motor.
        if (tiemposMotor == 2) {

            //Validacion del modelo del vehiculo para verificar sus correspondientes
            //permisibles maximos aceptados en la norma vigente.
            if (modeloVehiculo <= 2009) {
                HC_MAXIMO_2_TIEMPOS = 10000;

                if (hc > HC_MAXIMO_2_TIEMPOS || co > CO_MAXIMO_2_TIEMPOS) {
                    aprobada = "N";
                }
            } else if (modeloVehiculo >= 2010) {
                HC_MAXIMO_2_TIEMPOS = 2000;

                if (hc > HC_MAXIMO_2_TIEMPOS || co > CO_MAXIMO_2_TIEMPOS) {
                    aprobada = "N";
                }
            }
            if (o2 > O2_MAXIMO_2_TIEMPOS) {
                aprobada = "N";
            }
        } else if (tiemposMotor == 4) {

            if (hc > HC_MAXIMO_4_TIEMPOS || co > CO_MAXIMO_4_TIEMPOS || o2 > O2_MAXIMO_4_TIEMPOS) {
                aprobada = "N";
            }
        }

        //Verificamos que valor tiene la variable aprobada y asignamos un valor a la variable
        //estadoAprobacion de acuerdo a este valor.
        switch (aprobada) {
            case "Y":
                estadoAprobacion = "Aprobada";
                break;
            case "N":
                estadoAprobacion = "No Aprobada";
                CMensajes.mensajeAdvertencia("El vehiculo sobrepasa los limites maximos de emision");
                defectos.add(84018);
                break;
        }

        //Preguntar al usuario si desea continuar con el proceso de guardado de datos con el estado de aprobacion
        //Actual.
        if (!CMensajes.mensajePregunta("Estado de la Prueba: " + estadoAprobacion + ". ¿Desea Continuar?")) {
            estado = false;
        }

        return estado;
    }

    //Guarda los datos correspondientes tanto las medidas como su id en la tabla, guarda los defectos
    //solo si por lo menos uno, cierra la prueba y envia el dato si fue aprobada o no aprobada.
    public boolean guardarDatos(int idPrueba, int modeloVehiculo, int tiemposMotor, String tramaAuditoria) {
        boolean estado = true;
        boolean guardo;
        this.modeloVehiculo = modeloVehiculo;
        this.tiemposMotor = tiemposMotor;       
        llenarTipoMedidas();
        llenarValorMedidas();       
        //Verificacion de defectos.
        if (!verificarDefectos()) {
            return false;
        }
        if (frmGases.aplicFupa == true) {
            CmMedidas medidas = new CmMedidas(tipoMedidas, valorMedidas, idPrueba);
            guardo = medidas.borrarMedidas();
            guardo = medidas.guardarMedidas();
        } else {
            guardo = true;
        }
        if (guardo == true) {
            CPruebas cPruebas = new CPruebas();
            String trama = cPruebas.obtenerIp(idPrueba);
            //Se finaliza la prueba se envia el id del usuario que realizo la prueba
            //y el estado en la cual se encuentra la prueba despues de la verificacion de
            //los defectos.
            if (!cPruebas.finalizarPruebaGases(idPrueba, aprobada)) {
                CMensajes.mensajeCorrecto("Disculpe la Operacion no se Ejecuto Complentamente; Consulte al SOPORTE TECNICO (1) ");
                return false;
            }

            //Se verifica si hay algun defectos registro y se guarda de lo contrario no hace nada.
            if (!defectos.isEmpty()) {
                if (!cPruebas.guardarDefectos(defectos, idPrueba)) {
                    CMensajes.mensajeCorrecto("Disculpe la Operacion no se Ejecuto Complentamente; Consulte al SOPORTE TECNICO (2)");
                    return false;
                }
            }            
            if(trama!=null) {
               String[] trm = trama.split(";");
              //regTblAuditoriaSicov(tramaAuditoria, 1, 1, idPrueba, trm[0], Integer.parseInt(trm[1]));
            }           
            Boolean reg;
            int e=0;
            if (frmGases.aplicFupa == true) {
                reg = regCuponTest();
                e=1;
            } else {
                reg = true;
            }
            if (reg == true) {
                if(e==0){
                    CMensajes.mensajeCorrecto("Se Agrego Correctamente sin Consumo FUPA"); 
                }else{
                     CMensajes.mensajeCorrecto("Se Agrego Correctamente con Consumo FUPA");
                }
               
            } else {
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
        frmMenuPrincipal.cupos = CConexion.contadorCupo;
        if (CConexion.contadorCupo < 1) {
            CMensajes.mensajeAdvertencia("DISCULPE, SE HA VENCIDO EL LIMITE DE CUPOS DISPONIBLES ..!");
            System.exit(0);
            return false;
        }
        return true;
    }    
}
