package Logica.Mantenimiento;

import Logica.Servicio.*;
import Logica.Hotel;
import Logica.Parametros.Pago;
import Logica.Parametros.Proveedor;
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
public class CompraMantenimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroFactura;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @OneToMany
    private List<Articulos_Compra> articulos;
    private int cantidadArticulos;
    private double montoCompra;
    @ManyToOne
    private Usuario usuarioResponsable;
    @OneToOne
    private Proveedor unProveedor;
    @OneToOne
    private Pago unPago;

    public CompraMantenimiento(String numeroFactura, Date fecha, List<Articulos_Compra> articulosRelacionados, int cantidadArticulos, double montoCompra, Proveedor unProveedor) throws Exception {
        this.id = 0;
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.articulos = articulosRelacionados;
        this.cantidadArticulos = cantidadArticulos;
        this.montoCompra = montoCompra;
        this.unProveedor = unProveedor;
        this.usuarioResponsable=ControlAcceso.logueado;
        Hotel.persistencia.insert(this);
    }

    public CompraMantenimiento() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public List<Articulos_Compra> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulos_Compra> articulosRelacionados) {
        this.articulos = articulosRelacionados;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public double getMontoCompra() {
        return montoCompra;
    }

    public void setMontoCompra(double montoCompra) {
        this.montoCompra = montoCompra;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public Proveedor getUnProveedor() {
        return unProveedor;
    }

    public Pago getUnPago() {
        return unPago;
    }

    public void setUnPago(Pago unPago) {
        this.unPago = unPago;
    }

        
    public void setUnProveedor(Proveedor unEstablecimiento) {
        this.unProveedor = unEstablecimiento;
    }

    public CompraMantenimiento agregarUnPago (Pago unPago) {
         this.unPago=unPago;
         return this;
    }
    
      @Override
    public String toString() {
        return this.getNumeroFactura();
    }
    
}
