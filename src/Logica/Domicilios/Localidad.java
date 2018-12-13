package Logica.Domicilios;


import Logica.Hotel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Localidad implements Comparable<Localidad>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
    @ManyToOne 
    private Provincia provinciaPadre;
    private String nombre;

    public Localidad() {
    }

    public Localidad(String nombre, Provincia padre) throws Exception {
        this.id = 0;
        this.provinciaPadre = padre;
        this.nombre = nombre;
        Hotel.persistencia.insert(this);
    }

    public Provincia getProvinciaPadre() {
        return provinciaPadre;
    }

    public void setProvinciaPadre(Provincia provinciaPadre) {
        this.provinciaPadre = provinciaPadre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object unObjeto) {
        boolean igual = false;
        if (unObjeto instanceof Localidad) {
            if (this.getId().equals(((Localidad) unObjeto).getId())) {
                igual = true;
            }
        }
        return igual;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    public Object[] toVector() {
        Object fila[] = {this, this.getProvinciaPadre(), this.getProvinciaPadre().getPaisPadre()};
        return fila;
    }

    @Override
    public int compareTo(Localidad t) {
        if (this.provinciaPadre.getNombre().compareToIgnoreCase(t.getProvinciaPadre().getNombre()) == 0) {
            return this.nombre.compareToIgnoreCase(t.getNombre());
        } else {
            return this.provinciaPadre.getNombre().compareToIgnoreCase(t.getProvinciaPadre().getNombre());
        }
    }
}
