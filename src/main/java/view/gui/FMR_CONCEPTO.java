package view.gui;

//import controller.*;
//import exceptions.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author KNOWLES
 */
public class FMR_CONCEPTO extends javax.swing.JFrame {
    private final Account account;
    private JPanel dropdownPanel;
    private boolean isDropdownVisible = false;

    public FMR_CONCEPTO() {
        this(null);
    }

    public FMR_CONCEPTO(Account account) {
        this.account = account;
        Toolkit t=Toolkit.getDefaultToolkit();
        setIconImage(t.getImage(getClass().getResource("/resources/logo.png")));
        setTitle("LEVELING_UP_LIFE");
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        setupDropdownMenu();
        
        // Update welcome message with user's name if account exists
        if (account != null) {
            //welcomeLabel.setText("¡Bienvenido " + account.getNombre() + "!");
        }
    }

    private void setupDropdownMenu() {
        // Main menu button
        JButton menuButton = new JButton("Menú");
        menuButton.setBounds(10, 10, 100, 30);
        menuButton.addActionListener(e -> toggleDropdown());
        
        // Dropdown panel
        dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new GridLayout(7, 1, 0, 5));
        dropdownPanel.setBounds(10, 45, 150, 245);
        dropdownPanel.setVisible(false);
        dropdownPanel.setBackground(new Color(252, 240, 198));

        // Menu options
        String[] options = {"Eventos", "Productos", "Salas", "FaQ", "Reservas", "Volver", "Cerrar Sesión"};
        for (String option : options) {
            JButton button = new JButton(option);
            button.addActionListener(e -> handleMenuOption(option));
            dropdownPanel.add(button);
        }

        // Add components
        getContentPane().setLayout(null);
        getContentPane().add(menuButton);
        getContentPane().add(dropdownPanel);
    }

    private void toggleDropdown() {
        isDropdownVisible = !isDropdownVisible;
        dropdownPanel.setVisible(isDropdownVisible);
    }

    private void handleMenuOption(String option) {
        switch (option) {
            case "Eventos" -> new FMR_EVENT(account).setVisible(true);
            case "Productos" -> new FMR_PRODUCT(account).setVisible(true);
            case "Salas" -> new FMR_ROOMS(account).setVisible(true);
            case "FaQ" -> new FMR_FaQ(account).setVisible(true);
            case "Reservas" -> new FMR_BOOKING(account).setVisible(true);
            case "Volver" -> // Stay in current frame
                toggleDropdown();
            case "Cerrar Sesión" -> {
                new FMR_LOGIN().setVisible(true);
                this.dispose();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        text_bienvenida1 = new javax.swing.JTextField();
        boton_events = new javax.swing.JToggleButton();
        boton_produt = new javax.swing.JToggleButton();
        boton_salas = new javax.swing.JToggleButton();
        boton_FaQ = new javax.swing.JToggleButton();
        boton_reservas = new javax.swing.JToggleButton();
        boton_logout = new javax.swing.JToggleButton();
        boton_saberMas = new javax.swing.JToggleButton();
        fondo = new javax.swing.JLabel();
        TITULO = new javax.swing.JPanel();
        boton_myCuenta = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(875, 555));
        setSize(new java.awt.Dimension(875, 555));

        BG.setBackground(new java.awt.Color(252, 240, 198));
        BG.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BG.setFocusTraversalPolicyProvider(true);
        BG.setMinimumSize(new java.awt.Dimension(860, 520));
        BG.setPreferredSize(new java.awt.Dimension(860, 520));
        BG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N
        BG.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 150, 150));

        text_bienvenida1.setEditable(false);
        text_bienvenida1.setBackground(new Color(0, 0, 0, 0));
        text_bienvenida1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        text_bienvenida1.setForeground(new java.awt.Color(255, 255, 255));
        text_bienvenida1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        text_bienvenida1.setText(account != null ? "¡Bienvenido " + account.getNombre() + " a Leveling Up Life!\n" : "¡Bienvenido a Leveling Up Life!\n\n");
        text_bienvenida1.setToolTipText("");
        text_bienvenida1.setBorder(null);
        text_bienvenida1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_bienvenida1ActionPerformed(evt);
            }
        });
        BG.add(text_bienvenida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 500, 80));

        boton_events.setBackground(new java.awt.Color(51, 153, 0));
        boton_events.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_events.setForeground(new java.awt.Color(255, 255, 255));
        boton_events.setText("Eventos");
        boton_events.setToolTipText("");
        boton_events.setAutoscrolls(true);
        boton_events.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_events.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_eventsActionPerformed(evt);
            }
        });
        BG.add(boton_events, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 130, 30));

        boton_produt.setBackground(new java.awt.Color(51, 153, 0));
        boton_produt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_produt.setForeground(new java.awt.Color(255, 255, 255));
        boton_produt.setText("Productos");
        boton_produt.setToolTipText("");
        boton_produt.setAutoscrolls(true);
        boton_produt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_produt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_produtActionPerformed(evt);
            }
        });
        BG.add(boton_produt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 130, 30));

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
        BG.add(boton_salas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 130, 30));

        boton_FaQ.setBackground(new java.awt.Color(51, 153, 0));
        boton_FaQ.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_FaQ.setForeground(new java.awt.Color(255, 255, 255));
        boton_FaQ.setText("FaQ");
        boton_FaQ.setToolTipText("");
        boton_FaQ.setAutoscrolls(true);
        boton_FaQ.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_FaQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_FaQActionPerformed(evt);
            }
        });
        BG.add(boton_FaQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 130, 30));

        boton_reservas.setBackground(new java.awt.Color(51, 153, 0));
        boton_reservas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_reservas.setForeground(new java.awt.Color(255, 255, 255));
        boton_reservas.setText("Reservas");
        boton_reservas.setToolTipText("");
        boton_reservas.setAutoscrolls(true);
        boton_reservas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_reservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reservasActionPerformed(evt);
            }
        });
        BG.add(boton_reservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 130, 30));

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
        BG.add(boton_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 130, 30));

        boton_saberMas.setBackground(new java.awt.Color(51, 153, 0));
        boton_saberMas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_saberMas.setForeground(new java.awt.Color(255, 255, 255));
        boton_saberMas.setText("Saber mas");
        boton_saberMas.setToolTipText("");
        boton_saberMas.setAutoscrolls(true);
        boton_saberMas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_saberMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_saberMasActionPerformed(evt);
            }
        });
        BG.add(boton_saberMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 130, 30));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/calle.png"))); // NOI18N
        BG.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 96, -1, -1));

        TITULO.setBackground(new java.awt.Color(0, 0, 0));

        boton_myCuenta.setBackground(new java.awt.Color(0, 0, 0));
        boton_myCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/accBla.png"))); // NOI18N
        boton_myCuenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_myCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_myCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_myCuentaActionPerformed(evt);
            }
        });

        titulo.setBackground(new java.awt.Color(0, 0, 0));
        titulo.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("CONCEPTO");
        titulo.setToolTipText("");

        javax.swing.GroupLayout TITULOLayout = new javax.swing.GroupLayout(TITULO);
        TITULO.setLayout(TITULOLayout);
        TITULOLayout.setHorizontalGroup(
            TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TITULOLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(boton_myCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TITULOLayout.setVerticalGroup(
            TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TITULOLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_myCuenta)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        BG.add(TITULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 640, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_logoutActionPerformed
        FMR_LOGIN newFrame = new FMR_LOGIN();
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_logoutActionPerformed

    private void boton_eventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_eventsActionPerformed
        FMR_EVENT newFrame = new FMR_EVENT(account); 
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_eventsActionPerformed

    private void boton_saberMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_saberMasActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://www.canva.com/design/DAGi4FATlJQ/hDOv8aoWu_A_kF-RpAQNdQ/edit?ui=eyJIIjp7IkEiOnRydWV9fQ"));
        } catch (IOException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir la página web: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_boton_saberMasActionPerformed

    private void boton_salasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_salasActionPerformed
        FMR_ROOMS newFrame = new FMR_ROOMS(account); 
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_salasActionPerformed

    private void boton_FaQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_FaQActionPerformed
        FMR_FaQ newFrame = new FMR_FaQ(account); 
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_FaQActionPerformed

    private void boton_reservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reservasActionPerformed
        FMR_BOOKING newFrame = new FMR_BOOKING(account); 
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_reservasActionPerformed

    private void boton_produtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_produtActionPerformed
        FMR_PRODUCT newFrame = new FMR_PRODUCT(account); 
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_produtActionPerformed

    private void boton_myCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_myCuentaActionPerformed
        FMR_MYACC newFrame = new FMR_MYACC(account); 
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_myCuentaActionPerformed

    private void text_bienvenida1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_bienvenida1ActionPerformed
        // add your handling code here:
    }//GEN-LAST:event_text_bienvenida1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMR_CONCEPTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FMR_CONCEPTO().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BG;
    private javax.swing.JPanel TITULO;
    private javax.swing.JToggleButton boton_FaQ;
    private javax.swing.JToggleButton boton_events;
    private javax.swing.JToggleButton boton_logout;
    private javax.swing.JButton boton_myCuenta;
    private javax.swing.JToggleButton boton_produt;
    private javax.swing.JToggleButton boton_reservas;
    private javax.swing.JToggleButton boton_saberMas;
    private javax.swing.JToggleButton boton_salas;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField text_bienvenida1;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
