package Logica.Servicio;

import Logica.Hotel;
import Logica.Parametros.Pago;
import Logica.Parametros.Producto;
import Logica.Parametros.Servicio;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Cargo implements Observer{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer numCargo;
    private int cantidad;
    private Double precioU;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private Double costoTotal;
    @OneToOne
    private Producto miProducto;
    @OneToOne
    private Servicio miServicio;
    private Pago unPago;
    private String unEstadoCargo;
    private String descripcion;

    public Cargo() {
    }

    public Cargo (Producto unProducto ,int cant,Double unPreciu,Date fecha) throws Exception {
        this.fecha=fecha;
        this.miProducto = unProducto;
        this.cantidad = cant;
        this.precioU = unPreciu;
        this.unEstadoCargo = "NO PAGADO";
        this.descripcion=unProducto.getnombre();
        this.calcularCosto();
        Hotel.persistencia.insert(this);
    }
    
   public Cargo (Servicio unServicio ,int cant,Double unPreciu,Date fecha) throws Exception {
       this.fecha=fecha;
       this.miServicio=unServicio;
       this.cantidad=cant;
       this.precioU = unPreciu;
       this.unEstadoCargo = "NO PAGADO";
         this.descripcion=unServicio.getnombre();
       this.calcularCosto();
       Hotel.persistencia.insert(this);
        
       }

    public Cargo(int cantidad, Double precioU, Date fecha, Double costoTotal, String descripcion) throws Exception {
        this.cantidad = cantidad;
        this.precioU = precioU;
        this.fecha = fecha;
        this.costoTotal = costoTotal;
        this.unEstadoCargo = "PAGADO";
        this.descripcion = descripcion;
         Hotel.persistencia.insert(this);
    }
   

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecioU() {
        return precioU;
    }

    public void setPrecioU(Double precioU) {
        this.precioU = precioU;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Producto getUnProducto() {
        return miProducto;
    }

    public Servicio getUnaServicio() {
        return miServicio;
    }

    public Double getCostoTotal () {
        return costoTotal;
    }

    public void setCostoTotal (Double val) {
        this.costoTotal = val;
    }

    public Date getFecha () {
        return fecha;
    }

    public void setFecha (Date val) {
        this.fecha = val;
    }

    public Integer getNumCargo () {
        return numCargo;
    }

    
    public void setNumCargo (int val) {
        this.numCargo = val;
    }
 
    public String getUnEstadoCargo () {
        return unEstadoCargo;
    }

    public void setUnEstadoCargo (String val) {
        this.unEstadoCargo = val;
    }

    public Double calcularCosto(){
        Double costo=0.0;
   
        costo=this.precioU*this.cantidad;
        this.setCostoTotal(this.precioU*this.cantidad);
    
    
    return costo;
    }

    public Pago getUnPago() {
        return unPago;
    }

    public void setUnPago(Pago unPago) {
        this.unPago = unPago;
    }
    
    
    
public Boolean isProduto(){

return (this.miProducto!=null);

}
public Boolean isServicio(){

return (this.miServicio!=null);

}
    @Override
    public String toString() {
        return this.getNumCargo().toString();
    }

    @Override
    public void update(Observable o, Object arg) {
   }
    
}