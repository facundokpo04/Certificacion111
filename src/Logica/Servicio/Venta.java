package Logica.Servicio;

import Logica.Hotel;
import Logica.Parametros.Pago;
import Logica.Seguridad.Usuario;
import Presentacion.Seguridad.ControlAcceso;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @OneToMany
    private List<Productos_Compra> articulos;
    private int cantidadArticulos;
    private double montoVenta;
    @ManyToOne
    private Usuario usuarioResponsable;
    @OneToOne
    private Pago unPago;

    public Venta(Date fecha, List<Productos_Compra> articulosRelacionados, int cantidadArticulos, double montoVenta) throws Exception {
        this.id = 0;
        this.fecha = fecha;
        this.articulos = articulosRelacionados;
        this.cantidadArticulos = cantidadArticulos;
        this.montoVenta = montoVenta;
        this.usuarioResponsable=ControlAcceso.logueado;
        Hotel.persistencia.insert(this);
    }

    public Venta() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public List<Productos_Compra> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Productos_Compra> articulosRelacionados) {
        this.articulos = articulosRelacionados;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoCompra) {
        this.montoVenta = montoCompra;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }


  
    public Pago getUnPago() {
        return unPago;
    }

    public void setUnPago(Pago unPago) {
        this.unPago = unPago;
    }

    public Venta agregarUnPago (Pago unPago) {
         this.unPago=unPago;
         return this;
    }
    
    @Override
    public String toString() {
        return this.getId().toString();
    }
}

