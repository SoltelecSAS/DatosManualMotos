/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.CVehiculos;
import java.sql.Date;

/**
 *
 * @author Soltelec Ltda
 */
public class CmVehiculos {
    
    CVehiculos cVehiculos;
    
    private int idVehiculo;
    private String numeroPlaca;
    private int idLineaVehiculo;
    private int idMarcaVehiculo;
    private int modelo;
    private int cilindraje;
    private int idServicios;
    private int idClaseVehiculo;
    private String numeroLicencia;
    private int numeroEjes;
    private int idTipoVehiculo;
    private Date fechaCreacion;
    private int idUsuario;
    private Long idPropietario;
    private int numeroExostos;
    private int diametro;
    private int idTipoCombustible;
    private int tiemposMotor;
    private int velocidad;
    private int idColor;
    private String numeroSOAT;
    private int idAseguradora;
    private Date fechaSOAT;
    private Date fechaExpSOAT;
    private int idLlantas;
    private String nacionalidad;
    private int spService;
    private String numeroMotor;
    private String vin;
    private Date fechaRegistro;
    private int idPais;
    private int kilometraje;
    private int numeroSillas;
    private String vidriosPolarizados;
    private String blindaje;
    private String numeroChasis;

    //constantes que guardaran la informacion de los nombres de las tablas
    //cada constante tendra el nombre de la variable a la cual va ligada
    //la tabla.
    public static final String TABLA = "vehiculos";
    public static final String ID_VEHICULO = "CAR";
    public static final String NUMERO_PLACA = "CARPLATE";
    public static final String ID_LINEA_VEHICULO = "CARLINE";
    public static final String ID_MARCA_VEHICULO = "CARMARK";
    public static final String MODELO = "MODELO";
    public static final String CILINDRAJE = "CINLINDRAJE";
    public static final String ID_SERVICIOS = "SERVICE";
    public static final String ID_CLASE_VEHICULO = "CLASS";
    public static final String NUMERO_LICENCIA = "NUMERO_LICENCIA";
    public static final String NUMERO_EJES = "NUMERO_EJES";
    public static final String ID_TIPO_VEHICULO = "CARTYPE";
    public static final String FECHA_CREACION = "INDATE";
    public static final String ID_USUARIO = "GEUSER";
    public static final String ID_PROPIETARIO = "CAROWNER";
    public static final String NUMERO_EXOSTOS = "Numero_exostos";
    public static final String DIAMETRO = "Diametro";
    public static final String ID_TIPO_COMBUSTIBLE = "FUELTYPE";
    public static final String TIEMPOS_MOTOR = "Tiempos_motor";
    public static final String VELOCIDAD = "Velocidad";
    public static final String ID_COLOR = "Color";
    public static final String NUMERO_SOAT = "Numero_SOAT";
    public static final String ID_ASEGURADORA = "INSURING";
    public static final String FECHA_SOAT = "Fecha_soat";
    public static final String FECHA_EXPIRACION_SOAT = "Fecha_exp_soat";
    public static final String ID_LLANTAS = "WHEEL";
    public static final String NACIONALIDAD = "Nacionalidad";
    public static final String SP_SERVICE = "SPSERVICE";
    public static final String NUMERO_MOTOR = "Numero_motor";
    public static final String VIN = "VIN";
    public static final String FECHA_REGISTRO = "Fecha_registro";
    public static final String ID_PAIS = "pais";
    public static final String KILOMETRAJE = "kilometraje";
    public static final String NUMERO_SILLAS = "numero_sillas";
    public static final String VIDRIOS_POLARIZADOS = "vidrios_polarizados";
    public static final String BLINDAJE = "blindaje";
    public static final String NUMERO_CHASIS = "numero_chasis";
    
    
    
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public int getIdLineaVehiculo() {
        return idLineaVehiculo;
    }

    public void setIdLineaVehiculo(int idLineaVehiculo) {
        this.idLineaVehiculo = idLineaVehiculo;
    }

    public int getIdMarcaVehiculo() {
        return idMarcaVehiculo;
    }

    public void setIdMarcaVehiculo(int idMarcaVehiculo) {
        this.idMarcaVehiculo = idMarcaVehiculo;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public int getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(int idServicios) {
        this.idServicios = idServicios;
    }

    public int getIdClaseVehiculo() {
        return idClaseVehiculo;
    }

    public void setIdClaseVehiculo(int idClaseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public int getNumeroEjes() {
        return numeroEjes;
    }

    public void setNumeroEjes(int numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Long idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getNumeroExostos() {
        return numeroExostos;
    }

    public void setNumeroExostos(int numeroExostos) {
        this.numeroExostos = numeroExostos;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public int getIdTipoCombustible() {
        return idTipoCombustible;
    }

    public void setIdTipoCombustible(int idTipoCombustible) {
        this.idTipoCombustible = idTipoCombustible;
    }

    public int getTiemposMotor() {
        return tiemposMotor;
    }

    public void setTiemposMotor(int tiemposMotor) {
        this.tiemposMotor = tiemposMotor;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public String getNumeroSOAT() {
        return numeroSOAT;
    }

    public void setNumeroSOAT(String numeroSOAT) {
        this.numeroSOAT = numeroSOAT;
    }

    public int getIdAseguradora() {
        return idAseguradora;
    }

    public void setIdAseguradora(int idAseguradora) {
        this.idAseguradora = idAseguradora;
    }

    public Date getFechaSOAT() {
        return fechaSOAT;
    }

    public void setFechaSOAT(Date fechaSOAT) {
        this.fechaSOAT = fechaSOAT;
    }

    public Date getFechaExpSOAT() {
        return fechaExpSOAT;
    }

    public void setFechaExpSOAT(Date fechaExpSOAT) {
        this.fechaExpSOAT = fechaExpSOAT;
    }

    public int getIdLlantas() {
        return idLlantas;
    }

    public void setIdLlantas(int idLlantas) {
        this.idLlantas = idLlantas;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getSpService() {
        return spService;
    }

    public void setSpService(int spService) {
        this.spService = spService;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public int getNumeroSillas() {
        return numeroSillas;
    }

    public void setNumeroSillas(int numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

    public String getVidriosPolarizados() {
        return vidriosPolarizados;
    }

    public void setVidriosPolarizados(String vidriosPolarizados) {
        this.vidriosPolarizados = vidriosPolarizados;
    }

    public String getBlindaje() {
        return blindaje;
    }

    public void setBlindaje(String blindaje) {
        this.blindaje = blindaje;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }
    
    public CmVehiculos() {
        cVehiculos = new CVehiculos();
    }
    
    public boolean idPorPlaca(String placa) {
        boolean estado = true;
        CmVehiculos vehiculos = cVehiculos.idPorPlaca(placa);
        
        if (vehiculos != null) {
            this.setIdVehiculo(vehiculos.getIdVehiculo());
        } else {
            estado = false;
        }
        
        return estado;
    }
    
    public boolean cargarVehiculo(int idVehiculo) {
        boolean estado = true;
        
        CmVehiculos vehiculos = cVehiculos.cargarVehiculo(idVehiculo);
        
        if (vehiculos != null) {
            vehiculos.setIdVehiculo(vehiculos.getIdVehiculo());
                  this.setNumeroPlaca(vehiculos.getNumeroPlaca());
                  this.setIdLineaVehiculo(vehiculos.getIdLineaVehiculo());
                  this.setIdMarcaVehiculo(vehiculos.getIdMarcaVehiculo());
                  this.setModelo(vehiculos.getModelo());
                  this.setCilindraje(vehiculos.getCilindraje());
                  this.setIdServicios(getIdServicios());
                  this.setIdClaseVehiculo(getIdClaseVehiculo());
                  this.setNumeroLicencia(vehiculos.getNumeroLicencia());
                  this.setNumeroEjes(vehiculos.getNumeroEjes());
                  this.setIdTipoVehiculo(vehiculos.getIdTipoVehiculo());
                  this.setFechaCreacion(vehiculos.getFechaCreacion());
                  this.setIdUsuario(vehiculos.getIdUsuario());
                  this.setIdPropietario(vehiculos.getIdPropietario());
                  this.setNumeroExostos(vehiculos.getNumeroExostos());
                  this.setDiametro(vehiculos.getDiametro());
                  this.setIdTipoCombustible(vehiculos.getIdTipoCombustible());
                  this.setTiemposMotor(vehiculos.getTiemposMotor());
                  this.setVelocidad(vehiculos.getVelocidad());
                  this.setIdColor(vehiculos.getIdColor());
                  this.setNumeroSOAT(vehiculos.getNumeroSOAT());
                  this.setIdAseguradora(vehiculos.getIdAseguradora());
                  this.setFechaSOAT(vehiculos.getFechaSOAT());
                  this.setFechaExpSOAT(vehiculos.getFechaExpSOAT());
                  this.setIdLlantas(vehiculos.getIdLlantas());
                  this.setNacionalidad(vehiculos.getNacionalidad());
                  this.setSpService(vehiculos.getSpService());
                  this.setNumeroMotor(vehiculos.getNumeroMotor());
                  this.setVin(vehiculos.getVin());
                  this.setFechaRegistro(vehiculos.getFechaRegistro());
                  this.setIdPais(vehiculos.getIdPais());
                  this.setKilometraje(vehiculos.getKilometraje());
                  this.setNumeroSillas(vehiculos.getNumeroSillas());
                  this.setVidriosPolarizados(vehiculos.getVidriosPolarizados());
                  this.setBlindaje(vehiculos.getBlindaje());
                  this.setNumeroChasis(vehiculos.getNumeroChasis());
        } else {
            return false;
        }
        
        return estado;
    }
}
