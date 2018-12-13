package Logica.Parametros;

import Logica.Hotel;
import Presentacion.Facundo.UtilidadesGui;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Pago implements Serializable {

   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer numPago; 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPago;
    private String hora;
    private Double importe;
    @OneToOne
    private FormaPago unModoPago;
    
     private String entidadBancaria;
    private String nombreTitualar;
    private String tipoCuenta;
    private String nroCuenta;
    private String sucursal;
    private String cbu;
    private String cuit;
    private String nroDoc;
    private String numeroReferencia;
    
    
    
    public Pago () {
        
    }
    
    public Pago(Double importe, FormaPago modoPago, String nroRef, String entidadBancaria, String nombreTitular, String tipoCuenta,String nroCuenta,String sucursal,String cbu, String cuit,String nroDoc) throws Exception {
        this.numeroReferencia = nroRef;
        this.entidadBancaria = entidadBancaria.toUpperCase();
        this.nombreTitualar=nombreTitular.toUpperCase();
        this.tipoCuenta=tipoCuenta.toUpperCase();
        this.nroCuenta=nroCuenta;
        this.sucursal=sucursal.toUpperCase();
        this.cbu=cbu;
        this.cuit=cuit;
        this.nroDoc=nroDoc;
        this.unModoPago=modoPago;
        this.importe=importe;
         String hoy= UtilidadesGui.convertirString(new Date());
         Date hoy1 = UtilidadesGui.convertirA(hoy);
        this.fechaPago=hoy1;
        
          int hora;
        int minutos;
        String horaAc;
        Calendar unCalendar=new GregorianCalendar();
          hora =unCalendar.get(Calendar.HOUR_OF_DAY);
        minutos =unCalendar.get(Calendar.MINUTE);
       
        horaAc=hora+":"+minutos;
        this.hora=horaAc;
        Hotel.persistencia.insert(this);
    }
  
    public Pago (Double importe, ModoPago modoPago) throws Exception {
//        this();
//        this.unModoPago=modoPago;
//        this.importe=importe;
//        Hotel.persistencia.insert(this);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3F7EB02C-1962-7014-1271-7FECE07FD85B]
    // </editor-fold> 
    public Date getFechaPago () {
        return fechaPago;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8EF59AF8-CD37-5E98-B7B2-A2D4AB519D32]
    // </editor-fold> 
    public void setFechaPago (Date val) {
        this.fechaPago = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.02B8987B-FF5C-E32D-401D-82723C186541]
    // </editor-fold> 
    public String getHora () {
        return hora;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9D8A0B62-37C0-FB48-8933-F6AAD5B71430]
    // </editor-fold> 
    public void setHora (String val) {
        this.hora = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.024CFCBE-FE41-463D-84FB-9B54FFC46BBF]
    // </editor-fold> 
    public Double getImporte () {
        return importe;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.97F2EA27-6243-27B9-AC71-B94884D9F0CD]
    // </editor-fold> 
    public void setImporte (Double val) {
        this.importe = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0CE71D01-3D11-49AD-48AA-2E13B2456141]
    // </editor-fold> 
    public int getNumPago () {
        return numPago;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.FD7BD7B8-89D8-2C86-FADB-8DBC1E7ED800]
    // </editor-fold> 
    public void setNumPago (Integer val) {
        this.numPago = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8911FA38-56B3-6949-A4BA-5A07CDF88CC1]
    // </editor-fold> 

    @Override
    public String toString() {
        return this.numPago.toString();
    }

    public FormaPago getUnModoPago() {
        return unModoPago;
    }
  

}

