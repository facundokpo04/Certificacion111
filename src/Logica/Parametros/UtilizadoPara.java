package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import java.util.Observable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UtilizadoPara extends Observable implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cod;
    private String nombre;
    private String descripcion;

    public UtilizadoPara() throws Exception {
        
        
    }

    public UtilizadoPara( String nombre,String descripcion) throws Exception {
         
        this.nombre = nombre.toUpperCase();
        this.descripcion=descripcion.toUpperCase();
       Hotel.persistencia.insert(this);
    }

    public int getcod() {
        return cod;
    }

    public void setcod(int cod) {
        this.cod = cod;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
  
    
    @Override
    public String toString() {
        return (this.nombre);
    }
    
    
}
