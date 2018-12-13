package Logica.Parametros;

import Logica.Hotel;
import javax.persistence.Entity;

@Entity
public class PagoTarjetaCredito extends Pago{
    private Long numeroReferencia;

    public PagoTarjetaCredito() {
    }

    public PagoTarjetaCredito(Double importe, ModoPago modoPago) throws Exception {
        super(importe, modoPago);
    }

    public PagoTarjetaCredito(Long numeroReferencia, Double importe, ModoPago modoPago) throws Exception {
        this(importe, modoPago);
        this.numeroReferencia = numeroReferencia;
        Hotel.persistencia.insert(this);
    }

    public Long getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Long numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
    
}
