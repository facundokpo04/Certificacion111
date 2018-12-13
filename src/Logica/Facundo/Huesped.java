package Logica.Facundo;

import Logica.Hotel;
import Logica.Parametros.Nacionalidad;
import Logica.Parametros.TipoDocumento;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Huesped{
    @Id
    private String documento;
    private String apellido;
    private String nombre;
   
    @OneToOne
    private TipoDocumento unTipoDoc;
   
    @OneToOne
    Nacionalidad unaNacionalidad;

    public Huesped() {
    }

   

    public Huesped(String documento, TipoDocumento unTipoDoc, String apellido, String nombre, Nacionalidad unaNacionalidad) throws Exception {
        this.documento = documento;
        this.unTipoDoc = unTipoDoc;
        this.apellido = apellido;
        this.nombre = nombre;
        this.unaNacionalidad = unaNacionalidad;
         Hotel.persistencia.insert(this);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoDocumento getUnTipoDoc() {
        return unTipoDoc;
    }

    public void setUnTipoDoc(TipoDocumento unTipoDoc) {
        this.unTipoDoc = unTipoDoc;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nacionalidad getUnaNacionalidad() {
        return unaNacionalidad;
    }

    public void setUnaNacionalidad(Nacionalidad unaNacionalidad) {
        this.unaNacionalidad = unaNacionalidad;
    }

    @Override
    public String toString() {
        return this.getDocumento();
    }

    
    
}
