package Logica.Facundo;

import Exepciones.ClienteExepcion;
import Logica.Hotel;
import Logica.Parametros.TipoHabitacion;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.7DD1DD9B-3592-65FA-9CA0-1DA4563090B4]
// </editor-fold> 
@Entity
public class Habitacion {

    
    @Id
    private String idHabitacion;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.001F4F56-A149-47DF-428C-AE43CCE812FC]
    // </editor-fold> 
    @ManyToOne
    private TipoHabitacion tipoHabitacion;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,Reserva> reservas;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,ReservaDiaria> reservasDiarias;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B9CB2146-609F-36F1-3E62-C6706A319F73]
    // </editor-fold> 
    public Habitacion () {
        this.reservas=new HashMap();
        this.reservasDiarias=new  HashMap();
    
    }

    public Habitacion(String nombre,TipoHabitacion tipoHabitacion) throws Exception {
        this();
        this.idHabitacion=nombre;
        this.tipoHabitacion = tipoHabitacion;
        this.tipoHabitacion.agregarHabitacion(this);
        Hotel.persistencia.insert(this);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9E23D64F-98FE-1814-BA63-868B354152E1]
    // </editor-fold> 
    public String getIdHabitacion () {
        return idHabitacion;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4AEE39D1-2D02-8313-E1B3-973770755E39]
    // </editor-fold> 
    public void setIdHabitacion (String val) {
        this.idHabitacion = val;
    }

    public Map<Integer, Reserva> getReservas() {
        return reservas;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C7171F70-3BD2-8D3D-E13F-959ADE301C65]
    // </editor-fold> 
    public TipoHabitacion getTipoHabitacion () {
        return tipoHabitacion;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DF251B7E-604B-9ACE-CADF-BF7EEB8E4680]
    // </editor-fold> 
    public void setTipoHabitacion (TipoHabitacion val) {
        this.tipoHabitacion = val;
   
    }
    public void cambiarTipoHabitacion(TipoHabitacion unTipoHabitacion) throws Exception{
        this.tipoHabitacion.quitarHabitacion(this);
        this.tipoHabitacion = unTipoHabitacion;
        this.tipoHabitacion.agregarHabitacion(this);
        Hotel.persistencia.update(this);
    }
    
    
    public void agregarReservaDiaria(ReservaDiaria unaReservaDiaria) throws Exception
    {
     reservasDiarias.put(unaReservaDiaria.getNumDiario(), unaReservaDiaria);
        try {
            Hotel.persistencia.update(this);
        } catch (Exception ex) {
            throw new Exception("No se pudo Actualizar la Habitacion");
        }
    
    }
    
    
    public void agregarReserva(Reserva unaReserva)
    {
        reservas.put(unaReserva.getNumReserva(), unaReserva);
    
    }
    
     public void quitarReservaDiaria(ReservaDiaria unaReservaDiaria) throws Exception
    {
        reservasDiarias.remove(unaReservaDiaria.getNumDiario());
        try {
            Hotel.persistencia.update(this);
        } catch (Exception ex) {
           throw new Exception("No fue posible quitar la estadia de la habitacion");
        }
        
    
    }
     public void quitarReserva(Reserva unaReserva) throws Exception
    {
        reservas.remove(unaReserva.getNumReserva());
        try {
            Hotel.persistencia.update(this);
        } catch (Exception ex) {
            throw new ClienteExepcion("No se pudo Quitar la Reserva de la habitacion");
        }
        
    
    }
    public void isTipoHabitacion (TipoHabitacion unTipoHabitacion) {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6C469E1E-91E9-8988-DAB0-783D8CE7FA0B]
    // </editor-fold> 
    public final boolean estaReservada (Date FechaEntrada, Date fechaSalida) {
          Reserva aux=null;
        boolean retorno=false;
      Iterator iterador= this.reservas.values().iterator();
      while(iterador.hasNext()&&   !retorno )
          { aux=(Reserva)iterador.next();
            if(aux.isReservada(FechaEntrada,fechaSalida))
            {
               retorno=true;
               //break;
             }
        }
        return retorno;
       
    }
     public static Date sumaDias(Date fecha, int dias){ 
    Calendar cal = Calendar.getInstance(); 
       cal.setTime(fecha); 
       cal.add(Calendar.DAY_OF_YEAR, dias); 
       return cal.getTime(); }
     
    public Boolean estaOcupada(Date fechaEntrada, Date fechaSalida){
   
        Long cantDias=((fechaSalida.getTime()-fechaEntrada.getTime())/(24*60*60*1000));
            Date fecha=fechaEntrada;
            ReservaDiaria aux=null;
        boolean retorno=false;
      Iterator iterador= this.reservasDiarias.values().iterator();
      
      for(int i=0;i<cantDias && !retorno ;i++ ){
          
            while(iterador.hasNext()&&   !retorno )
          { aux=(ReservaDiaria)iterador.next();
            if(aux.isReservada(fecha))
            {
               retorno=true;
               //break;
             }
        }
            fecha=this.sumaDias(fecha, i);
           iterador= this.reservasDiarias.values().iterator();
           i++;}
        return retorno;
    
    
    
    }

    public Reserva tieneReserva( Date fecha){
            Reserva aux=null;
            Reserva retorno=null;
       
      Iterator iterador= this.reservas.values().iterator();
      while(iterador.hasNext() && retorno==null )
          { aux=(Reserva)iterador.next();
            if(aux.isReservada(fecha) && !aux.isTomada())
            {
               retorno=aux;
             }
        }
        return retorno;
    
    
    }
    public ReservaDiaria tieneReservaDiaria( Date fecha ){
           ReservaDiaria aux=null;
            ReservaDiaria retorno=null;
       
      Iterator iterador= this.reservasDiarias.values().iterator();
      while(iterador.hasNext() && retorno==null )
          { aux=(ReservaDiaria)iterador.next();
            if(aux.isReservada(fecha))
            {
               retorno=aux;
             }
        }
        return retorno;
    
    
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D4D39323-33B6-CC7D-3AA6-210F8B60C6D2]
    // </editor-fold> 
    public boolean tieneCapacidad (int nmrHuespedes) {
        return this.getTipoHabitacion().tieneCapacidad(nmrHuespedes);
        
    }

    @Override
    public String toString() {
        return this.idHabitacion + ""+ this.tipoHabitacion.getDescripcion();
    }
//Agregado Facundo
   public Map getReservas(Cliente unCliente){
       Reserva aux = null;
        Map retorno = new HashMap();
        Iterator iterador = this.reservas.values().iterator();
        while (iterador.hasNext()) {
            aux = (Reserva) iterador.next();
            if (aux.getCliente().isDocumento(unCliente.getDocumento())) {
                retorno.put(aux.getNumReserva(), aux);
            }
        }

        return retorno;
        
    
    }
    public boolean tieneReservaAsoc(){
        
        if(this.reservasDiarias.isEmpty() && this.reservas.isEmpty()){
         return false;
        }else
            return true;
        

}
    public Reserva getReserva(Date fecha){
        
        Reserva aux = null;
        Reserva retorno = null;
        Iterator iterador = this.reservas.values().iterator();
        while (iterador.hasNext()) {
            aux = (Reserva) iterador.next();
            if (aux.entraHoy(fecha)) {
                retorno=aux;
                break;
            }else if(aux.saleHoy(fecha)){
                 retorno=aux;
                 
        
        }
        }

        return retorno;
    
    }
       public Reserva getReservadia(Date fecha){
        
        Reserva aux = null;
        Reserva retorno = null;
        Iterator iterador = this.reservas.values().iterator();
        while (iterador.hasNext()) {
            aux = (Reserva) iterador.next();
            if (aux.isReservada(fecha)) {
                retorno=aux;
                break;
            }
        }

        return retorno;
    
    }
    // fin Agregado
}

