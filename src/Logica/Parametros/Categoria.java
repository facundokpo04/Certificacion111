package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import java.util.Observable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria extends Observable implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codCat;
    private String nombre;

    public Categoria() throws Exception {
        
        
    }

    public Categoria( String nombre) throws Exception {
         
        this.nombre = nombre.toUpperCase();
       Hotel.persistencia.insert(this);
    }

    public int getcodCat() {
        return codCat;
    }

    public void setcodCat(int codNac) {
        this.codCat = codNac;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }
    
   public void update() {
          this.setChanged();
        this.notifyObservers();
    }
    
    
    @Override
    public String toString() {
        return (this.nombre);
    }
    
    
}
