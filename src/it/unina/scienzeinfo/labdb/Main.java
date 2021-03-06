/*
 * Main.java
 *
 * Created on 22 aprile 2009, 15.35
 */
package it.unina.scienzeinfo.labdb;

import java.awt.Dimension; //Libreria per la gestione della dimensione delle finestre
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Frame principale.
 * @author  Gruppo 3
 */
public class Main extends javax.swing.JFrame {

    /** Creates new form Main */
    public Main() {
        //Settaggio dell'ambiente grafico del gestore Nimbus
        String plaf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
        try {
            UIManager.setLookAndFeel(plaf);
            SwingUtilities.updateComponentTreeUI(Main.this);
        } catch (Exception e) {
        }
        initComponents();
        //Gestione posizione della finestra


        Dimension screen = getToolkit().getScreenSize();
        int Largh = screen.width;
        int Alt = screen.height;
        int posX = (Largh - 811) / 2;
        int posY = (Alt - 423) / 2;
        setLocation(posX, posY);
        //Inizializzazione finetra di LogIn
        Login l;
        l = new Login(this, true);
        l.setVisible(true);
        if (l.getBottonePremuto() == Login.PREMUTO_ANNULLA) {
            dispose();
        } else {
            setVisible(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // La funzione InitComponet gestisce le proprietà degli elementi che
    //compongono il frame
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jToolBar = new javax.swing.JToolBar();
        bLogin = new javax.swing.JButton();
        bEsci = new javax.swing.JButton();
        jSFile = new javax.swing.JToolBar.Separator();
        bArea = new javax.swing.JButton();
        bProdotto = new javax.swing.JButton();
        bImpiegato = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        bStatVendite = new javax.swing.JButton();
        bStatObbiettivo = new javax.swing.JButton();
        bObbiettivo = new javax.swing.JButton();
        jSFunzioni = new javax.swing.JToolBar.Separator();
        bScontrino = new javax.swing.JButton();
        bFattura = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSfondo = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        menFile = new javax.swing.JMenu();
        miLogin = new javax.swing.JMenuItem();
        miEsci = new javax.swing.JMenuItem();
        menFunzioni = new javax.swing.JMenu();
        miArea = new javax.swing.JMenuItem();
        miProdotto = new javax.swing.JMenuItem();
        miImpiegato = new javax.swing.JMenuItem();
        miObbiettivo = new javax.swing.JMenuItem();
        miStatVendite = new javax.swing.JMenuItem();
        miStatObbiettivo = new javax.swing.JMenuItem();
        miFattura = new javax.swing.JMenuItem();
        miScontrino = new javax.swing.JMenuItem();
        menAiuto = new javax.swing.JMenu();
        miTastiVeloci = new javax.swing.JMenuItem();
        miManualeUtente = new javax.swing.JMenuItem();
        miAbout = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(".:Bevenuti al JetMarket:.");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setMaximizedBounds(new java.awt.Rectangle(100, 1000, 1000, 1000));
        setName("sad"); // NOI18N
        setResizable(false);

        bLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/connect.png"))); // NOI18N
        bLogin.setToolTipText("Connetti");
        bLogin.setFocusable(false);
        bLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bLogin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });
        jToolBar.add(bLogin);

        bEsci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/disconnect.png"))); // NOI18N
        bEsci.setToolTipText("Esci");
        bEsci.setFocusable(false);
        bEsci.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bEsci.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEsciActionPerformed(evt);
            }
        });
        jToolBar.add(bEsci);
        jToolBar.add(jSFile);

        bArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/bricks.png"))); // NOI18N
        bArea.setToolTipText("Area");
        bArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAreaActionPerformed(evt);
            }
        });
        jToolBar.add(bArea);

        bProdotto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/cart_put.png"))); // NOI18N
        bProdotto.setToolTipText("Prodotto");
        bProdotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProdottoActionPerformed(evt);
            }
        });
        jToolBar.add(bProdotto);

        bImpiegato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/user.png"))); // NOI18N
        bImpiegato.setToolTipText("Impiegato");
        bImpiegato.setFocusable(false);
        bImpiegato.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bImpiegato.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bImpiegato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImpiegatoActionPerformed(evt);
            }
        });
        jToolBar.add(bImpiegato);
        jToolBar.add(jSeparator1);

        bStatVendite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/chart_curve.png"))); // NOI18N
        bStatVendite.setToolTipText("Statistiche Vendita");
        bStatVendite.setFocusable(false);
        bStatVendite.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bStatVendite.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bStatVendite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStatVenditeActionPerformed(evt);
            }
        });
        jToolBar.add(bStatVendite);

        bStatObbiettivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/chart_bar.png"))); // NOI18N
        bStatObbiettivo.setToolTipText("Statistiche Obbiettivo");
        bStatObbiettivo.setFocusable(false);
        bStatObbiettivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bStatObbiettivo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bStatObbiettivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStatObbiettivoActionPerformed(evt);
            }
        });
        jToolBar.add(bStatObbiettivo);

        bObbiettivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/award_star_bronze_2.png"))); // NOI18N
        bObbiettivo.setToolTipText("Obbiettivo");
        bObbiettivo.setFocusable(false);
        bObbiettivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bObbiettivo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bObbiettivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bObbiettivoActionPerformed(evt);
            }
        });
        jToolBar.add(bObbiettivo);
        jToolBar.add(jSFunzioni);

        bScontrino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/application_view_columns.png"))); // NOI18N
        bScontrino.setToolTipText("Scontrino");
        bScontrino.setFocusable(false);
        bScontrino.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bScontrino.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bScontrino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bScontrinoActionPerformed(evt);
            }
        });
        jToolBar.add(bScontrino);

        bFattura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/book_open.png"))); // NOI18N
        bFattura.setToolTipText("Fattura");
        bFattura.setFocusable(false);
        bFattura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bFattura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bFattura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFatturaActionPerformed(evt);
            }
        });
        jToolBar.add(bFattura);
        jToolBar.add(jSeparator2);

        jSfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/SuperMarketDesk.png"))); // NOI18N

        menFile.setMnemonic('i');
        menFile.setText("File");

        miLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        miLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/connect.png"))); // NOI18N
        miLogin.setText("Login");
        miLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLoginActionPerformed(evt);
            }
        });
        menFile.add(miLogin);

        miEsci.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.CTRL_MASK));
        miEsci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/disconnect.png"))); // NOI18N
        miEsci.setText("Esci");
        miEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEsciActionPerformed(evt);
            }
        });
        menFile.add(miEsci);

        jMenuBar.add(menFile);

        menFunzioni.setMnemonic('F');
        menFunzioni.setText("Funzioni");

        miArea.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        miArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/bricks.png"))); // NOI18N
        miArea.setText("Area");
        miArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAreaActionPerformed(evt);
            }
        });
        menFunzioni.add(miArea);

        miProdotto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        miProdotto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/cart_put.png"))); // NOI18N
        miProdotto.setText("Prodotto");
        miProdotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProdottoActionPerformed(evt);
            }
        });
        menFunzioni.add(miProdotto);

        miImpiegato.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        miImpiegato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/user.png"))); // NOI18N
        miImpiegato.setText("Impiegato");
        miImpiegato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImpiegatoActionPerformed(evt);
            }
        });
        menFunzioni.add(miImpiegato);

        miObbiettivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        miObbiettivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/award_star_bronze_2.png"))); // NOI18N
        miObbiettivo.setText("Obbiettivo");
        miObbiettivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miObbiettivoActionPerformed(evt);
            }
        });
        menFunzioni.add(miObbiettivo);

        miStatVendite.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miStatVendite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/chart_curve.png"))); // NOI18N
        miStatVendite.setText("Stat Vendite");
        miStatVendite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miStatVenditeActionPerformed(evt);
            }
        });
        menFunzioni.add(miStatVendite);

        miStatObbiettivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        miStatObbiettivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/chart_bar.png"))); // NOI18N
        miStatObbiettivo.setText("Stat Obbiettivi");
        miStatObbiettivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miStatObbiettivoActionPerformed(evt);
            }
        });
        menFunzioni.add(miStatObbiettivo);

        miFattura.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        miFattura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/book_open.png"))); // NOI18N
        miFattura.setText("Fattura");
        miFattura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFatturaActionPerformed(evt);
            }
        });
        menFunzioni.add(miFattura);

        miScontrino.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        miScontrino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/application_view_columns.png"))); // NOI18N
        miScontrino.setText("Scontrino");
        miScontrino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miScontrinoActionPerformed(evt);
            }
        });
        menFunzioni.add(miScontrino);

        jMenuBar.add(menFunzioni);

        menAiuto.setMnemonic('1');
        menAiuto.setText("Aiuto");

        miTastiVeloci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/arrow_branch.png"))); // NOI18N
        miTastiVeloci.setText("Tasti Veloci");
        miTastiVeloci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miTastiVelociActionPerformed(evt);
            }
        });
        menAiuto.add(miTastiVeloci);

        miManualeUtente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/attach.png"))); // NOI18N
        miManualeUtente.setText("Manuale Utente");
        miManualeUtente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miManualeUtenteActionPerformed(evt);
            }
        });
        menAiuto.add(miManualeUtente);

        miAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/help.png"))); // NOI18N
        miAbout.setText("About");
        miAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAboutActionPerformed(evt);
            }
        });
        menAiuto.add(miAbout);

        jMenuBar.add(menAiuto);

        setJMenuBar(jMenuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSfondo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(jToolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                .add(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSfondo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Azione sull'elemento Scontrino del MenuItem
    private void miEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEsciActionPerformed
        dispose();
    }//GEN-LAST:event_miEsciActionPerformed
    //Azione sull'elemento Prodotto del MenuItem
    private void miProdottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProdottoActionPerformed
        prodotto();
}//GEN-LAST:event_miProdottoActionPerformed
    //Azione sul tasto Prodotto della toolbar
    private void bProdottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProdottoActionPerformed
        prodotto();
}//GEN-LAST:event_bProdottoActionPerformed
    //Azione sull'elemento Login del MenuItem
    private void miLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLoginActionPerformed
        Login l;
        l = new Login(this, true);
        l.setVisible(true);
        if (l.getBottonePremuto() == Login.PREMUTO_ANNULLA) {
            dispose();
        }
    }//GEN-LAST:event_miLoginActionPerformed
    //Azione sul tasto Scaffale della toolbar    //Azione sul tasto Area della toolbar
    private void bAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAreaActionPerformed
        area();
}//GEN-LAST:event_bAreaActionPerformed
    //Azione sull'elemento Scaffale del MenuItem    //Azione sull'elemento Area del MenuItem    //Azione sul tasto Impiegato della toolbar
    private void bImpiegatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImpiegatoActionPerformed
        impiegato();
}//GEN-LAST:event_bImpiegatoActionPerformed
    //Azione sul tasto Ripiano della toolbar    //Azione sul tasto Esci della toolbar
    private void bEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEsciActionPerformed
        dispose();
}//GEN-LAST:event_bEsciActionPerformed
    //Azione sull'elemento Impiegato del MenuItem
    private void miImpiegatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miImpiegatoActionPerformed
        impiegato();
    }//GEN-LAST:event_miImpiegatoActionPerformed
    //Azione sul tasto Statistiche della toolbar
    private void bStatVenditeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStatVenditeActionPerformed
        statistichevendita();
}//GEN-LAST:event_bStatVenditeActionPerformed
    //Azione sul tasto Login della toolbar
    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
        Login l;
        l = new Login(this, true);
        l.setVisible(true);
        if (l.getBottonePremuto() == Login.PREMUTO_ANNULLA) {
            dispose();
        }
    }//GEN-LAST:event_bLoginActionPerformed
    //Azione sul tasto Obbiettivo della toolbar
    private void bObbiettivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bObbiettivoActionPerformed
        obbiettivo();
    }//GEN-LAST:event_bObbiettivoActionPerformed
    //Azione sul tasto Scontrino della toolbar
    private void bScontrinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bScontrinoActionPerformed
        scontrino();
    }//GEN-LAST:event_bScontrinoActionPerformed
    //Azione sul tasto Fattura della toolbar
    private void bFatturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFatturaActionPerformed

        fattura();

}//GEN-LAST:event_bFatturaActionPerformed
    //Azione sull'elemento Area del MenuItem
    private void miAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAreaActionPerformed
        area();
}//GEN-LAST:event_miAreaActionPerformed
    //Azione sull'elemento Obbiettivo del MenuItem
    private void miObbiettivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miObbiettivoActionPerformed
        obbiettivo();
}//GEN-LAST:event_miObbiettivoActionPerformed
    //Azione sull'elemento StatisticheVendita del MenuItem
    private void miStatVenditeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miStatVenditeActionPerformed
        statistichevendita();
}//GEN-LAST:event_miStatVenditeActionPerformed
    //Azione sull'elemento Fattura del MenuItem
    private void miFatturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFatturaActionPerformed
        fattura();
}//GEN-LAST:event_miFatturaActionPerformed
    //Azione sull'elemento Scontrino del MenuItem
    private void miScontrinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miScontrinoActionPerformed
        scontrino();
}//GEN-LAST:event_miScontrinoActionPerformed
    //Azione sul tasto StatisticheObbiettivo della toolbar
    private void bStatObbiettivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStatObbiettivoActionPerformed
        statisticheobbiettivo();
}//GEN-LAST:event_bStatObbiettivoActionPerformed
    //Azione sull'elemento StatisticheObbiettivo del MenuItem
    private void miStatObbiettivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miStatObbiettivoActionPerformed
        statisticheobbiettivo();
    }//GEN-LAST:event_miStatObbiettivoActionPerformed

    private void miTastiVelociActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miTastiVelociActionPerformed
        File shortcut=new File("shortcut.pdf");
        try {
            java.awt.Desktop.getDesktop().open(shortcut);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_miTastiVelociActionPerformed

    private void miManualeUtenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miManualeUtenteActionPerformed
        File manuale=new File("ManualeUtente.pdf");
        try {
            java.awt.Desktop.getDesktop().open(manuale);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_miManualeUtenteActionPerformed

    private void miAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAboutActionPerformed
        JOptionPane.showMessageDialog(this, "JETMARKET v1.0 \n\nRealizzato da:\n\nPasquale Catalano \nSimone Celestino\nEmanuele Di Cesare", "About", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_miAboutActionPerformed

    //Metodi per avviare la gesione delle funzionalità
    private void prodotto() {
        Prodotto m;
        m = new Prodotto();
        m.setVisible(true);
    }

    private void area() {
        Area m;
        m = new Area();
        m.setVisible(true);
    }

    private void scontrino() {
        Scontrino m;
        m = new Scontrino();
        m.setVisible(true);
    }

    private void impiegato() {
        Impiegato m;
        m = new Impiegato();
        m.setVisible(true);
    }

    private void statisticheobbiettivo() {
        StatisticheObbiettivo m;
        m = new StatisticheObbiettivo();
        m.setVisible(true);
    }

    private void statistichevendita() {
        StatisticheVendita m;
        m = new StatisticheVendita();
        m.setVisible(true);
    }

    private void obbiettivo() {
        Obbiettivo m;
        m = new Obbiettivo();
        m.setVisible(true);
    }

    private void fattura() {
        Fattura m;
        m = new Fattura();
        m.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main();

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bArea;
    private javax.swing.JButton bEsci;
    private javax.swing.JButton bFattura;
    private javax.swing.JButton bImpiegato;
    private javax.swing.JButton bLogin;
    private javax.swing.JButton bObbiettivo;
    private javax.swing.JButton bProdotto;
    private javax.swing.JButton bScontrino;
    private javax.swing.JButton bStatObbiettivo;
    private javax.swing.JButton bStatVendite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JToolBar.Separator jSFile;
    private javax.swing.JToolBar.Separator jSFunzioni;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JLabel jSfondo;
    private javax.swing.JToolBar jToolBar;
    private javax.swing.JMenu menAiuto;
    private javax.swing.JMenu menFile;
    private javax.swing.JMenu menFunzioni;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miArea;
    private javax.swing.JMenuItem miEsci;
    private javax.swing.JMenuItem miFattura;
    private javax.swing.JMenuItem miImpiegato;
    private javax.swing.JMenuItem miLogin;
    private javax.swing.JMenuItem miManualeUtente;
    private javax.swing.JMenuItem miObbiettivo;
    private javax.swing.JMenuItem miProdotto;
    private javax.swing.JMenuItem miScontrino;
    private javax.swing.JMenuItem miStatObbiettivo;
    private javax.swing.JMenuItem miStatVendite;
    private javax.swing.JMenuItem miTastiVeloci;
    // End of variables declaration//GEN-END:variables
}
