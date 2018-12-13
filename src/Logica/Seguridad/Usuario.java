package Logica.Seguridad;

import Logica.Facundo.Persona;
import Logica.Hotel;
import Logica.Parametros.TipoDocumento;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Persona unaPersona;
    private String telefono;
    private String domicilio;
    private String pasword;
    private String usserame;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creadoDia;
    private String creadoHora;
    private boolean estado;
    @OneToOne
    private Rol rol;

    
    
    public Usuario() {
        
    }
  
    public Usuario(String dni,TipoDocumento tipoDoc,String apellido, String nombre,String telefono, String domicilio, String pasword, String usserame, Date creadoDia, String creadoHora,Rol rol,Hotel unHotel) throws Exception {
        this.unaPersona=unHotel.agregarPersona(dni, tipoDoc, apellido, nombre);
        this.telefono=telefono;
        this.domicilio=domicilio.toUpperCase();        
        this.pasword = pasword;
        this.usserame = usserame;
        this.creadoDia = creadoDia;
        this.creadoHora = creadoHora;
        this.rol = rol;
        this.estado=true;
        Hotel.persistencia.insert(this);
    }

    public Usuario(Persona unaPersona,String telefono, String domicilio, String pasword, String usserame, Date creadoDia, String creadoHora,Rol rol) throws Exception {
        this.unaPersona=unaPersona;
        this.telefono=telefono;
        this.domicilio=domicilio.toUpperCase();        
        this.pasword = pasword;
        this.usserame = usserame;
        this.creadoDia = creadoDia;
        this.creadoHora = creadoHora;
        this.rol = rol;
        this.estado=true;
        Hotel.persistencia.insert(this);
    }

    public void setUnaPersona(Persona unaPersona) {
        this.unaPersona = unaPersona;
    }

    public Persona getUnaPersona() {
        return unaPersona;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol=rol;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getUsserame() {
        return usserame;
    }

    public void setUsserame(String usserame) {
        this.usserame = usserame;
    }

    public Date getCreadoDia() {
        return creadoDia;
    }

    public void setCreadoDia(Date creadoDia) {
        this.creadoDia = creadoDia;
    }

    public String getCreadoHora() {
        return creadoHora;
    }

    public void setCreadoHora(String creadoHora) {
        this.creadoHora = creadoHora;
    }

   
    public boolean getEstado() {
        return estado;
    }

    public void setEstado (boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.unaPersona.getDocumento();
    }
    
}
