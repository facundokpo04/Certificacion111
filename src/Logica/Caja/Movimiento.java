package Logica.Caja;

import Logica.Hotel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_dia;
    private String hora_mov;
    private Double entrada;
    private Double salida;
    private static final String EGRESO = "EGRESO";
    private static final String INGRESO = "INGRESO";
    private String tipo;
    private String concepto;
    private String descripcion;

    public Movimiento() {
    }

    public Movimiento(Date fecha_dia, String hora_mov, Double entrada, Double salida, String tipo, String concepto) throws Exception {
        this.id = 0;
        this.fecha_dia = fecha_dia;
        this.hora_mov = hora_mov;
        this.entrada = entrada;
        this.salida = salida;
        this.tipo = tipo.toUpperCase();
        this.concepto = concepto.toUpperCase();
        if(entrada!= 0.0){
        this.descripcion=entrada.toString();
        }
        else{
           this.descripcion= salida.toString();
        }

        Hotel.persistencia.insert(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_dia() {
        return fecha_dia;
    }

    public void setFecha_dia(Date fecha_dia) {
        this.fecha_dia = fecha_dia;
    }

    public String getHora_mov() {
        return hora_mov;
    }

    public void setHora_mov(String hora_mov) {
        this.hora_mov = hora_mov;
    }

    public Double getEntrada() {
        return entrada;
    }

    public void setEntrada(Double entrada) {
        this.entrada = entrada;
    }

    public Double getSalida() {
        return salida;
    }

    public void setSalida(Double salida) {
        this.salida = salida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getImporte(){
     if(this.getEntrada().equals(0.0)){
         return this.getSalida();
    }
     else{
         return this.getEntrada();
     }
    }
    

    @Override
    public String toString() {
        return this.getTipo();
    }

    public Object[] toVector() {
        Object fila[] = {this, this.getFecha_dia(), this.getHora_mov(), this.getConcepto(), this.getEntrada(), this.getSalida()};
        return fila;
    }
}
