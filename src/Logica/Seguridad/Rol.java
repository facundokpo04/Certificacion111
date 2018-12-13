package Logica.Seguridad;

import Logica.Hotel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rol implements Serializable {
    @Id
    private String nombre;
    private String comentario;
    private String []permiso=new String[38];
    private boolean estado;
    
    
    public Rol() {
        
    }

    public Rol(String nombre,String comentario,String []permiso) throws Exception {
        this.nombre=nombre.toUpperCase();
        this.permiso=permiso;
        this.comentario=comentario.toUpperCase();
        this.estado=true;
        Hotel.persistencia.insert(this);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
     public String[] getPermiso() {
        return permiso;
    }

    public void setPermiso(String []permiso) {
        this.permiso = permiso;
    }
    
    
    public String permisos(String []permiso){
       String aux="";
//       if (permiso[0].toString().equals("si")){
//            aux="Reserva, CHECK IN, CHECK OUT, Pagos,";}
//       else{
//       if (permiso[1].toString().equals("si")){
//            aux=aux +" "+ "CHECK IN,";}
//       if (permiso[2].toString().equals("si")){
//            aux=aux +" "+ "CHECK OUT,";}
//       if (permiso[3].toString().equals("si")){
//            aux=aux +" "+ "Pagos,";}
//       }
//       if (permiso[4].toString().equals("si")){
//            aux=aux +" "+ "Caja, Apertura, Cierre, Igreso - Egreso,";}
//       else{
//        if (permiso[5].toString().equals("si")){
//             aux=aux +" "+ "Apertura de Caja,";}
//        if (permiso[6].toString().equals("si")){
//             aux=aux +" "+ "Cierre de Caja,";}
//        if (permiso[7].toString().equals("si")){
//             aux=aux +" "+ "Ingreso - Egreso,";}
//       }
//       if (permiso[8].toString().equals("si")){
//            aux=aux +" "+ "Mantenimiento,";}
//       if (permiso[9].toString().equals("si")){
//            aux=aux +" "+ "Parametros, Tipo Doc, Nacionalidad, Modo Llegada, Modo Pago, Tipo Habitación, Estado Habitación, Conceptos, Unidad de Medida, Categoría, Tipo Cliente, ";}
//       else{
//           if (permiso[10].toString().equals("si")){
//            aux=aux +" "+ "Tipo Doc,";}
//           if (permiso[11].toString().equals("si")){
//            aux=aux +" "+ "Nacionalidad,";}
//           if (permiso[12].toString().equals("si")){
//            aux=aux +" "+ "Modo Llegada,";}
//           if (permiso[13].toString().equals("si")){
//            aux=aux +" "+ "Modo Pago,";}
//           if (permiso[14].toString().equals("si")){
//            aux=aux +" "+ "Tipo Habitación,";}
//           if (permiso[15].toString().equals("si")){
//            aux=aux +" "+ "Estado Habitación,";}
//           if (permiso[16].toString().equals("si")){
//            aux=aux +" "+ "Conceptos,";}
//           if (permiso[17].toString().equals("si")){
//            aux=aux +" "+ "Unidad de Medida,";}
//           if (permiso[18].toString().equals("si")){
//            aux=aux +" "+ "Categoria,";}
//           if (permiso[19].toString().equals("si")){
//            aux=aux +" "+ "Tipo Cliente,";}
//         }
//       
//       if (permiso[20].toString().equals("si")){
//            aux=aux +" "+ "Cliente,";}
//       
//       if (permiso[21].toString().equals("si")){
//           aux=aux +" "+ "Servicio,";}
//      
//       if (permiso[22].toString().equals("si")){
//            aux=aux +" "+ "Seguridad, Usuario, Rol,";}
//       else{
//            if (permiso[23].toString().equals("si")){
//            aux=aux +" "+ "Usuario,";}
//            if (permiso[24].toString().equals("si")){
//            aux=aux +" "+ "Rol,";}
//       }
//       
//       if (permiso[25].toString().equals("si")){
//            aux=aux +" "+ "Auditoria,";}
//       
//       if (permiso[26].toString().equals("si")){
//            aux=aux +" "+ "Backups,";}
//       
//       if (permiso[27].toString().equals("si")){
//           aux=aux +" "+ "Infomres.";  
//    }
       return aux;
    }
    
      @Override
    public String toString() {
        return this.getNombre();
    }
   
    
}
