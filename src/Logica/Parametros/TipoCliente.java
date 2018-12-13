package Logica.Parametros;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import Logica.Facundo.Cliente;
import Logica.Hotel;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
public class TipoCliente implements Serializable {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Integer codTipoCliente;
   private String nombre;
   @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   private Map<String,Cliente> clientes;


    public TipoCliente() {
       this.clientes=new HashMap();
     }

    public TipoCliente( String nombre) throws Exception {
        this();
    
        this.nombre = nombre.toUpperCase();
         Hotel.persistencia.insert(this);
    }

    public Integer getCodCliente() {
        return codTipoCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codTipoCliente = codCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map getClientes() {
        return this.clientes;
    }
    
    public void AgregarCliente(Cliente unCliente)  {
        this.clientes.put(unCliente.getDocumento(), unCliente);
            
    }

    private class clientes {

        public clientes() {
        }
    }

    @Override
    public String toString() {
        return ( this.nombre);
    }
      //agregado Facundo
     public Boolean isNombre(String nombre){
     return (this.getNombre().compareTo(nombre)==0);
   
   }
   public Map buscarClienteNac(Nacionalidad  unaNacionalidad){
     Map retorno = new HashMap();
     Cliente aux = null;
     
       
              
        for (Object unCliente :this.getClientes().values().toArray()) {
            aux=(Cliente)unCliente;
            if (aux.isNacionalida(unaNacionalidad.getNombre())) {
                retorno.put(aux.getDocumento(),aux);
            }
        }
        return retorno;
    }
   //fin Agregado
    
}
