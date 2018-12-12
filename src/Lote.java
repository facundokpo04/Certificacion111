public class Lote {

    private Integer numero;

    private Float superficie;

    public Lote(Integer numero, Float superficie) {
        this.numero = numero;
        this.superficie = superficie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Float superficie) {
        this.superficie = superficie;
    }

    public Boolean asignarTipoSuelo(TipoSuelo unTipoSuelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
