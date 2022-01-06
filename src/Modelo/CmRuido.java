/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CPruebas;
import Utilidades.CMensajes;
import java.util.List;

/**
 *
 * @author 
 */
public class CmRuido {
    
    private String aprobada = "Y";
    private int[] tiposMedida;
    private float[] valorMedidas;
    
    private float[] exosto;

    public CmRuido() {
    } 

    public void setExosto(float[] exosto) {
        this.exosto = exosto;
    }
    
    private void llenarValorMedidas() {
        valorMedidas = new float[3];
        
        System.arraycopy(exosto, 0, valorMedidas, 0, 3);
    }
    
    private void llenarTiposMedidas() {
        tiposMedida = new int[3];
        
        tiposMedida[0] = 7003;
        tiposMedida[1] = 7004;
        tiposMedida[2] = 7005;
    }
    
    public boolean guardarDatos(int idPrueba) {
        boolean estado = true;
        
        llenarTiposMedidas();
        llenarValorMedidas();
        
        CmMedidas medidas = new CmMedidas(tiposMedida, valorMedidas, idPrueba);
        
        if (medidas.guardarMedidas()) {
            CPruebas cPruebas = new CPruebas();
            if (!cPruebas.finalizarPrueba(idPrueba, aprobada)) {
                return false;
            }
            
            CMensajes.mensajeCorrecto("Se Agrego Correctamente!");
        } else {
            estado = false;
        }
        
        return estado;
    }
}
