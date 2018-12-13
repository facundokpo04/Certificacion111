package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marca implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codMarca;
    private String nombre;

    public Marca() throws Exception {
        
        
    }

    public Marca( String nombre) throws Exception {
         
        this.nombre = nombre.toUpperCase();
       Hotel.persistencia.insert(this);
    }

    public int getcodMarca() {
        return codMarca;
    }

    public void setcodMarca(int codNac) {
        this.codMarca = codNac;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return (this.nombre);
    }
    
    
}
