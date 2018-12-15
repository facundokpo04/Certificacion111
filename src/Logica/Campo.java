package Logica;

import java.util.LinkedList;
import java.util.List;

public class Campo {

    private Integer id;
    private String nombre;
    private float superficie;
    private EstadoCampo estado;
    private List<Lote> lotes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public Campo(String nombre, float superficie) {
        this.nombre = nombre;
        this.superficie = superficie;
        this.lotes = new LinkedList<Lote>();
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public EstadoCampo getEstado() {
        return estado;
    }

    public void setEstado(EstadoCampo estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public Lote asignarLote(Lote unLote) throws Exception {
        if (existeLote(unLote.getNumero())) {
            throw new Exception("El Lote: " + unLote.getNumero() + " ya existe");

        } else if (!this.calcularSuperficieDisponible(unLote.getSuperficie())) {
            throw new Exception("No Hay superficie disponible para el lote ");

        } else {
            this.lotes.add(unLote);
            return unLote;
        }
    }
  
    
     public void removerLote(Lote unLote) throws Exception {
        try {
            this.lotes.remove(unLote);
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Boolean calcularSuperficieDisponible(Float superficie) {
        float supTotal = 0;

        List<Lote> l = this.getLotes();
        for (Lote unLote : l) {
            supTotal = unLote.getSuperficie() + supTotal;
        }
        return (((supTotal + superficie) <= this.superficie));
    }

    public Lote obtenerLote(Integer numero) {
        boolean existe = false;
        List<Lote> l = this.getLotes();
        for (Lote unLote : l) {
            if (unLote.getNumero() == numero) {
                return unLote;
            }
        }
        return null;
    }
    
    
    public Boolean existeLote(Integer numero) {
        boolean existe = false;
        List<Lote> l = this.getLotes();
        for (Lote unLote : l) {
            if (unLote.getNumero() == numero) {
                existe = true;
            }
        }
        return existe;
    }
}
