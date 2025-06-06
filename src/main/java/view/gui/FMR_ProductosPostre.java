/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.gui;
import controller.*;
//import exceptions.*;
import model.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
//import java.io.IOException;
//import java.net.URISyntaxException;

/**
 *
 * @author LENOVO
 */
public class FMR_ProductosPostre extends javax.swing.JFrame {
    private final Account account;
    private final ProductController productController;

    /**
     * Creates new form FMR_ProductosPostre
     */
    public FMR_ProductosPostre(Account account) {
        ProductController tempController;
        try {
            tempController = new ProductController(new FileHandler<>(new Product()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al inicializar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            tempController = null;
            System.exit(1);
        }
        this.
                productController = tempController;
        this.account = account;
        Toolkit t=Toolkit.getDefaultToolkit();
        setIconImage(t.getImage(getClass().getResource("/resources/logo.png")));
        setTitle("LEVELING_UP_LIFE");
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        loadActiveProducts(); // Llamar después de initComponents()
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_primarioPostres = new javax.swing.JPanel();
        boton_comprarPostres = new javax.swing.JToggleButton();
        boton_productPostres = new javax.swing.JToggleButton();
        boton_salasPostres = new javax.swing.JToggleButton();
        boton_FaQPostres = new javax.swing.JToggleButton();
        boton_bookingPostres = new javax.swing.JToggleButton();
        boton_logoutPostres = new javax.swing.JToggleButton();
        boton_volverPostres = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Postres = new javax.swing.JTable();
        boton_logoPostres = new javax.swing.JButton();
        boton_eventPostres = new javax.swing.JToggleButton();
        panel_tituloPostres = new javax.swing.JPanel();
        boton_perfilPostres = new javax.swing.JButton();
        label_tituloPostres = new javax.swing.JLabel();
        label_fondoPostres = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_primarioPostres.setBackground(new java.awt.Color(252, 240, 198));
        panel_primarioPostres.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_primarioPostres.setRequestFocusEnabled(false);
        panel_primarioPostres.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton_comprarPostres.setBackground(new java.awt.Color(51, 153, 0));
        boton_comprarPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_comprarPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_comprarPostres.setText("Comprar");
        boton_comprarPostres.setToolTipText("");
        boton_comprarPostres.setAutoscrolls(true);
        boton_comprarPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_comprarPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_comprarPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_comprarPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 470, 130, 30));

        boton_productPostres.setBackground(new java.awt.Color(53, 101, 153));
        boton_productPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_productPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_productPostres.setText("Productos");
        boton_productPostres.setToolTipText("");
        boton_productPostres.setAutoscrolls(true);
        boton_productPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_productPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_productPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_productPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 130, 30));

        boton_salasPostres.setBackground(new java.awt.Color(51, 153, 0));
        boton_salasPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_salasPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_salasPostres.setText("Salas");
        boton_salasPostres.setToolTipText("");
        boton_salasPostres.setAutoscrolls(true);
        boton_salasPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_salasPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_salasPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_salasPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 130, 30));

        boton_FaQPostres.setBackground(new java.awt.Color(51, 155, 0));
        boton_FaQPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_FaQPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_FaQPostres.setText("FaQ");
        boton_FaQPostres.setToolTipText("");
        boton_FaQPostres.setAutoscrolls(true);
        boton_FaQPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_FaQPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_FaQPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_FaQPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 130, 30));

        boton_bookingPostres.setBackground(new java.awt.Color(51, 153, 0));
        boton_bookingPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_bookingPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_bookingPostres.setText("Reservas");
        boton_bookingPostres.setToolTipText("");
        boton_bookingPostres.setAutoscrolls(true);
        boton_bookingPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_bookingPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_bookingPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_bookingPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 130, 30));

        boton_logoutPostres.setBackground(new java.awt.Color(51, 153, 0));
        boton_logoutPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_logoutPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_logoutPostres.setText("Cerrar sesion");
        boton_logoutPostres.setToolTipText("");
        boton_logoutPostres.setAutoscrolls(true);
        boton_logoutPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_logoutPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_logoutPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_logoutPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 130, 30));

        boton_volverPostres.setBackground(new java.awt.Color(51, 153, 0));
        boton_volverPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_volverPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_volverPostres.setText("Volver");
        boton_volverPostres.setToolTipText("");
        boton_volverPostres.setAutoscrolls(true);
        boton_volverPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_volverPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_volverPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_volverPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 150, 30));

        tabla_Postres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CATEGORIA", "PRECIO", "STOCK"
            }
        ));
        jScrollPane1.setViewportView(tabla_Postres);

        panel_primarioPostres.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 560, 340));

        boton_logoPostres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N
        boton_logoPostres.setBorder(null);
        boton_logoPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_logoPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_logoPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        boton_eventPostres.setBackground(new java.awt.Color(51, 153, 0));
        boton_eventPostres.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_eventPostres.setForeground(new java.awt.Color(255, 255, 255));
        boton_eventPostres.setText("Eventos");
        boton_eventPostres.setToolTipText("");
        boton_eventPostres.setAutoscrolls(true);
        boton_eventPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_eventPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_eventPostresActionPerformed(evt);
            }
        });
        panel_primarioPostres.add(boton_eventPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 130, 30));

        panel_tituloPostres.setBackground(new java.awt.Color(0, 0, 0));

        boton_perfilPostres.setBackground(new java.awt.Color(0, 0, 0));
        boton_perfilPostres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/accBla.png"))); // NOI18N
        boton_perfilPostres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_perfilPostres.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_perfilPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_perfilPostresActionPerformed(evt);
            }
        });

        label_tituloPostres.setBackground(new java.awt.Color(0, 0, 0));
        label_tituloPostres.setFont(new java.awt.Font("Times New Roman", 1, 55)); // NOI18N
        label_tituloPostres.setForeground(new java.awt.Color(255, 255, 255));
        label_tituloPostres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_tituloPostres.setText("POSTRES");
        label_tituloPostres.setToolTipText("");

        javax.swing.GroupLayout panel_tituloPostresLayout = new javax.swing.GroupLayout(panel_tituloPostres);
        panel_tituloPostres.setLayout(panel_tituloPostresLayout);
        panel_tituloPostresLayout.setHorizontalGroup(
            panel_tituloPostresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tituloPostresLayout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(label_tituloPostres)
                .addGap(124, 124, 124)
                .addComponent(boton_perfilPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_tituloPostresLayout.setVerticalGroup(
            panel_tituloPostresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tituloPostresLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(boton_perfilPostres)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tituloPostresLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(label_tituloPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_primarioPostres.add(panel_tituloPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 640, 100));

        label_fondoPostres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/categoriaComida.png"))); // NOI18N
        panel_primarioPostres.add(label_fondoPostres, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 640, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_primarioPostres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_primarioPostres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_comprarPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_comprarComidasActionPerformed
        int selectedRow = tabla_Postres.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, 
            "Por favor, seleccione un producto para comprar", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
        }

    // Obtener datos del producto
    String productId = tabla_Postres.getValueAt(selectedRow, 0).toString();
    String productName = tabla_Postres.getValueAt(selectedRow, 1).toString();
    double precio = Double.parseDouble(tabla_Postres.getValueAt(selectedRow, 3).toString());
    int stock = Integer.parseInt(tabla_Postres.getValueAt(selectedRow, 4).toString());

    // Panel de compra
    JPanel panel = new JPanel(new GridLayout(3, 2));
    JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(1, 1, stock, 1));
    
    panel.add(new JLabel("Producto:"));
    panel.add(new JLabel(productName));
    panel.add(new JLabel("Precio unitario:"));
    panel.add(new JLabel("$" + precio));
    panel.add(new JLabel("Cantidad (Máx: " + stock + "):"));
    panel.add(cantidadSpinner);
    
    int result = JOptionPane.showConfirmDialog(
        this, 
        panel, 
        "Confirmar Compra", 
        JOptionPane.OK_CANCEL_OPTION, 
        JOptionPane.PLAIN_MESSAGE);
    
    if (result == JOptionPane.OK_OPTION) {
        try {
            int cantidad = (int) cantidadSpinner.getValue();
            double total = precio * cantidad;
            
            // Actualizar stock (asumiendo que productController tiene este método)
            productController.updateStock(productId, -cantidad);
            
            JOptionPane.showMessageDialog(this, 
                "Compra realizada!\n" +
                "Producto: " + productName + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "Total: $" + total,
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Actualizar tabla
            loadActiveProducts();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al comprar: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_boton_comprarComidasActionPerformed

    private void boton_productPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_productPostresActionPerformed
        FMR_PRODUCT newFrame = new FMR_PRODUCT(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_productPostresActionPerformed

    private void boton_salasPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_salasPostresActionPerformed
        FMR_ROOMS newFrame = new FMR_ROOMS(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_salasPostresActionPerformed

    private void boton_FaQPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_FaQPostresActionPerformed
        FMR_FaQ newFrame = new FMR_FaQ(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_FaQPostresActionPerformed

    private void boton_bookingPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_bookingPostresActionPerformed
        FMR_BOOKING newFrame = new FMR_BOOKING(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_bookingPostresActionPerformed

    private void boton_logoutPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_logoutPostresActionPerformed
        FMR_LOGIN newFrame = new FMR_LOGIN();
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_logoutPostresActionPerformed

    private void boton_volverPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_volverPostresActionPerformed
        FMR_ProductosCategoria newFrame = new FMR_ProductosCategoria(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_volverPostresActionPerformed

    private void boton_logoPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_logoPostresActionPerformed
        FMR_CONCEPTO newFrame = new FMR_CONCEPTO(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_logoPostresActionPerformed

    private void boton_eventPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_eventPostresActionPerformed
        FMR_EVENT newFrame = new FMR_EVENT(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_eventPostresActionPerformed

    private void boton_perfilPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_perfilPostresActionPerformed
        FMR_MYACC newFrame = new FMR_MYACC(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_perfilPostresActionPerformed
    
    private void loadActiveProducts() {
    try {
        // Obtener productos disponibles del controlador
        List<Product> availableProducts = productController.getAvailableProducts();
        
        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tabla_Postres.getModel();
        model.setRowCount(0); // Limpiar la tabla
        
        // Filtrar solo productos de categoría 'C' y llenar la tabla
        for (Product product : availableProducts) {
            if (product.getCategoria() == 'P') {  // Solo incluir si la categoría es 'C'
                Object[] row = {
                    product.getId(),
                    product.getNombre(),
                    product.getCategoria(),  // Mostrará 'C' en la tabla
                    product.getPrecio(),
                    product.getStock()
                };
                model.addRow(row);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Error al cargar productos: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FMR_ProductosPostre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FMR_ProductosPostre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FMR_ProductosPostre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMR_ProductosPostre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FMR_ProductosPostre(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton boton_FaQPostres;
    private javax.swing.JToggleButton boton_bookingPostres;
    private javax.swing.JToggleButton boton_comprarPostres;
    private javax.swing.JToggleButton boton_eventPostres;
    private javax.swing.JButton boton_logoPostres;
    private javax.swing.JToggleButton boton_logoutPostres;
    private javax.swing.JButton boton_perfilPostres;
    private javax.swing.JToggleButton boton_productPostres;
    private javax.swing.JToggleButton boton_salasPostres;
    private javax.swing.JToggleButton boton_volverPostres;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_fondoPostres;
    private javax.swing.JLabel label_tituloPostres;
    private javax.swing.JPanel panel_primarioPostres;
    private javax.swing.JPanel panel_tituloPostres;
    private javax.swing.JTable tabla_Postres;
    // End of variables declaration//GEN-END:variables
}
