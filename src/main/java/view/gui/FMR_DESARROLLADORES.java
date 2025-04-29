package view.gui;

import controller.*;
import exceptions.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import javax.swing.border.EmptyBorder;

/**
 * @author KNOWLES
 */
public class FMR_DESARROLLADORES extends javax.swing.JFrame {
    private final Account account;
    
    // Variables para el panel de equipo de desarrollo
    private javax.swing.JScrollPane scrollPanelEquipo;
    private javax.swing.JPanel panelEquipoDesarrollo;
    private javax.swing.JLabel labelTituloEquipo;
    
    /**
     * Creates new form FMR_ContactoFaQ
     */
    public FMR_DESARROLLADORES() {
        this(null);
    }

    public FMR_DESARROLLADORES(Account account) {
        this.account = account;
        initComponents();
        setLocationRelativeTo(null);
        
        // Primero configuramos el panel de equipo
        setupTeamPanel();
        
        // Hacemos que el panel de fondo sea transparente
        jLabel1.setOpaque(false);
        panelEquipoDesarrollo.setOpaque(false);
        scrollPanelEquipo.setOpaque(false);
        scrollPanelEquipo.getViewport().setOpaque(false);
        
        // Aseguramos que la imagen de fondo esté detrás de todo
        BG.setComponentZOrder(jLabel1, BG.getComponentCount() - 1);
        
        // Y el panel de equipo por encima
        BG.setComponentZOrder(scrollPanelEquipo, 0);
        BG.setComponentZOrder(jLabel1, 1);
        
        // Refrescamos la UI para que los cambios se apliquen
        BG.revalidate();
        BG.repaint();
    }

    private void setupTeamPanel() {
        // ---- EQUIPO DE DESARROLLO ----
        labelTituloEquipo = new javax.swing.JLabel("EQUIPO DE DESARROLLO - GRUPO UNICORN");
        labelTituloEquipo.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTituloEquipo.setForeground(new java.awt.Color(51, 102, 0));
        labelTituloEquipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloEquipo.setOpaque(false);
        BG.add(labelTituloEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 560, 30));
    
        // Panel principal para contener la información del equipo
        panelEquipoDesarrollo = new javax.swing.JPanel();
        panelEquipoDesarrollo.setBackground(new java.awt.Color(252, 240, 198, 200)); // Añadimos transparencia
        panelEquipoDesarrollo.setLayout(new javax.swing.BoxLayout(panelEquipoDesarrollo, javax.swing.BoxLayout.Y_AXIS));
        panelEquipoDesarrollo.setOpaque(false); // Hacemos el panel transparente
    
        // Create background label for the panel
        jLabel1 = new JLabel();
        jLabel1.setBackground(new java.awt.Color(0, 0, 0, 80)); // Ya es semitransparente
        jLabel1.setOpaque(true);
    
        // Crear panel deslizable y agregar el panel del equipo
        scrollPanelEquipo = new javax.swing.JScrollPane();
        scrollPanelEquipo.setViewportView(panelEquipoDesarrollo);
        scrollPanelEquipo.setBorder(null); // Remove border
        scrollPanelEquipo.setOpaque(false);
        scrollPanelEquipo.getViewport().setOpaque(false);
    
        // Agregar información general del equipo
        JPanel panelInfoEquipo = new JPanel();
        panelInfoEquipo.setLayout(new BorderLayout(10, 10));
        panelInfoEquipo.setBackground(new java.awt.Color(252, 245, 220, 230)); // Semitransparente
        panelInfoEquipo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0), 2)
        ));
        panelInfoEquipo.setMaximumSize(new Dimension(620, 100));
        panelInfoEquipo.setPreferredSize(new Dimension(620, 100));
    
        JTextArea infoGeneral = new JTextArea(
            "MATERIA: METODOS Y TALLER DE PROGRAMACION\n" +
            "DOCENTE: VALENTIN LAIME ZAPATA\n" +
            "GRUPO: UNICORN"
        );
        infoGeneral.setLineWrap(true);
        infoGeneral.setWrapStyleWord(true);
        infoGeneral.setEditable(false);
        infoGeneral.setBackground(new java.awt.Color(252, 245, 220, 150)); // Semitransparente
        infoGeneral.setFont(new Font("Times New Roman", 1, 16));
        infoGeneral.setForeground(new java.awt.Color(51, 102, 0));
    
        panelInfoEquipo.add(infoGeneral, BorderLayout.CENTER);
        panelEquipoDesarrollo.add(panelInfoEquipo);
    
        // Agregar espacio entre secciones
        JPanel espaciador1 = new JPanel();
        espaciador1.setMaximumSize(new Dimension(620, 20));
        espaciador1.setPreferredSize(new Dimension(620, 20));
        espaciador1.setBackground(new java.awt.Color(252, 240, 198, 0)); // Completamente transparente
        espaciador1.setOpaque(false);
        panelEquipoDesarrollo.add(espaciador1);
    
        // Agregar miembros del equipo
        agregarMiembroEquipo("FIGUEREDO MANCILLA JOSE ARMANDO", "/resources/jose.jpg", 
            "Desarrollador principal del proyecto. Encargado de la arquitectura general del sistema y de la implementación de la lógica de proyecto. " +
            "Supervisó la integración entre el frontend y backend.Colaboró en decisiones clave de diseño, asegurando una estructura escalable y funcional.");
    
        agregarMiembroEquipo("ORELLANA CASTRO KEYLA YHAJAIRA", "/resources/keyla.jpg", 
            "Especialista en interfaces gráficas. Responsable del diseño y desarrollo de las pantallas de la aplicación y de la experiencia de usuario. " +
            "Además, participó en el testing y documentación del sistema, realizando pruebas exhaustivas y contribuyendo a la elaboración de la documentación técnica.");
    
        agregarMiembroEquipo("PUMA URIBE WENDY", "/resources/wendy.jpg", 
            "Desarrolladora de UI/UX. Diseñó los componentes visuales y contribuyó con la implementación de la interfaz gráfica del sistema. " +
            "También asumió responsabilidades en testing y documentación, realizando pruebas exhaustivas del sistema y elaborando la documentación técnica del proyecto.");
    
        agregarMiembroEquipo("COLQUE ORELLANA JHAIR HENRY", "/resources/Henry.jpg", 
            "Especialista en interfaces gráficas. Responsable del diseño y desarrollo de las pantallas de la aplicación y de la experiencia de usuario. " +
            "Además, participó en el testing y documentación del sistema, realizando pruebas exhaustivas y contribuyendo a la elaboración de la documentación técnica.");
    
        agregarMiembroEquipo("ARISPE LEON JUAN DAVID", "/resources/David.jpg", 
            "Especialista en interfaces gráficas. Responsable del diseño y desarrollo de las pantallas de la aplicación y de la experiencia de usuario. " +
            "Además, participó en el testing y documentación del sistema, realizando pruebas exhaustivas y contribuyendo a la elaboración de la documentación técnica.");
    
        // Asegurarse de que el panel y la imagen usen las mismas coordenadas
        BG.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 640, 365));
        BG.add(scrollPanelEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 640, 365));
    }

    private void agregarMiembroEquipo(String nombre, String rutaImagen, String descripcion) {
        // Panel para cada miembro del equipo
        JPanel panelMiembro = new JPanel();
        panelMiembro.setLayout(new BorderLayout(10, 10));
        panelMiembro.setBackground(new java.awt.Color(252, 245, 220, 230)); // Semitransparente
        panelMiembro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0), 2)
        ));
        
        // Aumentamos la altura máxima y preferida para que quepa todo el texto
        panelMiembro.setMaximumSize(new Dimension(620, 150)); // Aumentamos altura
        panelMiembro.setPreferredSize(new Dimension(620, 150)); // Aumentamos altura
        
        try {
            // Panel para la imagen
            JPanel panelImagen = new JPanel(new GridBagLayout());
            panelImagen.setBackground(new java.awt.Color(252, 245, 220, 200)); // Semitransparente
            panelImagen.setPreferredSize(new Dimension(80, 100));
    
            // Cargar la imagen si se proporcionó una ruta
            JLabel labelImagen;
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                // Cargar imagen desde recursos
                ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
                
                // Redimensionar la imagen manteniendo la relación de aspecto
                Image img = iconoOriginal.getImage();
                int anchoDeseado = 60;
                int altoDeseado = 60;
                
                // Calcular dimensiones manteniendo proporciones
                double relacion = (double) iconoOriginal.getIconWidth() / iconoOriginal.getIconHeight();
                if (relacion > 1) {
                    altoDeseado = (int) (anchoDeseado / relacion);
                } else {
                    anchoDeseado = (int) (altoDeseado * relacion);
                }
                
                Image imagenRedimensionada = img.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
                ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
                
                labelImagen = new JLabel(iconoRedimensionado);
            } else {
                // Si no hay imagen, mostrar iniciales como respaldo
                String iniciales = obtenerIniciales(nombre);
                labelImagen = new JLabel(iniciales);
                labelImagen.setFont(new Font("Arial", Font.BOLD, 24));
                labelImagen.setForeground(new java.awt.Color(255, 255, 255));
                labelImagen.setBackground(new java.awt.Color(51, 153, 0));
                labelImagen.setOpaque(true);
            }
            
            labelImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            labelImagen.setPreferredSize(new Dimension(60, 60));
            
            // Hacer la imagen circular
            JPanel panelCircular = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new java.awt.Color(51, 153, 0));
                    g2.fillOval(0, 0, 60, 60);
                    g2.dispose();
                }
            };
            panelCircular.setLayout(new GridBagLayout());
            panelCircular.setPreferredSize(new Dimension(60, 60));
            panelCircular.setOpaque(false);
            panelCircular.add(labelImagen);
            
            panelImagen.add(panelCircular);
            
            // Etiqueta para el nombre
            JLabel labelNombre = new JLabel(nombre);
            labelNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
            labelNombre.setForeground(new java.awt.Color(51, 102, 0));
            
            // Área de texto para la descripción con scroll en caso necesario
            JTextArea areaDescripcion = new JTextArea(descripcion);
            areaDescripcion.setLineWrap(true);
            areaDescripcion.setWrapStyleWord(true);
            areaDescripcion.setEditable(false);
            areaDescripcion.setBackground(new java.awt.Color(252, 245, 220, 200)); // Semitransparente
            areaDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            areaDescripcion.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Agrega margen interno
            
            // Agregar scroll al área de texto para descripciones largas
            JScrollPane scrollDescripcion = new JScrollPane(areaDescripcion);
            scrollDescripcion.setBorder(null); // Sin borde
            scrollDescripcion.setBackground(new java.awt.Color(252, 245, 220, 0));
            scrollDescripcion.setOpaque(false);
            scrollDescripcion.getViewport().setOpaque(false);
            
            // Panel para contener nombre y descripción
            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new BorderLayout(5, 5));
            panelInfo.setBackground(new java.awt.Color(252, 245, 220, 200)); // Semitransparente
            panelInfo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Agrega margen interno
            panelInfo.add(labelNombre, BorderLayout.NORTH);
            panelInfo.add(scrollDescripcion, BorderLayout.CENTER); // Usamos el scroll en lugar del área de texto directamente
            
            // Agregar componentes al panel del miembro
            panelMiembro.add(panelImagen, BorderLayout.WEST);
            panelMiembro.add(panelInfo, BorderLayout.CENTER);
            
            // Agregar espacio entre miembros
            JPanel espaciador = new JPanel();
            espaciador.setMaximumSize(new Dimension(500, 15));
            espaciador.setPreferredSize(new Dimension(500, 15));
            espaciador.setBackground(new java.awt.Color(252, 240, 198, 0)); // Completamente transparente
            espaciador.setOpaque(false);
            
            // Agregar a panel principal
            panelEquipoDesarrollo.add(panelMiembro);
            panelEquipoDesarrollo.add(espaciador);
            
        } catch (Exception e) {
            System.err.println("Error al agregar miembro del equipo: " + e.getMessage());
            // En caso de error, mostrar las iniciales como respaldo
            agregarMiembroEquipo(nombre, null, descripcion);
        }
    }

    private String obtenerIniciales(String nombreCompleto) {
        StringBuilder iniciales = new StringBuilder();
        String[] palabras = nombreCompleto.split(" ");
        
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
                if (iniciales.length() >= 2) break; // Máximo 2 iniciales
            }
        }
        
        return iniciales.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG = new javax.swing.JPanel();
        boton_events = new javax.swing.JToggleButton();
        boton_product = new javax.swing.JToggleButton();
        boton_salas = new javax.swing.JToggleButton();
        boton_FaQ = new javax.swing.JToggleButton();
        boton_booking = new javax.swing.JToggleButton();
        boton_logout = new javax.swing.JToggleButton();
        boton_concepto = new javax.swing.JButton();
        TITULO = new javax.swing.JPanel();
        boton_myCuenta = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        boton_volver = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(875, 555));
        setName("CONTACTO"); // NOI18N
        setPreferredSize(new java.awt.Dimension(875, 555));
        setSize(new java.awt.Dimension(875, 555));

        BG.setBackground(new java.awt.Color(252, 240, 198));
        BG.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BG.setMinimumSize(new java.awt.Dimension(860, 520));
        BG.setPreferredSize(new java.awt.Dimension(860, 520));
        BG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        BG.add(boton_events, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 130, 30));

        boton_product.setBackground(new java.awt.Color(51, 153, 0));
        boton_product.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boton_product.setForeground(new java.awt.Color(255, 255, 255));
        boton_product.setText("Productos");
        boton_product.setToolTipText("");
        boton_product.setAutoscrolls(true);
        boton_product.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_productActionPerformed(evt);
            }
        });
        BG.add(boton_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 130, 30));

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
        BG.add(boton_salas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 130, 30));

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
        BG.add(boton_FaQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 130, 30));

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
        BG.add(boton_booking, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 130, 30));

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
        BG.add(boton_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 130, 30));

        boton_concepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N
        boton_concepto.setBorder(null);
        boton_concepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_conceptoActionPerformed(evt);
            }
        });
        BG.add(boton_concepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

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
        titulo.setText("¿Quienes Somos?");
        titulo.setToolTipText("");

        javax.swing.GroupLayout TITULOLayout = new javax.swing.GroupLayout(TITULO);
        TITULO.setLayout(TITULOLayout);
        TITULOLayout.setHorizontalGroup(
            TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TITULOLayout.createSequentialGroup()
                .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        boton_volver.setBackground(new java.awt.Color(51, 153, 0));
        boton_volver.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
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
        BG.add(boton_volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 470, 130, 30));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pa (1).jpg"))); // NOI18N
        BG.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 640, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_eventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_eventsActionPerformed
        FMR_EVENT newFrame = new FMR_EVENT(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_eventsActionPerformed

    private void boton_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_productActionPerformed
        FMR_PRODUCT newFrame = new FMR_PRODUCT(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_productActionPerformed

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

    private void boton_conceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_conceptoActionPerformed
        FMR_CONCEPTO newFrame = new FMR_CONCEPTO(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_conceptoActionPerformed

    private void boton_myCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_myCuentaActionPerformed
        FMR_MYACC newFrame = new FMR_MYACC(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_myCuentaActionPerformed

    private void boton_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_volverActionPerformed
        FMR_FaQ newFrame = new FMR_FaQ(account);
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_volverActionPerformed

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
            java.util.logging.Logger.getLogger(FMR_DESARROLLADORES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FMR_DESARROLLADORES().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BG;
    private javax.swing.JPanel TITULO;
    private javax.swing.JToggleButton boton_FaQ;
    private javax.swing.JToggleButton boton_booking;
    private javax.swing.JButton boton_concepto;
    private javax.swing.JToggleButton boton_events;
    private javax.swing.JToggleButton boton_logout;
    private javax.swing.JButton boton_myCuenta;
    private javax.swing.JToggleButton boton_product;
    private javax.swing.JToggleButton boton_salas;
    private javax.swing.JToggleButton boton_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}