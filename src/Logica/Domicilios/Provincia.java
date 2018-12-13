package Logica.Domicilios;


import Logica.Hotel;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Provincia extends Observable implements Comparable<Provincia>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Pais paisPadre;
    private String nombre;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Localidad> localidades;

    public Provincia(String nombre, Pais padre) throws Exception {
        this.id = 0;
        this.nombre = nombre;
        this.paisPadre = padre;
        this.localidades = new LinkedList<Localidad>();
        Hotel.persistencia.insert(this);
    }

    public Provincia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pais getPaisPadre() {
        return paisPadre;
    }

    public void setPaisPadre(Pais paisPadre) {
        this.paisPadre = paisPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Localidad altaLocalidad(String nombreLocalidad) throws Exception {
        if (obtenerLocalidad(nombreLocalidad) != null) {
            throw new Exception("La Localidad: " + nombreLocalidad + " ya existe");
        } else {
            Localidad unaLocalidad = new Localidad(nombreLocalidad, this);
            this.localidades.add(unaLocalidad);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers(unaLocalidad);
            return unaLocalidad;
        }


    }

    public void bajaLocalidad(Localidad unaLocalidad) throws Exception {
        if (existeLocalidad(unaLocalidad)) {
            this.localidades.remove(unaLocalidad);
            Hotel.persistencia.update(this);
            Hotel.persistencia.delete(unaLocalidad);
        } else {
            throw new Exception("La Localidad a ser eliminada no existe en el Sistema");
        }
    }

    public Localidad modificarLocalidad(Localidad unaLocalidad, String nombreLocalidad) throws Exception {
        nombreLocalidad = nombreLocalidad.trim();
        if (obtenerLocalidad(nombreLocalidad) != null) {
            throw new Exception("La modificacion ingresada corresponde a otra localidad exitente en el sistema");
        } else {
            if (existeLocalidad(unaLocalidad) && (unaLocalidad.getNombre().equals(nombreLocalidad) != true)) {
                unaLocalidad.setNombre(nombreLocalidad);
                Hotel.persistencia.update(this);
            }
            return unaLocalidad;
        }
    }

    public boolean existeLocalidad(Localidad localidad) {
        boolean existe = false;
        List<Localidad> p = this.getLocalidades();
        for (Localidad unaLocalidad : p) {
            if (unaLocalidad.equals(localidad)) {
                existe = true;
            }
        }
        return existe;
    }

    public Localidad obtenerLocalidad(String nombreLocalidad) {
        Localidad devolver = null;
        for (Localidad unaLocalidad : this.getLocalidades()) {
            if (unaLocalidad.getNombre().equals(nombreLocalidad)) {
                devolver = unaLocalidad;
            }
        }
        return devolver;

    }

    @Override
    public boolean equals(Object unObjeto) {
        boolean igual = false;
        if (unObjeto instanceof Provincia) {
            if (this.getId().equals(((Provincia) unObjeto).getId())) {
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
        Object fila[] = {this, this.getPaisPadre()};
        return fila;
    }

    @Override
    public int compareTo(Provincia t) {
        if (this.paisPadre.getNombre().compareToIgnoreCase(t.getPaisPadre().getNombre()) == 0) {
            return this.nombre.compareToIgnoreCase(t.getNombre());
        } else {
            return this.paisPadre.getNombre().compareToIgnoreCase(t.getPaisPadre().getNombre());
        }

    }
}
