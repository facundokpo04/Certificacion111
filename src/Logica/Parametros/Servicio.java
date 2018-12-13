package Logica.Parametros;

import Logica.Hotel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Servicio {
   
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cod;
    private String nombre;
    private String descripcion; 
    private String estado;
    private double precio;
    @OneToOne
    private unidadMedida unUnidadMedida;
    

    public Servicio() {
    }
    

    public Servicio(String nombre, String descripcion,String estado,double precio,unidadMedida unaUnidad) throws Exception {
        this.nombre = nombre.toUpperCase();
        this.descripcion = descripcion.toUpperCase();
        this.estado=estado.toUpperCase();
        this.precio=precio;
        this.unUnidadMedida=unaUnidad;
        Hotel.persistencia.insert(this);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public unidadMedida getUnUnidadMedida() {
        return unUnidadMedida;
    }

    public void setUnUnidadMedida(unidadMedida unUnidadMedida) {
        this.unUnidadMedida = unUnidadMedida;
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
     
       
    public String getdescripcion () {
        return descripcion;
    }


    public void setdescripcion (String val) {
        this.descripcion = val;
    }

  

    @Override
    public String toString() {
        return this.nombre;
    } 
    
    
}
