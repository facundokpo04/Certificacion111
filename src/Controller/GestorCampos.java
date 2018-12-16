package Controller;

import Dao.CamposDaoHibernateImpl;
import Dao.LotesDaoHibernateImpl;
import Dao.TiposSueloDaoHibernate;
import Logica.Campo;
import Logica.EstadoCampo;
import Logica.Lote;
import Logica.TipoSuelo;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.SessionFactory;

public class GestorCampos {

    private String razonSocial;
    private String cuit;
    private List<Lote> lotes;
    private List<Campo> campos;
    private List<EstadoCampo> estadosCampo;
    private List<TipoSuelo> tiposSuelo;

    private final SessionFactory sessionFactory;

    private final CamposDaoHibernateImpl camposDao;
    private final LotesDaoHibernateImpl lotesDao;
    private final TiposSueloDaoHibernate tsDao;

    public GestorCampos(String razonSocial, String cuit, SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
        this.camposDao = new CamposDaoHibernateImpl(sessionFactory);
        this.lotesDao = new LotesDaoHibernateImpl(sessionFactory);
        this.tsDao = new TiposSueloDaoHibernate(sessionFactory);

        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.lotes = new LinkedList<Lote>();
        this.campos = new LinkedList<Campo>();
        this.estadosCampo = new LinkedList<EstadoCampo>();
        this.tiposSuelo = new LinkedList<TipoSuelo>();
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public void setEstadosCampo(List<EstadoCampo> estadosCampo) {
        this.estadosCampo = estadosCampo;
    }

    public void setTiposSuelo(List<TipoSuelo> tiposSuelo) {
        this.tiposSuelo = tiposSuelo;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public List<EstadoCampo> getEstadosCampo() {
        return estadosCampo;
    }

    public List<TipoSuelo> getTiposSuelo() {
        return tiposSuelo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public TipoSuelo registrarTipoSuelo(String nombre, String descripcion) {
        TipoSuelo unTipoSuelo = new TipoSuelo(nombre, descripcion);
        this.tiposSuelo.add(unTipoSuelo);
         this.tsDao.guardar(unTipoSuelo);
        return unTipoSuelo;
    }

    public EstadoCampo registrarEstadoCampo(String nombre, String descripcion) {
        EstadoCampo unEstadoCampo = new EstadoCampo(nombre, descripcion);
        this.estadosCampo.add(unEstadoCampo);
        return unEstadoCampo;
    }

    public Campo registrarCampo(String nombre, Float superficieHa) throws Exception {
        if (this.existeCampo(nombre)) {
            throw new Exception("El campo: " + nombre + " ya existe");

        } else {
            Campo unCampo = new Campo(nombre, superficieHa);
            this.campos.add(unCampo);
            this.camposDao.guardar(unCampo);
            return unCampo;
        }

    }

    public Lote registrarLote(Integer numero, Float superficie) {
        Lote unLote = new Lote(numero, superficie);
        this.lotes.add(unLote);
        this.lotesDao.guardar(unLote);
        return unLote;
    }

    public void asignarLote(Lote unLote, Campo unCampo) throws Exception {

        unCampo.asignarLote(unLote);
        this.camposDao.actualizar(unCampo);

    }

    public Boolean existeCampo(String nombre) {
        boolean existe = false;
        List<Campo> l = this.getCampos();
        for (Campo unCampo : l) {
            if (unCampo.getNombre().equals(nombre)) {
                existe = true;
            }
        }
        return existe;
    }
}
