/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CUsuario;
import Utilidades.CMensajes;
import java.sql.Date;

/**
 *
 * @author
 */
public class CmUsuario {
    private int idUsuario;
    private String nickUsuario;
    private String nombreUsuario;
    private String administrador;
    private String contraseña;
    private Date fechaValidacion;
    
    private CUsuario cUsuario;
    
    //constantes que guardaran la informacion de los nombres de las tablas
    //cada constante tendra el nombre de la variable a la cual va ligada
    //la tabla.
    public static final String TABLA = "usuarios";
    public static final String ID_USUARIO = "GEUSER";
    public static final String NICK_USUARIO = "Nick_Usuario";
    public static final String NOMBRE_USUARIO = "Nombre_usuario";
    public static final String ADMINISTRADOR = "es_administrador";
    public static final String CONTRASEÑA = "Contrasenia";
    public static final String FECHA_VALIDACION = "Fecha_validacion";

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public CmUsuario() {
        this.cUsuario = new CUsuario();
    }

    public CmUsuario(int idUsuario, String nickUsuario, String nombreUsuario, String administrador, String contraseña, Date fechaValidacion) {
        this.idUsuario = idUsuario;
        this.nickUsuario = nickUsuario;
        this.nombreUsuario = nombreUsuario;
        this.administrador = administrador;
        this.contraseña = contraseña;
        this.fechaValidacion = fechaValidacion;
        this.cUsuario = new CUsuario();
    }
    
    public boolean validarSesion(String nickUsuario, String contraseña) {
        boolean estado = true;
        try{
        CmUsuario usuario = cUsuario.validarSesion(nickUsuario, contraseña);
        if (usuario != null) {
            this.setIdUsuario(usuario.getIdUsuario());
        } else {
            estado = false;
        }
        } catch(Exception ex) {
            CMensajes.mostrarExcepcion(ex);
        }
        
        return estado;
    }
    
}