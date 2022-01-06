/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DataBase.CConexion;
import Modelo.CmUsuario;
import Utilidades.CMensajes;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

/**
 *
 * @author Soltelec Ltda
 */
public class CUsuario {
    
    CConexion db;

    public CUsuario() {
        db = new CConexion();
    }
    
    public CmUsuario validarSesion(String nick, String contraseña) {
        CmUsuario usuario = null;
        String condicion = CmUsuario.NICK_USUARIO + " = '" + nick + "'";
        //verifica si exite el usuario en la base de datos, si no es asi lanza un mensaje de error
        if (!db.consultarCampo(CmUsuario.NICK_USUARIO, CmUsuario.TABLA, condicion)) {
            CMensajes.mensajeError("No existe el usuario " + nick);
            return usuario;
        }
        
        condicion = CmUsuario.NICK_USUARIO + " = '" + nick + "' and " + CmUsuario.CONTRASEÑA + " = '" + contraseña + "' and  es_administrador ='A'";
        //verifica si la contraseña corresponde al usuario, si no es asi lanza un mensaje de error
        if (db.consultarCampo("*", CmUsuario.TABLA, condicion)) {
            CachedRowSetImpl crs = db.getCrs();
            try {
                if(crs.next()) {
                    usuario = new CmUsuario();
                    usuario.setIdUsuario(crs.getInt(CmUsuario.ID_USUARIO));
                }
            } catch (SQLException ex) {
                CMensajes.mostrarExcepcion(ex);
            }
        } else {
            CMensajes.mensajeError("La contraseña no coincide con el usuario");
        }
        
        return usuario;
    }
    
}
