package Logica.Parametros;

import javax.persistence.Entity;

@Entity
public class PagoEfectivo extends Pago{

    public PagoEfectivo() {
    }

    public PagoEfectivo(Double importe, ModoPago modoPago) throws Exception {
        super(importe, modoPago);
    }
    
    
}
