package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import java.util.Observable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ModoPago extends Observable implements Serializable {
     @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idModPago;
    String descripcion;

    public ModoPago() {
    }

    public ModoPago(String descripcion) throws Exception {
        this.descripcion = descripcion.toUpperCase();
        Hotel.persistencia.insert(this);
    }

    public Integer getIdModPago() {
        return idModPago;
    }

   public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean isDeposito(){
    
        return this.descripcion.toUpperCase().equals("DEPOSITO");
    }
    
    public Boolean isTarjeta(){
    
        return this.descripcion.toUpperCase().equals("TARJETA");
    }
    
    public Boolean isCheque(){
    
        return this.descripcion.toUpperCase().equals("CHEQUE");
    }
    
      public void update() {
          this.setChanged();
        this.notifyObservers();
    }
    
    @Override
    public String toString() {
       return this.descripcion;
    }
    
}
