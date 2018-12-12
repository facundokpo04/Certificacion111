public class GestorCampos {

    private String razonSocial;

    private String cuit;


    public GestorCampos(String razonSocial, String cuit) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
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

    public Boolean registrarTipoSuelo(String nombre, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean registrarEstadoCampo(String Nombre, String Descripcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean registrarCampo(String Nombre, Float superficieHa) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean registrarLote(Integer numero, Float Superficie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean existeCampo(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
