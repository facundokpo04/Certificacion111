package Logica.Parametros;

import Logica.Facundo.Cliente;
import Logica.Hotel;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


@Entity
public class TipoDocumento {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codTipo ;
    private String nombre;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<String,Cliente> clientes;

    public TipoDocumento() {
        clientes= new HashMap();
    }

    public TipoDocumento( String nombre) throws Exception {
        this();
       
        this.nombre = nombre.toUpperCase();
         Hotel.persistencia.insert(this);
    }

    public Integer getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(Integer codTipo) {
        this.codTipo = codTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void AgregarCliente(Cliente unCliente)  {
       this.clientes.put(unCliente.getDocumento(), unCliente);
            
    }
    
    @Override
    public String toString() {
        return  this.nombre;
    }
}
