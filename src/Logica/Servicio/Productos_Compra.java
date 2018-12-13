package Logica.Servicio;

import Logica.Hotel;
import Logica.Parametros.Producto;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Productos_Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Producto unArticulo;
    private double unPrecio;
    private int cantidad;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public Productos_Compra(Producto unArticulo, double unPrecio, int unaCantidad) throws Exception {
        this.unArticulo = unArticulo;
        this.unPrecio = unPrecio;
        this.cantidad = unaCantidad;
        this.fecha = new Date();
        Hotel.persistencia.insert(this);
    }

    public Productos_Compra() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getUnArticulo() {
        return unArticulo;
    }

    public void setUnArticulo(Producto unArticulo) {
        this.unArticulo = unArticulo;
    }

    public double getUnPrecio() {
        return unPrecio;
    }

    public void setUnPrecio(double unPrecio) {
        this.unPrecio = unPrecio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
 
    public String toLista() {
        return String.valueOf(this.getCantidad() + " - " + this.getUnArticulo().getnombre() + " c/u $" + this.getUnPrecio());
    }

    @Override
    public String toString() {
        return this.getUnArticulo().getnombre();
    }

}

