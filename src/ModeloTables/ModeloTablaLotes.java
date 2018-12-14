/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTables;


import Logica.Lote;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Facu
 */



public class ModeloTablaLotes extends DefaultTableModel {
     public String[] columnNames = {"Nro","Superficie","Tipo de Suelo"} ;
     public Object[][] data2;
     Collection lotes = new LinkedList();
     
     public ModeloTablaLotes ( ){
       
       
          }
    
     public ModeloTablaLotes (Collection lista ){
       
         this.lotes = lista;
//            System.out.println(articulos.size());
      if (!lotes.isEmpty()) {
           this.data2 = new Object[lotes.size()][3];
            int i = 0;
       for(Object unObj : lotes){
           Lote unLote = (Lote) unObj;
            data2[i][0] =unLote.getNumero().toString();
            data2[i][1] =unLote.getSuperficie().toString();
            data2[i][2]= unLote.getTipoSuelo().toString();
           i++;}
       }
          }
     
    @Override
 public int getColumnCount() { return this.columnNames.length; } //retormanos el numero de elementos //del array de datos

  
    @Override
 public int getRowCount() { 
      if(this.lotes!=null)  
        return this.lotes.size();
      else 
          return 0;
    } //retornamos el elemento indicado

 
   
   public String getColumnName(int col) { return this.columnNames[col]; } //y lo mismo para las celdas

    @Override
   public Object getValueAt(int row, int col) { return this.data2[row][col]; }
   /* * Este metodo sirve para determinar el editor predeterminado * para cada columna de celdas */

//   public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }
   /* * No tienes que implementar este método a menos que * las celdas de tu tabla sean Editables */

    @Override
   public boolean isCellEditable(int row, int col) {
   
           return false;}
       /* * No tienes que implementar este método a menos que * los datos de tu tabla cambien */

    @Override
   public void setValueAt(Object value, int row, int col) {
      if (value==null) {
           data2[row][col] = new Integer(0);
       }
      else {
           data2[row][col] = value;
       }
      fireTableCellUpdated(row, col); }

public void recargar(Collection lista){
    
          this.lotes = lista;
//            System.out.println(articulos.size());
      if (!lotes.isEmpty()) {
           this.data2 = new Object[lotes.size()][3];
            int i = 0;
       for(Object unObj : lotes){
           Lote unLote = ( Lote) unObj;
           data2[i][0] =unLote.getNumero().toString();
            data2[i][1] =unLote.getSuperficie().toString(); 
              data2[i][2] =unLote.getTipoSuelo().toString();
           i++;}
       }
       
}

}