package Logica.Parametros;

import Logica.Hotel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Producto implements Serializable {
   
    @Id
    private String cod;
    private String nombre;
    @OneToOne
    private Categoria unaCategoria;
    @OneToOne
    private unidadMedida unUnidadMedida;
    @OneToOne
    private Marca unaMarca;
    private int stock;
    private double precioVenta;

    public Producto() {
    }
    

    public Producto(String cod,String nombre,Marca unaMarca,unidadMedida unaUnidad,Categoria unaCategoria) throws Exception {
        this.cod = cod.toUpperCase();
        this.nombre = nombre.toUpperCase();
        this.unaMarca=unaMarca;
        this.unaCategoria=unaCategoria;
        this.unUnidadMedida=unaUnidad;
        this.stock=0;
        this.precioVenta=0.0;
        Hotel.persistencia.insert(this);
    }


    public unidadMedida getUnUnidadMedida() {
        return unUnidadMedida;
    }

    public void setUnUnidadMedida(unidadMedida unUnidadMedida) {
        this.unUnidadMedida = unUnidadMedida;
    }


    public String getcod () {
        return cod;
    }

    public void setcod (String val) {
        this.cod = val;
    }

    
    public String getnombre() {
        return nombre;
    }

     public void setnombre (String val) {
        this.nombre = val;
    }

    public Categoria getUnaCategoria() {
        return unaCategoria;
    }

    public void setUnaCategoria(Categoria unaCategoria) {
        this.unaCategoria = unaCategoria;
    }

    public Marca getUnaMarca() {
        return unaMarca;
    }

    public void setUnaMarca(Marca unaMarca) {
        this.unaMarca = unaMarca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

       
   public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
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
