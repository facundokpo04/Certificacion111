package Logica.Facundo;

import Exepciones.ReservasExepcion;
import Logica.Hotel;
import Logica.Parametros.Pago;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Reserva extends Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numReserva;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEntrada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSalida;
    private String horaLlegada;
    private long cantDias;
    private Integer cantPersonas;
    private String medioLlegada;
    private Double costo;
    private String medioReserva;
    @ManyToOne
    private Estado unEstado;
    @ManyToOne
    private Cliente unCliente;
    @OneToOne
    private Tarifa unaTarifa;
    @ManyToOne
    private Habitacion unaHabitacion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Pago> pagos;

    public Reserva() {
        this.pagos = new HashMap();
    }

    public Reserva(Date fechaEntrada, Date fechaSalida, String horaLlegada, int cantPersonas, String medioLlegada, String medioReserva) throws Exception {
        this();
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaLlegada = horaLlegada;
        this.cantDias = ((fechaSalida.getTime() - fechaEntrada.getTime()) / (24 * 60 * 60 * 1000));
        this.cantPersonas = cantPersonas;
        this.medioLlegada = medioLlegada;
        this.medioReserva = medioReserva;
        Hotel.persistencia.insert(this);

    }

    public Map<Integer, Pago> getPagos() {
        return pagos;
    }

    public boolean tienePersonaExtra() {
        if (cantPersonas > this.unaHabitacion.getTipoHabitacion().getCapacidadEstandar()) {
            return true;
        } else {
            return false;
        }



    }

    public Tarifa getUnaTarifa() {
        return unaTarifa;
    }

    public long getCantDias() {
        return cantDias;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.80E670AF-2BC1-435B-9E10-234FD44B0211]
    // </editor-fold> 
    public void setCantDias(int val) {
        this.cantDias = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.834DEDC2-ED6C-8EAD-54B9-40633B05DCF7]
    // </editor-fold> 
    public Integer getCantPersonas() {
        return cantPersonas;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.42BEEB6A-BDA2-BDD1-B9D8-2946ADF3B88B]
    // </editor-fold> 
    public void setCantPersonas(int val) {
        this.cantPersonas = val;
        this.calcularCostoTotal();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.725D725B-28D4-4332-28FE-B422EBCFDA92]
    // </editor-fold> 
    public Double getCosto() {
        return costo;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.338A8FA7-DD80-4D4E-08D4-CF87A7F13E6D]
    // </editor-fold> 
    public void setCosto(Double val) {
        this.costo = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E7413751-CEE4-6237-502E-82593D7D8C9B]
    // </editor-fold> 
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1DD2871B-731B-500B-3742-6DFB6839F8B0]
    // </editor-fold> 
    public void setFechaEntrada(Date val) {
        this.fechaEntrada = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C9F3F38D-3761-79D0-1ABA-DB46B2F7B9D7]
    // </editor-fold> 
    public Date getFechaSalida() {
        return fechaSalida;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E79BC595-B07C-8218-B727-242598A5986B]
    // </editor-fold> 
    public void setFechaSalida(Date val) {
        this.fechaSalida = val;
        this.cantDias = ((fechaSalida.getTime() - fechaEntrada.getTime()) / (24 * 60 * 60 * 1000));
        this.calcularCostoTotal();

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.747CAA96-F754-01B8-D63C-F86C1F196526]
    // </editor-fold> 
    public String getHoraLlegada() {
        return horaLlegada;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.AEE4673E-0191-74F2-28E4-3D405E17EA01]
    // </editor-fold> 
    public void setHoraLlegada(String val) {
        this.horaLlegada = val;
    }

    public String getMedioLlegada() {
        return medioLlegada;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0FB5B618-E874-C650-E7FA-EDCD4AEF919D]
    // </editor-fold> 
    public void setMedioLlegada(String val) {
        this.medioLlegada = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.806661A6-7689-CB24-56ED-92CB5B2BFABE]
    // </editor-fold> 
    public String getMedioReserva() {
        return medioReserva;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9651C232-20DB-058A-3AC6-CB0F120B28A6]
    // </editor-fold> 
    public void setMedioReserva(String val) {
        this.medioReserva = val;
    }

    public Integer getNumReserva() {
        return numReserva;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.FDE2B3E4-C90D-0A27-A6AA-F932CC40515B]
    // </editor-fold> 
    public void setNumReserva(int val) {
        this.numReserva = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E3E6F4AB-1E9E-387D-5389-CD881739C384]
    // </editor-fold> 
    public Estado getUnEstado() {
        return unEstado;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DD3F1ACD-5515-44BC-D920-90714FDE584F]
    // </editor-fold> 
    public void setUnEstado(Estado val) {
        this.unEstado = val;
    }

    public void setUnaTarifa(Tarifa unaTarifa) {
        this.unaTarifa = unaTarifa;
        this.calcularCostoTotal();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8C19DFF8-E80B-5835-C041-C4D5937AEB6D]
    // </editor-fold> 
    public void AgregarCliente(Cliente unCliente) {
        this.unCliente = unCliente;
        unCliente.agregarReserva(this);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9F32B133-01E4-889D-2E05-88F9099ED5CB]
    // </editor-fold> 
    public void agregarHabitacion(Habitacion unaHabitacion) {
        this.unaHabitacion = unaHabitacion;
        unaHabitacion.agregarReserva(this);
        // this.calcularCostoTotal();

    }

    public void cambiarHabitacion(Habitacion unaHabitacion) throws Exception {
        this.getHabitacion().quitarReserva(this);
        this.unaHabitacion = unaHabitacion;
        unaHabitacion.agregarReserva(this);

    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.97EB48A1-E837-6B76-1C44-4493E2A86296]
    // </editor-fold> 

    public void agregarTarifa(Tarifa unaTarifa) {
        this.unaTarifa = unaTarifa;
        this.calcularCostoTotal();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D92F3AC4-E662-AC19-AFD4-30AE726098AE]
    // </editor-fold> 
    public void setEstado(Estado unEstado) {
        this.unEstado = unEstado;
        unEstado.agregarReserva(this);

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A84EF4AD-CBE6-4E00-37CB-915DE9E21F89]
    // </editor-fold> 
    public Habitacion getHabitacion() {
        return this.unaHabitacion;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BB67BDBC-FC04-7B84-D953-9E5B073393F4]
    // </editor-fold> 
    public Double calcularCostoTotal() {
        Double costodia;
        costodia = this.unaTarifa.costoxdia(this.cantPersonas, this.getHabitacion().getTipoHabitacion());


        this.setCosto(costodia * this.cantDias);
        return this.getCosto();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BB67BDBC-FC04-7B84-D953-9E5B073393F4]
    // </editor-fold> 
    public Double calcularCostoxDia() {
        Double costoR;
        costoR = unaTarifa.getPrecio();
        int cantp = this.cantPersonas;
        while (cantp > this.unaHabitacion.getTipoHabitacion().getCapacidadEstandar()) {
            costoR += unaTarifa.getPrecioPerExt();
            cantp--;


        }

        return costoR;
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.56558E6D-825D-121C-A053-72EC54C6629A]
    // </editor-fold> 

    public boolean isTitular(String documento) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4BEB910E-8B81-C998-ED46-3516B6C290F2]
    // </editor-fold> 
    public Cliente getCliente() {
        return this.unCliente;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.64281608-C89C-8D8E-6EE8-453E4D69009B]
    // </editor-fold> 
    public boolean isHabitacion(String nmrHabitacion) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6FF75724-AFDE-602B-3AB8-04F41E8EC69D]
    // </editor-fold> 
    public Double agregarUnPago(Pago unPago) throws ReservasExepcion {

        Double deuda = this.costo - this.getTotalPagado();

        if (this.getTotalPagado() + (unPago.getImporte()) <= this.costo) {
            this.pagos.put(unPago.getNumPago(), unPago);
            this.setChanged();
            this.notifyObservers();



        } else {
            throw new ReservasExepcion("La Entrega Supera El Costo total de la Reserva" + "Falta Pagar: $" + deuda);
        }



        return (this.costo - this.getTotalPagado());

    }

    public Double getTotalPagado() {
        Double tot = new Double(0);

        if (!this.getPagos().isEmpty()) {
            Double aux = null;
            Map retorno = new HashMap();
            Iterator iterador = this.pagos.values().iterator();
            while (iterador.hasNext()) {
                aux = ((Pago) iterador.next()).getImporte();
                tot += aux;
            }


        }



        return tot;
    }

    public Double getDueda() {
        Double tot = this.costo - this.getTotalPagado();
        return tot;


    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FFE3139B-9CCB-77AA-B544-C2A01054F938]
    // </editor-fold> 
    public boolean estaVencida(Date FechaVencida) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9C50CE90-FC68-A121-859E-41530BD6DA8D]
    // </editor-fold> 
    public boolean isCaduca() {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DA193BAF-15DD-4FB8-01D0-89E511AA3A80]
    // </editor-fold> 
    public void activarReserva() {
    }

    public boolean isReservada(Date FechaEntrada, Date fechaSalida) {
            if (this.isActiva()) {
        if (FechaEntrada.compareTo(this.fechaSalida) >= 0 || fechaSalida.compareTo(this.fechaEntrada) <= 0) {
            return false;
        } else {
            return true;
        }
            }
             else {
            return false;
        }


    }

    public boolean isActiva() {
        return (this.isPendiente() || this.isConfirmada());
    }

    public boolean isReservada(Date fecha) {

        if (this.isActiva()) {
            if (fecha.compareTo(this.fechaEntrada) >= 0 && fecha.compareTo(this.fechaSalida) < 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "" + this.numReserva + " " + this.getCliente().getApellido() + "," + this.getCliente().getNombre();
    }

    public boolean isPendiente() {
        return this.unEstado.is("Pendiente");
    }

    void removerCliente() {
        this.unCliente = null;
    }

    public Pago eliminarPago(Integer nmrPago) {

        return (this.pagos.remove(nmrPago));

    }

    public boolean isTomada() {
        return this.unEstado.is("Tomada");
    }

    public void cambiarCliente(Cliente unCliente) throws Exception {

        this.getCliente().quitarReserva(this);
        this.unCliente = unCliente;
        unCliente.agregarReserva(this);

    }

    public void cambiarEstado(Estado unEstado) throws Exception {

        this.unEstado.quitarReserva(this);
        Hotel.persistencia.update(this.unEstado);
        this.setEstado(unEstado);
        Hotel.persistencia.update(unEstado);
    }
//agregado Facundo
    public Boolean isConfirmable(int porcentaje) {



        return ((this.calcularCostoTotal() * porcentaje) / 100 < this.getTotalPagado());

    }

//
  
    public Reserva caducar(Hotel unHotel) throws Exception{
        
      this.unaHabitacion.quitarReserva(this);
//      this.unCliente.quitarReserva(this);
      Estado unEstado=unHotel.getEstadoReserva(4);
      this.cambiarEstado(unEstado);
      Hotel.persistencia.update(this);
       
      
      return null;
     
    }
    
    public boolean isConfirmada() {
        return this.unEstado.is("Confirmada");
    }

    public boolean isNumeroReserva(int nmrReserva) {
        return this.numReserva == nmrReserva;
    }

    public boolean saleHoy(Date fecha) {
        return this.fechaSalida.compareTo(fecha) == 0;
    }

    public boolean entraHoy(Date fecha) {
        return this.fechaEntrada.compareTo(fecha) == 0;
    }
}
