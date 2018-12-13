package Logica.Facundo;

import Logica.Hotel;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codEstado;
    private String nombre;
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,Reserva> reservas;

    public Estado() {
        this.reservas=new HashMap();
    }

    public Estado( String nombre) throws Exception {
        this();
        this.nombre = nombre;
        Hotel.persistencia.insert(this);
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
  public void agregarReserva(Reserva unaReserva)
  { 
     reservas.put(unaReserva.getNumReserva(), unaReserva);
     
  
  }  
public void quitarReserva(Reserva unaReserva) throws Exception
{ 
     this.reservas.remove(unaReserva.getNumReserva());

}
    boolean is(String estado) {
      return this.nombre.compareTo(estado)==0;
    }
    
    
}
