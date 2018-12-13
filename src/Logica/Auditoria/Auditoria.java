package Logica.Auditoria;

import Logica.Hotel;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String operacion;
    private String tabla;
    private String dato;
    private String usuario;
    private String detalle;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String hora;

    public Auditoria() {
    } 
    
    
    public Auditoria(String unaOp, String tabla,String dato,String detalle, String usuario) throws Exception {
        this.id = 0;
        this.operacion = unaOp.toUpperCase();
        this.tabla = tabla.toUpperCase();
        this.dato = dato.toUpperCase();
        this.detalle = detalle.toUpperCase();
        this.fecha = new Date();
        int hora;
        int minutos;
        String horaFinal;
        Calendar unCalendar=new GregorianCalendar();
        hora =unCalendar.get(Calendar.HOUR_OF_DAY);
        minutos =unCalendar.get(Calendar.MINUTE);
        horaFinal=hora+":"+minutos;
        this.hora = horaFinal;
        this.usuario = usuario.toUpperCase();
        Hotel.persistencia.insert(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    public String getDato() {
        return dato;
    }

    public void setDati(String dato) {
        this.dato = dato;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return this.getOperacion();
    }
    
}
