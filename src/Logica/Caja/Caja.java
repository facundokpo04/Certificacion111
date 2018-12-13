package Logica.Caja;

import Logica.Parametros.Concepto;
import Logica.Hotel;
import Logica.Seguridad.Usuario;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;


@Entity
public class Caja extends Observable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @OneToOne
    private Diario unDiario;
    @OneToMany
    private List<Diario> diarios;
    @OneToMany
    private List<Concepto> conceptos;

    public Caja() {
    }


    public Caja(String nombre) throws Exception {
        this.nombre = nombre;
        this.diarios = new LinkedList();
        this.conceptos = new LinkedList();
        Hotel.persistencia.insert(this);
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

    public List<Diario> getDiario() {
        return diarios;
    }

    public void setDiario(List<Diario> diario) {
        this.diarios = diario;
    }

      public Diario getUnDiario() {
        return this.unDiario;
    }
    
    public List<Concepto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<Concepto> conceptos) {
        this.conceptos = conceptos;
    }

    private boolean existeDiario(Diario unDiario) {
        boolean existe = false;
        return existe;
    }

    private boolean existeConcepto(Concepto unCon) {
        boolean existe = false;
        for (Concepto oneConcept : this.getConceptos()) {
            if (unCon.equals(oneConcept)) {
                existe = true;
            }
        }
        return existe;
    }

    public Diario abrirDiario(Double saldoInicial, Double saldoFinal, String horaApertura, Date fechaApertura,Usuario unUsuario) throws Exception {
        List<Diario> DiariosCaja= this.getDiario();
            if(!(DiariosCaja.isEmpty())){
                for (Diario diario : DiariosCaja) {
                         if (diario.getUltimo()) {
                            diario.setUltimo(false);
                         }
                }
            }
        
        Diario nuevoDiario = new Diario(saldoInicial, saldoFinal, horaApertura, fechaApertura,unUsuario);
        this.diarios.add(nuevoDiario);
                    
         
                                
        
        Hotel.persistencia.update(this);
        return nuevoDiario;
    }

    public void cerrarDiario(Double saldoFinal, String horaCierre,Date fechaCierre, Diario unDiario) throws Exception {
        if (unDiario.isCerrado() != false) {
            throw new Exception("El Diario que quiere cerrar ya ha sido cerrado!");
        }
        unDiario.setHoraCierre(horaCierre);
        unDiario.setSaldoFinal(saldoFinal);
        unDiario.setFechaCierre(fechaCierre);
        unDiario.setUltimo(true);
        unDiario.setCerrado(true);
               
        Hotel.persistencia.update(this);

    }
    
    
    public Diario ultimoDiario(){
    Diario ultimo=null;
    List<Diario> DiariosCaja= getDiario();
        if(!(DiariosCaja.isEmpty())){
            for (Diario l : DiariosCaja) {
                  if (!(l.isCerrado())) {
                     ultimo=l;
                     break;
                  }
           }
     }
    return ultimo;
    }
    
    public Concepto agregarConcepto(String detalle, String tipo, String descripcion ) throws Exception{
        boolean existe = false;
        Concepto nuevoConcepto = null;
        for (Concepto unConcepto : this.getConceptos()) {
            if (unConcepto.getDetalle().toUpperCase().equals(detalle.toUpperCase()) && unConcepto.getTipo().toUpperCase().equals(tipo.toUpperCase())) {
                existe = true;
                break;
                }
            
        }
    
        
        if (!existe) {
            nuevoConcepto = new Concepto(tipo, detalle,descripcion);
            this.conceptos.add(nuevoConcepto);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
           if (nuevoConcepto.getDetalle().equals("Apertura de Caja") || nuevoConcepto.getDetalle().equals("Sobrante") || nuevoConcepto.getDetalle().equals("Faltante")){
            }
           else{
               }
        }
        return nuevoConcepto;
        
    }
    
    public Concepto editarConcepto(int cod, String detalle, String tipo, String desc) throws Exception{
         Concepto retorno = null;
         if(this.existeConcepto(cod)){
         Concepto concepto=null;
         boolean existe = false;
        for (Concepto unConcepto : this.getConceptos()) {
            if (unConcepto.getDetalle().toUpperCase().equals(detalle.toUpperCase()) && unConcepto.getTipo().toUpperCase().equals(tipo.toUpperCase())) {
                existe = true;
                break;
                }
            
        }
        
        for (Concepto unConcepto : this.getConceptos()) {
            if (unConcepto.getId()==cod) {
                concepto=unConcepto;
                break;
                }
            
        }
        
        if(existe==false){
            concepto.setDetalle(detalle);
            concepto.setTipo(tipo);
            concepto.setDescripcion(desc);
            retorno=concepto;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
}
        else{JOptionPane.showMessageDialog(null, "Ya existe el concepto.");}
           }
        else 
        {
          JOptionPane.showMessageDialog(null,"El Concepto no Existe");
        }
       Hotel.persistencia.update(this);
        return retorno;
    }
    

    public Boolean existeConcepto(int cod){
      boolean existe = false;
        for (Concepto concept : this.getConceptos()) {
            if (concept.getId().equals(cod)) {
                existe = true;
            }
        }
        return existe;
     }
    

    public boolean eliminarConcepto(Concepto unConcepto) throws Exception {
        boolean auxx=false;
        if (!existeConcepto(unConcepto.getId())) {
            JOptionPane.showMessageDialog(null,"El Concepto que desea eliminar no existe.");
        } else {
            boolean aux=true;
            List<Diario> losDiarios = this.getDiario();
            for (Diario unDiario : losDiarios) {
                Iterator it = unDiario.getMovimientos().entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry ent = (Map.Entry)it.next();
                    Movimiento unMovimiento = (Movimiento)ent.getValue();
                    if (unMovimiento.getConcepto().equals(unConcepto.getDetalle())) {
                        aux=false;
                    } else { }                
            } 
           
                }
                if(aux){
                        this.conceptos.remove(unConcepto);
                        Hotel.persistencia.update(this);
                        Hotel.persistencia.delete(unConcepto);
                        setChanged();
                        notifyObservers();
                        auxx=true;
                        }
                else{JOptionPane.showMessageDialog(null,"No se puede eliminar el concepto");}
                             }
        return auxx;
        }

       
    
   }
