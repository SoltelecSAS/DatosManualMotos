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
import Vista.frmLuces;
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
public class CmLuces {

    private float InclinacionLuz;
    private float IntensidadLuzAlta;
    private float IntensidadLuzBaja;
    private float mayorIntensidadAlta;

    private float InclinacionLuzSecundaria;
    private float IntensidadLuzAltaSe;
    private float IntensidadLuzBajaSe;
    
    private float InclinacionLuzTer;
    private float IntensidadLuzAltaTer;
    private float IntensidadLuzBajaTer;

    private float[] valorMedidas;
    private int[] tipoMedidas;
    private int cantidadLuces;
    private List<Integer> defectos;
    private String aprobada;

    //Constantes para los valores minimos y maximos permitidos para la prueba
    //de luces.
    public static final double INTENSIDAD_LUZ_BAJA_A = 2.5; //24005
    public static final double RANGO_MAXIMO_DESVIACION_A = 3.5; //24006
    public static final double RANGO_MINIMO_DESVIACION_A = 0.5; //24006

    public void setInclinacionLuz(float InclinacionLuz) {
        this.InclinacionLuz = InclinacionLuz;
    }

    public void setIntensidadLuzAlta(float IntensidadLuzAlta) {
        this.IntensidadLuzAlta = IntensidadLuzAlta;
        System.out.println("valor que tiene la luz alta EN CMLUCES: "+ IntensidadLuzAlta);
    }

    public void setIntensidadLuzBaja(float IntensidadLuzBaja) {
        this.IntensidadLuzBaja = IntensidadLuzBaja;
    }

    public void setInclinacionLuzSecundaria(float InclinacionLuzSecundaria) {
        this.InclinacionLuzSecundaria = InclinacionLuzSecundaria;
    }

    public void setIntensidadLuzAltaSe(float IntensidadLuzAltaSe) {
        this.IntensidadLuzAltaSe = IntensidadLuzAltaSe;
    }

    public void setIntensidadLuzBajaSe(float IntensidadLuzBajaSe) {
        this.IntensidadLuzBajaSe = IntensidadLuzBajaSe;
    }

    public float getInclinacionLuzTer() {
        return InclinacionLuzTer;
    }

    public void setInclinacionLuzTer(float InclinacionLuzTer) {
        this.InclinacionLuzTer = InclinacionLuzTer;
    }

    public float getIntensidadLuzBajaTer() {
        return IntensidadLuzBajaTer;
    }

    public void setIntensidadLuzBajaTer(float IntensidadLuzBajaTer) {
        this.IntensidadLuzBajaTer = IntensidadLuzBajaTer;
    }

    public float getIntensidadLuzAltaTer() {
        return IntensidadLuzAltaTer;
    }

    public void setIntensidadLuzAltaTer(float IntensidadLuzAltaTer) {
        this.IntensidadLuzAltaTer = IntensidadLuzAltaTer;
    }

    public float getIntensidadLuzAlta() {
        return IntensidadLuzAlta;
    }

    public CmLuces() {
    }

    private void calcularMayor() {
        if (cantidadLuces == 2) 
        {
            if (IntensidadLuzAlta > IntensidadLuzAltaSe) 
            {
                mayorIntensidadAlta = IntensidadLuzAlta;
            } else 
            {
                mayorIntensidadAlta = IntensidadLuzAltaSe;
            }
        }
        else {
            mayorIntensidadAlta = IntensidadLuzAlta;
        }
    }

    private void llenarValorMedidas() {
        valorMedidas = new float[(cantidadLuces * 3) +1];
        System.out.println("Taño del arreglo de luces : "+ valorMedidas.length );
        valorMedidas[0] = InclinacionLuz;
        valorMedidas[1] = IntensidadLuzBaja;
        valorMedidas[2] = 0;
        //if(IntensidadLuzAlta != null ){
        if(IntensidadLuzAlta != 100){
        valorMedidas[3] = IntensidadLuzAlta;
        }
      // }
        

        if (cantidadLuces == 2) {
            valorMedidas[4] = InclinacionLuzSecundaria;
            valorMedidas[5] = IntensidadLuzAltaSe;
            valorMedidas[6] = IntensidadLuzBajaSe;
        }
         if (cantidadLuces == 3) {
            valorMedidas[4] = InclinacionLuzSecundaria;
            valorMedidas[5] = IntensidadLuzAltaSe;
            valorMedidas[6] = IntensidadLuzBajaSe;
            valorMedidas[7] = InclinacionLuzTer;
            valorMedidas[8] = IntensidadLuzAltaTer;
            valorMedidas[9] = IntensidadLuzBajaTer;
        }
    }

    private void llenarTipoMedidas() {
        tipoMedidas = new int[(cantidadLuces * 3) + 1];
        tipoMedidas[0] = 2013;//ANGULO LUZ BAJA 1
        tipoMedidas[1] = 2014;//INTENSIDAD LUZ BAJA 1 
        tipoMedidas[2] = 2011;//SUMA DE TODAS LAS INTENSIDADES
        tipoMedidas[3] = 2056;//INTENSIDAD LUZ ALTA 1
        
        
        
        

        if (cantidadLuces == 2) {
            tipoMedidas[4] = 2002;//ANGULO BAJA 2
            tipoMedidas[5] = 2057;//INTENSIDAD ALTA 2
            tipoMedidas[6] = 2015;//INTENSIDAD BAJA 2
        }
        if (cantidadLuces == 3) {
            tipoMedidas[4] = 2002;//ANGULO BAJA 2
            tipoMedidas[5] = 2057;//INTENSIDAD ALTA 2
            tipoMedidas[6] = 2015;//INTENSIDAD BAJA 2
            tipoMedidas[7] = 2022;//ANGULO BAJA 3
            tipoMedidas[8] = 2061;//INTENSIDAD ALTA 3
            tipoMedidas[9] = 2000;//INTENSIDAD BAJA 3
            
            
        }

//        System.out.println("10" + tipoMedidas[10]);

    }

    private boolean verificarDefectos() {
        boolean estado = true;        
        aprobada = "Y";
        defectos = new ArrayList<>();
        String estadoAprobacion = "";
        switch (cantidadLuces) {
            case 1:
                if ( IntensidadLuzBaja < INTENSIDAD_LUZ_BAJA_A  ) //INSERTA DEFECTO DE INTENSIDAD
                {
                    aprobada = "N";
                    defectos.add(24005);
                    
                }
                if (InclinacionLuz < 0.5 || InclinacionLuz > RANGO_MAXIMO_DESVIACION_A) //INSERTA DEFECTO DE INCLINACION
                {
                    aprobada = "N";
                    defectos.add(24006);
                    CMensajes.mensajeAdvertencia("La Desviacion de un haz de luz esta fuera del rango permitido");
                }
                break;
            case 2:
                if ( IntensidadLuzBajaSe < INTENSIDAD_LUZ_BAJA_A || IntensidadLuzBaja < INTENSIDAD_LUZ_BAJA_A ) //INSERTA DEFECTO DE INTENSIDAD
                {
                    aprobada = "N";
                    defectos.add(24005);
                    CMensajes.mensajeAdvertencia("La intensidad en un haz de luz es inferior a los 2.5 Klux");
                }
                if (InclinacionLuzSecundaria < RANGO_MINIMO_DESVIACION_A || InclinacionLuzSecundaria > RANGO_MAXIMO_DESVIACION_A || InclinacionLuz < RANGO_MINIMO_DESVIACION_A || InclinacionLuz > RANGO_MAXIMO_DESVIACION_A) //INSERTA DEFECTO DE INCLINACION
                {
                    aprobada = "N";
                    defectos.add(24006);
                    CMensajes.mensajeAdvertencia("La Desviacion de un haz de luz esta fuera del rango permitido");
                }
                break;
            case 3:
                if ( IntensidadLuzBajaSe < INTENSIDAD_LUZ_BAJA_A || IntensidadLuzBaja < INTENSIDAD_LUZ_BAJA_A || IntensidadLuzBajaTer < INTENSIDAD_LUZ_BAJA_A)//INSERTA DEFECTO DE INTENSIDAD
                {
                    aprobada = "N";
                    defectos.add(24005);
                    CMensajes.mensajeAdvertencia("La intensidad en un haz de luz es inferior a los 2.5 Klux");
                }
                if ( InclinacionLuzSecundaria < RANGO_MINIMO_DESVIACION_A || InclinacionLuzSecundaria > RANGO_MAXIMO_DESVIACION_A || InclinacionLuz < RANGO_MINIMO_DESVIACION_A || InclinacionLuz > RANGO_MAXIMO_DESVIACION_A || InclinacionLuzTer  < RANGO_MINIMO_DESVIACION_A || InclinacionLuzTer > RANGO_MAXIMO_DESVIACION_A) //INSERTA DEFECTO DE INCLINACION
                {
                    aprobada = "N";
                    defectos.add(24006);
                    CMensajes.mensajeAdvertencia("La Desviacion de un haz de luz esta fuera del rango permitido");
                }
                break;
            default:
                break;
        }
        
        switch (aprobada) 
        {
            case "Y":
                estadoAprobacion = "Aprobada";
                break;
            case "N":
                estadoAprobacion = "No Aprobada";
                break;
        }
        if (!CMensajes.mensajePregunta("Estado de la Prueba: " + estadoAprobacion + ". ¿Desea Continuar?")) 
        {
            estado = false;
        }
        return estado;
    }

    public boolean guardarDatos(int idPrueba, int cantidadLuces) {
        boolean estado = true;
        this.cantidadLuces = cantidadLuces;
        calcularMayor();
        llenarTipoMedidas();
        llenarValorMedidas();
        CmMedidas medidas = new CmMedidas(tipoMedidas, valorMedidas, idPrueba);
        medidas.borrarDefpruebas();
        if (!verificarDefectos()) {
            return false;
        }

        boolean guardo;
        String tramaAuditoria = "{\"intensidadDerecha\":\"".concat(String.valueOf(IntensidadLuzBaja)).concat("\",").concat("\"inclinacionDerecha\":\"").concat(String.valueOf(InclinacionLuz)).concat("\",").concat("\"intensidadIzquierda\":\"").concat(String.valueOf("0.0")).concat("\",").concat("\"inclinacionIzquierda\":\"").concat(String.valueOf("0.0 ")).concat("\",").concat("\"SumatoriaIntensidad\":\"").concat(String.valueOf(IntensidadLuzBaja)).concat("\",").concat("\"tablaAfectada\":\"medidas\",\"idRegistro\":\"").concat(String.valueOf(idPrueba)).concat("\"}");
        if (frmLuces.aplicFupa == true) {
            
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
                    CMensajes.mensajeCorrecto("Disculpe la Operacion no se Ejecuto Completamente; Consulte al SOPORTE TECNICO (2)");
                    return false;
                }
            }
            if(trama!=null) {
               String[] trm = trama.split(";");
               regTblAuditoriaSicov(tramaAuditoria, 1, 2, idPrueba, trm[0], Integer.parseInt(trm[1]));
            } 
           
            int e = 0;
            Boolean reg;
            System.out.print(" voy a entrar logica de descarg ");
            if (frmLuces.aplicFupa == true) {                
                reg = regCuponTest();                 
                e = 1;
            } else {
                reg = true;
            }
            System.out.print(" variable es "+reg);
            if (reg == true) {
                 System.out.print("VALIDO SI CONSUMO O NO FUPA  Y E ES: "+e);
                if (e == 0) {
                    CMensajes.mensajeCorrecto("Se Agrego Correctamente sin Consumo FUPA");
                } else {
                    CMensajes.mensajeCorrecto("Se Agrego Correctamente con Consumo FUPA");
                }
                 System.out.print("donde entro: "+e);
            } else {
                estado = false;
            }
            return estado;
        }
        return estado;
    }

    private void regTblAuditoriaSicov(String tramaAuditoria, Integer evento, Integer operacion, Integer indPrueba, String ipServ, int idTrama) {
        try {
            ResultSet rs;
           // CachedRowSetImpl crs;
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
