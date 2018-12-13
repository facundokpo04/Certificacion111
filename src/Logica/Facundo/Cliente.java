package Logica.Facundo;

import Logica.Domicilios.Direccion;
import Logica.Parametros.TipoDocumento;
import Logica.Parametros.TipoCliente;
import Logica.Hotel;
import Logica.Parametros.Nacionalidad;
import Presentacion.Facundo.UtilidadesGui;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Cliente extends Persona {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F849DFA4-D56A-001A-A704-B0DAC6E2B81A]
    // </editor-fold> 
   
    @ManyToOne
    private TipoCliente unTipoCli;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    @OneToOne
    private Nacionalidad unaNac;
    private String direccion; 
    private String telefono;
    private String otroTelefono;
    private String email;
    @OneToOne
    private Direccion domicilio;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,Reserva> reservas;

    public Cliente() {
    }

    
     public Cliente (String documento,TipoDocumento unTipoDoc, String apellido,String nombre) throws Exception {
       super(documento, unTipoDoc, apellido, nombre);
       this.reservas=new HashMap();   
    }
     
    public Cliente(TipoCliente unTipoCli, Date fechaNacimiento, Nacionalidad unaNac, String direccion, String telefono, String otroTelefono, String email, String documento, TipoDocumento unTipoDoc, String apellido, String nombre) throws Exception {
        
        this(documento, unTipoDoc, apellido, nombre);
        
        this.unTipoCli = unTipoCli;
        this.fechaNacimiento = fechaNacimiento;
        this.unaNac = unaNac;
        this.direccion = direccion;
        this.telefono = telefono;
        this.otroTelefono = otroTelefono;
        this.email = email;
        unTipoDoc.AgregarCliente(this);
        unTipoCli.AgregarCliente(this);
        Hotel.persistencia.insert(this);
    }

    public Direccion getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Direccion domicilio) {
        this.domicilio = domicilio;
    }
  
   
   
    public String getOtroTelefono() {
        return otroTelefono;
    }

    public void setOtroTelefono(String otroTelefono) {
        this.otroTelefono = otroTelefono;
    }

    public String getDireccion () {
        return direccion;
    }

    public void setDireccion (String val) {
        this.direccion = val;
    }

 
    public String getEmail () {
        return email;
    }

 
    public void setEmail (String val) {
        this.email = val;
    }

  
    public Date getFechaNacimiento () {
        return fechaNacimiento;
    }
   public String getFechaNacimientoString () {
        return UtilidadesGui.convertirString(fechaNacimiento);
    }

    public void setFechaNacimiento (Date val) {
        this.fechaNacimiento = val;
    }

    public String getTelefono () {
        return telefono;
    }
   
    public void setTelefono (String val) {
        this.telefono = val;
    }

    public TipoCliente getUnTipoCli () {
        return unTipoCli;
    }
  
    public void setUnTipoCli (TipoCliente val) {
        this.unTipoCli = val;
        unTipoCli.AgregarCliente(this);
    }

    public Nacionalidad getUnaNac () {
        return unaNac;
    }

    public void setUnaNac (Nacionalidad val) {
        this.unaNac = val;
    }

    @Override
    public void setUnTipoDoc(TipoDocumento val) {
        super.setUnTipoDoc(val);
        val.AgregarCliente(this);
    }

   
    public    boolean isApellido(String unApellido) {
      return  (this.getApellido().compareTo(unApellido)==0);
      
    }

    public boolean isNombre(String nombre) {
      return (this.getNombre().compareTo(nombre)==0);
    }
   public void agregarReserva(Reserva unaReserva) {
       reservas.put(unaReserva.getNumReserva(), unaReserva);
       

   }
   public void agregarDireccion(Direccion unaDireccion) throws Exception {
          this.setDomicilio(unaDireccion);
          Hotel.persistencia.update(this);
          
       

   }
   public void removerReservas() {
      Reserva aux=null;
      Map retorno=new HashMap();
      Iterator iterador= this.reservas.values().iterator();
      while(iterador.hasNext() )
          { aux=(Reserva)iterador.next();
            aux.removerCliente();
        }
   }
   
   void quitarReserva(Reserva unaReserva) throws Exception {
        reservas.remove( unaReserva.getNumReserva());
        try { 
        Hotel.persistencia.update(this);
        } catch (Exception ex) {
         throw new Exception("No se pudo desasociar el Cliente con La Reserva ");
        }
}
   
  
    public boolean isDocumento(String documento) {
      return (this.getDocumento().compareTo(documento)==0);
    }
    public boolean isNacionalida(String nacionalidad) {
      return (this.getUnaNac().getNombre().compareTo(nacionalidad)==0);
    }
    @Override
    public String toString() {
        return this.getDocumento();}

    //agregado Facundo
    public Map<Integer, Reserva> getReservas() {
        return reservas;
    }
  
    public String getFechaNac(){
      return UtilidadesGui.convertirString(fechaNacimiento);
    }
    
    //fin Agregado
}

