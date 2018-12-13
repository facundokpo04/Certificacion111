package Logica.Facundo;

import Exepciones.ReservasExepcion;
import Logica.Hotel;
import Logica.Parametros.Articulo;
import Logica.Parametros.MedioLlegada;
import Logica.Parametros.Nacionalidad;
import Logica.Parametros.Pago;
import Logica.Parametros.TipoDocumento;
import Logica.Servicio.Cargo;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D2CC2D27-0A98-9974-584E-53CE1C8EC907]
// </editor-fold> 
@Entity
public class Estadia extends Observable{

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int numEstadia;   
   @Temporal(javax.persistence.TemporalType.DATE) 
    private Date checkIn;
   @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkOut;
    private String horaLlegada;
    private Double costoTotal;
    @OneToOne
    private Estado unEstado;
    @ManyToOne
    private Cliente mTitular;
    @ManyToOne
    private MedioLlegada mLlegada; 
    private String comentarios;
    @OneToOne
    private Reserva miReserva;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<String,Huesped> huespedes;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,ReservaDiaria> reservasDiarias;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,Pago> pagos;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,Cargo> cargos;
    private Double costoCargos;
    

    public Estadia () {
          this.huespedes= new HashMap();
          this.reservasDiarias=new HashMap();
          this.pagos=new HashMap();
          this.cargos= new HashMap<Integer, Cargo>();
    }

    public Estadia(Date checkIn, Date checkOut, String horaLlegada,  MedioLlegada mLlegada, String comentarios, Reserva miReserva) throws Exception 
    {
        this();
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.horaLlegada = horaLlegada;
        this.mLlegada = mLlegada;
        this.comentarios = comentarios;
        this.miReserva = miReserva;
        this.mTitular=this.miReserva.getCliente();
        
        this.traspasarPagos(miReserva);
        this.costoCargos=0.0;
        this.costoTotal=0.0;
        Hotel.persistencia.insert(this);
    }
    
    
 public Map<Integer, Pago> getPagos() {
        return pagos;
    }
    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Map getHuespedes() {
        return huespedes;
    }

    public void setmTitular(Cliente mTitular) {
        this.mTitular = mTitular;
    }

    public Cliente getmTitular() {
        return mTitular;
    }

    public Map<Integer, Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(Map<Integer, Cargo> cargos) {
        this.cargos = cargos;
    }

    public Huesped agregarHuesped(Huesped miHuesped) throws  Exception  {
       Huesped retorno=null;
       if(!this.existeHuesped(miHuesped.getDocumento())){
         
         this.huespedes.put(miHuesped.getDocumento(),miHuesped);
         retorno=miHuesped;
         Hotel.persistencia.update(this);
         this.setChanged();
         this.notifyObservers();
        
        }else{
       
       throw new Exception("El Huesped ya Existe");
       }
       
       return retorno;
    }
 
 public Huesped ModificarHuesped(Huesped unHuesped ,String nombre, String apellido, Nacionalidad unaNacionalidad) throws Exception  {
       Huesped retorno=null;
       if(this.existeHuesped(unHuesped.getDocumento())){
               retorno=this.huespedes.get(unHuesped.getDocumento());
               retorno.setApellido(apellido);
               retorno.setNombre(nombre);
               retorno.setUnaNacionalidad(unaNacionalidad);
                  
           try {
               Hotel.persistencia.update(this);
               Hotel.persistencia.update(retorno);
                       this.setChanged();
         this.notifyObservers();
        
            
           } catch (Exception ex) {
               throw new Exception("Error al Actualiar el Huesped");
           }
        
        }
       else{
         throw new Exception("El Huesped no Existe");
       
       }
       
       return retorno;
    }

    public Cliente cambiarTitular  (Cliente unCliente){
          this.setmTitular(mTitular);
          return this.getmTitular();
             
              }

    public MedioLlegada getmLlegada() {
        return mLlegada;
    }

    public void setmLlegada(MedioLlegada mLlegada) {
        this.mLlegada = mLlegada;
    }
      
    public Reserva getMiReserva() {
        return miReserva;
    }

    public void setMiReserva(Reserva miReserva) {
        this.miReserva = miReserva;
    }
 
    public Date getCheckIn () {
        return checkIn;
    }

    public void setCheckIn (Date val) {
        this.checkIn = val;
    }

    public Date getCheckOut () {
        return checkOut;
    }

    public void setCheckOut (Date val) {
        this.checkOut = val;
    }

    public String getHoraLlegada () {
        return horaLlegada;
    }

    public void setHoraLlegada (String val) {
        this.horaLlegada = val;
    }

    public int getNumEstadia () {
        return numEstadia;
    }

    public void setNumEstadia (int val) {
        this.numEstadia = val;
    }

    public Estado getUnEstado () {
        return unEstado;
    }

    public void setUnEstado (Estado val) {
        this.unEstado = val;
    }

    public Double getCostoCargos() {
        this.calcularCostoCargos();
        return costoCargos;
    }

    public void setCostoCargos(Double costoCargos) {
        this.costoCargos = costoCargos;
    }

    public Map<Integer, ReservaDiaria> getReservasDiarias() {
        return reservasDiarias;
    }
    
    
    
    public void agregarUnPago (Pago unPago) throws ReservasExepcion {
          Double deuda = this.costoTotal- this.getTotalPagado();

        if (this.getTotalPagado() + (unPago.getImporte()) <= this.costoTotal) {
            this.pagos.put(unPago.getNumPago(), unPago);
            this.setChanged();
            this.notifyObservers();



        } else {
            throw new ReservasExepcion("La Entrega Supera El Costo total de la Estadia" + "Falta Pagar: $" + deuda);
        }
           
   }
   
     public  Double getTotalPagado() {        Double tot = new Double(0);

       if(!this.getPagos().isEmpty())
       {
            Double aux=null;
         Map retorno=new HashMap();
         Iterator iterador= this.pagos.values().iterator();
         while(iterador.hasNext() )
          { aux=((Pago)iterador.next()).getImporte();
            tot+=aux;
        }
           
       
       }
    
        
    
           return tot;
    }
     
     public void traspasarPagos(Reserva unaReserva){
         this.pagos.putAll(unaReserva.getPagos());
     
     }
     
     
    public Boolean existeHuesped(String documento){
      return(huespedes.containsKey(documento));
    }
    public static Date sumaDias(Date fecha, int dias){ 
    Calendar cal = Calendar.getInstance(); 
       cal.setTime(fecha); 
       cal.add(Calendar.DAY_OF_YEAR, dias); 
       return cal.getTime(); 
} 
    public void generarReservasDiarias(Reserva unaReserva) throws ReservasExepcion{
        
        int cantDias;
        
        cantDias=(int) unaReserva.getCantDias();
     
      if (cantDias>0) {
     
       for(int i=0;i<cantDias;i++ ){
           
          ReservaDiaria res;
           try {
               res = new ReservaDiaria(sumaDias(unaReserva.getFechaEntrada(), i),unaReserva.getCantPersonas(),
            unaReserva.getHabitacion(),unaReserva.getUnaTarifa());
               res.setUnaEstadia(this);
            unaReserva.getHabitacion().agregarReservaDiaria(res);
            this.reservasDiarias.put(res.getNumDiario(), res);
            Hotel.persistencia.update(this);
            Hotel.persistencia.update(unaReserva.getHabitacion());
           } catch (Exception ex) {
               throw new ReservasExepcion("No se pudo Generar Una Estadia Para la Reserva");
           }
  
           }
    }
    this.calcularCosto();
    }
    
    public Double calcularCosto(){
        Double costoE=0.0;
            ReservaDiaria aux=null;
        Map retorno=new HashMap();
      Iterator iterador= this.reservasDiarias.values().iterator();
      while(iterador.hasNext() )
          { aux=(ReservaDiaria)iterador.next();
             costoE+=aux.calcularCosto();
        }
          this.setCostoTotal(costoE);
         return costoE;
        

    }
    
//    public void cambiarHabitacion(Habitacion unaHabitacion,Tarifa unaTarifa,Date fecha)
//    {
//        ReservaDiaria unaRDiaria;
//        unaRDiaria=this.buscarReservaDiaria(fecha);
//        if(unaHabitacion.tieneReserva(fecha)==null && unaHabitacion.tieneReservaDiaria(fecha)==null)
//        unaRDiaria.cambiarHabitacion(unaHabitacion,unaTarifa);
//        else
//            new ReservasExepcion("la habitacion no esta Disponible");
//        
//        
//    
//    
//    }
      
    public void cambiarHabitacion(Habitacion unaHabitacion,Date fecha) throws Exception   {
        ReservaDiaria unaRDiaria;
        unaRDiaria=this.buscarReservaDiaria(fecha);
        if(unaHabitacion.tieneReserva(fecha)==null && unaHabitacion.tieneReservaDiaria(fecha)==null)
              try {
            unaRDiaria.cambiarHabitacion(unaHabitacion);
        } catch (Exception ex) {
              new ReservasExepcion(ex.getMessage());
        }
        else
            new ReservasExepcion("la habitacion no esta Disponible");
        
//        Hotel.persistencia.update(this);
        this.setChanged();
        this.notifyObservers();

    }
 
    public ReservaDiaria buscarReservaDiaria(Date fecha)
    {
          ReservaDiaria aux=null;
        ReservaDiaria retorno=null;
      Iterator iterador= this.reservasDiarias.values().iterator();
      while(iterador.hasNext() && retorno==null )
          { aux=(ReservaDiaria)iterador.next();
             if((fecha.compareTo(aux.getFecha()))==0)
                 retorno=aux;
                 
                 
        }
      
      return retorno;
    
    }
    
   public  void update() {
          this.setChanged();
        this.notifyObservers();
    }
    public Double getTotalPagadoCargos() {
        Double tot = 0.0;

        if (!this.getCargos().isEmpty()) {
             Cargo aux2=null;
            Iterator iterador = this.cargos.values().iterator();
            while (iterador.hasNext()) {
                
                aux2= ((Cargo) iterador.next());
                if(aux2.getUnEstadoCargo().equals("PAGADO")){
                  tot += aux2.getCostoTotal();
                
                }
            }


        }



        return tot;
    }
    
    
     public Double getDuedaCargo() {
        Double tot = this.costoCargos - this.getTotalPagadoCargos();
        return tot;


    }
     
     
     
      @Override
    public String toString() {
        return String.valueOf(this.getNumEstadia());
    }
    public boolean isReservada(Date FechaEntrada, Date fechaSalida) {
        if (FechaEntrada.compareTo(this.checkIn) >= 0 || fechaSalida.compareTo(this.checkOut) <= 0) {
            return false;
        } else {
            return true;
        }
    }  
    public void agregarCargo(Cargo unCargo) throws Exception{
     this.getCargos().put(unCargo.getNumCargo(), unCargo);
     
        try {
            Hotel.persistencia.update(this);
//            this.costoCargos= this.costoCargos+unCargo.getCostoTotal();
            this.setChanged();
            this.notifyObservers();
        } catch (Exception ex) {
            throw  new Exception("No se pudo Agregar el cargo");
        }
     
     
   }
      public Double calcularCostoCargos(){
        Double costoE=0.0;
          Cargo aux=null;
        Map retorno=new HashMap();
      Iterator iterador= this.getCargos().values().iterator();
      while(iterador.hasNext() )
          { aux=(Cargo)iterador.next();
             costoE+=aux.calcularCosto();
        }
          this.setCostoCargos(costoE);
         return costoE;
        

    }
     public Double getDueda() {
         this.calcularCosto();
        Double tot = this.costoTotal - this.getTotalPagado();
        return tot;


    }

  
     public Boolean isEstadia(Integer nroEstadia){
            return this.numEstadia == nroEstadia;
     
     }
    
       public boolean tienePersonaExtra() {
        if (this.miReserva.tienePersonaExtra()) {
            return true;
        } else {
            return false;
        }
        }
    public String personaExtraStr(){
        if (this.tienePersonaExtra()) {
            return "SI";
        } else {
            return "NO";
        }
    }

   
  public List cargarLista(){
     List lista= new LinkedList();
        for (ReservaDiaria aux : this.getReservasDiarias().values()) {
            lista.add(aux);
        }
     return lista;
    }
    
    
  public List cargarLista2(){
     List lista= new LinkedList();
        for (Cargo aux : this.getCargos().values()) {
            lista.add(aux);
        }
     return lista;
    }
  
  public void quitarHuespedes() throws Exception{
  
   
     Huesped aux = null;
     try {
         
         
         this.huespedes.clear();
//       for (Object unreg : this.huespedes.values()) {
//            aux=(Huesped)unreg;
//            this.huespedes.remove(aux.getDocumento());
//           
//              
//        }
             Hotel.persistencia.update(this);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getLocalizedMessage());
            
        }
    
    
  
  
  }
  
  public boolean isActiva(){
      if(this.getUnEstado()==null){
      return false;
      }
      else
      return (!this.getUnEstado().getNombre().equals("Finalizada"));
  
  
  }
   public void checkIn( Hotel unHotel) throws Exception{
        Estado unEstado=unHotel.getEstadoReserva(2);
        try {
            this.cambiarEstado(unEstado);
            
            
        } catch (Exception ex) {
            throw  new Exception("No se Pudo Cambiar el estado de la Estadia");
        }
    

  
  
  }  
  
  public void checkOut( Hotel unHotel) throws Exception{
        Estado unEstado=unHotel.getEstadoReserva(5);
        try {
            this.cambiarEstado(unEstado);
            this.quitarHuespedes();
            
        } catch (Exception ex) {
            throw  new Exception("No se Pudo Cambiar el estado de la Estadia");
        }
     for (ReservaDiaria aux : this.getReservasDiarias().values()) {
            aux.checkOut();
        }
      Hotel.persistencia.update(this);
  
  
  }  
        public void cambiarEstado(Estado unEstado) throws Exception {

         this.setUnEstado(unEstado);
        Hotel.persistencia.update(this);
        
       
    }
        
        
        public Double calcularCostoHasta(Date hoy) {
        Double costoE = 0.0;
        ReservaDiaria aux = null;
        Map retorno = new HashMap();
        Iterator iterador = this.reservasDiarias.values().iterator();
        while (iterador.hasNext()) {
            aux = (ReservaDiaria) iterador.next();
            if (aux.getFecha().compareTo(hoy) <= 0) {
                costoE += aux.calcularCosto();
            }
        }
        return costoE;
    }
  public Double calcularCostoHastaHoy() {
        Date hoy = new Date();
            hoy.setHours(0);
            hoy.setMinutes(0);
            hoy.setSeconds(0);
        Double costoE = 0.0;
        ReservaDiaria aux = null;
        Map retorno = new HashMap();
        Iterator iterador = this.reservasDiarias.values().iterator();
        while (iterador.hasNext()) {
            aux = (ReservaDiaria) iterador.next();
            if (aux.getFecha().compareTo(hoy) <= 0) {
                costoE += aux.calcularCosto();
            }
        }
        return costoE;
    }
    public Double calcularCostoDesde(Date hoy) {
        Double costoE = 0.0;
        ReservaDiaria aux = null;
        Map retorno = new HashMap();
        Iterator iterador = this.reservasDiarias.values().iterator();
        while (iterador.hasNext()) {
            aux = (ReservaDiaria) iterador.next();
            if (aux.getFecha().compareTo(hoy) > 0) {
                costoE += aux.calcularCosto();
            }
        }
        return costoE;
    }
    public List getReservasDiariasHasta(Date hoy) {
        List<ReservaDiaria> reservasD = new LinkedList();
        ReservaDiaria aux = null;
        Iterator iterador = this.reservasDiarias.values().iterator();
        while (iterador.hasNext()) {
            aux = (ReservaDiaria) iterador.next();
            if (aux.getFecha().compareTo(hoy) <= 0) {
                reservasD.add(aux);
            }
        }
        return reservasD;
    }
    public List getReservasDiariasDesde(Date hoy) {
        List<ReservaDiaria> reservasD = new LinkedList();
        ReservaDiaria aux = null;
        Iterator iterador = this.reservasDiarias.values().iterator();
        while (iterador.hasNext()) {
            aux = (ReservaDiaria) iterador.next();
            if (aux.getFecha().compareTo(hoy) > 0) {
                reservasD.add(aux);
            }
        }
        return reservasD;
    }
    public Double getDuedaHasta(Date hoy) {
        Double tot = this.calcularCostoHasta(hoy) - this.getTotalPagado();
        if (tot <= 0.0) {
            return 0.0;
        } else {
            return tot;
        }
    }
    
    public List cargarLista3() {
       Date fecha = new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        fecha.setSeconds(0);
        return this.getReservasDiariasHasta(fecha);
    }

    public void eliminarHuesped(Huesped unHuesped) throws Exception {
       
        this.huespedes.remove(unHuesped.getDocumento());
        try {
            Hotel.persistencia.update(this);
        } catch (Exception ex) {
            throw new Exception("No se pudo Eliminar el Huesped");
        }
    }
        
        
}

