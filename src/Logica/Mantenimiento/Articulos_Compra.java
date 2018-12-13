package Logica.Mantenimiento;

import Logica.Parametros.Articulo;
import Logica.Hotel;
import Logica.Parametros.UtilizadoPara;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Articulos_Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Articulo unArticulo;
    private double unPrecio;
    private int cantidad;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @OneToOne
    private UtilizadoPara unaUtilidad;
    
    public Articulos_Compra(Articulo unArticulo, double unPrecio, int unaCantidad, UtilizadoPara unaUtilidad) throws Exception {
        this.unArticulo = unArticulo;
        this.unPrecio = unPrecio;
        this.cantidad = unaCantidad;
        this.fecha = new Date();
        this.unaUtilidad=unaUtilidad;
        Hotel.persistencia.insert(this);
    }

    public Articulos_Compra() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Articulo getUnArticulo() {
        return unArticulo;
    }

    public void setUnArticulo(Articulo unArticulo) {
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

    public UtilizadoPara getUnaUtilidad() {
        return unaUtilidad;
    }

    public void setUnaUtilidad(UtilizadoPara unaUtilidad) {
        this.unaUtilidad = unaUtilidad;
    }

        
    public String toLista() {
        return String.valueOf(this.getCantidad() + " - " + this.getUnArticulo().getnombre() + " c/u $" + this.getUnPrecio());
    }

    @Override
    public String toString() {
        return this.getUnArticulo().getnombre();
    }

}

