package IGU;

public class MenuPrincipalFrame extends javax.swing.JFrame {

    public MenuPrincipalFrame() {
        initComponents();
        jMenuSalir.setText("Salir");
        jMenuGestionar.add(jMenuSalir);
        
    jMenuSalir.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuSalirActionPerformed(evt);
        }
    });
   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jMenuGestionar = new javax.swing.JMenuBar();
        menuPrestamos = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuLibros = new javax.swing.JMenuItem();
        menuSuscriptores = new javax.swing.JMenuItem();
        jMenuInform = new javax.swing.JMenu();
        menuPrestamosVigentes = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(232, 216, 232));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        menuPrestamos.setText("Gestionar");

        jMenuItem3.setText("Prestamos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuPrestamos.add(jMenuItem3);

        menuLibros.setText("Libros");
        menuLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLibrosActionPerformed(evt);
            }
        });
        menuPrestamos.add(menuLibros);

        menuSuscriptores.setText("Suscriptores");
        menuSuscriptores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSuscriptoresActionPerformed(evt);
            }
        });
        menuPrestamos.add(menuSuscriptores);

        jMenuGestionar.add(menuPrestamos);

        jMenuInform.setText("Informes");

        menuPrestamosVigentes.setText("Prestamos Vigentes");
        menuPrestamosVigentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPrestamosVigentesActionPerformed(evt);
            }
        });
        jMenuInform.add(menuPrestamosVigentes);

        jMenuGestionar.add(jMenuInform);

        jMenuSalir.setText("Salir");
        jMenuGestionar.add(jMenuSalir);

        setJMenuBar(jMenuGestionar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new GestionPrestamosFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void menuLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLibrosActionPerformed
        new GestionLibrosFrame().setVisible(true);
    }//GEN-LAST:event_menuLibrosActionPerformed

    private void menuSuscriptoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSuscriptoresActionPerformed
        new GestionSuscriptoresFrame().setVisible(true);
    }//GEN-LAST:event_menuSuscriptoresActionPerformed

    private void menuPrestamosVigentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPrestamosVigentesActionPerformed
        new FrmPrestamosVigentes().setVisible(true);
    }//GEN-LAST:event_menuPrestamosVigentesActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {      
    int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que desea salir del sistema?",
            "Confirmar salida",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        System.exit(0);
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
            java.util.logging.Logger.getLogger(MenuPrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuGestionar;
    private javax.swing.JMenu jMenuInform;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem menuLibros;
    private javax.swing.JMenu menuPrestamos;
    private javax.swing.JMenuItem menuPrestamosVigentes;
    private javax.swing.JMenuItem menuSuscriptores;
    // End of variables declaration//GEN-END:variables
}
