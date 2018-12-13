/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Facundo;

import Logica.Hotel;
import Logica.Parametros.TipoHabitacion;
import Presentacion.Seguridad.ControlAcceso;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Facu
 */
@Entity
public class GrupoTarifas {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codGrupTarifa;  
    private String nombre;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Collection<Tarifa> tarifas;

    public GrupoTarifas() {
          this.tarifas=new LinkedList();
    }

    public GrupoTarifas(String nombre) throws Exception {
        this();
        this.nombre = nombre;
        Hotel.persistencia.insert(this);
    }

    public Collection<Tarifa> getTarifas() {
        return tarifas;
    }

    public int getCodGrupTarifa() {
        return codGrupTarifa;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    
    
    public void agregarTarifa(TipoHabitacion tiphab , double precio,double precioPere)
     {
        try {
            Tarifa tar1= new Tarifa(this.nombre, null, null, precio, precioPere,tiphab);
            tiphab.agregarTarifa(tar1);
            Hotel.persistencia.update(tiphab);
            this.tarifas.add(tar1);
            Hotel.persistencia.update(this);
                    
                 
            
            
            
        } catch (Exception ex) {
            
            
        }
        

      }
    
      public void modificarTarifa(TipoHabitacion tiphab , double precio,double precioPere)
     {
          Tarifa tar1= this.buscarTarifa(tiphab);
          tar1.setPrecio(precio);
          tar1.setPrecioPerExt(precioPere);
         
        try {
         
            Hotel.persistencia.update(this);
                    
                 
            
            
            
        } catch (Exception ex) {
            
            
        }
        

      }
    
    public Tarifa buscarTarifa(int codTarifa){
        Tarifa aux = null;
        Tarifa retorno = null;
        Iterator iterador = this.tarifas.iterator();
        while (iterador.hasNext() && aux == null) {
            aux = (Tarifa) iterador.next();
            if (aux.isTarifa(codTarifa)) {
                retorno = aux;
            }   
        }
        return retorno;
    }
      public Tarifa buscarTarifa(TipoHabitacion unTipoHabitacion){
        Tarifa aux = null;
        Tarifa retorno = null;
        Iterator iterador = this.tarifas.iterator();
        while (iterador.hasNext() && retorno == null) {
            aux = (Tarifa) iterador.next();
            if (aux.isTipoHab(unTipoHabitacion)) {
                retorno = aux;
            }   
        }
        return retorno;

    
    
    }
    public boolean isTarifa(String nombre){
    
      return (this.nombre.compareToIgnoreCase(nombre)==0);
    }
  

    @Override
    public String toString() {
        return this.nombre;//To change body of generated methods, choose Tools | Templates.
    }
    
    
}

