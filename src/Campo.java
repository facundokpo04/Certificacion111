public class Campo {

    private String nombre;

    private float superficie;

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

    public Boolean asignarLote(Lote unLote) {
        throw new UnsupportedOperationException("Not supported yet.");
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
