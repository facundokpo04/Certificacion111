package Logica.Parametros;

import Logica.Facundo.Habitacion;
import Logica.Hotel;
import Logica.Facundo.Tarifa;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cod;
    private String descripcion;
    private int capacidadEstandar; 
    private int capacidadMaxima;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Collection<Tarifa> tarifas;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<String,Habitacion> habitacionesTipo;

    
    public TipoHabitacion () {
        this.habitacionesTipo=new HashMap();
        this.tarifas=new LinkedList();
    }

    public TipoHabitacion(String descripcion, int capacidadEstandar, int capacidadMaxima) throws Exception {
        this();
        this.descripcion = descripcion.toUpperCase();
        this.capacidadEstandar = capacidadEstandar;
        this.capacidadMaxima = capacidadMaxima;
        Hotel.persistencia.insert(this);
    }


    public int getCapacidadEstandar () {
        return capacidadEstandar;
    }

    public void setCapacidadEstandar (int val) {
        this.capacidadEstandar = val;
    }

    
    public int getCapacidadMaxima () {
        return capacidadMaxima;
    }

    public int getcod () {
        return cod;
    }
    public void setCapacidadMaxima (int val) {
        this.capacidadMaxima = val;
    }

   
    public String getDescripcion () {
        return descripcion;
    }


    public void setDescripcion (String val) {
        this.descripcion = val;
    }


    public Collection getHabitaciones () {
        return this.habitacionesTipo.values();
    }
    
    public Collection getHabitacionesLibres(Date fechaEntrada, Date fechaSalida){
        Collection retorno= new LinkedList();
           for(Object unObj : this.getHabitaciones()){
           Habitacion unhab = (Habitacion) unObj;
           if(!unhab.estaReservada(fechaEntrada, fechaSalida) & !unhab.estaOcupada(fechaEntrada, fechaSalida))
           {
                 retorno.add(unhab)     ;       
           
           }
    
    
            }
           return retorno;
    }
    
    public void agregarTarifa(Tarifa unaTarifa )
     {
        tarifas.add(unaTarifa);

      }
    public void agregarHabitacion(Habitacion unaHabitacion )
     {
          habitacionesTipo.put(unaHabitacion.getIdHabitacion(), unaHabitacion);
}
    public boolean tieneCapacidad(int nmrHuespedes) {
       return(this.getCapacidadMaxima()>=nmrHuespedes);
    }

    public Collection<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(Collection<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public Tarifa buscarTarifaNombre(String nomTarifa){
    Tarifa aux=null;
    Tarifa retorno=null;
    Iterator iterador= this.tarifas.iterator();
    while(iterador.hasNext() && retorno==null )
    { aux=(Tarifa)iterador.next();
    if(aux.isTarifa(nomTarifa)){
    retorno=aux;
    }

    }
    return retorno;

    }
    
    
    
    @Override
    public String toString() {
        return this.descripcion;
    }
//agreado Facundo
    public boolean isTipoHabitacion(String unadescripcion){
        return this.descripcion.equals(unadescripcion);
    
    }
    
    public void quitarHabitacion(Habitacion unaHabitacion) throws Exception{
        this.habitacionesTipo.remove(unaHabitacion.getIdHabitacion());
        Hotel.persistencia.update(this);
    
    }
   public void quitarTarifa(Tarifa unTarifa) throws Exception{
       this.tarifas.remove(unTarifa);
       Hotel.persistencia.update(this);
   
   
   
   }
    public void EliminarTarifas() throws Exception{
        for (Object unTar : this.getTarifas()) {
            Tarifa aux=(Tarifa)unTar;
             this.tarifas.remove(aux);
            
        }
      
       Hotel.persistencia.update(this);
   
   
   
   }
}

