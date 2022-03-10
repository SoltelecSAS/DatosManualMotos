/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DataBase.CConexion;
import Utilidades.CMensajes;
import Vista.frmLogin;
import Vista.frmMenuPrincipal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel;

/**
 *
 * @author
 */
public class Main {

    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code and Open Window (optional) ">
           /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceMistSilverLookAndFeel());
                } catch (Exception e) { }
                if (CConexion.validacionApp() == false) {
                    return;
                }
                Process p = null;
                try {
                    p = Runtime.getRuntime().exec("wmic bios get serialnumber");
                    BufferedReader inn = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    inn.readLine();
                    inn.readLine();
                    String line = inn.readLine();
                    line = line.replace("G", "");
                    //System.out.println(" la mac encriptamiento = " + CConexion.macEquipo);
                    System.out.println("**   **   *** ");
                   // System.out.println(" la mac del equipo: " + line.trim() + "**");
                    //System.out.println(" VALOR DE LA CLAVE: " + CConexion.Clave);
                    System.out.println("****************************");
                    
                    if (CConexion.Clave.equalsIgnoreCase("MAC")) {
                        p = Runtime.getRuntime().exec("getmac");
                        inn = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        inn.readLine();                        
                        inn.readLine();
                        inn.readLine();
                        line =  inn.readLine();
                        line = line.replace("G", "");
                        int posCut = line.indexOf(" ");
                        String mac = line.substring(0,posCut);
                        String macSinEspacio= CConexion.macEquipo.trim();
                        macSinEspacio = macSinEspacio.replace("-","");
                        mac= mac.replace("-", "");
                        //System.out.println("valor de la mac cuando entro al if:"+ mac);
                       // System.out.println("valor de la mac que tomo del archivo :"+macSinEspacio);
                        if (!macSinEspacio.equalsIgnoreCase(macSinEspacio)) {
                            CMensajes.mensajeError("QUE PENA Equipo No Autorizado");
                            return;
                        }
                        
                    } else {
                        if (!CConexion.macEquipo.equalsIgnoreCase(line.trim())) {
                            CMensajes.mensajeError("QUE PENA Equipo No Autorizado");
                            return;
                        }
                    }
                } catch (IOException ex) { }
                frmLogin login = new frmLogin();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            }
        });
        //</editor-fold>
    }
}
