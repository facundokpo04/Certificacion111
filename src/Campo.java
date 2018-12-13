
import java.util.LinkedList;
import java.util.List;

public class Campo {

    private String nombre;

    private float superficie;
    private List<Lote> lotes;

    public Campo(String nombre, float superficie) {
        this.nombre = nombre;
        this.superficie = superficie;
        this.lotes = new LinkedList<Lote>();
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
        if (existeLote(unLote.getNumero()) != null) {
            throw new Exception("La Localidad: " + unLote.getNumero() + " ya existe");
        } else {
            this.lotes.add(unLote);
       
            return unLote;
        }
    }

    public Float calcularSuperficieDisponible(Float superficie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean asignarEstado(EstadoCampo estado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean existeLote(Integer numero) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
