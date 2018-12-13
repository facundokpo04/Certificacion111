package Logica.Parametros;

import Logica.Hotel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nacionalidad {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codNac;
    private String nombre;

    public Nacionalidad() throws Exception {
        
        
    }

    public Nacionalidad( String nombre) throws Exception {
         
        this.nombre = nombre.toUpperCase();
       Hotel.persistencia.insert(this);
    }

    public int getCodNac() {
        return codNac;
    }

    public void setCodNac(int codNac) {
        this.codNac = codNac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    @Override
    public String toString() {
        return ( this.nombre );
    }
    
     }
