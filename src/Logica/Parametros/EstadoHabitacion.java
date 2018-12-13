package Logica.Parametros;

import Logica.Hotel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EstadoHabitacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codEstado;
    private String nombre;

    public EstadoHabitacion() throws Exception {
        
        
    }

    public EstadoHabitacion( String nombre) throws Exception {
         
        this.nombre = nombre.toUpperCase();
       Hotel.persistencia.insert(this);
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codNac) {
        this.codEstado = codNac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    @Override
    public String toString() {
        return ( this.getNombre() );
    }
    
     }
