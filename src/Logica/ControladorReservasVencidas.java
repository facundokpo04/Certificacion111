/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.Caja.Caja;
import Logica.Facundo.Reserva;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Facu
 */
@Entity
public class ControladorReservasVencidas {
    @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private int codControlador;
   @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
   private Map<Integer, Reserva> reservas;
    @OneToOne
    private Hotel miHotel;
    public ControladorReservasVencidas() {
        this.reservas=new HashMap<Integer, Reserva>();
        
    }

    public ControladorReservasVencidas(Hotel miHotel) throws Exception {
        this.miHotel = miHotel;
        Hotel.persistencia.insert(this);
    }

    public int getCodControlador() {
        return codControlador;
    }

    public Map<Integer, Reserva> getReservas() {
        return reservas;
    }
  
   
   public void agregarReserva(Reserva unaReserva) throws Exception{
   
     this.reservas.put(unaReserva.getNumReserva(), unaReserva);
        try {
            Hotel.persistencia.update(this);
        } catch (Exception ex) {
            throw new Exception("No se pudo Guardar la Reserva Vencidad");
        }
     
   }
    
}
