package Logica;

import Logica.Facundo.Cliente;
import Logica.Facundo.Tarifa;
import Logica.Parametros.TipoDocumento;
import Logica.Parametros.TipoHabitacion;
import Logica.Parametros.TipoCliente;
import Logica.Facundo.Habitacion;
import Logica.Facundo.Reserva;
import Logica.Parametros.unidadMedida;
import Logica.Parametros.EstadoHabitacion;
import Logica.Parametros.Nacionalidad;
import Logica.Parametros.Concepto;
import Logica.Parametros.Servicio;
import Logica.Parametros.MedioLlegada;
import Logica.Parametros.Categoria;
import Logica.Parametros.ModoPago;
import Logica.Parametros.Articulo;
import Logica.Caja.Caja;
import Exepciones.ClienteExepcion;
import Exepciones.ReservasExepcion;
import Logica.Auditoria.Auditoria;
import Logica.Caja.Diario;
import Logica.Domicilios.Direccion;
import Logica.Domicilios.Localidad;
import Logica.Domicilios.Pais;
import Logica.Domicilios.Provincia;
import Logica.Facundo.ConfiguracionHotel;
import Logica.Facundo.Estadia;
import Logica.Facundo.Estado;
import Logica.Facundo.GrupoTarifas;
import Logica.Facundo.Huesped;
import Logica.Facundo.Persona;
import Logica.Mantenimiento.Articulos_Compra;
import Logica.Mantenimiento.CompraMantenimiento;
import Logica.Parametros.FormaPago;
import Logica.Parametros.Marca;
import Logica.Parametros.Numero;
import Logica.Parametros.Pago;
import Logica.Parametros.PagoCheque;
import Logica.Parametros.PagoDeposito;
import Logica.Parametros.PagoTarjetaCredito;
import Logica.Parametros.Producto;
import Logica.Parametros.Proveedor;
import Logica.Parametros.UtilizadoPara;
import Logica.Seguridad.Rol;
import Logica.Seguridad.Usuario;
import Logica.Servicio.Cargo;
import Logica.Servicio.Compra;
import Logica.Servicio.Productos_Compra;
import Logica.Servicio.Venta;
import Persistencia.Persistencia;
import Presentacion.Seguridad.ControlAcceso;
import Presentacion.Seguridad.StringMD;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

@Entity
public class Hotel extends Observable {

    public static Persistencia persistencia;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String email;
    @Id
    private String cuit;
    @OneToOne
    public Foto logo = null;
    @OneToOne
    public Foto logo2 = null;
    private int porcentajeVenta = 50;
    @OneToOne
    private Caja unaCaja;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Cliente> clientes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, TipoCliente> tiposClientes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, TipoDocumento> tiposDocumento;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Nacionalidad> nacionalidades;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Map<Integer, Reserva> reservas;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, TipoHabitacion> tiposHabitacion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Habitacion> habitaciones;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Estado> estadosReserva;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, ModoPago> modosPagos;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, unidadMedida> unidadesMedida;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, MedioLlegada> mediosLlegada;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Articulo> articulos;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Servicio> servicios;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Categoria> categorias;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, EstadoHabitacion> estados;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Usuario> usuarios;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Rol> roles;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Map<String, Persona> personas;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Auditoria> auditorias;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Proveedor> proveedores;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Producto> productos;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Marca> marcas;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Compra> compras;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, UtilizadoPara> utilidades;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, CompraMantenimiento> comprasMant;
    @OneToOne
    private ConfiguracionHotel unaConfiguracionHotel;
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    private Map<Integer,Estadia> estadias;
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    private Map<Integer,Cargo> cargos;
    
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    private Map<Integer,Tarifa> tarifas;

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    private Map<Integer,Venta> ventas;
    
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    private Map<String,FormaPago> formasPago;
     @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Map<String,Huesped> huespedes;
    
    private Integer porcentajeConfirmacion = 10;
    private Integer CantDiasVencimiento = 3;
    
    private Integer porcentajeRecargoEstadia=50;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, GrupoTarifas> gruposTarifas;
    @OneToMany
    private List<Pais> paises;
    @OneToMany
    private List<Direccion> direcciones;
    @OneToMany
    private List<Provincia> provincias;
    @OneToMany
    private List<Localidad> localidades;
    
    @OneToOne
    private ControladorReservasVencidas unControladorReservasVencidas;
              
    
 
    public Hotel() {
        this.clientes = new HashMap();
        this.tiposClientes = new HashMap();
        this.tiposDocumento = new HashMap();
        this.nacionalidades = new HashMap();
        this.reservas = new HashMap();
        this.tiposHabitacion = new HashMap();
        this.habitaciones = new HashMap();
        this.estadosReserva = new HashMap();
        this.modosPagos = new HashMap();
        this.mediosLlegada = new HashMap();
        this.unidadesMedida = new HashMap();
        this.articulos = new HashMap();
        this.servicios = new HashMap();
        this.categorias = new HashMap();
        this.estados = new HashMap();
        this.usuarios = new HashMap();
        this.roles = new HashMap();
        this.personas = new HashMap();
        this.proveedores = new HashMap();
        this.productos = new HashMap();
        this.auditorias = new HashMap();
        this.marcas = new HashMap();
        this.compras = new HashMap();
        this.utilidades = new HashMap();
        this.comprasMant = new HashMap();
        this.estadias=new HashMap();
        this.tarifas=new HashMap();
        this.cargos=new HashMap();
        this.formasPago=new HashMap();
        this.ventas=new HashMap();
              //AgregadoFacundo
         this.huespedes= new HashMap();
        this.gruposTarifas= new HashMap();
        this.paises= new LinkedList<Pais>();
        this.direcciones= new LinkedList<Direccion>();
        this.provincias= new LinkedList<Provincia>();
        this.localidades= new LinkedList<Localidad>();
        
    }
    
    

    public Hotel(String razonSocial, String direccion, String telefono, String email, String cuit,Foto unaFoto,Foto unaFoto2) throws Exception, Exception {
        this();
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuit = cuit;
        this.logo = unaFoto;
        this.logo2 = unaFoto2;

        Hotel.persistencia.insert(this);
        this.unaCaja = new Caja("Caja La Cautiva");
        Hotel.persistencia.update(this);

    }
 
    
    
    
    public List<Localidad> getLocalidades() {
        return localidades;
    }
    
    public List<Provincia> getProvincias() {
        return provincias;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }
    public Map getModosPagos() {
        return modosPagos;

    }
  
   public Map getComprasMant() {
        return comprasMant;

    }

    public Foto getLogo2() {
        return logo2;
    }

    public int getPorcentajeVenta() {
        return porcentajeVenta;
    }

    public void setPorcentajeVenta(int porcentajeVenta) {
        this.porcentajeVenta = porcentajeVenta;
    }



    public Integer getCantDiasVencimiento() {
        return CantDiasVencimiento;
    }

    public void setCantDiasVencimiento(Integer CantDiasVencimiento) {
        this.CantDiasVencimiento = CantDiasVencimiento;
    }

    public void setPorcentajeConfirmacion(Integer porcentajeConfirmacion) {
        this.porcentajeConfirmacion = porcentajeConfirmacion;
    }
    
   
   
    public Map getUtilidades() {
        return utilidades;

    }
    
    public Map<Integer, Compra> getCompras() {
        return compras;
    }

    public Integer getPorcentajeRecargoEstadia() {
        return porcentajeRecargoEstadia;
    }

    public void setPorcentajeRecargoEstadia(Integer porcentajeRecargoEstadia) {
        this.porcentajeRecargoEstadia = porcentajeRecargoEstadia;
    }

    public Map<Integer, Reserva> getReservas() {
        return reservas;
    }

    public Map<Integer, Venta> getVentas() {
        return ventas;
    }

    public Map getClientes() {
        return clientes;
    }

    public Map<Integer, Tarifa> getTarifas() {
        return tarifas;
    }

    public Map<Integer, Estado> getEstadosReserva() {
        return estadosReserva;
    }

    public void setFormasPago(Map<String, FormaPago> formasPago) {
        this.formasPago = formasPago;
    }

    public Map<String, FormaPago> getFormasPago() {
        return formasPago;
    }


    public Map<Integer, Cargo> getCargos() {
        return cargos;
    }

    public Caja getUnaCaja() {
        return this.unaCaja;
    }

    public Map getTiposClientes() {
        return tiposClientes;
    }

    public Map<Integer, Marca> getMarcas() {
        return marcas;
    }

    public Map<Integer, Estadia> getEstadias() {
        return estadias;
    }
     public Map<Integer, GrupoTarifas> getGruposTarifas() {
        return gruposTarifas;
    }
    
    public Map getProductos() {
        return productos;
    }
    
    public Foto getLogo() {
        return logo;
    }

    public void setLogo(Foto logo) {
        this.logo = logo;
    }
    
    public Map getTiposDocumento() {
        return tiposDocumento;
    }

    public Map getNacionalidades() {
        return nacionalidades;
    }

     public Map getProveedores() {
        return proveedores;
    }
    
    public Map getAuditorias() {
        return auditorias;
    }
     
    public Map getRoles() {
        return roles;
    }

    public Map getCategorias() {
        return categorias;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String val) {
        this.cuit = val;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String val) {
        this.direccion = val;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String val) {
        this.email = val;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String val) {
        this.razonSocial = val;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String val) {
        this.telefono = val;
    }

    public Map getTiposHabitacion() {
        return tiposHabitacion;
    }

    public Map getPersonas() {
        return personas;
    }

    public Map getservicios() {
        return servicios;
    }

    public Map getarticulos() {
        return articulos;
    }

    public Map getMediosLlegada() {
        return mediosLlegada;
    }

    public Map<String, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Map<Integer, unidadMedida> getunidadesMedida() {
        return unidadesMedida;
    }

    public Map<Integer, EstadoHabitacion> getEstados() {
        return estados;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public Integer getPorcentajeConfirmacion() {
        return porcentajeConfirmacion;
    }

    public Cliente AgregarCliente(String documento, TipoDocumento unTipoDoc, TipoCliente unTipoCli, String nombre, String apellido, Date fechaNac, Nacionalidad unaNacionalidad, String direc, String email, String unTelefono, String otroTelefono) throws ClienteExepcion, Exception {
        Cliente retorno = null;
        if (!this.existeCliente(documento)) {
            retorno = new Cliente(unTipoCli, fechaNac, unaNacionalidad, direc, unTelefono, otroTelefono, email, documento, unTipoDoc, apellido, nombre);
            this.generarAuditoria("ALTA","CLIENTE",documento,apellido +" " + nombre, ControlAcceso.getUsuario()); 
            this.clientes.put(retorno.getDocumento(), retorno);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        } else {
            throw new ClienteExepcion("El Cliente Ya Existe");
        }
        return retorno;
    }

    public Cliente modificarCliente(String documento, TipoDocumento unTipoDoc, TipoCliente unTipoCli, String nombre, String apellido, Date fechaNac, Nacionalidad unaNacionalidad, String direc, String email, String unTelefono, String otroTelefono) throws ClienteExepcion, Exception {
        Cliente retorno = null;
        if (this.existeCliente(documento)) {
            Cliente unCliente = (Cliente) clientes.get(documento);
            unCliente.setNombre(nombre);
            unCliente.setApellido(apellido);
            unCliente.setDireccion(direc);
            unCliente.setEmail(email);
            unCliente.setFechaNacimiento(fechaNac);
            unCliente.setTelefono(unTelefono);
            unCliente.setOtroTelefono(otroTelefono);
            unCliente.setUnTipoCli(unTipoCli);
            unCliente.setUnaNac(unaNacionalidad);
            retorno = unCliente;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();

        } else {
            throw new ClienteExepcion("El Cliente no Existe");
        }
        return retorno;
    }

    public Cliente buscarCliente(String documento) throws ClienteExepcion {
        if (this.existeCliente(documento)) {
            return (Cliente) this.clientes.get(documento);
        } else {
            throw new ClienteExepcion("El Cliente no Existe");

        }

    }

    public Boolean existeCliente(String documento) {
        return (clientes.containsKey(documento));
    }

    public Map buscarClientes(TipoCliente unTipoCliente) {

        Map retorno = null;

        if (unTipoCliente != null) {
            retorno = unTipoCliente.getClientes();
        }
        return retorno;
    }

    public Map buscarClientesApe(String apellido) {
        Cliente aux = null;
        Map retorno = new HashMap();
        Iterator iterador = this.clientes.values().iterator();
        while (iterador.hasNext()) {
            aux = (Cliente) iterador.next();
            if (aux.isApellido(apellido)) {
                retorno.put(aux.getDocumento(), aux);
            }
        }

        return retorno;
    }

    public Collection verificarDisponibilidad(TipoHabitacion unTipoHabitacion, Date fechaEntrada, Date fechaSalida) throws ReservasExepcion {
        Collection retorno = null;
        retorno = unTipoHabitacion.getHabitacionesLibres(fechaEntrada, fechaSalida);
        if (!retorno.isEmpty()) {
            return retorno;
        } else {
            throw new ReservasExepcion("No hay Habitaciones Libres Para esas Fechas");
        }
    }

    public Map buscarClientesNom(String nombre) {
        Cliente aux = null;
        Map retorno = new HashMap();
        Iterator iterador = this.clientes.values().iterator();
        while (iterador.hasNext()) {
            aux = (Cliente) iterador.next();
            if (aux.isNombre(nombre)) {
                retorno.put(aux.getDocumento(), aux);
            }
        }
        return retorno;

    }

    public Map buscarClientesNom(TipoCliente unTipoCliente, String nombre, String apellido) {
        Cliente aux = null;
        Map retorno = null;
        Iterator iterador = this.buscarClientes(unTipoCliente).values().iterator();
        while (iterador.hasNext()) {
            aux = (Cliente) iterador.next();
            if (aux.isNombre(nombre) && aux.isApellido(apellido)) {
                retorno.put(aux.getDocumento(), aux);
            }
        }
        return retorno;
    }

    public TipoCliente agregarTipoCliente(String nombreTipo) throws Exception {
        TipoCliente unTipoCliente = null;
        if(!existeTipoCliente(nombreTipo)){
            unTipoCliente = new TipoCliente(nombreTipo);
            this.tiposClientes.put(unTipoCliente.getCodCliente(), unTipoCliente);
            Hotel.persistencia.update(this);
        }
        return unTipoCliente;

    }

    public Boolean existeNacionalidad(int cod) {
        return (nacionalidades.containsKey(cod));
    }

     public Boolean existeProveedor(int cod) {
        return (proveedores.containsKey(cod));
    }
     
    public Usuario existeUsuario(String usuario, String contrasenia) {
        Usuario unUsu = null;
        for (Map.Entry ent : getUsuarios().entrySet()) {
            Usuario unUsuario = (Usuario) ent.getValue();
            if (unUsuario.getUsserame().toUpperCase().equals(usuario.toUpperCase())) {
                if (unUsuario.getPasword().toUpperCase().equals(contrasenia.toUpperCase())) {
                    unUsu = unUsuario;
                }
            } else {
            }
        }

        return unUsu;
    }

    public Rol existeRol(String nombre) {
        Rol unRoll = null;
        Iterator it = getRoles().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry ent = (Map.Entry) it.next();
            Rol unRol = (Rol) ent.getValue();
            if (unRol.getNombre().equals(nombre)) {
                unRoll = unRol;
            } else {
            }
        }
        return unRoll;
    }

    public Usuario existePersona(String dni) {
        Usuario unaPer = null;
        for (Map.Entry ent : getUsuarios().entrySet()) {
            Usuario unUsser = (Usuario) ent.getValue();
            if (unUsser.toString().equals(dni)) {
                unaPer = unUsser;
            } else {
            }
        }
        return unaPer;
    }
    
    
    public Persona existePersona2(String dni) {
        Persona unaPer = null;
        for (Iterator it = getPersonas().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            Persona unUsser = (Persona) ent.getValue();
            if (unUsser.toString().equals(dni)) {
                unaPer = unUsser;
            } else {
            }
        }
        return unaPer;
    }

    public Boolean existeEstadoHabitacion(int cod) {
        return (estados.containsKey(cod));
    }

    public Boolean existeCategoria(int cod) {
        return (categorias.containsKey(cod));
    }
    
    public Boolean existeUtilidad (int cod) {
        return (utilidades.containsKey(cod));
    }
    
     public Boolean existeMarca(int cod) {
        return (marcas.containsKey(cod));
    }

    public Boolean existeServicio(int cod) {
        return (servicios.containsKey(cod));
    }

     public Boolean existeProducto(int cod) {
        return (productos.containsKey(cod));
    }
     
    public Boolean existeArticulo(int cod) {
        return (articulos.containsKey(cod));
    }

    public Boolean existeUnidadMedida(int cod) {
        return (unidadesMedida.containsKey(cod));
    }

    public Boolean existeTipoHabitacion(int cod) {

        return (tiposHabitacion.containsKey(cod));
    }

    public Boolean existeModoPago(int cod) {
        return (modosPagos.containsKey(cod));
    }

    public Boolean existeTipoDocumento(int cod) {
        return (tiposDocumento.containsKey(cod));
    }

    public Nacionalidad agregarNacionalidad(String nombre) throws Exception {
        Nacionalidad unaNacionalidad = null;
        if(!existeNacionalidad(nombre)){
            unaNacionalidad = new Nacionalidad(nombre);
            this.nacionalidades.put(unaNacionalidad.getCodNac(), unaNacionalidad);
            Hotel.persistencia.update(this);
        }
        return unaNacionalidad;
    }

  
    
     public Proveedor agregarProveedor(String nombre,String telefono) throws Exception {
        Proveedor unProveedor = null;
        if(!existeProveedor(nombre)){
            unProveedor = new Proveedor(nombre,telefono);
            this.proveedores.put(unProveedor.getcodProv(), unProveedor);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers(unProveedor);
        }
        return unProveedor;
    }
    public Concepto agregarConcepto(String detalle, String tipo, String descripcion) throws Exception {
        Concepto unConcepto= unaCaja.agregarConcepto(detalle, tipo, descripcion);
        return unConcepto;
    }
    
        public Estadia agregarEstadia(Date checkin, Date checkout, String hora, MedioLlegada unMedio,String comentarios,Reserva miReserva) throws Exception {
            Estadia unaEst = new Estadia (checkin,checkout,"12:30",unMedio,comentarios, miReserva);
            this.estadias.put(unaEst.getNumEstadia(),unaEst);
            setChanged();
            notifyObservers();
            return unaEst;
    }

    public Concepto editarConcepto(int cod, String detalle, String tipo, String descripcion) throws Exception {
        Concepto unConcepto=unaCaja.editarConcepto(cod, detalle, tipo, descripcion);
        if(unConcepto!=null){
            setChanged();
            notifyObservers();
         }
        return unConcepto;
    }

    public boolean eliminarConcepto(Concepto unConcepto) throws Exception {
        boolean unConceptoo= unaCaja.eliminarConcepto(unConcepto);
        if(unConceptoo){
            setChanged();
            notifyObservers();
         }
        return unConceptoo;
    }

    public EstadoHabitacion agregarEstadoHabitacion(String nombre) throws Exception {
        EstadoHabitacion unEstado = null;
        if(!existeEstadoHabitacion(nombre)){
            unEstado = new EstadoHabitacion(nombre);
            this.estados.put(unEstado.getCodEstado(), unEstado);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return unEstado;
    }

    public Articulo agregarArticulo(String nombre, unidadMedida unidad, Categoria unaCat, Marca unaMarca) throws Exception {
        Articulo unArticulo = null;
        unArticulo = new Articulo(nombre, unidad, unaCat, unaMarca);
        this.articulos.put(unArticulo.getCodArticulo(), unArticulo);
        Hotel.persistencia.update(this);
        setChanged();
        notifyObservers();
        return unArticulo;
    }

     public Persona agregarPersona(String dni,TipoDocumento tipoDoc,String apellido, String nombre) throws Exception {
        Persona unaPer = null;
        unaPer = new Persona(dni,tipoDoc,apellido,nombre);
        this.personas.put(unaPer.getDocumento(),unaPer);
        Hotel.persistencia.update(this);
        setChanged();
        notifyObservers();
        return unaPer;
       }
    

    public Categoria agregarCategoria(String nombre) throws Exception {
        Categoria unaCategoria = null;
        if(!existeCategoria(nombre)){
            Categoria unaCate = new Categoria(nombre);
            unaCategoria=unaCate;
            this.categorias.put(unaCategoria.getcodCat(), unaCategoria);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers(unaCategoria);
        }
        return unaCategoria;
    }
    
    public UtilizadoPara agregarUtilizadoPara(String nombre,String descripcion) throws Exception {
        UtilizadoPara unaUtilidad = null;
        if(!existeUtilidad(nombre)){
            UtilizadoPara unaCate = new UtilizadoPara(nombre,descripcion);
            unaUtilidad=unaCate;
            this.utilidades.put(unaUtilidad.getcod(), unaUtilidad);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return unaUtilidad;
    }
    
  public Marca agregarMarca(String nombre) throws Exception {
        Marca unaCategoria = null;
        if(!existeMarca(nombre)){
            Marca unaCate = new Marca(nombre);
            unaCategoria=unaCate;
            this.marcas.put(unaCategoria.getcodMarca(), unaCategoria);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers(unaCategoria);
        }
        return unaCategoria;
    }

    
         public Boolean existeEstadoHabitacion(String nombre) {
         boolean retorno=false;
        for (Iterator it = getMediosLlegada().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            MedioLlegada unMedio = (MedioLlegada)ent.getValue();
            if (unMedio.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
    
         
    public Boolean existeModoPago (String nombre) {
         boolean retorno=false;
        for (Iterator it = getModosPagos().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            ModoPago unModo = (ModoPago)ent.getValue();
            if (unModo.getDescripcion().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
    
     public Boolean existeFormaPago (String nombre) {
         boolean retorno=false;
        for (Iterator it = getFormasPago().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            FormaPago unModo = (FormaPago)ent.getValue();
            if (unModo.getDescripcion().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
       
  public Boolean existeNacionalidad (String nombre) {
         boolean retorno=false;
        for (Iterator it = getNacionalidades().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            Nacionalidad unaNac = (Nacionalidad)ent.getValue();
            if (unaNac.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
  
   public Boolean existeProveedor (String nombre) {
         boolean retorno=false;
        for (Iterator it = getProveedores().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            Proveedor unProv = (Proveedor)ent.getValue();
            if (unProv.getnombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
  
   public Boolean existeTipoHabitacion (String nombre) {
         boolean retorno=false;
        for (Iterator it = getTiposHabitacion().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            TipoHabitacion unaNac = (TipoHabitacion)ent.getValue();
            if (unaNac.getDescripcion().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
 
  public Boolean existeTipoCliente(String nombre) {
         boolean retorno=false;
        for (Iterator it = getTiposClientes().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            TipoCliente unTipo = (TipoCliente)ent.getValue();
            if (unTipo.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
  
   public Boolean existeUnidadMedidaNombre(String nombre) {
        boolean retorno=false;
        for (Map.Entry ent : getunidadesMedida().entrySet()) {
            unidadMedida unTipo = (unidadMedida)ent.getValue();
            if (unTipo.getnombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
  
    public Boolean existeUnidadMedidaAbreviasion(String abreviasion) {
         boolean retorno=false;
        for (Iterator it = getunidadesMedida().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            unidadMedida unTipo = (unidadMedida)ent.getValue();
            if (unTipo.getabreviacion().toUpperCase().equals(abreviasion.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }  
   
  public Boolean existeServicio (String nombre) {
         boolean retorno=false;
        for (Iterator it = getservicios().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            Servicio unaNac = (Servicio)ent.getValue();
            if (unaNac.getnombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
  
       public Boolean existeMedioLlegada(String nombre) {
         boolean retorno=false;
        for (Iterator it = getMediosLlegada().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            MedioLlegada unEstado = (MedioLlegada)ent.getValue();
            if (unEstado.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
    
     public Boolean existeTipoDocumento(String nombre) {
        boolean retorno=false;
        for (Iterator it = getTiposDocumento().entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry) it.next();
            TipoDocumento unEstado = (TipoDocumento)ent.getValue();
            if (unEstado.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
    
     public Boolean existeCategoria(String nombre) {
         boolean retorno=false;
         Iterator it = getCategorias().entrySet().iterator();
            while(it.hasNext()) {
                    Map.Entry ent = (Map.Entry)it.next();
                    Categoria unaCat = (Categoria)ent.getValue();
                    if (unaCat.getnombre().toUpperCase().equals(nombre.toUpperCase())) {
                        retorno=true;
                    }                    
            } 
         return retorno;
    }
     
     public Boolean existeUtilidad(String nombre) {
         boolean retorno=false;
         Iterator it = getUtilidades().entrySet().iterator();
            while(it.hasNext()) {
                    Map.Entry ent = (Map.Entry)it.next();
                    UtilizadoPara unaCat = (UtilizadoPara)ent.getValue();
                    if (unaCat.getnombre().toUpperCase().equals(nombre.toUpperCase())) {
                        retorno=true;
                    }                    
            } 
         return retorno;
    }
    
     
     public Boolean existeMarca(String nombre) {
         boolean retorno=false;
        for (Map.Entry ent : getMarcas().entrySet()) {
            Marca unaCat = (Marca)ent.getValue();
            if (unaCat.getnombre().toUpperCase().equals(nombre.toUpperCase())) {
                retorno=true;
            }
        } 
         return retorno;
    }
     
      public Boolean existeProducto(String nombre, Marca unaMarca) {
         boolean retorno=false;
         Iterator it = getProductos().entrySet().iterator();
            while(it.hasNext()) {
                    Map.Entry ent = (Map.Entry)it.next();
                    Producto unaCat = (Producto)ent.getValue();
                    if (unaCat.getnombre().toUpperCase().equals(nombre.toUpperCase())) {
                       if(unaCat.getUnaMarca().getnombre().equals(unaMarca.getnombre())){
                           retorno=true;}   
                    }                    
            } 
         return retorno;
    }
    
      public Boolean existeProductoCod(String cod) {
         boolean retorno=false;
         Iterator it = getProductos().entrySet().iterator();
            while(it.hasNext()) {
                    Map.Entry ent = (Map.Entry)it.next();
                    Producto unaCat = (Producto)ent.getValue();
                    if (unaCat.getcod().toUpperCase().equals(cod.toUpperCase())) {
                       retorno=true;}   
              } 
         return retorno;
    }
        public unidadMedida agregarUnidadMedida(String nombre, String abreviacion) throws Exception {
        unidadMedida unaUnidadMedida = null;
        if((!existeUnidadMedidaNombre(nombre))&&(!existeUnidadMedidaAbreviasion(abreviacion))){
            unaUnidadMedida = new unidadMedida(nombre, abreviacion);
            this.unidadesMedida.put(unaUnidadMedida.getcod(), unaUnidadMedida);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers(unaUnidadMedida);
            
        }
        return unaUnidadMedida;
    }

    public Servicio agregarServicio(String nombre, String descripcion,String estado, double precio, unidadMedida unaUnidad) throws Exception {
        Servicio unServicio = null;
        if(!existeServicio(nombre)){
            unServicio = new Servicio(nombre, descripcion, estado, precio, unaUnidad);
            this.servicios.put(unServicio.getcod(), unServicio);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return unServicio;
    }
    
  public Producto agregarProducto(String cod,String nombre, Marca unaMarca, unidadMedida unaUnidad, Categoria unaCategoria) throws Exception {
        Producto unProducto = null;
        if(!existeProducto(nombre,unaMarca)){
          if(!existeProductoCod(cod)){
            unProducto = new Producto(cod,nombre, unaMarca, unaUnidad,unaCategoria);
            this.productos.put(unProducto.getcod(), unProducto);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        }
        return unProducto;
    }

    public TipoHabitacion agregarTipoHabitacion(String descripcion, int estandar, int maximo) throws Exception {
        TipoHabitacion unTipoHabitacion = null;
        if(!existeTipoHabitacion(descripcion)){
            unTipoHabitacion = new TipoHabitacion(descripcion, estandar, maximo);
            this.tiposHabitacion.put(unTipoHabitacion.getcod(), unTipoHabitacion);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return unTipoHabitacion;
    }

    public ModoPago agregarModoPago(String nombre) throws Exception {
        ModoPago unPago = null;
        if(!existeModoPago(nombre)){
            unPago = new ModoPago(nombre);
            this.modosPagos.put(unPago.getIdModPago(), unPago);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return unPago;
    }

    public FormaPago agregarFormaPago(String nombre,String[]datos) throws Exception {
        FormaPago unaForma = null;
        if(!existeFormaPago(nombre)){
            unaForma = new FormaPago(nombre,datos);
            this.formasPago.put(unaForma.getDescripcion(), unaForma);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return unaForma;
    }
    
    public Nacionalidad editarNacionalidad(int cod, String nombre) throws Exception {
        Nacionalidad retorno;
        if (this.existeNacionalidad(cod)) {
            Nacionalidad unaNac = (Nacionalidad) nacionalidades.get(cod);
            unaNac.setNombre(nombre);
            retorno = unaNac;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();

        } else {
            throw new ClienteExepcion("La Nacionalidad no Existe");
        }
        Hotel.persistencia.update(this);
        return retorno;
    }
    
     public Proveedor editarProveedor(int cod, String nombre,String telefono) throws Exception {
        Proveedor retorno;
        if (this.existeProveedor(cod)) {
          if(!this.existeProveedor(nombre)){
            Proveedor unProv = (Proveedor) proveedores.get(cod);
            unProv.setnombre(nombre);
            unProv.settelefono(telefono);
            retorno = unProv;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
            }
            else { retorno=null;  } 
        }
         else {
            throw new ClienteExepcion("El Proveedor no Existe");
          }
       // Hotel.persistencia.update(this);
        return retorno;
    }

    public Usuario editarUsuario(String dni, String nombre, String apellido, TipoDocumento unTipo, String telefono, String direccion, String usuario, String contasenia, Rol rol) throws Exception {
        Usuario retorno=null;
        Usuario unUsuario = existePersona(dni);
        if (unUsuario != null) {
            Usuario aux = existeUsuario(usuario, contasenia); 
//            if (aux==null){
                Usuario unUs = (Usuario) usuarios.get(dni);
                unUs.getUnaPersona().setNombre(nombre);
                unUs.getUnaPersona().setApellido(apellido);
                unUs.getUnaPersona().setDocumento(dni);
                unUs.setTelefono(telefono);
                unUs.setDomicilio(direccion);
                unUs.getUnaPersona().setUnTipoDoc(unTipo);
                unUs.setUsserame(usuario);
                unUs.setPasword(contasenia);
                unUs.setRol(rol);
                retorno = unUs;
                Hotel.persistencia.update(this);
//             }
//           else{
//             JOptionPane.showMessageDialog(null,"El Usuario y Contraseña ya existe!");
//           }
        } else {
            throw new ClienteExepcion("El Usuario no Existe");
        }
        return retorno;
    }

    public Rol editarRol(String nombre, String descripcion, String[] permisos) throws Exception {
        Rol retorno;
        Rol unR = (Rol) roles.get(nombre.toLowerCase());
//        Rol unRol=existeRol(nombre);
//        if (unRol!=null){
//            JOptionPane.showConfirmDialog(null, "Ya existe el Rol");
//            retorno=null;
//        }
//        else{
        unR.setComentario(descripcion);
        unR.setNombre(nombre);
        unR.setPermiso(permisos);
        retorno = unR;
        Hotel.persistencia.update(this);
//        }
        return retorno;
    }

    
    public void editarCargos(Estadia unaEstadia, List<Cargo> cargos) throws Exception {      
        Map aux = new HashMap();
        for (Map.Entry ent : unaEstadia.getCargos().entrySet()) {
            Cargo unCargo = (Cargo)ent.getValue();
            aux.put(unCargo.getNumCargo(), unCargo);
        }
        
       for (Iterator<Cargo> it = cargos.iterator(); it.hasNext();) {
            Cargo x = it.next();
            aux.put(x.getNumCargo(),x); 
        }
       
       unaEstadia.getCargos().clear();
       
        Iterator ittt = aux.entrySet().iterator();
        while(ittt.hasNext()) {
            Map.Entry ent = (Map.Entry)ittt.next();
            Cargo unCar = (Cargo)ent.getValue();
//            unaEstadia.getCargos().put(unCar.getNumCargo(), unCar);
            unaEstadia.agregarCargo(unCar);
            
        }   
       setChanged();
       notifyObservers();
    }
    
    
    public void ActivarUsuario(Usuario unUsuario) throws Exception {
        unUsuario.setEstado(true);
        Hotel.persistencia.update(this);
        setChanged();
        notifyObservers();
    }

    public void ActivarRol(Rol unRol) throws Exception {
        unRol.setEstado(true);
        Hotel.persistencia.update(this);
        setChanged();
        notifyObservers();
    }

    public void cambiarEstado(Cargo miCargo, Pago miPago, Estadia miEstadia) throws Exception{
          miCargo.setUnEstadoCargo("PAGADO");
          miCargo.setUnPago(miPago);
          Hotel.persistencia.update(this);
          miEstadia.update();
          setChanged();
          notifyObservers();
    }
    
    public EstadoHabitacion editarEstadoHabitacion(int cod, String nombre) throws Exception {
        EstadoHabitacion retorno=null;
        if (!this.existeEstadoHabitacion(nombre)) {
            EstadoHabitacion unaNac = (EstadoHabitacion) estados.get(cod);
            unaNac.setNombre(nombre);
            retorno = unaNac;
            Hotel.persistencia.update(this);
        }
        return retorno;
    }

    public Articulo editarArticulo(int cod, String nombre, unidadMedida unidad, Categoria unaCat, Marca unaMarca) throws Exception {
        Articulo retorno;
        if (this.existeArticulo(cod)) {
            Articulo unaNac = (Articulo) articulos.get(cod);
            unaNac.setnombre(nombre);
            unaNac.setunidadMedida(unidad);
            unaNac.setunaCategoria(unaCat);
            unaNac.setUnaMarca(unaMarca);
            retorno = unaNac;
            Hotel.persistencia.update(this);
        } else {
            throw new ClienteExepcion("El Artículo no Existe");
        }
        Hotel.persistencia.update(this);
        return retorno;
    }

    public unidadMedida editarUnidadMedida(int cod, String nombre, String abreviatura) throws Exception {
        unidadMedida retorno=null;
        if ((!this.existeUnidadMedidaNombre(nombre))&&(!existeUnidadMedidaAbreviasion(abreviatura))) {
            unidadMedida unaNac = (unidadMedida) unidadesMedida.get(cod);
            unaNac.setnombre(nombre);
            unaNac.setabreviacion(abreviatura);
            retorno = unaNac;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return retorno;
    }

    public Categoria editarCategoria(int cod, String nombre) throws Exception {
        Categoria retorno;
        if (this.existeCategoria(cod)) {
            Categoria unaNac = (Categoria) categorias.get(cod);
            unaNac.setnombre(nombre);
            retorno = unaNac;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();

        } else {
            throw new ClienteExepcion("La Categoria no Existe");
        }
       // Hotel.persistencia.update(this);
        return retorno;
    }

    public UtilizadoPara editarUtilidad(int cod, String nombre,String desc) throws Exception {
        UtilizadoPara retorno;
        if (this.existeUtilidad(cod)) {
            UtilizadoPara unaNac = (UtilizadoPara) utilidades.get(cod);
            unaNac.setnombre(nombre);
            unaNac.setDescripcion(desc);
            retorno = unaNac;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();

        } else {
            throw new ClienteExepcion("La Utilidad no Existe");
        }
        return retorno;
    }
    
     public Marca editarMarca(int cod, String nombre) throws Exception {
        Marca retorno;
        if (this.existeMarca(cod)) {
            Marca unaNac = (Marca) marcas.get(cod);
            unaNac.setnombre(nombre);
            retorno = unaNac;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        } else {
            throw new ClienteExepcion("La Marca no Existe");
        }
        return retorno;
    }
    
    public Servicio editarServicio(int cod, String nombre, String descripcion, String estado, double precio, unidadMedida unaUnidad) throws Exception {
        Servicio retorno=null;
        Servicio unaNac = (Servicio) servicios.get(cod);
        if (!this.existeServicio(nombre)) {
            unaNac.setnombre(nombre);
            unaNac.setdescripcion(descripcion);
            unaNac.setEstado(estado);
            unaNac.setUnUnidadMedida(unaUnidad);
            unaNac.setPrecio(precio);
            retorno = unaNac;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
            
       }else{
            if(unaNac.getnombre().toUpperCase().equals(nombre.toUpperCase())){
                unaNac.setdescripcion(descripcion);
                unaNac.setEstado(estado);
                unaNac.setUnUnidadMedida(unaUnidad);
                unaNac.setPrecio(precio);
                retorno = unaNac;
                Hotel.persistencia.update(this);
                setChanged();
                notifyObservers();
            }
        }
        return retorno;
    }
    
    public Producto editarProducto(String cod,String nombre, Marca unaMarca, unidadMedida unaUnidad, Categoria unaCategoria) throws Exception {
        Producto retorno=null;
        Producto unaNac = (Producto) productos.get(cod);
        if (!this.existeProducto(nombre,unaMarca)) {
            if(!existeProductoCod(cod)){
            unaNac.setcod(cod);
            unaNac.setnombre(nombre);
            unaNac.setUnaMarca(unaMarca);
            unaNac.setUnaCategoria(unaCategoria);
            unaNac.setUnUnidadMedida(unaUnidad);
            retorno = unaNac;
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
            }
            
       }else{
            if(unaNac.getnombre().toUpperCase().equals(nombre.toUpperCase())){
                if(unaNac.getUnaMarca().getnombre().equals(unaMarca.getnombre())){
                   if(!existeProductoCod(cod)){
                    unaNac.setUnaMarca(unaMarca);
                    unaNac.setUnaCategoria(unaCategoria);
                    unaNac.setUnUnidadMedida(unaUnidad);
                    unaNac.setcod(cod);
                    retorno = unaNac;
                    Hotel.persistencia.update(this);
                    setChanged();
                    notifyObservers();
                   }
                }
            }
        }
        return retorno;
    }
    

    public TipoHabitacion editarTipoHabitacion(int cod, String descripcion, int estandar, int maximo) throws Exception {
        TipoHabitacion retorno=null;
        if (!this.existeTipoHabitacion(descripcion)) {
            TipoHabitacion unaNac = (TipoHabitacion) tiposHabitacion.get(cod);
            unaNac.setDescripcion(descripcion);
            unaNac.setCapacidadEstandar(estandar);
            unaNac.setCapacidadMaxima(maximo);
            retorno = unaNac;
            Hotel.persistencia.update(this);
        }
        return retorno;
    }

    public TipoCliente editarTipoCliente(int cod, String nombre) throws Exception {
        TipoCliente retorno=null;
        if (!this.existeTipoCliente(nombre)) {
            TipoCliente unaNac = (TipoCliente) tiposClientes.get(cod);
            unaNac.setNombre(nombre);
            retorno = unaNac;
            Hotel.persistencia.update(this);
        }
        return retorno;
    }

    public ModoPago editarModoPago(int cod, String nombre) throws Exception {
        ModoPago retorno=null;
        if (!this.existeModoPago(nombre)) {
            ModoPago unaNac = (ModoPago) modosPagos.get(cod);
            unaNac.setDescripcion(nombre);
            retorno = unaNac;
            Hotel.persistencia.update(this);
        }
        return retorno;
    }
    
     public FormaPago editarFormaPago(int cod, String nombre,String[]data) throws Exception {
        FormaPago retorno=null;
        if (!this.existeFormaPago(nombre)) {
            FormaPago unaNac = (FormaPago) formasPago.get(nombre);
            unaNac.setDescripcion(nombre);
            unaNac.setDatosPago(data);
            retorno = unaNac;  
        }
        else{
            FormaPago unaNac = (FormaPago) formasPago.get(nombre);
            unaNac.setDatosPago(data);
            retorno = unaNac;
        
        }
        Hotel.persistencia.update(this);
        return retorno;
    }

    public MedioLlegada editarMedioLlegada(int cod, String nombre) throws Exception {
        MedioLlegada retorno=null;
        if (!this.existeMedioLlegada(nombre)) {
            MedioLlegada unaNac = (MedioLlegada) mediosLlegada.get(cod);
            unaNac.setNombre(nombre);
            retorno = unaNac;
            Hotel.persistencia.update(this);
        }
        return retorno;
    }

    public TipoDocumento editarTipoDocumento(int cod, String nombre) throws Exception {
        TipoDocumento retorno=null;
        if (!this.existeTipoDocumento(nombre)) {
            TipoDocumento unTipoo = (TipoDocumento) tiposDocumento.get(cod);
            unTipoo.setNombre(nombre);
            retorno = unTipoo;
            Hotel.persistencia.update(this);
        }
        return retorno;
    }

    public ModoPago agregarModosPagos(String nombre) throws Exception {
        ModoPago unModoPago = null;
        unModoPago = new ModoPago(nombre);
        this.modosPagos.put(unModoPago.getIdModPago(), unModoPago);
        Hotel.persistencia.update(this);
        return unModoPago;
    }

    public TipoDocumento agregarTipoDocumento(String nombre) throws Exception {
        TipoDocumento unTipoDocumento = null;
        if(!existeTipoDocumento(nombre)){
            unTipoDocumento = new TipoDocumento(nombre);
            this.tiposDocumento.put(unTipoDocumento.getCodTipo(), unTipoDocumento);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        }
        return unTipoDocumento;
    }

  

    public Usuario agregarUsuario(String dni, String nombre, String apellido, TipoDocumento unTipo, String telefono, String direccion, String usuario, String contasenia, Rol rol, Hotel unHotel) throws Exception {
        Usuario unUsuario = null;
        
        Date creadoDia = new Date();
        creadoDia.setHours(0);
        creadoDia.setMinutes(0);
        creadoDia.setSeconds(0);

        int hora;
        int minutos;
        String creadoHora;
        Calendar unCalendar = new GregorianCalendar();
        hora = unCalendar.get(Calendar.HOUR_OF_DAY);
        minutos = unCalendar.get(Calendar.MINUTE);
        creadoHora = hora + ":" + minutos;

        Persona aux;
        Usuario aux1;
        Usuario aux2;
        aux = existePersona2(dni);
        aux2=existePersona(dni);
        aux1 = existeUsuario(usuario, contasenia);
        if (aux == null) {
            if(aux2 == null ){
            if (aux1 == null) {
                unUsuario = new Usuario(dni, unTipo, apellido, nombre, telefono, direccion, contasenia, usuario, creadoDia, creadoHora, rol,unHotel);
                this.usuarios.put(dni, unUsuario);
                Hotel.persistencia.update(this);
                setChanged();
                notifyObservers();
              } else {
                JOptionPane.showMessageDialog(null, "El Usuario y Contraseña ya existe!");
            } }
             else {
                JOptionPane.showMessageDialog(null, "La Persona ya tiene asociado un Usuario");
            }
        } else {
           if(aux2 == null ){
            if(aux1==null){
            unUsuario = new Usuario(aux, telefono, direccion, contasenia, usuario, creadoDia, creadoHora, rol);
            this.usuarios.put(aux.getDocumento(), unUsuario);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
           }
            else{
                JOptionPane.showMessageDialog(null, "El Usuario y Contraseña ya existe!"); 
            }
            }
             else {
                JOptionPane.showMessageDialog(null, "La Persona ya tiene asociado un Usuario");
            }
            
        }
        return unUsuario;
    }

    public Rol agregarRol(String nombre, String comentario, String[] permisos) throws Exception {
        Rol unRol = null;
        Rol existe = existeRol(nombre);
        if (existe == null) {
            unRol = new Rol(nombre.toUpperCase(), comentario.toUpperCase(), permisos);
            this.roles.put(nombre, unRol);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        } else {
            JOptionPane.showMessageDialog(null, "El Rol ya existe");
        }
        return unRol;
    }

    public Habitacion seleccionarHabitacion(int numHab) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.845E02EC-409F-B306-644F-95DE2C5A159A]
    // </editor-fold> 
    public Tarifa seleccionarTarifa(int numTarifa) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5CE73CAE-AFDE-C26B-C10E-5DB462F35166]
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.41955B78-A6A0-30B3-9408-BC38DF51CA40]
    // </editor-fold> 
    public Reserva confirmarOperacion(Cliente unCliente, Habitacion unaHabitacion, Tarifa unaTarifa, Date fechaEntrada, Date fechaSalida, int cantPer, String medioLlegada, String medioReserva, String horaLlegada) throws Exception {
        Reserva unaReserva = new Reserva(fechaEntrada, fechaSalida, horaLlegada, cantPer, medioLlegada, medioReserva);
        unaReserva.agregarHabitacion(unaHabitacion);
        unaReserva.AgregarCliente(unCliente);
        unaReserva.agregarTarifa(unaTarifa);
        unaReserva.setEstado(estadosReserva.get(1));
        this.agregarReserva(unaReserva);
        this.generarAuditoria("ALTA", "RESERVA", unaHabitacion.getIdHabitacion(), unCliente.getDocumento(), ControlAcceso.getUsuario());
        Hotel.persistencia.update(this);
        this.setChanged();
        this.notifyObservers();
        return unaReserva;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AFB0104A-850D-4F2E-1A20-1183655C6206]
    // </editor-fold> 
    public void agregarReserva(Reserva unaReserva) {
        this.reservas.put(unaReserva.getNumReserva(), unaReserva);
    }

    public void EliminarCliente(String doc) throws ClienteExepcion {
        Cliente unCliente = null;
        if (this.existeCliente(doc)) {
            unCliente = (Cliente) clientes.get(doc);
            try {
                Hotel.persistencia.delete(unCliente);
                this.clientes.remove(unCliente.getDocumento());
                Hotel.persistencia.update(this);
            } catch (Exception ex) {
                 throw new ClienteExepcion("El Cliente no puede ser eliminado.");
            }
            setChanged();
            notifyObservers();
        } else {
            throw new ClienteExepcion("El Cliente no Existe");
        }
    }

    public boolean eliminarNacionalidad(int cod) throws Exception {
        boolean retorno = false;
        Nacionalidad unaNac = null;
        if (this.existeNacionalidad(cod)) {
            unaNac = (Nacionalidad) nacionalidades.get(cod);
            this.nacionalidades.remove(unaNac.getCodNac());
            retorno = true;
            Hotel.persistencia.update(this);
            Hotel.persistencia.delete(unaNac);
            setChanged();
            notifyObservers();
        } else {
            throw new ClienteExepcion("La Nacionalidad no Existe");
        }
        return retorno;
    }

    public Usuario eliminarUsuario(String usuario, String contrasenia) throws Exception {
        Usuario unUsuario = null;
        unUsuario = existeUsuario(usuario, contrasenia);
        if (unUsuario != null) {
            unUsuario.setEstado(false);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        } else {
            throw new ClienteExepcion("La Usuario no Existe");
        }
        return unUsuario;
    }
    
    public void eliminarCargo(Estadia unaEstadia,Cargo unCargo) throws Exception {
           Iterator ittt = unaEstadia.getCargos().entrySet().iterator();
           while(ittt.hasNext()) {
                Map.Entry ent = (Map.Entry)ittt.next();
                Cargo cargo = (Cargo)ent.getValue();
                if(cargo.equals(unCargo)){
                        unaEstadia.getPagos().remove(unCargo.getUnPago().getNumPago());
                        unaEstadia.getCargos().remove(cargo.getNumCargo());
                        
                        cargo.getUnProducto().ModificarStock(cargo.getCantidad());
                }
           }
            this.cargos.remove(unCargo.getNumCargo());  
            Hotel.persistencia.update(this);
            Hotel.persistencia.delete(unCargo);
            setChanged();
            notifyObservers();
    }

    public boolean eliminarRol(String nombre) throws Exception {
        Rol unRol = null;
        unRol = existeRol(nombre);
        boolean bandera=false;
        if (unRol != null) {
            
             for (Map.Entry ent : getUsuarios().entrySet()) {
                    Usuario unUsuario = (Usuario) ent.getValue();
                    if (unUsuario.getRol().getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                        bandera=true;
                    } else {
                    }
                }
             if(bandera==false){
                 unRol.setEstado(false);
                 Hotel.persistencia.update(this);
                 setChanged();
                 notifyObservers();
             }
        } else {
            throw new ClienteExepcion("El Rol no Existe");
        }
        return bandera;
    }

    public boolean eliminarEstadoHabitacion(int cod) throws Exception {
        boolean retorno = false;
        EstadoHabitacion unaEstado = null;
        if (this.existeEstadoHabitacion(cod)) {
            unaEstado = (EstadoHabitacion) estados.get(cod);
            try {
                this.estados.remove(unaEstado.getCodEstado());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unaEstado);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"No se puede eliminar el Estado porque esta asociado a una Habitacion");
            }
           } else {
            throw new ClienteExepcion("La Nacionalidad no Existe");
        }
        return retorno;
    }

    public boolean eliminarArticulo(int cod) throws Exception {
        boolean retorno = false;
        Articulo unaArt = null;
        if (this.existeArticulo(cod)) {
            unaArt = (Articulo) articulos.get(cod);
            try {
                this.articulos.remove(unaArt.getCodArticulo());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unaArt);
             } catch (Exception e) {
                throw new ClienteExepcion("El Artículo no puede eliminarse porque esta asociado a un Mantenimiento");
              }
            setChanged();
            notifyObservers();
        } else {
            throw new ClienteExepcion("El Artículo no Existe");
        }
        return retorno;
    }

    public boolean eliminarUnidadMedida(int cod) throws Exception {
        boolean retorno = false;
        unidadMedida unaUnidad = null;
        if (this.existeUnidadMedida(cod)) {
            unaUnidad = (unidadMedida) unidadesMedida.get(cod);
            try {
                this.unidadesMedida.remove(unaUnidad.getcod());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unaUnidad);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"La Unidad de Medida no se puede eliminar");
            } 
        } else {
            throw new ClienteExepcion("La Unidad de Medida no Existe");
        }
        return retorno;
    }

    public boolean eliminarCategoria(int cod) throws Exception {
        boolean retorno = false;
        Categoria unMant = null;
        if (this.existeCategoria(cod)) {
            unMant = (Categoria) categorias.get(cod);
            try {
                this.categorias.remove(unMant.getcodCat());
            retorno = true;
            Hotel.persistencia.update(this);
            Hotel.persistencia.delete(unMant);
            setChanged();
            notifyObservers();
            } catch (Exception e) {
                throw new ClienteExepcion("No puede darse de baja la Categoria porque esta asociada a un Mantenimiento");
             }
         } else {
            throw new ClienteExepcion("La Categoria no Existe");
        }
        return retorno;
    }
    
    public boolean eliminarUtilidad(int cod) throws Exception {
        boolean retorno = false;
        UtilizadoPara unMant = null;
        if (this.existeUtilidad(cod)) {
            unMant = (UtilizadoPara) utilidades.get(cod);
            try {
                this.utilidades.remove(unMant.getcod());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unMant);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                throw new ClienteExepcion("No puede darse de baja la Utilidad porque esta asociado a un Articulo");
             }
         } else {
            throw new ClienteExepcion("La Utilidad no Existe");
        }
        return retorno;
    }
    
    public boolean eliminarMarca(int cod) throws Exception {
        boolean retorno = false;
        Marca unMant = null;
        if (this.existeMarca(cod)) {
            unMant = (Marca) marcas.get(cod);
            try {
                this.marcas.remove(unMant.getcodMarca());
            retorno = true;
            Hotel.persistencia.update(this);
            Hotel.persistencia.delete(unMant);
            setChanged();
            notifyObservers();
            } catch (Exception e) {
                throw new ClienteExepcion("No puede darse de baja la Marca porque esta asociada a un Producto");
             }
         } else {
            throw new ClienteExepcion("La Marca no Existe");
        }
        return retorno;
    }

    public boolean eliminarServicio(int cod) throws Exception {
        boolean retorno = false;
        Servicio unServicio = null;
        if (this.existeServicio(cod)) {
            unServicio = (Servicio) servicios.get(cod);
            try {
                this.servicios.remove(unServicio.getcod());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unServicio);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"No se puede eliminar el Servicio");
            } 
        } else {
            throw new ClienteExepcion("El Servicio no Existe");
        }
        return retorno;
    }
    
    public boolean eliminarProducto(String cod) throws Exception {
        boolean retorno = false;
        Producto unProducto = null;
        if (this.existeProductoCod(cod)) {
            unProducto = (Producto) productos.get(cod);
            try {
                this.productos.remove(unProducto.getcod());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unProducto);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"No se puede eliminar el Producto");
            } 
        } else {
            throw new ClienteExepcion("El Producto no Existe");
        }
        return retorno;
    }

    public boolean eliminarTipoHabitacion(int cod) throws Exception {
        boolean retorno = false;
        TipoHabitacion unaNac = null;
        if (this.existeTipoHabitacion(cod)) {
            unaNac = (TipoHabitacion) tiposHabitacion.get(cod);
            try {
                this.tiposHabitacion.remove(unaNac.getcod());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unaNac);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"El Tipo de Habitacion no puede eliminarse");
            }   
        } else {
            throw new ClienteExepcion("El Tipo de Habitación no Existe");
        }
        return retorno;
    }

    public boolean eliminarTipoCliente(int cod) throws Exception {
        boolean retorno = false;
        TipoCliente unaNac = null;
        if (this.existeTipoCliente(cod)) {
            unaNac = (TipoCliente) tiposClientes.get(cod);
            try {
                this.tiposClientes.remove(unaNac.getCodCliente());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unaNac);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"No se puede eliminar el Tipo de Cliente");
            }
        } else {
            throw new ClienteExepcion("El Tipo de Cliente no Existe");
        }
        return retorno;
    }

        public boolean eliminarProveedor(int cod) throws Exception {
        boolean retorno = false;
        Proveedor unProv = null;
        if (this.existeProveedor(cod)) {
            unProv = (Proveedor) proveedores.get(cod);
            try {
                this.proveedores.remove(unProv.getcodProv());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unProv);
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"No se puede eliminar el Proveedor!");
            }
        } else {
            throw new ClienteExepcion("El Proveedor no Existe");
        }
        return retorno;
    }
    
    public boolean eliminarModoPago(int cod) throws Exception {
        boolean retorno = false;
        ModoPago unaNac = null;
        if (this.existeModoPago(cod)) {
            unaNac = (ModoPago) modosPagos.get(cod);
            try {
                this.modosPagos.remove(unaNac.getIdModPago());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unaNac);
                setChanged();
                notifyObservers(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"El Modo de Pago no se puede eliminar");
            }   
        } else {
            throw new ClienteExepcion("El Modo de Pago no Existe");
        }
        return retorno;
    }
    
    public boolean eliminarFormaPago(int cod) throws Exception {
        boolean retorno = false;
        FormaPago unaNac=null;
        Iterator it = this.getFormasPago().entrySet().iterator();
        while(it.hasNext()) {
        Map.Entry ent = (Map.Entry)it.next();
        FormaPago unaForma = (FormaPago)ent.getValue();
        if (unaForma.getIdModPago().equals(cod)){
            unaNac=unaForma;
            }
        }
        if (this.existeFormaPago(unaNac.getDescripcion())) {      
            try {
                this.formasPago.remove(unaNac.getDescripcion());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(unaNac);
                setChanged();
                notifyObservers(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"La Forma de Pago no se puede eliminar");
            }   
        } else {
            throw new ClienteExepcion("La Forma de Pago no Existe");
        }
        return retorno;
    }

    public boolean eliminarMedioLlegada(int cod) throws Exception {
        boolean retorno = false;
        MedioLlegada unaNac = null;
        if (this.existeMedioLlegada(cod)) {
            unaNac = (MedioLlegada) mediosLlegada.get(cod);
           try {
              this.mediosLlegada.remove(unaNac.getCodMedio());
              retorno = true;
              Hotel.persistencia.update(this);
              Hotel.persistencia.delete(unaNac);
              setChanged();
              notifyObservers(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"El Medio de Llegada no se puede eliminar");
            }
           
        } else {
            throw new ClienteExepcion("El Medio de Llegada no Existe");
        }
        return retorno;
    }

    public boolean eliminarTipoDocumento(int cod) throws Exception {
        boolean retorno = false;
        TipoDocumento doc = null;
        if (this.existeTipoDocumento(cod)) {
            doc = (TipoDocumento) tiposDocumento.get(cod);
            try {
               this.tiposDocumento.remove(doc.getCodTipo());
                retorno = true;
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(doc);
                setChanged();
                notifyObservers(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"El Tipo de Documento no se puede eliminar");
            }    
        } else {
            throw new ClienteExepcion("El Tipo de Documento no Existe");
        }
        return retorno;
    }

    public MedioLlegada agregarMedioLlegada(String nombre) throws Exception {
       MedioLlegada unMedioLlegada = null;
       if(!existeMedioLlegada(nombre)){
        unMedioLlegada = new MedioLlegada(nombre);
        this.mediosLlegada.put(unMedioLlegada.getCodMedio(), unMedioLlegada);
        Hotel.persistencia.update(this);
       }
       return unMedioLlegada;
    }

    private boolean existeMedioLlegada(int codMedio) {
        return this.mediosLlegada.containsKey(codMedio);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1FD38D94-DEB6-93F1-C03D-0C156236EAAE]
    // </editor-fold> 
    public void CancelarReserva(int documento, int nmrReserva) {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.053A53EA-C21F-7E18-2C89-4C799CD0C125]
    // </editor-fold> 
    public void cambiarFechasReserva(Reserva unaReserva, Date fechaEntrada, Date fechaSalida) {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F3039DC8-7832-9BFA-8801-22BC1C233A6D]
    // </editor-fold> 
    public Double cambiarNumeroHuespedes(Reserva unaReserva, int nmrHuespedes) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.34893B35-BEB1-CF90-69CA-6319E717818A]
    // </editor-fold> 
    public Double cambiarTarifa(Tarifa unaTarifa, Reserva unaReserva) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.743558B5-342E-619C-9119-DC4C5378DFC3]
    // </editor-fold> 
    public Reserva cambiarCliente(Cliente unCliente, Reserva unaReserva) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D317D036-9EA4-AF12-46C2-81E2A6B54F51]
    // </editor-fold> 
    public void cambiarHabitacion(Habitacion unaHabitacion, Reserva unaReserva) {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E99D88ED-F45E-63CF-C13B-24B0948738EB]
    // </editor-fold> 
    public Reserva registrarUnPago(Double importe, ModoPago medioPago, Reserva unaReserva) throws ReservasExepcion, Exception {

        Pago unPago = new Pago(importe, medioPago);


        unaReserva.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        unaReserva.update();
        return unaReserva;
    }
    
     public Pago registrarUnPago(Double importe, ModoPago medioPago, Estadia unaEstadia) throws ReservasExepcion, Exception {
        Pago unPago = new Pago(importe, medioPago);
        unaEstadia.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        unaEstadia.update();
        return unPago;
    }

    public void eliminarUnPago(Reserva unaReserva, Integer nmrPago) throws Exception {
        Pago unPago = unaReserva.eliminarPago(nmrPago);
        try {
            Hotel.persistencia.update(this);
            Hotel.persistencia.delete(unPago);
            unaReserva.update();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede borrar el Pago.");
        }
       

    }

    public Collection eliminarReservasCaducas(Date fecha) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.53CE6D0B-91CF-0743-0831-A4A68404CFD8]
    // </editor-fold> 
    public void reactivarReserva(int nmrReserva) {
    }

    private boolean existeTipoCliente(int codTipCliente) {

        return this.tiposClientes.containsKey(codTipCliente);
    }

  public void cargarDatos() { 
        try {
            
            TipoDocumento unTipoDoc = new TipoDocumento("DNI");  
            this.generarAuditoria("ALTA", "TIPO DOCUMENTO", "DNI", "", "nancy");
            
            String[] permiso = new String[38];       
            permiso[0] = "si";
            permiso[1] = "si";
            permiso[2] = "si";
            permiso[3] = "si";
            permiso[4] = "si";
            permiso[5] = "si";
            permiso[6] = "si";
            permiso[7] = "si";
            permiso[8] = "si";
            permiso[9] = "si";
            permiso[10] = "si";
            permiso[11] = "si";
            permiso[12] = "si";
            permiso[13] = "si";
            permiso[14] = "si";
            permiso[15] = "si";
            permiso[16] = "si";
            permiso[17] = "si";
            permiso[18] = "si";
            permiso[19] = "si";
            permiso[20] = "si";
            permiso[21] = "si";
            permiso[22] = "si";
            permiso[23] = "si";
            permiso[24] = "si";
            permiso[25] = "si";
            permiso[26] = "si";
            permiso[27] = "si";
            permiso[28] = "si";
            permiso[29] = "si";
            permiso[30] = "si";
            permiso[31] = "si";
            permiso[32] = "si";
            permiso[33] = "si";
            permiso[34] = "si";
            permiso[35] = "si";
            permiso[36] = "si";
            permiso[37] = "si";
            
            Rol unRol = this.agregarRol("ENCARGADO","ADMINISTRADOR DEL SISTEMA", permiso);
            
            this.generarAuditoria("ALTA", "ROL", "ENCARGADO", "ADMINISTRADOR DEL SISTEMA", "nancy");
            String mensaje;
            mensaje=("MD5 = " + StringMD.getStringMessageDigest("nancy", StringMD.MD5));
            Usuario unUsuario=this.agregarUsuario("36458838", "NANCY", "GANZ", unTipoDoc, "3755553213", "Las Heras 555", "nancy", mensaje, unRol, this);
            this.generarAuditoria("ALTA", "USUARIO", "GANZ NANCY 36458838", "nancy", "nancy");
            
            ControlAcceso.logueado=unUsuario;
            
            Nacionalidad unaNacionalidad;
            unaNacionalidad = this.agregarNacionalidad("ARGENTINA");
            this.generarAuditoria("ALTA", "NACIONALIDAD", "ARGENTINA", "", "nancy");
            this.agregarNacionalidad("BOLIVIA");
            this.generarAuditoria("ALTA", "NACIONALIDAD", "BOLIVIA", "", "nancy");
            this.agregarNacionalidad("BRASIL");
            this.generarAuditoria("ALTA", "NACIONALIDAD", "BRASIL", "", "nancy");
            this.agregarTipoCliente("PARTICULAR");
            this.generarAuditoria("ALTA", "TIPO CLIENTE", "PARTICULAR", "", "nancy");
            TipoCliente tipc = this.agregarTipoCliente("AGENCIA");
            this.generarAuditoria("ALTA", "NACIONALIDAD", "ARGENTINA", "", "nancy");
            
            this.agregarTipoDocumento("PASAPORTE");
            this.generarAuditoria("ALTA", "TIPO DOCUMENTO", "PASAPORTE", "", "nancy");
            this.estadosReserva.put(1, new Estado("PENDIENTE"));
            this.generarAuditoria("ALTA", "ESTADO", "PENDIENTE", "", "nancy");
            TipoHabitacion th1 = this.agregarTipoHabitacion("DOBLE", 2, 3);
            this.generarAuditoria("ALTA", "TIPO HABITACION", "DOBLE", "", "nancy");
            TipoHabitacion th2 = this.agregarTipoHabitacion("TRIPLE", 3, 4);

            Date auxx = new Date();
            MedioLlegada medio = new MedioLlegada("AUTO PARTICULAR");
            this.generarAuditoria("ALTA", "MEDIO LLEGADA", "AUTO PARTICULAR", "", "nancy");
 
            Date fecha = new Date();
            fecha.setMonth(02);
            fecha.setDate(12);
            fecha.setYear(1992);


            Date fecha1 = new Date();
            fecha1.setDate(1);
            fecha1.setMonth(5);
            Date fecha2 = new Date();
            fecha2.setDate(30);
            fecha2.setMonth(8);
            
            TipoHabitacion unTipoHabit = new TipoHabitacion("SIMPLE", 1, 2);
            this.generarAuditoria("ALTA", "TIPO HABITACION", "SIMPLE", "", "nancy");

            this.getMediosLlegada().put(medio.getCodMedio(),medio);
            this.getTiposHabitacion().put(unTipoHabit.getcod(), unTipoHabit);
            this.getTiposDocumento().put(unTipoDoc.getCodTipo(), unTipoDoc);

            String[]datos= new String[9];
            datos[0]="no";
            datos[1]="no";
            datos[2]="no";
            datos[3]="no";
            datos[4]="no";
            datos[5]="no";
            datos[6]="no";
            datos[7]="no";
            datos[8]="no";
            FormaPago unPago=this.agregarFormaPago("EFECTIVO", datos);
            this.generarAuditoria("ALTA", "FORMA PAGO", "EFECTIVO", "", "nancy");
            
            String[]datoss= new String[9];
            datoss[0]="no";
            datoss[1]="no";
            datoss[2]="no";
            datoss[3]="no";
            datoss[4]="no";
            datoss[5]="no";
            datoss[6]="no";
            datoss[7]="si";
            datoss[8]="no";
            FormaPago unPagoo=this.agregarFormaPago("TARJETA", datoss);
            this.generarAuditoria("ALTA", "FORMA PAGO", "TARJETA", "", "nancy");
            
            this.agregarConcepto("CHEQUE","EGRESO", "CHEQUE");
            this.generarAuditoria("ALTA", "CONCEPTO", "CHEQUE", "", "nancy");
            Concepto unCon=this.agregarConcepto("CAJA","INGRESO", "DINERO EN EFECTIVO");
            this.generarAuditoria("ALTA", "CONCEPTO", "CAJA", "", "nancy");
            
            Marca unaMarca = this.agregarMarca("VILLAVICENCIO");
            this.generarAuditoria("ALTA", "MARCA", "VILLAVICENCIO", "", "nancy");
            Categoria unaCategoria = this.agregarCategoria("BEBIDAS SIN ALCOHOL");
            this.generarAuditoria("ALTA", "CATEGORIA", "BEBIDAS SIN ALCOHOL", "", "nancy");
            unidadMedida unaUnidad = this.agregarUnidadMedida("BOTELLA", "BOTELLA");
            this.generarAuditoria("ALTA", "UNIDAD MEDIDA", "BOTELLA", "", "nancy");
            unidadMedida unaUnidad2 = this.agregarUnidadMedida("POR PERSONA", "PERSONA");
            this.generarAuditoria("ALTA", "UNIDAD MEDIDA", "POR PERSONA", "", "nancy");
            this.agregarServicio("CABALGATAS", "PASEOS POR EL BOSQUE", "DISPONIBLE",150,unaUnidad2);
            this.generarAuditoria("ALTA", "SERVICIO", "CABALGATAS", "", "nancy");
            Proveedor unProveedor=this.agregarProveedor("DOS MIL", "3758698562");
            this.generarAuditoria("ALTA", "PROVEEDOR", "DOS MIL", "", "nancy");
            
            this.altaPais("ARGENTINA");
            this.altaPais("BRASIL");
            Pais unPais=this.obtenerPais("ARGENTINA");
            
            Provincia unaProvincia=this.altaProvincia(unPais, "SAN LUIS");
             Provincia unaProvincia1=this.altaProvincia(unPais, "MISIONES");
            Localidad unaLocalidad=  this.altaLocalidad(unPais, unaProvincia, "VILLA MERCEDEZ");
            Localidad unaLocalidad1=  this.altaLocalidad(unPais, unaProvincia1, "PUERTO IGUAZU");
            Direccion unaDireccion=this.altaDireccion(unPais, unaProvincia, unaLocalidad, "SANTA ROSA 2068");
            
            Cliente cli1=this.AgregarCliente("34060319", unTipoDoc, tipc, "FACUNDO", "DOMINGUEZ", new Date("09/02/1989"),unaNacionalidad, "santaRosa268", "facundokpo04@gmail.com", "420041", "15555694");
            Cliente cli3= this.AgregarCliente("34060320", unTipoDoc, tipc, "JOSE", "GOMEZ", new Date("09/02/1989"), unaNacionalidad, "santaRosa268", "facundokpo04@gmail.com", "420041", "15555694");
            Cliente cli4= this.AgregarCliente("32232322", unTipoDoc, tipc, "NANCY", "GANZ", new Date("09/02/1989"), unaNacionalidad, "santaRosa268", "nan_bg1@hotmail.com", "420041", "15555694");

            cli1.agregarDireccion(unaDireccion);
            cli3.agregarDireccion(unaDireccion);
            cli4.agregarDireccion(unaDireccion);
            
            this.agregarGrupotarifa("Normal");
            this.buscarTarifaNombre("Normal").agregarTarifa(th1, 200,70);
            this.buscarTarifaNombre("Normal").agregarTarifa(th2, 280, 70);
            this.agregarGrupotarifa("Especial");
            this.buscarTarifaNombre("Especial").agregarTarifa(th1, 300,100);
            this.buscarTarifaNombre("Especial").agregarTarifa(th2, 380, 100);
            
            this.agregarHabitacion("101", th1);
            this.agregarHabitacion("102", th2);
            this.agregarHabitacion("103", th1);
            this.agregarHabitacion("104", th1);
            this.agregarHabitacion("105", th1);
            this.agregarHabitacion("106", th1);
            this.agregarHabitacion("107", th1);
            this.agregarHabitacion("108", th1);
            this.agregarHabitacion("109", th1);
            this.agregarHabitacion("110", th1);
            this.agregarHabitacion("111", th1);

            
            this.setPorcentajeConfirmacion(10);
            this.setCantDiasVencimiento(3);
            
            this.estadosReserva.put(1, new Estado("Pendiente"));
            this.generarAuditoria("ALTA", "ESTADO RESERVA", "PENDIENTE", "", "nancy");
            this.estadosReserva.put(2, new Estado("Tomada"));
            this.generarAuditoria("ALTA", "ESTADO RESERVA", "TOMADA", "", "nancy");
            this.estadosReserva.put(3, new Estado("Confirmada"));
            this.generarAuditoria("ALTA", "ESTADO RESERVA","CONFIRMADA", "", "nancy");
            this.estadosReserva.put(4, new Estado("Caduca"));
            this.generarAuditoria("ALTA", "ESTADO RESERVA", "CADUCA", "", "nancy");
            this.estadosReserva.put(5, new Estado("Finalizada"));
            this.generarAuditoria("ALTA", "ESTADO RESERVA", "FINALIZADA", "", "nancy");
                        
            this.unaCaja.abrirDiario(1500.0,0.0, "07:30", fecha2,unUsuario);
            
            this.generarAuditoria("ALTA","Movimiento","APERTURA DE CAJA","1500", ControlAcceso.getUsuario());
             
            
            Producto unProd = new Producto("AGUA-01", "AGUA MINERAL 1 LTS", unaMarca, unaUnidad, unaCategoria);
            this.productos.put(unProd.getcod(), unProd);
            this.generarAuditoria("ALTA","PRODUCTO","AGUA MINERAL 1 LTS","AGUA-01", ControlAcceso.getUsuario());
            
            Marca unaMarcaaA = this.agregarMarca("AGUA DE LAS MISIONES");
            this.generarAuditoria("ALTA", "MARCA", "VILLAVICENCIO", "", "nancy");
            
            Producto unProdd = new Producto("AGUA-02", "AGUA MINERAL 3/4 LTS", unaMarca, unaUnidad, unaCategoria);
            this.productos.put(unProdd.getcod(), unProdd);
            this.generarAuditoria("ALTA","PRODUCTO","AGUA MINERAL 3/4 LTS","AGUA-02", ControlAcceso.getUsuario());
            
            Producto unProddd = new Producto("AGUA-03", "AGUA MINERAL 1 LTS", unaMarcaaA, unaUnidad, unaCategoria);
            this.productos.put(unProddd.getcod(), unProddd);
            this.generarAuditoria("ALTA","PRODUCTO","AGUA MINERAL 1 LTS","AGUA-03", ControlAcceso.getUsuario());
            
            Producto unProdddd = new Producto("AGUA-04", "AGUA MINERAL 3/4 LTS", unaMarcaaA, unaUnidad, unaCategoria);
            this.productos.put(unProdddd.getcod(), unProdddd);
            this.generarAuditoria("ALTA","PRODUCTO","AGUA MINERAL 3/4 LTS","AGUA-04", ControlAcceso.getUsuario());
            
            
            
            
            Marca unaMarcaa = this.agregarMarca("MAGISTRAL");
            this.generarAuditoria("ALTA", "MARCA", "MAGISTRAL", "", "nancy");
            
            Categoria unaCategoriaa = this.agregarCategoria("LIMPIEZA");
            this.generarAuditoria("ALTA", "CATEGORIA", "LIMPIEZA", "", "nancy");
            
            this.agregarArticulo("DETERGENTE 1 LTS", unaUnidad, unaCategoriaa, unaMarcaa);
            this.generarAuditoria("ALTA", "ARTICULO", "DETERGENTE 1 LTS", "", "nancy");
            
            Productos_Compra unaAdquisicion;
            List<Productos_Compra> aux= new LinkedList<Productos_Compra>();
            unaAdquisicion = this.agregarAdquisicion(unProd, 8, 10);
            aux.add(unaAdquisicion);
            unaAdquisicion = this.agregarAdquisicion(unProdd, 10, 25);
            aux.add(unaAdquisicion);
            unaAdquisicion = this.agregarAdquisicion(unProddd, 12, 11);
            aux.add(unaAdquisicion);
            unaAdquisicion = this.agregarAdquisicion(unProdddd, 10, 30);
            aux.add(unaAdquisicion);
            
            Compra unaCompraa = new Compra(null, fecha, aux, 76, 762, unProveedor);
            this.compras.put(unaCompraa.getId(), unaCompraa);
            this.generarAuditoria("ALTA", "COMPRA", unProveedor.getnombre(), "762", "nancy");
            
            this.registrarUnPago(762.0,unPago,"","","","","","","","","", unaCompraa);

            
            unProd.ModificarStock(10);
            unProdd.ModificarStock(25);
            unProddd.ModificarStock(11);
            unProdddd.ModificarStock(30);
            
            Diario unDiario;
            unDiario= this.getUnaCaja().ultimoDiario();
            
           
            Concepto elConcepto = null;
           
            for (Concepto unConcepto : this.getUnaCaja().getConceptos()) {
                if (unConcepto.getDetalle().equals("APERTURA DE CAJA")) {
                    elConcepto = unConcepto;
                }
            }
            if (elConcepto==null){
                elConcepto=this.agregarConcepto("APERTURA DE CAJA", "INGRESO", "INICIO DE CAJA DIARIA");
              }
            
            
            unDiario.altaMovimiento(new Date(), "07:00", 1500.0, "INGRESO", elConcepto);
            unDiario.altaMovimiento(fecha, "09:50", 762.0, "EGRESO",unCon);
            this.generarAuditoria("ALTA", "MOVIMIENTO", "EGRESO", "762", "nancy");
            
            Iterator it = this.getCompras().entrySet().iterator();
            while(it.hasNext()) {
            Map.Entry ent = (Map.Entry)it.next();
            Compra unaCompra = (Compra)ent.getValue();
            for (Productos_Compra itt : unaCompra.getArticulos()) {
                itt.getUnArticulo().setPrecioVenta(itt.getUnPrecio()+2); 
                  }
            }
            
            this.agregarUtilizadoPara("PILETA", "MANTENIMIENTO");
            this.generarAuditoria("ALTA", "UTILIDAD", "PILETA", "", "nancy");
            
            Hotel.persistencia.update(this);
            
        } catch (Exception ex) {
            Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    static {
        persistencia = new Persistencia();
    }

    public boolean isEmptyUsuarios() {
        boolean hay = false;
        if (!this.getUsuarios().isEmpty()) {
            hay = true;

        }
        return hay;
    }

    public Usuario esUsuario(String usuario,String contrasenia) {
        Usuario retorno = null;
        for (Map.Entry ent : getUsuarios().entrySet()) {
            Usuario unUsuario = (Usuario) ent.getValue();
           if(unUsuario.getEstado()){
                String mensaje;
                mensaje=("MD5 = " + StringMD.getStringMessageDigest(contrasenia, StringMD.MD5));


                if ((unUsuario.getUsserame().toUpperCase().equals(usuario.toUpperCase())) && (unUsuario.getPasword().equals(mensaje))){
                    retorno = unUsuario;
                    break;
                }
           }
        }
        return retorno;
    }
    
    
    public void generarAuditoria(String operacion,String tabla,String dato, String detalle, String usuario) throws Exception {
        Auditoria nuevaAuditoria = new Auditoria(operacion,tabla,dato, detalle, usuario);
        this.auditorias.put(nuevaAuditoria.getId(),nuevaAuditoria);
        Hotel.persistencia.update(this);
    }
   
      public void editarEmpresa(String nombre, String telefono,  String unMail, String unaCiudad, String cuit, Foto unaFoto,Foto unaFoto2) throws Exception {
        this.direccion = unaCiudad;
        this.cuit = cuit;
        this.razonSocial = nombre;
        this.telefono = telefono;
        this.logo = unaFoto;
        this.email = unMail;
        this.logo2=unaFoto2;
        Hotel.persistencia.update(this);

    }
      
      
     public Productos_Compra agregarAdquisicion(Producto unArticulo, double unPrecio, int unaCantidad) throws Exception {
        Productos_Compra nuevaAdquisision = new Productos_Compra(unArticulo, unPrecio, unaCantidad);
        return nuevaAdquisision;
    }
     
    
   public Articulos_Compra agregarAdquisicion(Articulo unArticulo, double unPrecio, int unaCantidad, UtilizadoPara unaUtilidad) throws Exception {
        Articulos_Compra nuevaAdquisision = new Articulos_Compra(unArticulo, unPrecio, unaCantidad,unaUtilidad);
        return nuevaAdquisision;
    }
      public Proveedor obtenerEstablecimiento(String nombre) {
        Proveedor retornar = null;
        Iterator it = this.getProveedores().entrySet().iterator();
            while(it.hasNext()) {
                    Map.Entry ent = (Map.Entry)it.next();
                    Proveedor unProveedor = (Proveedor)ent.getValue();
                        if (nombre.toUpperCase().equals(unProveedor.getnombre().toUpperCase())) {
                                retornar = unProveedor;
                            }
            }
        return retornar;
    }
      
      
    public Compra agregarCompra(Numero numeroFactura, Date fecha, List<Productos_Compra> articulosRelacionados, int cantidadArticulos, double montoCompra, Proveedor unEstablecimiento) throws Exception {
        Compra nuevaCompra=null;
        if (articulosRelacionados.isEmpty()) {
            throw new Exception("Usted debe agregar al menos un articulo a la compra antes de realizarla");
        } else {
           if(numeroFactura!=null){
                nuevaCompra = new Compra(numeroFactura.getNumero(), fecha, articulosRelacionados, cantidadArticulos, montoCompra, unEstablecimiento);
                this.compras.put(nuevaCompra.getId(),nuevaCompra);
                Hotel.persistencia.update(this);
//                setChanged();
//                notifyObservers();
           }
           else{
                nuevaCompra = new Compra(null, fecha, articulosRelacionados, cantidadArticulos, montoCompra, unEstablecimiento);
                this.compras.put(nuevaCompra.getId(),nuevaCompra);
                Hotel.persistencia.update(this);
//                setChanged();
//                notifyObservers();
           }
        }
        return nuevaCompra;
    }
    
     public Venta agregarVenta(Date fecha, List<Productos_Compra> articulosRelacionados, int cantidadArticulos, double montoCompra) throws Exception {
         Venta nuevaVenta = null;
         nuevaVenta = new Venta(fecha, articulosRelacionados, cantidadArticulos, montoCompra);
         this.ventas.put(nuevaVenta.getId(), nuevaVenta);
         Hotel.persistencia.update(this);
         return nuevaVenta;
    }
    
   
   public Compra registrarUnPago (Double importe, ModoPago medioPago, Compra unaCompra) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
   
      public Compra registrarUnPago (Double importe, FormaPago medioPago, String nroRef, String entidadBancaria, String nombreTitular, String tipoCuenta,String nroCuenta,String sucursal,String cbu, String cuit,String nroDoc, Compra unaCompra) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago,nroRef,entidadBancaria,nombreTitular,tipoCuenta, nroCuenta,sucursal,cbu,cuit,nroDoc);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
      
      
      public Venta registrarUnPago (Double importe, FormaPago medioPago, String nroRef, String entidadBancaria, String nombreTitular, String tipoCuenta,String nroCuenta,String sucursal,String cbu, String cuit,String nroDoc, Venta unaVenta) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago,nroRef,entidadBancaria,nombreTitular,tipoCuenta, nroCuenta,sucursal,cbu,cuit,nroDoc);
        unaVenta.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaVenta;
    }
      
      public Pago registrarUnPago (Double importe, FormaPago medioPago, String nroRef, String entidadBancaria, String nombreTitular, String tipoCuenta,String nroCuenta,String sucursal,String cbu, String cuit,String nroDoc) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago,nroRef,entidadBancaria,nombreTitular,tipoCuenta, nroCuenta,sucursal,cbu,cuit,nroDoc);
//        unaEstadia.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unPago;
    }
      public Pago registrarUnPago (Double importe, FormaPago medioPago, String nroRef, String entidadBancaria, String nombreTitular, String tipoCuenta,String nroCuenta,String sucursal,String cbu, String cuit,String nroDoc, Estadia miEstadia) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago,nroRef,entidadBancaria,nombreTitular,tipoCuenta, nroCuenta,sucursal,cbu,cuit,nroDoc);
        miEstadia.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        this.setChanged();
        this.notifyObservers();
        return unPago;
    }
      public Pago registrarUnPago (Double importe, FormaPago medioPago, String nroRef, String entidadBancaria, String nombreTitular, String tipoCuenta,String nroCuenta,String sucursal,String cbu, String cuit,String nroDoc, Reserva miReserva) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago,nroRef,entidadBancaria,nombreTitular,tipoCuenta, nroCuenta,sucursal,cbu,cuit,nroDoc);
        miReserva.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        this.setChanged();
        this.notifyObservers();
        return unPago;
    }
       public CompraMantenimiento registrarUnPago (Double importe, FormaPago medioPago, String nroRef, String entidadBancaria, String nombreTitular, String tipoCuenta,String nroCuenta,String sucursal,String cbu, String cuit,String nroDoc, CompraMantenimiento unaCompra) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago,nroRef,entidadBancaria,nombreTitular,tipoCuenta, nroCuenta,sucursal,cbu,cuit,nroDoc);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
   
   
   public Compra registrarUnPagoDeposito (Long cbu, String NombreTitular, Long numeroOperacion, String entidadBancaria,Double importe, ModoPago medioPago, Compra unaCompra) throws ReservasExepcion, Exception {
        PagoDeposito unPago=new PagoDeposito(cbu,NombreTitular,numeroOperacion,entidadBancaria,importe, medioPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
   
   
      
  public Compra registrarUnPagoTarjeta (Long numeroReferencia, Double importe, ModoPago modoPago, Compra unaCompra) throws ReservasExepcion, Exception {
        PagoTarjetaCredito unPago=new PagoTarjetaCredito(numeroReferencia,importe, modoPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
    
     public CompraMantenimiento agregarCompraMant(Numero numeroFactura, Date fecha, List<Articulos_Compra> articulosRelacionados, int cantidadArticulos, double montoCompra, Proveedor unEstablecimiento) throws Exception {
        CompraMantenimiento nuevaCompra=null;
        if (articulosRelacionados.isEmpty()) {
            throw new Exception("Usted debe agregar al menos un articulo a la compra antes de realizarla");
        } else {
           if(numeroFactura!=null){
                nuevaCompra = new CompraMantenimiento(numeroFactura.getNumero(), fecha, articulosRelacionados, cantidadArticulos, montoCompra, unEstablecimiento);
                this.comprasMant.put(nuevaCompra.getId(),nuevaCompra);
                Hotel.persistencia.update(this);
//                setChanged();
//                notifyObservers();
           }
           else{
                nuevaCompra = new CompraMantenimiento(null, fecha, articulosRelacionados, cantidadArticulos, montoCompra, unEstablecimiento);
                this.comprasMant.put(nuevaCompra.getId(),nuevaCompra);
                Hotel.persistencia.update(this);
//                setChanged();
//                notifyObservers();
           }
        }
        return nuevaCompra;
    }
    
   
   public CompraMantenimiento registrarUnPago (Double importe, ModoPago medioPago, CompraMantenimiento unaCompra) throws ReservasExepcion, Exception {
        Pago unPago=new Pago(importe, medioPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
   
   public CompraMantenimiento registrarUnPagoDeposito (Long cbu, String NombreTitular, Long numeroOperacion, String entidadBancaria,Double importe, ModoPago medioPago, CompraMantenimiento unaCompra) throws ReservasExepcion, Exception {
        PagoDeposito unPago=new PagoDeposito(cbu,NombreTitular,numeroOperacion,entidadBancaria,importe, medioPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
      
  public CompraMantenimiento registrarUnPagoTarjeta (Long numeroReferencia, Double importe, ModoPago modoPago, CompraMantenimiento unaCompra) throws ReservasExepcion, Exception {
        PagoTarjetaCredito unPago=new PagoTarjetaCredito(numeroReferencia,importe, modoPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
  
   public CompraMantenimiento registrarUnPagoCheque (Long numeroReferencia, String entidad, Double importe, ModoPago modoPago, CompraMantenimiento unaCompra) throws ReservasExepcion, Exception {
        PagoCheque unPago=new PagoCheque(numeroReferencia,entidad,importe, modoPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
   
   public Compra registrarUnPagoCheque (Long numeroReferencia,String entidad, Double importe, ModoPago modoPago, Compra unaCompra) throws ReservasExepcion, Exception {
        PagoCheque unPago=new PagoCheque(numeroReferencia,entidad,importe, modoPago);
        unaCompra.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        return unaCompra;
    }
  private void agregarEstadia(Estadia unaEstadia) {
       this.estadias.put(unaEstadia.getNumEstadia(), unaEstadia);
       
    }

  public Estado agregarEstadoReserva(String nombre) throws Exception{
         Estado unEstado=null;
         unEstado=new Estado(nombre);
         this.estadosReserva.put(unEstado.getCodEstado(), unEstado);
         Hotel.persistencia.update(this);
       
        return unEstado;
    }
   public void cambiarCliente (int numRes,Cliente unCliente) throws ReservasExepcion{
        Reserva unaReserva=null;
                 if(this.existeReserva(numRes)){
            unaReserva=this.reservas.get(numRes);
            try {
                unaReserva.cambiarCliente(unCliente);
            } catch (Exception ex) {
                Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
            }
                  try {
                 Hotel.persistencia.update(this);
             } catch (Exception ex) {
                throw new ReservasExepcion("No se pudo actualizar la base de datos");
             }
              unaReserva.update();
               this.setChanged();
                this.notifyObservers();
        }
         else{
           throw new ReservasExepcion("La reserva no existe");
        }
                
    }

  public void cambiarEstadoReserva(Reserva unaReserva , Estado unEstado) throws Exception{
   
            unaReserva.cambiarEstado(unEstado);
            Hotel.persistencia.update(this);
        
        }

    public void cambiarHabitacion (int numRes,Habitacion unaHabitacion) throws ReservasExepcion {
         Reserva unaReserva=null;
        if(this.existeReserva(numRes)){
            unaReserva=this.reservas.get(numRes);
             try {
                 unaReserva.cambiarHabitacion(unaHabitacion);
             } catch (Exception ex) {
                 Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
             }
                  try {
                 Hotel.persistencia.update(this);
             } catch (Exception ex) {
                throw new ReservasExepcion("No se pudo actualizar la base de datos");
             }
              unaReserva.update();
               this.setChanged();
                this.notifyObservers();
        }
         else{
           throw new ReservasExepcion("La reserva no existe");
        }
    }


    public void cambiarFechasReserva(int numRes, Date fechaEntrada, Date fechaSalida) throws ReservasExepcion {
        Reserva unaReserva = null;
        if (this.existeReserva(numRes)) {
            unaReserva = (Reserva) this.reservas.get(numRes);

            if (!unaReserva.getHabitacion().estaReservada(unaReserva.getFechaSalida(), fechaSalida)
                    && !unaReserva.getHabitacion().estaReservada(fechaEntrada, unaReserva.getFechaEntrada())) {
                unaReserva.setFechaEntrada(fechaEntrada);
                unaReserva.setFechaSalida(fechaSalida);
                try {

                    Hotel.persistencia.update(this);

                } catch (Exception ex) {
                    throw new ReservasExepcion("No se pudo actualizar la base de datos");
                }
                unaReserva.update();
                this.setChanged();
                this.notifyObservers();

            } else {
                throw new ReservasExepcion("No Hay disponibilidad Para esos Dias");
            }
        } else {
            throw new ReservasExepcion("La reserva no existe");
        }


    }

    public void cambiarNumeroHuespedes(int numRes, int nmrHuespedes) throws ReservasExepcion {
        Reserva unaReserva = null;
        if (this.existeReserva(numRes)) {
            unaReserva = (Reserva) this.reservas.get(numRes);
            if (unaReserva.getHabitacion().getTipoHabitacion().getCapacidadMaxima() >= nmrHuespedes) {
                unaReserva.setCantPersonas(nmrHuespedes);
                try {
                    Hotel.persistencia.update(this);
                } catch (Exception ex) {
                    throw new ReservasExepcion("No se pudo actualizar la base de datos");
                }

                unaReserva.update();
                this.setChanged();
                this.notifyObservers();
            } else {

                throw new ReservasExepcion("El tipo de habitacion No tiene disponibilidad para la cant de huespedes");

            }
        } else {
            throw new ReservasExepcion("La reserva no existe");
        }


    }

    public void cambiarTarifa(int numRes, Tarifa unaTarifa) throws ReservasExepcion {
        Reserva unaReserva = null;
        if (this.existeReserva(numRes)) {
            unaReserva = (Reserva) this.reservas.get(numRes);
            unaReserva.setUnaTarifa(unaTarifa);
            try {
                Hotel.persistencia.update(this);
            } catch (Exception ex) {
                throw new ReservasExepcion("No se pudo actualizar la base de datos");
            }


            unaReserva.update();
            this.setChanged();
            this.notifyObservers();
        } else {
            throw new ReservasExepcion("La reserva no existe");
        }



    }

    private boolean existeReserva(int numReserva) {
        return (reservas.containsKey(numReserva));

    }



 public Estadia realizarCheckIn(Date checkIn, Date checkOut, String horaLlegada,  MedioLlegada mLlegada, String comentarios, Reserva miReserva,Cliente mTitular) throws Exception
    {
        Estadia unaEstadia=new Estadia(checkIn, checkOut,  horaLlegada,   mLlegada,  comentarios, miReserva);
        unaEstadia.cambiarTitular(mTitular);
        this.agregarEstadia(unaEstadia);
        unaEstadia.generarReservasDiarias(miReserva);
        miReserva.cambiarEstado(estadosReserva.get(2));
       // miReserva.setEstado(this.estadosReserva.get(2));
        Hotel.persistencia.update(this);
        this.setChanged();
        this.notifyObservers();
         
        return unaEstadia;
    
    
    }

 public Reserva registrarUnPagoTarjeta (Long numeroReferencia, Double importe, ModoPago modoPago, Reserva unaReserva) throws ReservasExepcion, Exception {
        
        PagoTarjetaCredito unPago=new PagoTarjetaCredito(numeroReferencia,importe, modoPago);
       
        
        unaReserva.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        unaReserva.update();
        return unaReserva;
    }

  public Pago registrarUnPagoTarjeta (Long numeroReferencia, Double importe, ModoPago modoPago, Estadia unaEstadia) throws ReservasExepcion, Exception {
       PagoTarjetaCredito unPago=new PagoTarjetaCredito(numeroReferencia,importe, modoPago);
        unaEstadia.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        unaEstadia.update();
        return unPago;
    }
 
 public Reserva registrarUnPagoDeposito (Long cbu, String NombreTitular, Long numeroOperacion, String entidadBancaria,Double importe, ModoPago medioPago, Reserva unaReserva) throws ReservasExepcion, Exception {
        PagoDeposito unPago=new PagoDeposito(cbu,NombreTitular,numeroOperacion,entidadBancaria,importe, medioPago);
        unaReserva.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        unaReserva.update();
        return unaReserva;
    }
 
 public Pago registrarUnPagoDeposito (Long cbu, String NombreTitular, Long numeroOperacion, String entidadBancaria,Double importe, ModoPago medioPago, Estadia unaEstadia) throws ReservasExepcion, Exception {
        PagoDeposito unPago=new PagoDeposito(cbu,NombreTitular,numeroOperacion,entidadBancaria,importe, medioPago);
        unaEstadia.agregarUnPago(unPago);
        Hotel.persistencia.update(this);
        unaEstadia.update();
        return unPago;
    }
    
 public Collection consultaEmpleado(String  consulta){
    List retorno = new LinkedList();
     Producto aux = null;
     for (Object unreg : this.getProductos().values()) {
            aux=(Producto)unreg;
            if (aux.getcod().toUpperCase().startsWith(consulta.toUpperCase()) ) {
                retorno.add(aux);
            }     
        }
        return retorno;
    }
 
 public Collection consultaEmpleado2(String  consulta){
     List retorno = new LinkedList();
     Servicio aux = null;
     for (Object unreg : this.getservicios().values()) {
            aux=(Servicio)unreg;
            if (aux.getnombre().toUpperCase().startsWith(consulta.toUpperCase()) ) {
                retorno.add(aux);
            }     
        }
        return retorno;
    }
 
  public Collection consultaEmpleado3(String  consulta){
    List retorno = new LinkedList();
     Articulo aux = null;
     for (Object unreg : this.getarticulos().values()) {
            aux=(Articulo)unreg;
            if (aux.getnombre().toUpperCase().startsWith(consulta.toUpperCase()) ) {
                retorno.add(aux);
            }     
        }
        return retorno;
    }
 
 public boolean ModificarPrecio (String categoria, double porcentaje) throws Exception{
        double calculo=0.0;
        boolean bandera=true;
        try {
            Iterator it = this.getCompras().entrySet().iterator();
            while(it.hasNext()) {
            Map.Entry ent = (Map.Entry)it.next();
            Compra unaCompra = (Compra)ent.getValue();
            for (Productos_Compra itt : unaCompra.getArticulos()) {
                if(itt.getUnArticulo().getUnaCategoria().getnombre().toUpperCase().equals(categoria.toUpperCase())){   
                    calculo=(itt.getUnPrecio()+((itt.getUnPrecio()*porcentaje)/100));
                    calculo = (calculo*100);
                    calculo = java.lang.Math.round(calculo);
                    calculo = (calculo/100);
                    itt.setUnPrecio(calculo);

                }
                  }
            }
            Hotel.persistencia.update(this);
            this.setChanged();
            this.notifyObservers();
     } catch (Exception e) {
         bandera=false;
     } 
     return bandera;
 }
 public Huesped buscarHuesped(String documento){
      return(huespedes.get(documento));
    }
 public Boolean existeHuesped(String documento){
      return(huespedes.containsKey(documento));
    }
  public Huesped agregarHuesped(String documento,TipoDocumento  unTipoDoc,  String nombre, String apellido, Nacionalidad unaNacionalidad,Estadia unaEstadia) throws  Exception  {
       Huesped retorno=null;
       
       if(this.estaHospedado(documento)){
       throw new Exception("El Huesped ya esta hospedado");
       }
       
      else if(!this.existeHuesped(documento)){
         retorno=new Huesped( documento, unTipoDoc, apellido, nombre, unaNacionalidad);
         this.huespedes.put(retorno.getDocumento(), retorno);
         unaEstadia.agregarHuesped(retorno);
         Hotel.persistencia.update(this);
         this.setChanged();
         this.notifyObservers();
        
        }else
       {
       
       retorno=this.buscarHuesped(documento);
       unaEstadia.agregarHuesped(retorno);
         Hotel.persistencia.update(this);
         this.setChanged();
         this.notifyObservers();
       }
       
       return retorno;
    }
  
  public boolean estaHospedado(String dniHue){
  boolean retorno=false;
   Estadia aux = null;
     for (Object unreg : this.getEstadias().values()) {
            aux=(Estadia)unreg;
            if(aux.isActiva()){
              if(aux.existeHuesped(dniHue)){
                retorno=true;
              }
            }
          
              
        }
 
  
  return retorno;
  
  
  }
 public void notificar(){
   setChanged();
   notifyObservers();
 }
// <editor-fold defaultstate="collapsed" desc="Agregado Facundo">
    public List<Reserva> salenHoy(Date fecha){
      Reserva aux = null;
        List<Reserva> retorno = new LinkedList<Reserva>();
        Iterator iterador = this.reservas.values().iterator();
        while (iterador.hasNext()) {
            aux = (Reserva) iterador.next();
            if (aux.isActiva()) {
                if(aux.saleHoy(fecha)){
                retorno.add(aux);
                }
            }
        }

        return retorno;
     
     } 
    public List<Reserva> EntranHoy(Date fecha){
      Reserva aux = null;
        List<Reserva> retorno = new LinkedList<Reserva>();
        Iterator iterador = this.reservas.values().iterator();
        while (iterador.hasNext()) {
            aux = (Reserva) iterador.next();
            if (aux.isActiva()) {
                if(aux.entraHoy(fecha)){
                retorno.add(aux);
                }
            }
        }

        return retorno;
     
     } 
    public Habitacion agregarHabitacion(String nombre, TipoHabitacion unTipoHabitacion) throws Exception {
        Habitacion unaHabitacion = null;
        unaHabitacion = new Habitacion(nombre, unTipoHabitacion);
        this.generarAuditoria("ALTA", "HABITACION", nombre, unTipoHabitacion.getDescripcion(),  ControlAcceso.getUsuario());
        this.habitaciones.put(nombre, unaHabitacion);
        Hotel.persistencia.update(this);
        this.setChanged();
        this.notifyObservers();
        return unaHabitacion;

    }
    public Habitacion modificarHabitacion(String idHabitacion, TipoHabitacion unTipoHabitacion)throws Exception{
      Habitacion retorno = null;
        retorno = this.buscarHabitacion(idHabitacion);
        if(retorno!=null){
            if(!retorno.tieneReservaAsoc()){
            retorno.cambiarTipoHabitacion(unTipoHabitacion);
            this.generarAuditoria("MODIFICACION", "HABITACION", unTipoHabitacion.getDescripcion(), "", ControlAcceso.getUsuario());
            Hotel.persistencia.update(this);
            this.setChanged();
            this.notifyObservers();
            }
            else{
            throw new ClienteExepcion("La Habitacion no se Puede Modificar por que Tiene Reservas Asociadas");
            }
        }
        else{
          throw new ClienteExepcion("La Habitacion no Existe");
        
        }
     
        
        return retorno;
    
    }
    public Habitacion eliminarHabitacion(String idHabitacion)throws Exception{
       Habitacion retorno = null;
        retorno = this.buscarHabitacion(idHabitacion);
        if(retorno!=null){
            if(!retorno.tieneReservaAsoc()){
                retorno.getTipoHabitacion().quitarHabitacion(retorno);
                this.generarAuditoria("BAJA", "HABITACION", retorno.getIdHabitacion(), "", ControlAcceso.getUsuario());
                this.habitaciones.remove(retorno.getIdHabitacion());
                Hotel.persistencia.update(this);
                Hotel.persistencia.delete(retorno);
            }
            else{
            throw new ClienteExepcion("La Habitacion no se Puede Eliminar por que Tiene Reservas Asociadas");
            }
        }
        else{
          throw new ClienteExepcion("La Habitacion no Existe");
        
        }
     
        
        return retorno;
    
    }
    
public Habitacion buscarHabitacion(String idHabitacion){
    
    return this.habitaciones.get(idHabitacion);

}
 public TipoHabitacion buscarTipoHabitacionNombre(String descripcion) {
        TipoHabitacion aux = null;
        TipoHabitacion retorno = null;
        Iterator iterador = this.tiposHabitacion.values().iterator();
        while (iterador.hasNext()) {
            aux = (TipoHabitacion) iterador.next();
            if (aux.isTipoHabitacion(descripcion)) {
                retorno = aux;
            }
        }

        return retorno;
    }
    public List<Cliente> buscarClientePais(Pais unPais) {
        Cliente aux = null;
        List retorno = new LinkedList();
        Iterator iterador = this.getClientes().values().iterator();
        while (iterador.hasNext()) {
            aux = (Cliente) iterador.next();
            if (aux.getDomicilio().getPais().equals(unPais)) {
                retorno.add(aux);
            }
        }
        return retorno;
    }

    public List<Cliente> buscarClienteProvincia(Provincia unaProvincia) {
        Cliente aux = null;
        List retorno = new LinkedList();
        Iterator iterador = this.getClientes().values().iterator();
        while (iterador.hasNext()) {
            aux = (Cliente) iterador.next();
            if (aux.getDomicilio().getProvincia().equals(unaProvincia)) {
                retorno.add(aux);
            }
        }
        return retorno;
    }

    public List<Cliente> buscarClienteCiudad(Localidad unaLocalidad) {
        Cliente aux = null;
        List retorno = new LinkedList();
        Iterator iterador = this.getClientes().values().iterator();
        while (iterador.hasNext()) {
            aux = (Cliente) iterador.next();
            if (aux.getDomicilio().getLocalidad().equals(unaLocalidad)) {
                retorno.add(aux);
            }
        }
        return retorno;
    }

    public TipoCliente buscarUntipoCliente(String tipoCliente) {

        TipoCliente aux = null;
        TipoCliente retorno = null;
        Iterator iterador = this.tiposClientes.values().iterator();
        while (iterador.hasNext()) {
            aux = (TipoCliente) iterador.next();
            if (aux.isNombre(tipoCliente)) {
                retorno = aux;
            }
        }
        return retorno;


    }

    public Map consultaCliente(String consulta) {
        Map retorno = new HashMap();
        Cliente aux = null;



        for (Object unCliente : this.getClientes().values().toArray()) {
            aux = (Cliente) unCliente;
            if (aux.getApellido().startsWith(consulta.toUpperCase()) || aux.getNombre().startsWith(consulta.toUpperCase()) || aux.getDocumento().startsWith(consulta.toUpperCase()) || aux.getUnTipoCli().getNombre().startsWith(consulta.toUpperCase())) {
                retorno.put(aux.getDocumento(), aux);
            }
        }
        return retorno;
    }

    public Map buscarClienteNac(Nacionalidad unaNacionalidad) {
        Map retorno = new HashMap();
        Cliente aux = null;



        for (Object unCliente : this.getClientes().values().toArray()) {
            aux = (Cliente) unCliente;
            if (aux.isNacionalida(unaNacionalidad.getNombre())) {
                retorno.put(aux.getDocumento(), aux);
            }
        }
        return retorno;
    }

    public Estado getEstadoReserva(Integer key) {

        return estadosReserva.get(key);

    }

    public Reserva buscarReserva(Integer nmrReserva) {

        Reserva retorno = null;
        Reserva aux = null;



        for (Object unaReserva : this.getReservas().values().toArray()) {
            aux = (Reserva) unaReserva;
            if (aux.isNumeroReserva(nmrReserva)) {
                retorno = aux;
                break;
            }
        }
        return retorno;


    }

    public GrupoTarifas agregarGrupotarifa(String nombre) throws Exception {
        GrupoTarifas retorno = null;
        if (this.buscarTarifaNombre(nombre) == null) {
            retorno = new GrupoTarifas(nombre);
            this.generarAuditoria("ALTA","TARIFA",nombre,"", ControlAcceso.getUsuario()); 
            this.gruposTarifas.put(retorno.getCodGrupTarifa(), retorno);
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers();
        } else {
            throw new ClienteExepcion("la Tarifa Ya Existe");
        }
        return retorno;
    }

    public GrupoTarifas buscarTarifaNombre(String nombre) {
        GrupoTarifas aux = null;
        GrupoTarifas retorno = null;
        Iterator iterador = this.gruposTarifas.values().iterator();
        while (iterador.hasNext()) {
            aux = (GrupoTarifas) iterador.next();
            if (aux.isTarifa(nombre)) {
                retorno = aux;
            }
        }

        return retorno;
    }
      public Estadia buscarEstadia(Integer nmrEstadia) {

        Estadia retorno = null;
        Estadia aux = null;



        for (Object unaEstadia : this.getEstadias().values().toArray()) {
            aux =(Estadia)unaEstadia;
            if (aux.isEstadia(nmrEstadia)) {
                retorno = aux;
                break;
            }
        }
        return retorno;


    }
    // <editor-fold defaultstate="collapsed" desc=" ABM de Localidad "> 
    public Direccion obtenerDireccion(Pais unPais, Provincia unaProvincia, Localidad unaLocalidad, String unDomicilio) {
        List<Direccion> d = this.getDirecciones();
        Direccion retornar = null;
        for (Direccion unaDir : d) {
            if (unaDir.getPais().equals(unPais) && unaDir.getProvincia().equals(unaProvincia) && unaDir.getLocalidad().equals(unaLocalidad) && unaDir.getDomicilio().toUpperCase().trim().equals(unDomicilio.toUpperCase().trim())) {
                retornar = unaDir;
                break;
            }
        }
        return retornar;
    }

    public List<Direccion> seUtilizaLocalidad(Localidad unaLocalidad) {
        List<Direccion> retornar = new LinkedList<Direccion>();
        for (Direccion unadir : this.getDirecciones()) {
            if (unadir.getLocalidad().equals(unaLocalidad)) {
                retornar.add(unadir);
            }
        }
        return retornar;
    }

    public boolean utilizaLocalidad(String nombreLocalidad) {
        boolean utiliza = false;
        List<Direccion> d = this.getDirecciones();
        for (Direccion unaDir : d) {
            if (unaDir.getLocalidad().getNombre().equals(nombreLocalidad)) {
                utiliza = true;
            }
        }
        return utiliza;
    }

    public Localidad altaLocalidad(Pais unPais, Provincia unaProvincia, String nombreLocalidad) throws Exception {
        Localidad unaLocalidad = unPais.altaLocalidad(unaProvincia, nombreLocalidad);
        this.localidades.add(unaLocalidad);
        this.generarAuditoria("ALTA", "LOCALIDAD", nombreLocalidad,"",ControlAcceso.getUsuario());
        Hotel.persistencia.update(this);
        Hotel.persistencia.update(unPais);
        setChanged();
        notifyObservers();

        return unaLocalidad;
    }

    public void bajaLocalidad(Pais unPais, Provincia unaProvincia, Localidad unaLocalidad) throws Exception {
        if (utilizaLocalidad(unaLocalidad.getNombre())) {
            throw new Exception("La localidad: " + unaLocalidad.getNombre() + " no puede ser eliminada");
        } else {
            unPais.bajaLocalidad(unaProvincia, unaLocalidad);
            this.generarAuditoria("BAJA", "LOCALIDAD", unaLocalidad.getNombre(),"",ControlAcceso.getUsuario());
            Hotel.persistencia.update(this);
            Hotel.persistencia.update(unPais);
            setChanged();
            notifyObservers();
        }

    }

    public void modificarLocalidad(Pais unPais, Provincia unaProvincia, Localidad unaLocalidad, String nombreLocalidad) throws Exception {

        Localidad nuevaLocalidad = unPais.modificarLocalidad(unaProvincia, unaLocalidad, nombreLocalidad);

        List<Direccion> aModificar = seUtilizaLocalidad(unaLocalidad);
        if (!aModificar.isEmpty()) {
            for (Direccion unaDir : aModificar) {
                this.generarAuditoria("MODIFCACION", "LOCALIDAD", nombreLocalidad,unaLocalidad.getNombre(),ControlAcceso.getUsuario());
                unaDir.setLocalidad(nuevaLocalidad);
                Hotel.persistencia.update(this);
                Hotel.persistencia.update(unPais);
                setChanged();
                notifyObservers();
            }
        }


    }

    public Direccion altaDireccion(Pais unPais, Provincia unaProvincia, Localidad unaLocalidad, String unDomicilio) throws Exception {
        Direccion unaDireccion = obtenerDireccion(unPais, unaProvincia, unaLocalidad, unDomicilio);
        if (unaDireccion != null) {
            return unaDireccion;
        } else {
            unaDireccion = new Direccion(unPais, unaProvincia, unaLocalidad, unDomicilio);
            this.direcciones.add(unaDireccion);
            Hotel.persistencia.update(this);
            return unaDireccion;
        }
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc=" ABM de Provincia "> 

    public boolean utilizaProvincia(String nombreProvincia) {
        boolean utiliza = false;
        List<Direccion> d = this.getDirecciones();
        for (Direccion unaDireccion : d) {
            if (unaDireccion.getProvincia().getNombre().equals(nombreProvincia)) {
                utiliza = true;
            }
        }
        return utiliza;
    }

    public Provincia altaProvincia(Pais unPais, String nombreProvincia) throws Exception {
        Provincia unaProvincia = unPais.altaProvincia(nombreProvincia);
        this.provincias.add(unaProvincia);
        this.generarAuditoria("ALTA", "PROVINCIA", nombreProvincia,"",ControlAcceso.getUsuario());
        this.setChanged();
        this.notifyObservers();
        return unaProvincia;
    }

    public void bajaProvincia(Pais unPais, Provincia unaProvincia) throws Exception {
        if (unPais.existeProvincia(unaProvincia)) {
            if (!utilizaProvincia(unaProvincia.getNombre())) {
                this.provincias.remove(unaProvincia);
                this.generarAuditoria("ALTA", "PROVINCIA", unaProvincia.getNombre(),"",ControlAcceso.getUsuario());
                unPais.bajaProvincia(unaProvincia);
                this.setChanged();
                this.notifyObservers();


            } else {
                throw new Exception("La provincia seleccionada esta siendo utilizada en otra instancia por lo que no puede ser eliminada");
            }
        }
    }

    public void modificarProvincia(Pais unPais, Provincia unaProvincia, String nombreProvincia) throws Exception {
        unPais.modificarProvincia(unaProvincia, nombreProvincia);
        this.generarAuditoria("MODIFICACION", "PROVINCIA", nombreProvincia,unaProvincia.getNombre(),ControlAcceso.getUsuario());
        this.setChanged();
        this.notifyObservers();
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc=" ABM de Pais ">
    public boolean utilizaPais(String nombrePais) {
        boolean utiliza = false;
        List<Direccion> d = this.getDirecciones();
        for (Direccion unDomicilio : d) {
            if (unDomicilio.getPais().getNombre().toUpperCase().equals(nombrePais.toUpperCase())) {
                utiliza = true;
            }
        }
        return utiliza;
    }

    public boolean existePais(Pais unPais) {
        boolean existe = false;

        for (Pais el : this.getPaises()) {
            if (unPais.equals(el)) {
                existe = true;
            }
        }
        return existe;
    }

   
    
    public Pais obtenerPais(String nombrePais) {
        Pais devolver = null;
        if (!this.getPaises().isEmpty()) {
            for (Pais unPais : this.getPaises()) {
                if (unPais.getNombre().toUpperCase().equals(nombrePais.toUpperCase())) {
                    devolver = unPais;
                    break;
                }
            }
        }
        return devolver;
    }


    public void altaPais(String nombrePais) throws Exception {
        nombrePais = nombrePais.trim();
        if (obtenerPais(nombrePais) != null) {
            throw new Exception("El País: " + nombrePais + " ya existe");
        } else {
            Pais unPais = new Pais(nombrePais);
            this.paises.add(unPais);
            this.generarAuditoria("ALTA", "PAIS", nombrePais,"",ControlAcceso.getUsuario());
            Hotel.persistencia.update(this);
            setChanged();
            notifyObservers(unPais);
        }
    }
    

    public void bajaPais(Pais unPais) throws Exception {
        if (existePais(unPais)) {
            if (!utilizaPais(unPais.getNombre())) {
                this.paises.remove(unPais);
                this.generarAuditoria("BAJA", "PAIS", unPais.getNombre(),"",ControlAcceso.getUsuario());
                Hotel.persistencia.delete(unPais);
                Hotel.persistencia.update(this);
            } else {
                throw new Exception("El País " + unPais.getNombre() + " no puede ser eliminado");
            }
        }
    }
    

    public void modificaPais(Pais unPais, String nombrePais) throws Exception {
        if (existePais(unPais)) {
            unPais.setNombre(nombrePais);
            this.generarAuditoria("MODIFICACION", "PAIS", unPais.getNombre(),nombrePais,ControlAcceso.getUsuario());
            Hotel.persistencia.update(this);
        }
    }
    

// </editor-fold>   
 //</editor-fold >
}
