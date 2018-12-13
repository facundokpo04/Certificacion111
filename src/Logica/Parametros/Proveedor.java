package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proveedor  extends Observable  implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codProv;
    private String nombre;
    private String telefono;
    @OneToMany
    private List<Numero> numeros;
    
    public Proveedor() throws Exception {
        
        
    }

    public Proveedor(String nombre,String telefono) throws Exception {
       this.nombre = nombre.toUpperCase();
       this.telefono=telefono;
       this.numeros = new LinkedList();
       Hotel.persistencia.insert(this);
    }

    public int getcodProv() {
        return codProv;
    }

    public void setcodProv(int codProv) {
        this.codProv = codProv;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }
    
   public String gettelefono() {
        return telefono;
    }

    public void settelefono(String telefono) {
        this.telefono = telefono;
    }
    
     public List<Numero> getNumeros() {
        return numeros;
    }
    
    public void setNumeros(List<Numero> numeros) {
        this.numeros = numeros;
    }
    
    
    public Numero obtenerNumeroComprobante(String numero) {
        Numero retornar = null;
        for (Numero unNumero : this.getNumeros()) {
            if (unNumero.toString().toUpperCase().equals(numero.toUpperCase())) {
                retornar = unNumero;
            }
       }
        return retornar;
        
    }
    
    public Numero agregarNumero(String unNumero) throws Exception {
        Numero nuevoNumero = new Numero(unNumero);
        this.numeros.add(nuevoNumero);
        Hotel.persistencia.update(this);
        return nuevoNumero;
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
