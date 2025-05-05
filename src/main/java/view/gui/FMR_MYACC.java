package view.gui;

import controller.*;
import exceptions.*;
import model.*;

import javax.swing.*;
import java.awt.*;
//import java.io.IOException;
//import java.net.URISyntaxException;

/**
 * @author KNOWLES
 */
public class FMR_MYACC extends javax.swing.JFrame {
    private final Account account;
    private final AccountController accountController;
    private JPanel dropdownPanel;
    private boolean isDropdownVisible = false;

    public FMR_MYACC() {
        this(null);
    }

    public FMR_MYACC(Account account) {
        try {
            this.accountController = new AccountController(new FileHandler<>(new Account()));
        } catch (AccountException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        this.account = account;
        Toolkit t=Toolkit.getDefaultToolkit();
        setIconImage(t.getImage(getClass().getResource("/resources/logo.png")));
        setTitle("LEVELING_UP_LIFE");
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        setupDropdownMenu();
        setupAccountFields();
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

    private void setupAccountFields() {
        // Create panel for fields
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
    
        // Define field labels and values
        String[][] fieldData = {
            {"Nombre:", account != null ? account.getNombre() : ""},
            {"Apellido:", account != null ? account.getApellido() : ""},
            {"Teléfono:", account != null ? account.getPhone() : ""},
            {"Email:", account != null ? account.getEmail() : ""},
            {"Usuario:", account != null ? account.getUser() : ""},
            {"Contraseña:", "********"}  // Password shown as asterisks
        };
    
        // Create and configure components for each field
        for (int i = 0; i < fieldData.length; i++) {
            // Label
            JLabel label = new JLabel(fieldData[i][0]);
            label.setFont(new Font("Times New Roman", Font.BOLD, 18));
            label.setForeground(Color.WHITE);
            gbc.gridx = 0;
            gbc.gridy = i;
            fieldsPanel.add(label, gbc);
    
            // Value field (non-editable)
            JTextField valueField = new JTextField(fieldData[i][1]);
            valueField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            valueField.setPreferredSize(new Dimension(200, 30));
            valueField.setEditable(false);
            valueField.setBackground(new Color(240, 240, 240));
            gbc.gridx = 1;
            fieldsPanel.add(valueField, gbc);
    
            // Add modify button for all fields except username (index 4)
            if (i != 4) {
                JButton modifyButton = new JButton("Modificar");
                modifyButton.setBackground(new Color(51, 153, 0));
                modifyButton.setForeground(Color.WHITE);
                modifyButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
                final int index = i;
                modifyButton.addActionListener(e -> handleModification(index, valueField.getText()));
                gbc.gridx = 2;
                fieldsPanel.add(modifyButton, gbc);
    
                // Disable buttons if no account
                if (account == null) {
                    modifyButton.setEnabled(false);
                }
            }
        }
    
        // Remove existing modify button
        boton_saberMas.setVisible(false);
    
        // Add panel to TITULO
        TITULO.setLayout(new BorderLayout());
        TITULO.add(titulo, BorderLayout.NORTH);
        TITULO.add(fieldsPanel, BorderLayout.CENTER);
    }
    
    private void toggleDropdown() {
        isDropdownVisible = !isDropdownVisible;
        dropdownPanel.setVisible(isDropdownVisible);
    }

    private void handleModification(int fieldIndex, String currentValue) {
        String[] fieldNames = {"nombre", "apellido", "teléfono", "email", "usuario", "contraseña"};
        
        if (fieldIndex == 5) { // Caso especial para contraseña
            try {
                // 1. Pedir contraseña actual
                String oldPassword = JOptionPane.showInputDialog(
                    this,
                    "Ingrese su contraseña actual:",
                    "Cambio de contraseña - Paso 1/3",
                    JOptionPane.PLAIN_MESSAGE
                );
                
                if (oldPassword == null || oldPassword.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar su contraseña actual");
                    return;
                }
                
                // 2. Pedir nueva contraseña
                String newPassword = JOptionPane.showInputDialog(
                    this,
                    "Ingrese la nueva contraseña:",
                    "Cambio de contraseña - Paso 2/3",
                    JOptionPane.PLAIN_MESSAGE
                );
                
                if (newPassword == null || newPassword.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La nueva contraseña no puede estar vacía");
                    return;
                }
                
                // 3. Confirmar nueva contraseña
                String confirmPassword = JOptionPane.showInputDialog(
                    this,
                    "Confirme la nueva contraseña:",
                    "Cambio de contraseña - Paso 3/3",
                    JOptionPane.PLAIN_MESSAGE
                );
                
                if (confirmPassword == null || !confirmPassword.equals(newPassword)) {
                    JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
                    return;
                }
                
                // Si todo está correcto, actualizar la contraseña
                accountController.updatePassword(account.getUser(), oldPassword, newPassword);
                
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, 
                    "Contraseña actualizada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Refrescar la ventana
                Account updatedAccount = accountController.getByUsername(account.getUser());
                new FMR_MYACC(updatedAccount).setVisible(true);
                this.dispose();
                
            } catch (AccountException e) {
                JOptionPane.showMessageDialog(this,
                    "Error al actualizar la contraseña: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        // Resto del código para otros campos (nombre, apellido, etc.)
        String newValue = JOptionPane.showInputDialog(
            this,
            "Ingrese nuevo " + fieldNames[fieldIndex] + ":",
            "Modificar " + fieldNames[fieldIndex],
            JOptionPane.PLAIN_MESSAGE
        );
    
        if (newValue != null && !newValue.trim().isEmpty()) {
            try {
                switch (fieldIndex) {
                    case 0 -> accountController.updateName(account.getUser(), newValue);
                    case 1 -> accountController.updateLast(account.getUser(), newValue);
                    case 2 -> accountController.updatePhone(account.getUser(), newValue);
                    case 3 -> accountController.updateEmail(account.getUser(), newValue);
                    case 4 -> JOptionPane.showMessageDialog(this, "El usuario no puede ser modificado");
                }
                
                // Refresh the form with updated account
                Account updatedAccount = accountController.getByUsername(account.getUser());
                new FMR_MYACC(updatedAccount).setVisible(true);
                this.dispose();
                
                JOptionPane.showMessageDialog(this, 
                    "Información actualizada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (AccountException e) {
                JOptionPane.showMessageDialog(this,
                    "Error al actualizar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
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
        boton_concepto = new javax.swing.JButton();
        boton_events = new javax.swing.JToggleButton();
        boton_produt = new javax.swing.JToggleButton();
        boton_salas = new javax.swing.JToggleButton();
        boton_FaQ = new javax.swing.JToggleButton();
        boton_reservas = new javax.swing.JToggleButton();
        boton_logout = new javax.swing.JToggleButton();
        TITULO = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        boton_saberMas = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(875, 555));

        BG.setBackground(new java.awt.Color(252, 240, 198));
        BG.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BG.setFocusTraversalPolicyProvider(true);
        BG.setMinimumSize(new java.awt.Dimension(860, 520));
        BG.setPreferredSize(new java.awt.Dimension(860, 520));
        BG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton_concepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N
        boton_concepto.setBorder(null);
        boton_concepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_conceptoActionPerformed(evt);
            }
        });
        BG.add(boton_concepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

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

        TITULO.setBackground(new java.awt.Color(0, 0, 0));

        titulo.setBackground(new java.awt.Color(0, 0, 0));
        titulo.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("MI CUENTA");
        titulo.setToolTipText("");

        boton_saberMas.setBackground(new java.awt.Color(51, 153, 0));
        boton_saberMas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_saberMas.setForeground(new java.awt.Color(255, 255, 255));
        boton_saberMas.setText("Modificar");
        boton_saberMas.setToolTipText("");
        boton_saberMas.setAutoscrolls(true);
        boton_saberMas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_saberMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_saberMasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TITULOLayout = new javax.swing.GroupLayout(TITULO);
        TITULO.setLayout(TITULOLayout);
        TITULOLayout.setHorizontalGroup(
            TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TITULOLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TITULOLayout.createSequentialGroup()
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TITULOLayout.createSequentialGroup()
                        .addComponent(boton_saberMas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        TITULOLayout.setVerticalGroup(
            TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TITULOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(boton_saberMas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        BG.add(TITULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 640, 520));

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
            // Get the text from fields, use existing values if empty
            String newName = nameField.getText().trim().isEmpty() ? account.getNombre() : nameField.getText().trim();
            String newLastName = lastNameField.getText().trim().isEmpty() ? account.getApellido() : lastNameField.getText().trim();
            String newPhone = phoneField.getText().trim().isEmpty() ? account.getPhone() : phoneField.getText().trim();
            String newEmail = emailField.getText().trim().isEmpty() ? account.getEmail() : emailField.getText().trim();
            
            // Update only if values are different from current ones
            if (!newName.equals(account.getNombre())) {
                accountController.updateName(account.getUser(), newName);
            }
            if (!newLastName.equals(account.getApellido())) {
                accountController.updateLast(account.getUser(), newLastName);
            }
            if (!newPhone.equals(account.getPhone())) {
                accountController.updatePhone(account.getUser(), newPhone);
            }
            if (!newEmail.equals(account.getEmail())) {
                accountController.updateEmail(account.getUser(), newEmail);
            }
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Información actualizada exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
                
            // Refresh the current account information
            Account updatedAccount = accountController.getByUsername(account.getUser());
            new FMR_MYACC(updatedAccount).setVisible(true);
            this.dispose();
            
        } catch (AccountException e) {
            JOptionPane.showMessageDialog(this,
                "Error al actualizar: " + e.getMessage(),
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

    private void boton_conceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_conceptoActionPerformed
        FMR_CONCEPTO newFrame = new FMR_CONCEPTO(account); 
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_conceptoActionPerformed

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
            java.util.logging.Logger.getLogger(FMR_MYACC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
            new FMR_MYACC().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BG;
    private javax.swing.JPanel TITULO;
    private javax.swing.JToggleButton boton_FaQ;
    private javax.swing.JToggleButton boton_events;
    private javax.swing.JToggleButton boton_logout;
    private javax.swing.JToggleButton boton_produt;
    private javax.swing.JToggleButton boton_reservas;
    private javax.swing.JToggleButton boton_saberMas;
    private javax.swing.JToggleButton boton_salas;
    private javax.swing.JButton boton_concepto;
    private javax.swing.JLabel titulo;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    //private JTextField userField;
    // End of variables declaration//GEN-END:variables
}
