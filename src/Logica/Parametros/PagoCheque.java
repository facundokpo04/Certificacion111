package Logica.Parametros;

import Logica.Hotel;
import javax.persistence.Entity;

@Entity
public class PagoCheque extends Pago{
    private Long numeroReferencia;
    private String entidadBancaria;

    public PagoCheque() {
    }

    public PagoCheque(Double importe, ModoPago modoPago) throws Exception {
        super(importe, modoPago);
    }

    public PagoCheque(Long numeroReferencia,String entidad, Double importe, ModoPago modoPago ) throws Exception {
        this(importe, modoPago);
        this.numeroReferencia = numeroReferencia;
        this.entidadBancaria = entidad.toUpperCase();
        Hotel.persistencia.insert(this);
    }

    public Long getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Long numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(String entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }
    
}
