package view.gui;

import controller.*;
//import exceptions.*;
import model.Event;
import model.Account;
import interfaces.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author JHAIR BY KNOWLES
 */
public class FMR_EVENTACTIVOS extends javax.swing.JFrame {
   private final Account account;
   private final EventController eventController;
    /**
     * Creates new form FMR_EVENTACTIVOS
     */
    public FMR_EVENTACTIVOS(Account account) {
       this.account = account;
        IFile<Event> fileHandler = new FileHandler<>(new Event());
        this.eventController = new EventController(fileHandler);
        Toolkit t=Toolkit.getDefaultToolkit();
        setIconImage(t.getImage(getClass().getResource("/resources/logo.png")));
        setTitle("LEVELING_UP_LIFE");
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        loadActiveEvents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        boton_volver = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEventoActivos = new javax.swing.JTable();
        TITULO1 = new javax.swing.JPanel();
        boton_myCuenta1 = new javax.swing.JButton();
        titulo1 = new javax.swing.JLabel();
        boton_concepto = new javax.swing.JButton();
        boton_evento = new javax.swing.JToggleButton();
        boton_products = new javax.swing.JToggleButton();
        boton_salas = new javax.swing.JToggleButton();
        boton_faq = new javax.swing.JToggleButton();
        boton_booking = new javax.swing.JToggleButton();
        boton_logout = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(875, 555));
        setPreferredSize(new java.awt.Dimension(875, 555));
        setSize(new java.awt.Dimension(875, 555));

        BG1.setBackground(new java.awt.Color(252, 240, 198));
        BG1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BG1.setMinimumSize(new java.awt.Dimension(860, 520));
        BG1.setPreferredSize(new java.awt.Dimension(860, 520));
        BG1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        boton_volver.setBackground(new java.awt.Color(51, 153, 0));
        boton_volver.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        boton_volver.setForeground(new java.awt.Color(255, 255, 255));
        boton_volver.setText("Volver");
        boton_volver.setToolTipText("");
        boton_volver.setAutoscrolls(true);
        boton_volver.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_volverActionPerformed(evt);
            }
        });

        TableEventoActivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Inicio", "Fin", "Tipo", "Capacidad", "Costo"
            }
        ));
        jScrollPane1.setViewportView(TableEventoActivos);

        TITULO1.setBackground(new java.awt.Color(0, 0, 0));

        boton_myCuenta1.setBackground(new java.awt.Color(0, 0, 0));
        boton_myCuenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/accBla.png"))); // NOI18N
        boton_myCuenta1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_myCuenta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_myCuenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_myCuenta1ActionPerformed(evt);
            }
        });

        titulo1.setBackground(new java.awt.Color(0, 0, 0));
        titulo1.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        titulo1.setForeground(new java.awt.Color(255, 255, 255));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("Evento Activos");
        titulo1.setToolTipText("");

        javax.swing.GroupLayout TITULO1Layout = new javax.swing.GroupLayout(TITULO1);
        TITULO1.setLayout(TITULO1Layout);
        TITULO1Layout.setHorizontalGroup(
            TITULO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TITULO1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addComponent(boton_myCuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TITULO1Layout.setVerticalGroup(
            TITULO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TITULO1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(TITULO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_myCuenta1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(boton_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TITULO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(TITULO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(boton_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        BG1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 640, 560));

        boton_concepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N
        boton_concepto.setBorder(null);
        boton_concepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_conceptoActionPerformed(evt);
            }
        });
        BG1.add(boton_concepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 150, 150));

        boton_evento.setBackground(new java.awt.Color(51, 101, 153));
        boton_evento.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_evento.setForeground(new java.awt.Color(255, 255, 255));
        boton_evento.setText("Eventos");
        boton_evento.setToolTipText("");
        boton_evento.setAutoscrolls(true);
        boton_evento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_evento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_eventoActionPerformed(evt);
            }
        });
        BG1.add(boton_evento, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 130, 30));

        boton_products.setBackground(new java.awt.Color(51, 153, 0));
        boton_products.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_products.setForeground(new java.awt.Color(255, 255, 255));
        boton_products.setText("Productos");
        boton_products.setToolTipText("");
        boton_products.setAutoscrolls(true);
        boton_products.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_products.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_productsActionPerformed(evt);
            }
        });
        BG1.add(boton_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 130, 30));

        boton_salas.setBackground(new java.awt.Color(51, 153, 0));
        boton_salas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_salas.setForeground(new java.awt.Color(255, 255, 255));
        boton_salas.setText("Salas");
        boton_salas.setToolTipText("");
        boton_salas.setAutoscrolls(true);
        boton_salas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_salas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_salasActionPerformed(evt);
            }
        });
        BG1.add(boton_salas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 130, 30));

        boton_faq.setBackground(new java.awt.Color(51, 153, 0));
        boton_faq.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_faq.setForeground(new java.awt.Color(255, 255, 255));
        boton_faq.setText("FaQ");
        boton_faq.setToolTipText("");
        boton_faq.setAutoscrolls(true);
        boton_faq.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_faq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_faqActionPerformed(evt);
            }
        });
        BG1.add(boton_faq, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 130, 30));

        boton_booking.setBackground(new java.awt.Color(51, 153, 0));
        boton_booking.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_booking.setForeground(new java.awt.Color(255, 255, 255));
        boton_booking.setText("Reservas");
        boton_booking.setToolTipText("");
        boton_booking.setAutoscrolls(true);
        boton_booking.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_booking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_bookingActionPerformed(evt);
            }
        });
        BG1.add(boton_booking, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 130, 30));

        boton_logout.setBackground(new java.awt.Color(51, 153, 0));
        boton_logout.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_logout.setForeground(new java.awt.Color(255, 255, 255));
        boton_logout.setText("Cerrar sesion");
        boton_logout.setToolTipText("");
        boton_logout.setAutoscrolls(true);
        boton_logout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_logoutActionPerformed(evt);
            }
        });
        BG1.add(boton_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_volverActionPerformed
        // Volver a la pantalla principal de eventos
    FMR_EVENT eventFrame = new FMR_EVENT(account);
    eventFrame.setVisible(true);
    this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_boton_volverActionPerformed

    private void boton_myCuenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_myCuenta1ActionPerformed
        FMR_MYACC newFrame = new FMR_MYACC(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_myCuenta1ActionPerformed

    private void boton_conceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_conceptoActionPerformed
        FMR_CONCEPTO newFrame = new FMR_CONCEPTO(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_conceptoActionPerformed

    private void boton_eventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_eventoActionPerformed
        FMR_EVENT newFrame = new FMR_EVENT(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_eventoActionPerformed

    private void boton_productsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_productsActionPerformed
        FMR_PRODUCT newFrame = new FMR_PRODUCT(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_productsActionPerformed

    private void boton_salasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_salasActionPerformed
        FMR_ROOMS newFrame = new FMR_ROOMS(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_salasActionPerformed

    private void boton_faqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_faqActionPerformed
        FMR_FaQ newFrame = new FMR_FaQ(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_faqActionPerformed

    private void boton_bookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_bookingActionPerformed
        FMR_BOOKING newFrame = new FMR_BOOKING(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_bookingActionPerformed

    private void boton_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_logoutActionPerformed
        FMR_LOGIN newFrame = new FMR_LOGIN();
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_logoutActionPerformed
    private void loadActiveEvents() {
        try {
            // Obtener eventos activos del controlador
            List<Event> activeEvents = eventController.listarEventosActivos();
            
            // Obtener el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) TableEventoActivos.getModel();
            model.setRowCount(0); // Limpiar la tabla
            
            // Llenar la tabla con los eventos
            for (Event event : activeEvents) {
                Object[] row = {
                    event.getNombre(),
                    event.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    event.getFechaFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    getTipoEvento(event.getTipo()),
                    event.getCapacidad(),
                    String.format("$%.2f", event.getPrecio())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar eventos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to convert event type to readable string
    private String getTipoEvento(char tipo) {
        switch (tipo) {
            case 'C': return "Conferencia";
            case 'T': return "Taller";
            case 'F': return "Fiesta";
            default: return "Desconocido";
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
                if ("Nimbus".equals(info.getName())) {    // Fixed syntax error here
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FMR_EVENTACTIVOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FMR_EVENTACTIVOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FMR_EVENTACTIVOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMR_EVENTACTIVOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FMR_EVENTACTIVOS(null).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BG1;
    private javax.swing.JPanel TITULO1;
    private javax.swing.JTable TableEventoActivos;
    private javax.swing.JToggleButton boton_booking;
    private javax.swing.JButton boton_concepto;
    private javax.swing.JToggleButton boton_evento;
    private javax.swing.JToggleButton boton_faq;
    private javax.swing.JToggleButton boton_logout;
    private javax.swing.JButton boton_myCuenta1;
    private javax.swing.JToggleButton boton_products;
    private javax.swing.JToggleButton boton_salas;
    private javax.swing.JToggleButton boton_volver;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel titulo1;
    // End of variables declaration//GEN-END:variables
}
