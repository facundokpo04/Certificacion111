package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import java.util.Observable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class FormaPago extends Observable implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idModPago;
    String descripcion;
    String[] datosPago = new String[9];
   

    public FormaPago() {
    }

    public FormaPago(String descripcion,String[]datosPago) throws Exception {
        this.descripcion = descripcion.toUpperCase();
        this.datosPago=datosPago;
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

    public String[] getDatosPago() {
        return datosPago;
    }

    public void setDatosPago(String[] datosPago) {
        this.datosPago = datosPago;
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
