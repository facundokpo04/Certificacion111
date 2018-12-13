package Logica.Parametros;

import Logica.Hotel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MedioLlegada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CodMedio;
    private String Nombre;

    public MedioLlegada() {
    }

    public MedioLlegada(String Nombre) throws Exception {
        this.Nombre = Nombre.toUpperCase();
        Hotel.persistencia.insert(this);
    }

    public Integer getCodMedio() {
        return CodMedio;
    }

    public void setCodMedio(Integer CodMedio) {
        this.CodMedio = CodMedio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String toString() {
        return this.Nombre;

    }
}