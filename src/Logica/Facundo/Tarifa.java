package Logica.Facundo;

import Logica.Hotel;
import Logica.Parametros.TipoHabitacion;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Tarifa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codTariha;           
    private String nombre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaHasta;
    private Double precio;
    private Double  precioPerExt;
    @ManyToOne
    private TipoHabitacion mitipoHabitacion;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2BC0D6AF-8A40-1B1A-8137-A7A876AF271B]
    // </editor-fold> 
    public Tarifa () {
    }

    public Tarifa(String nombre, Date fechaDesde, Date fechaHasta, double precio,double precioPere,TipoHabitacion unTipoHabitacion) throws Exception {
        this.nombre = nombre;
        this.mitipoHabitacion=unTipoHabitacion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.precio = precio;
        this.precioPerExt=precioPere;
        Hotel.persistencia.insert(this);
        
    }

    public void setPrecioPerExt(Double precioPerExt) {
        this.precioPerExt = precioPerExt;
    }

    public TipoHabitacion getMitipoHabitacion() {
        return mitipoHabitacion;
    }

    public void setMitipoHabitacion(TipoHabitacion mitipoHabitacion) {
        this.mitipoHabitacion = mitipoHabitacion;
    }

    public int getCodTariha() {
        return codTariha;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecioPerExt() {
        return precioPerExt;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.478152F0-F289-6985-AC43-F37288790D5B]
    // </editor-fold> 
    public Date getFechaDesde () {
        return fechaDesde;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.68725F7A-EA88-0B93-E893-71D1C200990D]
    // </editor-fold> 
    public void setFechaDesde (Date val) {
        this.fechaDesde = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3E24C20B-EB90-4C90-9106-8388ADE56D4C]
    // </editor-fold> 
    public Date getFechaHasta () {
        return fechaHasta;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C621D752-46B7-A4E7-2B70-B22090EF6578]
    // </editor-fold> 
    public void setFechaHasta (Date val) {
        this.fechaHasta = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A937CF5A-062F-DA2C-E316-0C71D0FCEAF6]
    // </editor-fold> 
    public Double getPrecio () {
        return precio;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5DADB8CE-255D-051A-9319-03AE3F9FA492]
    // </editor-fold> 
    public void setPrecio (Double val) {
        this.precio = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FC8A0A93-BD0A-38EA-EC54-918759351BD3]
    // </editor-fold> 
    public Double getPrecio (TipoHabitacion unTipoHabitacion, TipoHabitacion cantPer) {
        return null;
    }

    @Override
    public String toString() {
       return this.nombre;
    }

    public Double costoxdia(int cantPers,TipoHabitacion unTipHa)
    {
       Double costoR;
        costoR=this.getPrecio();
       
        while(cantPers>unTipHa.getCapacidadEstandar())
        {
            costoR+=this.getPrecioPerExt();
            cantPers--;
        
        
        }
        
        return costoR;
    }
    
    
    public Boolean isTarifa(String Nombre){
        
       return this.getNombre().equals(Nombre);
    } 
      public Boolean isTarifa(int codTar){
        
       return this.getCodTariha()==codTar;
    } 
       public Boolean isTipoHab(TipoHabitacion unTipoHabitacion){
        
       return this.getMitipoHabitacion().equals(unTipoHabitacion);
    } 
}


