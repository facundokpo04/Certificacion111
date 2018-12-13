package Logica.Servicio;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;


public class Lista_articulos extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Productos_Compra) {
            JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            listCellRendererComponent.setText(((Productos_Compra) value).toLista());
            return listCellRendererComponent;
        } else {
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }

    }
}
