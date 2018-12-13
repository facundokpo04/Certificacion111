package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Concepto implements Comparable<Concepto>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private String detalle;
    private String descripcion;

    public Concepto() {
    }

    public Concepto(String tipo, String detalle, String descripcion) throws Exception {
        this.id = 0;
        this.tipo = tipo.toUpperCase();
        this.detalle = detalle.toUpperCase();
        this.descripcion= descripcion.toUpperCase();
        Hotel.persistencia.insert(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    @Override
    public String toString() {
        return detalle;
    }


    @Override
    public int compareTo(Concepto t) {
        if (this.tipo.compareToIgnoreCase(t.getTipo()) == 0) {
            return this.detalle.compareTo(getDetalle());
        } else {
            return this.tipo.compareToIgnoreCase(t.getTipo());
        }
    }

}
