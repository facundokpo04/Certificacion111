package Logica.Caja;

import Logica.Parametros.Concepto;
import Logica.Hotel;
import Logica.Seguridad.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Diario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApertura;
    private Double saldoInicial;
    private Double saldoFinal;
    private String horaApertura;
    private String horaCierre;
    private boolean ultimo;
    private boolean cerrado;
    @OneToOne
    private Usuario unUsuario;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCierre;
   
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,Movimiento> movimientos;
    

    public Diario() {
    }

    public Diario(Double saldoInicial, Double saldoFinal, String horaApertura, Date fechaApertura, Usuario unUsuario) throws Exception {
        this.id = 0;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.horaApertura = horaApertura;
        this.fechaApertura = fechaApertura;
        this.movimientos = new HashMap();
        this.unUsuario = unUsuario;
        this.ultimo=true;
        this.cerrado = false;
        Hotel.persistencia.insert(this);
    }

    public boolean isCerrado() {
        return cerrado;
     }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    
     public boolean getUltimo() {
        return ultimo;
     }

    public void setUltimo(boolean ultimo) {
        this.ultimo = ultimo;
    }
    
    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaAperturao(Date fechaDiario) {
        this.fechaApertura = fechaDiario;
    }
    
     public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }


    public Map getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Map movimientos) {
        this.movimientos = movimientos;
    }

    public Usuario getUnUsuario() {
        return unUsuario;
    }

    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public void altaMovimiento(Date fecha_dia, String hora_mov, Double importe, String tipo, Concepto concepto) throws Exception {
        if ("INGRESO".equals(tipo.toUpperCase())) {
                Movimiento unIngreso = new Movimiento(fecha_dia, hora_mov, importe, 0.00, tipo, concepto.getDetalle());
                this.movimientos.put(unIngreso.getId(),unIngreso);
        } else {
            Movimiento unEgreso = new Movimiento(fecha_dia, hora_mov, 0.00, importe, tipo, concepto.getDetalle());
            this.movimientos.put(unEgreso.getId(),unEgreso);
        }
        Hotel.persistencia.update(this);
    }
    
    @Override
    public String toString() {
        return id.toString();
    }
}
