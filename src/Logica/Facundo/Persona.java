package Logica.Facundo;

import Logica.Hotel;
import Logica.Parametros.TipoDocumento;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.DCB5D0EB-A320-66D4-9BE8-514930327D7C]
// </editor-fold> 
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Persona {
    @Id
    private String documento;
    @ManyToOne
    private TipoDocumento unTipoDoc;
    private String apellido;
    private String nombre;

    public Persona(String documento, TipoDocumento unTipoDoc, String apellido, String nombre) throws Exception {
        this.documento = documento;
        this.unTipoDoc = unTipoDoc;
        this.apellido = apellido.toUpperCase();
        this.nombre = nombre.toUpperCase();
       // Hotel.persistencia.insert(this);
    }
   
    
    public Persona () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.004D82DE-1795-A241-69DD-5622C7081416]
    // </editor-fold> 
    public String getApellido () {
        return apellido;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0F5A03CA-0877-BB97-8115-2833449DAF82]
    // </editor-fold> 
    public void setApellido (String val) {
        this.apellido = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.D6F0EF5D-24DB-99A7-67A6-7B9EEF8126A4]
    // </editor-fold> 
    public String getDocumento () {
        return documento;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.365A9279-5C2A-4C3B-C892-BE8BB6AD4152]
    // </editor-fold> 
    public void setDocumento (String val) {
        this.documento = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EE9F0215-A8BD-EF77-532C-B03A11C9FD7E]
    // </editor-fold> 
    public String getNombre () {
        return nombre;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.373BBDAD-08D9-35D7-02F5-89D34CC3FA78]
    // </editor-fold> 
    public void setNombre (String val) {
        this.nombre = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.029725C6-C664-6A94-635D-B127DAFD5B43]
    // </editor-fold> 
    public TipoDocumento getUnTipoDoc () {
        return unTipoDoc;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9392892E-9668-845E-F410-C093928DE485]
    // </editor-fold> 
    public void setUnTipoDoc (TipoDocumento val) {
        this.unTipoDoc = val;
    }

      @Override
    public String toString() {
        return this.getDocumento();
    }
    
}

