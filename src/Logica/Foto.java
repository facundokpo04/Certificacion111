package Logica;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.ImageIcon;
import org.hibernate.annotations.Type;

@Entity
public class Foto extends ImageIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Type(type = "org.hibernate.type.PrimitiveByteArrayBlobType")
    private byte[] foto;

    public Foto() {
    }

    public Foto(String url) throws Exception {
        super(url);
        foto = this.toArrayByte(url);
        Hotel.persistencia.insert(this);

    }

    public Foto(byte[] foto, String extencion) throws Exception {
        super(foto);
        this.foto = foto;
        Hotel.persistencia.insert(this);

    }

    public Object[] toVector() {
        return new Object[]{this};
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (getId() == null || ((Foto) obj).getId() == null) {
            return false;
        }
        return ((Foto) obj).getId().equals(this.getId());
    }

    @Override
    public Image getImage() {

        return new ImageIcon(foto).getImage();


    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
     public static byte[] toArrayByte(String url) {
        File file = new File(url);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFile;
    }
    
}
