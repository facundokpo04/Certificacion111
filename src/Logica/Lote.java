package Logica;

public class Lote {

    private Integer id;
    private Integer numero;
    private Float superficie;
    private TipoSuelo tipoSuelo;

    public Lote(Integer numero, Float superficie) {
        this.id = 0;
        this.numero = numero;
        this.superficie = superficie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TipoSuelo getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(TipoSuelo tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

}
