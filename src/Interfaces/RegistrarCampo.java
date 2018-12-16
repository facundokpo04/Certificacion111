/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Logica.Campo;
import Controller.GestorCampos;
import Logica.Lote;
import Logica.TipoSuelo;
import ModeloTables.ModeloTablaLotes;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author facundo
 */
public class RegistrarCampo extends javax.swing.JFrame {

    private GestorCampos miGestor;
    private List<Lote> lotes;
    private Campo miCampo;
    ModeloTablaLotes miModTabLotes;
    TableRowSorter<TableModel> elqueOrdena;

    /**
     * Creates new form RegistrarCampo
     */
    public RegistrarCampo() {

        initComponents();
    }

    public RegistrarCampo(GestorCampos unGestor) throws Exception {
        this.miGestor = unGestor;
        this.lotes = new LinkedList<Lote>();
        List<TipoSuelo> l = this.miGestor.getTiposSuelo();
        this.miModTabLotes = new ModeloTablaLotes();
        this.elqueOrdena = new TableRowSorter<TableModel>(miModTabLotes);

        initComponents();

        this.jTableLotes.setModel(miModTabLotes);
        this.jTableLotes.setRowSorter(this.elqueOrdena);
        this.enablePanel(false);
        for (TipoSuelo untp : l) {

            this.jCbxTiposSuelo.addItem(untp);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCampo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtNombreCampo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jbtnagrCampo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jtxtSuperficie = new javax.swing.JFormattedTextField();
        jPanelLotes = new javax.swing.JPanel();
        jPanelLotes2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCbxTiposSuelo = new javax.swing.JComboBox<>();
        jbtnagrLote = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jtxtNroLote = new javax.swing.JFormattedTextField();
        jtxtSupLote = new javax.swing.JFormattedTextField();
        jToolBar2 = new javax.swing.JToolBar();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLotes = new javax.swing.JTable();
        btnAceptar2 = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelCampo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Superficie:");

        jtxtNombreCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreCampoActionPerformed(evt);
            }
        });

        jLabel3.setText("Usuario:");

        jLabel4.setText("FacundoD");

        jbtnagrCampo.setText("Guardar Campo");
        jbtnagrCampo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnagrCampoMouseClicked(evt);
            }
        });
        jbtnagrCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnagrCampoActionPerformed(evt);
            }
        });

        jLabel9.setText("Ha");

        jtxtSuperficie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtSuperficieKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelCampoLayout = new javax.swing.GroupLayout(jPanelCampo);
        jPanelCampo.setLayout(jPanelCampoLayout);
        jPanelCampoLayout.setHorizontalGroup(
            jPanelCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCampoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtNombreCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanelCampoLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtSuperficie, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnagrCampo))))
        );
        jPanelCampoLayout.setVerticalGroup(
            jPanelCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtNombreCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanelCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCampoLayout.createSequentialGroup()
                        .addGroup(jPanelCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jtxtSuperficie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addComponent(jbtnagrCampo, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jLabel4.getAccessibleContext().setAccessibleName("jlblUser");

        jPanelLotes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelLotes.setEnabled(false);

        jPanelLotes2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lotes"));
        jPanelLotes2.setEnabled(false);

        jLabel5.setText("Nro");

        jLabel6.setText("Superficie");

        jLabel7.setText("Tipo de Suelo");

        jbtnagrLote.setText("Guardar Lote");
        jbtnagrLote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnagrLoteMouseClicked(evt);
            }
        });
        jbtnagrLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnagrLoteActionPerformed(evt);
            }
        });

        jLabel8.setText("Ha");

        jtxtNroLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtNroLoteKeyTyped(evt);
            }
        });

        jtxtSupLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtSupLoteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelLotes2Layout = new javax.swing.GroupLayout(jPanelLotes2);
        jPanelLotes2.setLayout(jPanelLotes2Layout);
        jPanelLotes2Layout.setHorizontalGroup(
            jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLotes2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCbxTiposSuelo, 0, 132, Short.MAX_VALUE)
                    .addComponent(jtxtNroLote)
                    .addComponent(jtxtSupLote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLotes2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnagrLote)
                .addContainerGap())
        );
        jPanelLotes2Layout.setVerticalGroup(
            jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLotes2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jtxtNroLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jtxtSupLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLotes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCbxTiposSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(jbtnagrLote)
                .addContainerGap())
        );

        jToolBar2.setRollover(true);

        btnAceptar.setText("<html><center>Editar<html><center>Lote</");
        btnAceptar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAceptar.setPreferredSize(new java.awt.Dimension(67, 57));
        btnAceptar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAceptar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAceptarMouseClicked(evt);
            }
        });
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jToolBar2.add(btnAceptar);

        btnCancelar.setText("<html><center>Quitar<html><center>Lote</");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelar.setPreferredSize(new java.awt.Dimension(67, 57));
        btnCancelar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jToolBar2.add(btnCancelar);

        jTableLotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nro", "Superficie", "Tipo de Suelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Float.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableLotes);

        javax.swing.GroupLayout jPanelLotesLayout = new javax.swing.GroupLayout(jPanelLotes);
        jPanelLotes.setLayout(jPanelLotesLayout);
        jPanelLotesLayout.setHorizontalGroup(
            jPanelLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelLotes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelLotesLayout.setVerticalGroup(
            jPanelLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLotesLayout.createSequentialGroup()
                .addGroup(jPanelLotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLotesLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelLotes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAceptar2.setText("<html><center>Confirmar<html><center>Cambios</");
        btnAceptar2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAceptar2.setPreferredSize(new java.awt.Dimension(67, 57));
        btnAceptar2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAceptar2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAceptar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar2ActionPerformed(evt);
            }
        });

        btnCancelar1.setText("<html><center>Cancelar<html><center>Cambios</");
        btnCancelar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelar1.setPreferredSize(new java.awt.Dimension(67, 57));
        btnCancelar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCancelar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelCampo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelLotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizarTabla(Collection registros) {

        jTableLotes.removeAll();
        this.miModTabLotes.recargar(registros);
        jTableLotes.setModel(miModTabLotes);
        jTableLotes.setRowSorter(elqueOrdena);

    }

    private void enablePanel(Boolean valor) {

        for (Component component : jPanelLotes2.getComponents()) {
            component.setEnabled(valor);
        }
        for (Component component : jPanelLotes.getComponents()) {
            component.setEnabled(valor);
        }

    }

    private void limpiarLotes() {

        this.jtxtNroLote.setText("");
        this.jtxtSupLote.setText("");
        this.jCbxTiposSuelo.setSelectedIndex(0);

    }

    private void enablePanelCampos(Boolean valor) {

        for (Component component : jPanelCampo.getComponents()) {
            component.setEnabled(valor);
        }

    }

    private void jtxtNombreCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreCampoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreCampoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        int fila = this.jTableLotes.getSelectedRow();
        if (fila != -1) {
            jtxtNroLote.setText(jTableLotes.getValueAt(fila, 0).toString());
            jtxtSupLote.setText(jTableLotes.getValueAt(fila, 1).toString());
            jCbxTiposSuelo.setSelectedItem(jTableLotes.getValueAt(fila, 2));

        } else {

            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Lote de la Tabla",
                    "Información para el usuario",
                    JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int fila = jTableLotes.getSelectedRow();
        if (fila != -1) {

            Integer nroLote = Integer.parseInt(jTableLotes.getValueAt(fila, 0).toString());
            Lote unLote = this.miCampo.obtenerLote(nroLote);

            try {
                this.miGestor.quitarLote(unLote, miCampo);
                this.actualizarTabla(this.miCampo.getLotes());
                this.limpiarLotes();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Información para el usuario",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar2ActionPerformed
        int registrarCampo = JOptionPane.showConfirmDialog(null, "¿Confirma el registro del Campo?", "Confirmación", JOptionPane.YES_OPTION);
        if (this.miCampo.getLotes().size() > 0) {
            if (registrarCampo == JOptionPane.YES_OPTION) {
                // mostramos la ventana de confirmacion de impresion
                new CampoRegistrado(this.miCampo).setVisible(true);
                this.dispose();
            } else {
                try {
                    this.miGestor.removerCampo(miCampo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Información para el usuario",
                            JOptionPane.WARNING_MESSAGE);
                }
                this.dispose();

            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe registrar al menos un lote",
                    "Información para el usuario",
                    JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnAceptar2ActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        if (this.miCampo!=null) {
            try {
                this.miGestor.removerCampo(miCampo);
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Debe registrar al menos un lote",
                        "Información para el usuario",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            this.dispose();
        }

    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void jbtnagrLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnagrLoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnagrLoteActionPerformed

    private void jbtnagrLoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnagrLoteMouseClicked

        Lote unLote = null;
        int numeroLote = Integer.parseInt(this.jtxtNroLote.getText());
        float superficie = Float.parseFloat(this.jtxtSupLote.getText());
        TipoSuelo tipSuelo = (TipoSuelo) this.jCbxTiposSuelo.getSelectedItem();
        unLote = this.miGestor.registrarLote(numeroLote, superficie);
        unLote.setTipoSuelo(tipSuelo);

        try {
            // TODO add your handling code here:

            this.miGestor.asignarLote(unLote, miCampo);
            this.actualizarTabla(this.miCampo.getLotes());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Información para el usuario",
                    JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_jbtnagrLoteMouseClicked

    private void jbtnagrCampoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnagrCampoMouseClicked
        try {
            // TODO add your handling code here:

            this.miCampo = this.miGestor.registrarCampo(this.jtxtNombreCampo.getText(), Float.parseFloat(this.jtxtSuperficie.getText()));
            this.enablePanel(true);
            this.enablePanelCampos(false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Información para el usuario",
                    JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jbtnagrCampoMouseClicked

    private void jbtnagrCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnagrCampoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnagrCampoActionPerformed

    private void btnAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseClicked

    }//GEN-LAST:event_btnAceptarMouseClicked

    private void jtxtSuperficieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSuperficieKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            if ((car != '-') && (car != '+')) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_jtxtSuperficieKeyTyped

    private void jtxtNroLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNroLoteKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            if ((car != '-') && (car != '+')) {
                evt.consume();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNroLoteKeyTyped

    private void jtxtSupLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSupLoteKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            if ((car != '-') && (car != '+')) {
                evt.consume();
            }
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jtxtSupLoteKeyTyped

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptar2;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JComboBox<TipoSuelo> jCbxTiposSuelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelCampo;
    private javax.swing.JPanel jPanelLotes;
    private javax.swing.JPanel jPanelLotes2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLotes;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jbtnagrCampo;
    private javax.swing.JButton jbtnagrLote;
    private javax.swing.JTextField jtxtNombreCampo;
    private javax.swing.JFormattedTextField jtxtNroLote;
    private javax.swing.JFormattedTextField jtxtSupLote;
    private javax.swing.JFormattedTextField jtxtSuperficie;
    // End of variables declaration//GEN-END:variables
}
