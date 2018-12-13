package Logica.Parametros;

import Logica.Hotel;
import javax.persistence.Entity;

@Entity
public class PagoDeposito  extends Pago{
    private Long cbu;
    private String NombreTitular;
    private Long numeroOperacion;
    private String entidadBancaria;

    public PagoDeposito() {
    }

    public PagoDeposito(Double importe, ModoPago modoPago) throws Exception {
        super(importe, modoPago);
    }

    public PagoDeposito(Long cbu, String NombreTitular, Long numeroOperacion, String entidadBancaria, Double importe, ModoPago modoPago) throws Exception {
        this(importe, modoPago);
        this.cbu = cbu;
        this.NombreTitular = NombreTitular.toUpperCase();
        this.numeroOperacion = numeroOperacion;
        this.entidadBancaria = entidadBancaria.toUpperCase();
        Hotel.persistencia.insert(this);
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public String getNombreTitular() {
        return NombreTitular;
    }

    public void setNombreTitular(String NombreTitular) {
        this.NombreTitular = NombreTitular;
    }

    public Long getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(Long numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(String entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }
    
    
}
