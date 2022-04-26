/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import com.sun.rowset.CachedRowSetImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import Utilidades.CMensajes;
import Utilidades.Utilidades;
import Vista.frmMenuPrincipal;
import java.io.FileReader;
import java.sql.Timestamp;

/**
 *
 * @author
 */
public class CConexion {

    private static Connection conn = null;
    private Statement stm;
    private ResultSet rs;
    private CachedRowSetImpl crs;
    public static String Clave;
    private PreparedStatement pstm;
    protected static String baseDatos;
    protected static String ipServidor;
    protected static String usuario;
    protected static String puerto;
    protected static String contrasena;
    public static Integer contadorCupo;
    public static String macEquipo;

    public CachedRowSetImpl getCrs() {
        return crs;
    }

    /**
     * Metodo para conectar con la base de datos, los datos necesarios se
     * obtienen desde un archivo "conf.properties" que se encuentra en el
     * directorio raiz del programa.
     *
     * @return
     */
    public String obtenerIp(int idPrueba) {
        String ipServidor = null;
        try {
            if (conectar()) {
                PreparedStatement pst = conn.prepareStatement("SELECT Fecha_aborto FROM pruebas  WHERE Id_Pruebas=" + idPrueba);
                ResultSet result = pst.executeQuery();
                while (result.next()) {
                    ipServidor = result.getString("Fecha_aborto");
                }
            }
        } catch (SQLException ex) {
        }
        return ipServidor;
    }

    public static boolean validacionApp() {
        try {
            FileReader fw = new FileReader("./bin/packnet.dll");
            FileReader fwP = new FileReader("./bin/pack.dll");
            System.out.println(" URL = " + fw.toString());
            java.io.BufferedReader br = new java.io.BufferedReader(fw);
            java.io.BufferedReader brP = new java.io.BufferedReader(fwP);
            String tmpStr;
            int posCut;
            brP.readLine();
            brP.readLine();
            tmpStr = Utilidades.deCifrar(brP.readLine());
            posCut = tmpStr.indexOf("&");
            String e = tmpStr.substring(posCut + 1, tmpStr.length());
            br.readLine();
            tmpStr = Utilidades.deCifrar(br.readLine());
            posCut = tmpStr.indexOf("&");
            CConexion.baseDatos = tmpStr.substring(posCut + 1, tmpStr.length());
             System.out.println(" BASES DE DATOS "+CConexion.baseDatos );
            br.readLine();
            br.readLine();

            tmpStr = Utilidades.deCifrar(br.readLine());
            posCut = tmpStr.indexOf("&");
            CConexion.ipServidor = tmpStr.substring(posCut + 1, tmpStr.length());
             System.out.println(" IP OF SERVIDOR  "+ CConexion.ipServidor );
             br.readLine();
            br.readLine();
           
            tmpStr = Utilidades.deCifrar(br.readLine());
            posCut = tmpStr.indexOf("&");
            CConexion.usuario = tmpStr.substring(posCut + 1, tmpStr.length());
                System.out.println(" USER OF SERVIDOR  "+ CConexion.usuario );
            br.readLine();
            br.readLine();
            tmpStr = Utilidades.deCifrar(br.readLine());
            posCut = tmpStr.indexOf("&");
            CConexion.puerto = tmpStr.substring(posCut + 1, tmpStr.length());
            System.out.println(" PUERTO OF SERVIDOR  "+ CConexion.puerto );
            br.readLine();
            br.readLine();
            tmpStr = Utilidades.deCifrar(br.readLine());
            posCut = tmpStr.indexOf("&");
            CConexion.contrasena = tmpStr.substring(posCut + 1, tmpStr.length());           
            
            br.readLine();
            br.readLine();
            tmpStr = Utilidades.deCifrar(br.readLine());
            System.out.println("valor de la desencriptacion : "+ tmpStr);
            posCut = tmpStr.indexOf("&");
            CConexion.macEquipo = tmpStr.substring(posCut + 1, tmpStr.length());
            String macprueba = tmpStr.substring(posCut + 1, tmpStr.length());
            CConexion.macEquipo = CConexion.macEquipo.trim();
            tmpStr = Utilidades.deCifrar(br.readLine());
             posCut = tmpStr.indexOf("&");
             CConexion.Clave = tmpStr.substring(posCut + 1, tmpStr.length());
            
             CConexion.contadorCupo = consultarA2();
            
             
            frmMenuPrincipal.cupos= CConexion.contadorCupo;
            if (CConexion.contadorCupo > 40 && CConexion.contadorCupo < 50) {
                CMensajes.mensajeAdvertencia("Se le Informa que el numero de cupos es menor a 50 ");
            }
            if (CConexion.contadorCupo > 30 && CConexion.contadorCupo < 40) {
                CMensajes.mensajeAdvertencia("Se le Informa que el numero de cupos es menor a 40 ");
            }
            if (CConexion.contadorCupo > 20 && CConexion.contadorCupo < 30) {
                CMensajes.mensajeAdvertencia("Se le Informa que el numero de cupos es menor a 30 ");
            }
            if (CConexion.contadorCupo > 10 && CConexion.contadorCupo < 20) {
                CMensajes.mensajeAdvertencia("Se le Informa que el numero de cupos es menor a 20 ");
            }
            if (CConexion.contadorCupo > 1 && CConexion.contadorCupo < 10) {
                CMensajes.mensajeAdvertencia("Se le Informa que el numero de cupos es menor a 10 ");
            }
            if (CConexion.contadorCupo < 1) {
                CMensajes.mensajeAdvertencia("DISCULPE, SE HA VENCIDO EL LIMITE DE CUPOS DISPONIBLES ..!");
                return false;
            }
        } catch (IOException ex) {
            int eve = 1;
        }
        return true;
    }

    public static void execStoredProcChangeFechaFinal() {
        try {
           /* if (conectar()) {
                Statement stmt = null;
                String createProcedure = "ALTER TABLE auditoria_sicov CHANGE COLUMN FECHA_REGISTRO_BD FECHA_REGISTRO_BD TIMESTAMP  NULL DEFAULT NULL  AFTER FECHA_EVENTO; ";
                stmt = conn.createStatement();
                stmt.executeUpdate(createProcedure);
                stmt.close();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Integer consultarA2() {
        int cupos = 0;
        String sql = "SELECT cant_a2 FROM zbefore; ";
         try {       
           if (conectar()) {                
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet result = pst.executeQuery();
                while (result.next()) {
                 cupos = result.getInt("cant_a2");
                }               
           } 
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
         System.out.println("cantidad de fupas disponibles: "+ cupos );
         return cupos ;
        }
public void updateA2(Integer cupos) {
        boolean estado = false;
        String sql = "UPDATE  zbefore set cant_a2 = " + cupos + ";";
        try {
            if (conectar()) {
                stm = conn.createStatement();
                stm.executeUpdate(sql);
                estado = true;
            }
        } catch (SQLException ex) {
            CMensajes.mostrarExcepcion(ex);
        } finally {
            cerrarConexion();
        }       
    }
    public static void execStoredProcRestoredFechaFinal() {
        try {
            /*if (conectar()) {
                Statement stmt = null;
                String createProcedure = "ALTER TABLE auditoria_sicov CHANGE COLUMN FECHA_REGISTRO_BD FECHA_REGISTRO_BD TIMESTAMP  DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP AFTER FECHA_EVENTO; ";
                stmt = conn.createStatement();
                stmt.executeUpdate(createProcedure);
                stmt.close();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static boolean conectar() {
        boolean estado = false;
        //String URL = "jdbc:mysql://" + CConexion.ipServidor +  "/" + CConexion.baseDatos;
        String URL = "jdbc:mysql://" + CConexion.ipServidor + ":" + CConexion.puerto + "/" + CConexion.baseDatos;
        System.out.println("Detalle de conexion : " + URL);
        //String URL = "jdbc:mysql://localhost:3306/db_cda";        
        //CMensajes.mensajeAdvertencia(URL);
     //   CMensajes.mensajeAdvertencia(CConexion.usuario+" - "+CConexion.contrasena);
        
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = DriverManager.getConnection(URL, CConexion.usuario, CConexion.contrasena);
          // System.out.println(URL + " - "+ CConexion.ipServidor +" - "+ CConexion.contrasena);
           // conn = DriverManager.getConnection(URL, "root", "50lt3l3c545");
            
            
            estado = true;
        } catch (ClassNotFoundException | SQLException e) {
            CMensajes.mostrarExcepcion(e);
        }
        System.out.println("valor de estado:" + estado);
        return estado;
    }
    
    
    public static Connection conectarBd() {
               // String URL = "jdbc:mysql://" + CConexion.ipServidor + "/" + CConexion.baseDatos;
        boolean estado = false;
       String URL = "jdbc:mysql://localhost:3306/db_cda";
        try {
            Class.forName("com.mysql.jdbc.Connection");
           // conn = DriverManager.getConnection(URL, CConexion.usuario, CConexion.contrasena);
                 conn = DriverManager.getConnection(URL, "root", "50lt3l3c545");
            estado = true;
        } catch (ClassNotFoundException | SQLException e) {
            CMensajes.mostrarExcepcion(e);
        }
          System.out.println("valor de estado 2:" + estado);

        return conn;
    }
    

    /**
     * Metodo para cerrar la conexion con la base de datos.
     */
    private static void cerrarConexion() {
        try {

            conn.close();
        } catch (SQLException ex) {
            CMensajes.mostrarExcepcion(ex);
        }
    }

    /**
     * Metodo para agregar un registro a la base de datos, pueden ser varios
     * campos afectados pero deben ser igual a la cantidad de valores pasados.
     *
     * @param tabla especifica la tabla a la cual se agregara el registro
     * @param campos especifica el campo(s) al cual se les agregara los datos
     * @param valores valor(es) que sera guardado en la tabla y campos ya
     * especificados
     * @return
     */
    public boolean agregar(String tabla, String campos, String valores) { 
        boolean estado = false;
        String sql = "INSERT INTO " + tabla + campos + " VALUES " + valores;
        System.out.println("sql ejecutado: " + sql);    
        if (conectar()) {
            try {
                stm = conn.createStatement();
                stm.executeUpdate(sql);
                estado = true;
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
        }
        return estado;
    }

    /**
     * Metodo para agregar o actualizar una imagen en la base de datos.
     *
     * @param campo especifica la columna donde se guardara la imagen
     * @param condicion espeficica la condicion con la cual se actualizara el
     * campo
     * @param image especifica la imagen que sera guardada en la base de datos
     * @return
     */
    public boolean updateImage(String campo, String condicion, File image) {
        boolean estado = false;
        String sql = "UPDATE fotos SET " + campo + " = ? WHERE " + condicion;
        if (conectar()) {
            try {
                pstm = conn.prepareStatement(sql);
                FileInputStream fis = new FileInputStream(image);
                pstm.setBinaryStream(1, fis, image.length());
                pstm.execute();
                estado = true;
            } catch (SQLException | FileNotFoundException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        }

        return estado;
    }

    /**
     * Metodo para consultar datos y poderlos almacenar en la variable
     * CachedRowSetImpl para su posterior uso.
     *
     * @param sql especifica la cadena de consulta que se hara en la base de
     * datos.
     * @return
     */
    public boolean consultar(String sql) {
        boolean estado = false;

        if (conectar()) {
            try {
                stm = conn.createStatement();
                //System.out.println("consultar: " + sql);
                rs = stm.executeQuery(sql);
                crs = new CachedRowSetImpl();
                if (rs.next()) {
                    rs.beforeFirst();
                    crs.populate(rs);
                    estado = true;
                } else {
                    CMensajes.mensajeAdvertencia("No hay registros!");
                }
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
        }

        return estado;
    }

    /**
     * Metodo para consultar uno o varios campos con una condicion especifica.
     *
     * @param campo campo o campos a consultar.
     * @param tabla tabla que contiene los campos deseados
     * @param condicion condicion con la cual se quiere consultar los campos.
     * @return
     */
    public boolean consultarCampo(String campo, String tabla, String condicion) {
        boolean estado = false;
        String sql = "SELECT " + campo + " FROM " + tabla + " WHERE " + condicion;

        if (conectar()) {
            try {
                stm = conn.createStatement();
                //System.out.println("consultarCampo: " + sql);
                rs = stm.executeQuery(sql);
                crs = new CachedRowSetImpl();
                if (rs.next()) {
                    rs.beforeFirst();
                    crs.populate(rs);
                    estado = true;
                }
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
        }
        return estado;
    }

    /**
     * Metodo para actualizar registros en la base de datos, los campos deben
     * ser la misma cantidad de valores pasados como parametros ya que estos son
     * los que contrendran dichos valores.
     *
     * @param tabla tabla que se desea actualizar
     * @param campos campos que seran actualizados
     * @param valores especifica los valores nuevos para cada campo
     * @param condicion condicion para actualizar los campos.
     * @return
     */
    public boolean actualizarRegistro(String tabla, String[] campos, String[] valores, String condicion) {
        boolean estado = false;
        String sql = "UPDATE " + tabla + " SET ";

        for (int i = 0; i < campos.length; i++) {
            sql += campos[i] + " = '" + valores[i] + "', ";
        }

        sql = sql.substring(0, sql.length() - 2) + " WHERE " + condicion;

        //System.out.println("sql: " + sql);
        if (conectar()) {
            try {
                stm = conn.createStatement();
                stm.executeUpdate(sql);
                estado = true;
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
        }

        return estado;
    }

    /**
     * Metodo para eliminar un registro de la base de datos.
     *
     * @param tabla tabla la cual contiene el registro a eliminar.
     * @param condicion condicion para eliminar el registro.
     * @return
     */
    public boolean eliminarRegistro(String tabla, String condicion) {
        boolean estado = false;
        String sql = "DELETE FROM " + tabla + " WHERE " + condicion;
        System.out.println("sentencia para borrar defectos: " + sql);

        if (conectar()) {
            try {
                stm = conn.createStatement();
                stm.executeUpdate(sql);
                estado = true;
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
        }

        return estado;
    }

    public boolean actualizarRegistro(String tabla, String[] campos, String[] valores, String condicion, int idPrueba) {
        boolean estado = false;
        String sql = "UPDATE " + tabla + " SET ";
        Timestamp fechaPrueba =obtenerFechaPrueba(idPrueba);
        for (int i = 0; i < campos.length; i++) {
            sql += campos[i] + " = '" + valores[i] + "', ";
        }
        sql = sql.concat("Fecha_aborto = ").concat("null");   
        sql = sql.concat(", Fecha_final = '").concat(fechaPrueba.toString().concat("' "));
        sql = sql.substring(0, sql.length()) + " WHERE " + condicion;
        if (conectar()) {
            try {
                stm = conn.createStatement();
                stm.executeUpdate(sql);
                estado = true;
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
        }
        return estado;
    }
    
    public Timestamp obtenerFechaPrueba(int idPrueba) {
        Timestamp fechaPrueba = null;
        try {
            if (conectar()) {
                PreparedStatement pst = conn.prepareStatement("SELECT Fecha_final FROM pruebas  WHERE Id_Pruebas=" + idPrueba);
                ResultSet result = pst.executeQuery();
                while (result.next()) {
                    fechaPrueba = result.getTimestamp("Fecha_final");
                }
            }
        } catch (SQLException ex) {
            int eve = 0;
        }
        return fechaPrueba;
    }
    
    public boolean actualizarRegistroGases(String tabla, String[] campos, String[] valores, String condicion, int idPrueba) {
        boolean estado = false;
        String sql = "UPDATE " + tabla + " SET ";
        for (int i = 0; i < campos.length; i++) {
            sql += campos[i] + " = '" + valores[i] + "', ";
        }
        sql = sql.concat("Fecha_aborto = ").concat("null");
       sql = sql.concat(", observaciones = ").concat("null");
        sql = sql.substring(0, sql.length()) + " WHERE " + condicion;

        if (conectar()) {
            try {
                stm = conn.createStatement();
                stm.executeUpdate(sql);
                estado = true;
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            } finally {
                cerrarConexion();
            }
        }
        return estado;
    }
}
