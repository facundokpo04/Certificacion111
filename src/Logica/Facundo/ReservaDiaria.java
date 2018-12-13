package Logica.Facundo;

import Exepciones.ReservasExepcion;
import Logica.Hotel;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class ReservaDiaria {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer numDiario;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private Integer cantPersonas;
    private Double costo;
    @OneToOne
    private Tarifa unaTarifa;
    @ManyToOne
    private Habitacion unaHabitacion;
    @ManyToOne
    private Estadia unaEstadia;

    public ReservaDiaria() {
    }

    public ReservaDiaria( Date fecha, Integer cantPersonas,  Habitacion unaHabitacion,Tarifa unaTarifa ) throws Exception {
       
       
        this.fecha = fecha;
        this.cantPersonas = cantPersonas;
        this.unaHabitacion = unaHabitacion;
        this.unaTarifa=unaTarifa;
        this.calcularCosto();
        Hotel.persistencia.insert(this);
    }

    public Tarifa getUnaTarifa() {
        return unaTarifa;
    }

    public void setUnaTarifa(Tarifa unaTarifa) {
        this.unaTarifa = unaTarifa;
    }

    public Integer getNumDiario() {
        return numDiario;
    }

    public void setNumDiario(Integer numDiario) {
        this.numDiario = numDiario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(Integer cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Habitacion getUnaHabitacion() {
        return unaHabitacion;
    }

    public void setUnaHabitacion(Habitacion unaHabitacion) {
        this.unaHabitacion = unaHabitacion;
    }

    public Estadia getUnaEstadia() {
        return this.unaEstadia;
    }

    public void setUnaEstadia(Estadia unEstadia) {
        this.unaEstadia = unEstadia;
        try {
            Hotel.persistencia.update(this);
        } catch (Exception ex) {
           System.out.print( ex.getMessage());
        }
    }
   
      public Double calcularCosto(){
          Double costoR;
        costoR=unaTarifa.getPrecio();
        int cantp=this.cantPersonas;
        while(cantp>this.unaHabitacion.getTipoHabitacion().getCapacidadEstandar())
        {
            costoR+=unaTarifa.getPrecioPerExt();
            cantp--;
        
        
        }
        this.setCosto(costoR);
        return costoR;
     
      }    
      
      //falta comprobar disponibilidad debe hacerlo la estadia
   public void cambiarHabitacion(Habitacion unaHabitacion) throws ReservasExepcion {
       Tarifa unaTarifa;
        try {
            this.getUnaHabitacion().quitarReservaDiaria(this);
        } catch (Exception ex) {
            throw  new ReservasExepcion(ex.getMessage());
        }
       this.unaHabitacion=unaHabitacion;
        try {
           unaHabitacion.agregarReservaDiaria(this);
        } catch (Exception ex) {
            throw  new ReservasExepcion(ex.getMessage());
        }
       unaTarifa=this.unaHabitacion.getTipoHabitacion().buscarTarifaNombre(this.getUnaTarifa().getNombre());
      if(unaTarifa!=null)
       this.setUnaTarifa(unaTarifa);
      else
          new ReservasExepcion("No existe la misma Tarifa Para Habitacion");
     
      this.calcularCosto();
        try {
            Hotel.persistencia.update(this);
        } catch (Exception ex) {
             new ReservasExepcion("No se Pudo Actualizar la Estadia");
        }
      
   
   }
//    public void cambiarHabitacion(Habitacion unaHabitacion,Tarifa unaTarifa){
//      
//       this.getUnaHabitacion().quitarReservaDiaria(this);
//       this.unaHabitacion=unaHabitacion;
//       this.getUnaHabitacion().agregarReservaDiaria(this);
//       //unaTarifa=this.unaHabitacion.getTipoHabitacion().buscarTarifaNombre(this.getUnaTarifa().getNombre());
//  
//        this.setUnaTarifa(unaTarifa);
//
//     
//        this.calcularCosto();
//   
//   }
   public void cambiarTarifa(){
   
   }
   public Boolean isReservada(Date fecha1){
       if((fecha1.getDate()==this.getFecha().getDate()) && (fecha1.getMonth()==this.getFecha().getMonth()) &&(fecha1.getYear()==this.getFecha().getYear())){
           
    return true;}
   
   else
       return false;
      
   
   }
   
    @Override
    public String toString() {
      return ""+this.unaEstadia.getNumEstadia()+","+this.unaEstadia.getmTitular().getApellido()+","+this.unaEstadia.getmTitular().getNombre();
 
     
    }
   public boolean isActiva() {
        return this.unaEstadia.getUnEstado().is("Caduca");
    }

    public String personaExtra(){
        if (this.unaEstadia.tienePersonaExtra()) {
            return "SI";
        } else {
            return "NO";
        }
    }
    
public void checkOut() throws Exception {
        try {
            this.getUnaHabitacion().quitarReservaDiaria(this);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
       
        

}
    
    
}
