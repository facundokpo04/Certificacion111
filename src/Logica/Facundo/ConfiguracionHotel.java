package Logica.Facundo;

import Logica.Hotel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConfiguracionHotel implements Serializable {
   private double porcentConfirmacion;
    private int cantDiasAnulacion;
    @Id
    private Integer idConf;

    public ConfiguracionHotel() {
    }

    public ConfiguracionHotel(double porcentConfirmacion, int cantDiasAnulacion) throws Exception {
        this.porcentConfirmacion = porcentConfirmacion;
        this.cantDiasAnulacion = cantDiasAnulacion;
        Hotel.persistencia.insert(this);
    }

    public double getPorcentConfirmacion() {
        return porcentConfirmacion;
    }

    public void setPorcentConfirmacion(double porcentConfirmacion) {
        this.porcentConfirmacion = porcentConfirmacion;
    }

    public int getCantDiasAnulacion() {
        return cantDiasAnulacion;
    }

    public void setCantDiasAnulacion(int cantDiasAnulacion) {
        this.cantDiasAnulacion = cantDiasAnulacion;
    }

    public Integer getIdConf() {
        return idConf;
    }

    public void setIdConf(Integer idConf) {
        this.idConf = idConf;
    }
    
}
