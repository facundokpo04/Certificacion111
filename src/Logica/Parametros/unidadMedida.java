package Logica.Parametros;

import Logica.Hotel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class unidadMedida extends Observable {
    
   @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cod;
    private String nombre;
    private String abreviacion; 
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<Integer,Articulo> articulosUnidad;
    
    
    public unidadMedida () {
        this.articulosUnidad=new HashMap();
      }

    public unidadMedida(String nombre, String abreviacion) throws Exception {
        this();
        this.nombre = nombre.toUpperCase();
        this.abreviacion = abreviacion.toUpperCase();
        Hotel.persistencia.insert(this);
    }


    public int getcod () {
        return cod;
    }

    public void setcod (int val) {
        this.cod = val;
    }

    
    public String getnombre() {
        return nombre;
    }

     public void setnombre (String val) {
        this.nombre = val;
    }
     
       
    public String getabreviacion () {
        return abreviacion;
    }


    public void setabreviacion (String val) {
        this.abreviacion = val;
    }


    public Collection getArticulos () {
        return this.articulosUnidad.values();
    }
    
    
    public void agregarArticulo(Articulo unArticulo )
     {
    articulosUnidad.put(unArticulo.getCodArticulo(), unArticulo);

}
    
   public void update() {
          this.setChanged();
        this.notifyObservers();
    }
       
    @Override
    public String toString() {
        return this.nombre;
    } 
    
    
}
