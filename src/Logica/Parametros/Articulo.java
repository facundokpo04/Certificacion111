package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import java.util.Observable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Articulo extends Observable implements Serializable  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codArticulo;
    private String nombre;
    @ManyToOne
    private unidadMedida unidad;
    @ManyToOne
    private Categoria unaCategoria;
    private double stock;
    @OneToOne
    private Marca unaMarca;
    
    
    public Articulo () {   }

    public Articulo(String nombre,unidadMedida unidad,Categoria unaCategoria,Marca unaMarca) throws Exception {
        this();
        this.nombre = nombre.toUpperCase();
        this.unidad = unidad;
        this.unaCategoria= unaCategoria;
        this.stock=0;
        this.unaMarca=unaMarca;
        Hotel.persistencia.insert(this);
    }


    public int getCodArticulo () {
        return codArticulo;
    }

    public void setCodArticulo (int val) {
        this.codArticulo = val;
    }

    
    public String getnombre () {
        return nombre;
    }

     public void setnombre(String val) {
        this.nombre = val;
    }
    
    public void setunidadMedida (unidadMedida val) {
        this.unidad = val;
    }

    public unidadMedida getunidadMedida () {
        return unidad;
    }

    public Categoria getunaCategoria () {
        return unaCategoria;
    }

    public void setunaCategoria (Categoria val) {
        this.unaCategoria = val;
    }
    
    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Marca getUnaMarca() {
        return unaMarca;
    }

    public void setUnaMarca(Marca unaMarca) {
        this.unaMarca = unaMarca;
    }
    
    
    
      public int ModificarStock(int cant) throws Exception{
        int aux=0;
        this.stock=stock+cant;
        Hotel.persistencia.update(this);
        return aux;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }

}
