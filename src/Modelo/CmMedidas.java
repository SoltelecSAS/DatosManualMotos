/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CMedidas;

/**
 *
 * @author 
 */
public class CmMedidas {
    
    CMedidas cMedidas;
    
    private int idMedidas;
    private int[] idTipoMedida;
    private float[] valorMedida;
    private int idPrueba;
    private String[] condicion;
    
    //constantes que guardaran la informacion de los nombres de las tablas
    //cada constante tendra el nombre de la variable a la cual va ligada
    //la tabla.
    public static final String TABLA = "medidas";
    public static final String ID_MEDIDAS = "MEASURE";
    public static final String ID_TIPO_MEDIDA = "MEASURETYPE";
    public static final String VALOR_MEDIDA = "valor_medida";
    public static final String ID_PRUEBA = "TEST";
    public static final String CONDICION = "Condicion";

    public int getIdMedidas() {
        return idMedidas;
    }

    public void setIdMedidas(int idMedidas) {
        this.idMedidas = idMedidas;
    }

    public int[] getIdTipoMedida() {
        return idTipoMedida;
    }

    public void setIdTipoMedida(int[] idTipoMedida) {
        this.idTipoMedida = idTipoMedida;
    }

    public float[] getValorMedida() {
        return valorMedida;
    }

    public void setValorMedida(float[] valorMedida) {
        this.valorMedida = valorMedida;
    }

    public int getPrueba() {
        return idPrueba;
    }

    public void setPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String[] getCondicion() {
        return condicion;
    }

    public void setCondicion(String[] condicion) {
        this.condicion = condicion;
    }

    public CmMedidas(int[] idTipoMedida, float[] valorMedida, int idPrueba) {
        this.idTipoMedida = idTipoMedida;
        this.valorMedida = valorMedida;
        this.idPrueba = idPrueba;
        cMedidas = new CMedidas();
    }

    public CmMedidas() {
        cMedidas = new CMedidas();
    }
    
    /*
     * Metodo para guardar las medidas dinamicamente 
     */
    public boolean guardarMedidas() {
        boolean estado = true;
        if (!cMedidas.guardarMedidas(this.idTipoMedida, this.valorMedida, this.idPrueba)) {
            estado = false;
        }        
        return estado;
    }
    public boolean borrarMedidas() {
        boolean estado = true;
        if (!cMedidas.borrarMedidas(this.idPrueba)) {
            estado = false;
        }        
        return estado;
    }
    public boolean borrarDefpruebas() {
        boolean estado = true;
        if (!cMedidas.borrarDefPrueba(this.idPrueba)) {
            estado = false;
        }        
        return estado;
    } 
    
}
