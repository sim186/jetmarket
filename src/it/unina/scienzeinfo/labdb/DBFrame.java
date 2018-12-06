/*
 * DBFrame.java
 *
 * Created on 23 aprile 2009, 8.48
 */

package it.unina.scienzeinfo.labdb;
import java.sql.*;
import javax.swing.*;
import java.awt.Dimension;
import java.sql.Statement;

/**
 * Classe base per i frame di gestione delle tabelle principali.
 * <br>
 * Permette le operazioni di inserimento, cancellazione, ricerca e navigazione.
 * @author  Gruppo 3
 */
abstract public class DBFrame extends javax.swing.JFrame{
    
    /**
     * Crea un nuovo DBFrame
     */
    public DBFrame() {
        initComponents();        
        //bOk.setVisible(false);
    }
    
    /**
     * Indica che il frame si trova nello stato di inserimento di un nuovo record
     * o dei parametri di ricerca.
     **/
    final static public int APPEND_QUERY=1;
    /**
     * Indica che il frame si trova nello stato di navigazione (ricerca già effettuata).
     **/
    final static public int BROWSE=2;
    /**
     * Indica che il frame si trova nello stato di modifica dei dati.
     **/
    final static public int UPDATE=3;
    /**
     * Indica che il frame si trova nello stato di vendita.
     **/
    final static public int SELL=4;
   /**
     * Indica che il frame si trova nello stato di scontrino o fattura.
     **/
    final static public int TICKET=5;
    
    /** indica che l'eccezione è stata sollevata eseguendo un comando <i>SELECT</i>.*/
    final static public int CONTESTO_ESEGUI_QUERY=1;
    /** Stato corrente del Frame.*/
    private int modalita;
    /** Posizione del record corrente nel ResultSet.**/
    private int pos=1;
    /** modello della tabella di navigazione */
    private DBTableModel modelloTabella;
    /** ResultSet dell'ultima query eseguita*/ 
    protected ResultSet rs;
    /**Query da eseguire.*/
    protected String query;
    /*Stringa contenente il nome della tabella in uso*/
    private String nomeTabella;
    /*Modello di Jtable */
    private javax.swing.JTable tabFrameTable;    

    /*Gestione posizione della finestra*/
    public void ImpostaPosizioneFinestra()
    {
       Dimension screen=getToolkit().getScreenSize();
       int Largh=screen.width;
       int Alt=screen.height;
       int posX=(Largh - 811)/2;
       int posY=(Alt - 423)/2;
       setLocation(posX,posY);
    }
    /** Restituisce modello della tabella di navigazione. */
    DBTableModel getModelloTabella() {
        return modelloTabella;
    }
    
    /** Imposta il puntatore al Form che usa quello corrente cone Lookup.*/
    void  setModelloTabella(DBTableModel gmt) {
        modelloTabella=gmt;
    }
    
    //Restituisce la stringa per la query
    String getQuery() {
        return query;
    }
    
    /** Imposta la query corrente.*/
    void  setQuery(String query) {
        this.query=query;
    }
    
    /** Restituisce il nome della tabella corrente.*/
    String getNomeTabella() {
        return nomeTabella;
    }
    
    /**Imposta il nome della tabella.*/
    void  setNomeTabella(String nomeTabella) {
        this.nomeTabella=nomeTabella;
    }
    
    
    /**
     * Imposta la tabella di navigazione del form.
     * <br>
     * In genere dovrebbe essere richiamato nei costruttori delle classi derivate.
     **/
    protected void setFrameTable(JTable t) {
        tabFrameTable=t;
        modelloTabella=new DBTableModel();
        tabFrameTable.setModel(modelloTabella);
        tabFrameTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabFrameTable.addMouseListener(new java.awt.event.MouseAdapter() {});
        
        tabFrameTable.getSelectionModel().addListSelectionListener(
                     new javax.swing.event.ListSelectionListener() {
                            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                                selezioneTabellaCambiata();
                            }
                     }
        );
    }
    
    /**Restituisce il codice della relativa scheda*/
    protected javax.swing.JTextField getTCodice() {
        return tCodice;
    }
    
    /**
     * Imposta lo stato corrente del form.
     * <br>
     * In base allo stato vengono abilitati o disabilitati alcuni oggetti del form.
     **/
    public void setModalita(int modo) {
        modalita=modo;
        switch (modo)
        {
            /** Indica che il frame si trova nello stato di inserimento
             * di un nuovo record o dei parametri di ricerca **/
            case APPEND_QUERY: 
                bPrecedente.setEnabled(false);
                bSuccessivo.setEnabled(false);
                bNuovo.setEnabled(true);
                bModifica.setEnabled(false);
                bSalva.setEnabled(true);
                bCerca.setEnabled(true);
                bAnnulla.setEnabled(false);
                bElimina.setEnabled(false);
                if (tabFrameTable!=null)
                    tabFrameTable.setEnabled(false);
                tCodice.setEnabled(true);
                break;
                /** Indica che il frame si trova nello stato di
                 * navigazione (ricerca già effettuata). */
            case BROWSE:
                bPrecedente.setEnabled(true);
                bSuccessivo.setEnabled(true);
                bNuovo.setEnabled(true);
                bModifica.setEnabled(true);
                bSalva.setEnabled(false);
                bCerca.setEnabled(false);
                bAnnulla.setEnabled(false);
                bElimina.setEnabled(true);
                if (tabFrameTable!=null)
                    tabFrameTable.setEnabled(true);
                tCodice.setEnabled(false);
                break;
                /** Indica che il frame si trova nello
                 * stato di modifica dei dati. **/
            case UPDATE:
                bPrecedente.setEnabled(false);
                bSuccessivo.setEnabled(false);
                bNuovo.setEnabled(true);
                bModifica.setEnabled(false);
                bSalva.setEnabled(true);
                bCerca.setEnabled(false);
                bAnnulla.setEnabled(true);
                bElimina.setEnabled(true);
                if (tabFrameTable!=null)
                    tabFrameTable.setEnabled(false);
                tCodice.setEnabled(false);
                break;
            case SELL:
                 /** Indica che il frame si trova nello stato
                  * di vendita.*/
                bPrecedente.setVisible(false);
                bSuccessivo.setVisible(false);
                bNuovo.setVisible(false);
                bModifica.setVisible(false);
                bSalva.setVisible(false);
                bCerca.setVisible(false);
                bAnnulla.setVisible(false);
                bElimina.setVisible(false);
                if (tabFrameTable!=null)
                    tabFrameTable.setEnabled(false);
                tCodice.setVisible(false);
                bNuovoCodice.setVisible(false);
                jCodice.setVisible(false);
                bChiudi.setVisible(false);
                bElenco.setVisible(true);
            case TICKET:
                 /** Indica che il frame si trova nello stato
                  * di scontrino o fattura.*/
                bPrecedente.setEnabled(true);
                bSuccessivo.setEnabled(true);
                bNuovo.setEnabled(true);
                bModifica.setEnabled(false);
                bSalva.setEnabled(false);
                bCerca.setEnabled(true);
                bAnnulla.setEnabled(false);
                bElimina.setEnabled(false);
                if (tabFrameTable!=null)
                    tabFrameTable.setEnabled(false);
                tCodice.setEnabled(false);
                bNuovoCodice.setVisible(false);
                break;

        }
    }
    
    /**
     * Mostra una descrizione di un errore SQL in un linguaggio comprensibile 
     * per l'utente finale.
     * <br>
     * L'implementazione di DBFrame fa eccezione, essa fornisce un messaggio 
     * standard per gli errori non previsti esplicitamente.
     **/
    protected void mostraErrori(SQLException e,int contesto)
    {
        String msg = null;
        if(e.getErrorCode()==17068 || e.getErrorCode()==17011){
            //Errori da non mostrare all'utente
            return;
        }
        switch(e.getErrorCode())
        {
            case 00001:   msg="Elemento già presente nel DataBase \nModifica i campi obbligatori!"; break;
            case 2291 :   msg="Verifica che i dati inseriti siano coerenti\ncon i dati inseriti precedentemente!";break;
            case 2292 :   msg="Non è possibile eliminare/modifare l'elemento \npoichè altri elementi fanno riferimento ad esso";break;
            case 936  :   msg="Si sta cercando di eliminare da una tabella vuota";break;
            default   :   msg="Si è verificato un errore,Riprova!";break;
        }
        JOptionPane.showMessageDialog(this, msg, "Errore", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Mostra una descrizione di un errore SQL in un linguaggio comprensibile 
     * per l'utente finale.
     * <br>
     * L'implementazione di DBFrame fa eccezione, essa fornisce un messaggio 
     * standard per gli errori non previsti esplicitamente.
     **/
    protected void mostraErrori(SQLException e)
    {
        mostraErrori(e,0);
    }

    protected boolean eseguiSalva(Connection con) {
        return true;
    }


    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bPrecedente = new javax.swing.JButton();
        bSuccessivo = new javax.swing.JButton();
        bNuovo = new javax.swing.JButton();
        bSalva = new javax.swing.JButton();
        bCerca = new javax.swing.JButton();
        jCodice = new javax.swing.JLabel();
        tCodice = new javax.swing.JTextField();
        bModifica = new javax.swing.JButton();
        bAnnulla = new javax.swing.JButton();
        bElimina = new javax.swing.JButton();
        bNuovoCodice = new javax.swing.JButton();
        bChiudi = new javax.swing.JButton();
        bElenco = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bPrecedente.setFont(new java.awt.Font("URW Gothic L", 0, 11));
        bPrecedente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/1leftarrow.resized.png"))); // NOI18N
        bPrecedente.setText("Prev");
        bPrecedente.setToolTipText("Vai alla riga precedente");
        bPrecedente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPrecedente.setPreferredSize(new java.awt.Dimension(75, 48));
        bPrecedente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPrecedente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrecedenteActionPerformed(evt);
            }
        });

        bSuccessivo.setFont(new java.awt.Font("URW Gothic L", 0, 11));
        bSuccessivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/1rightarrow.resized.png"))); // NOI18N
        bSuccessivo.setText("Next");
        bSuccessivo.setToolTipText("Vai alla riga successiva");
        bSuccessivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSuccessivo.setPreferredSize(new java.awt.Dimension(75, 48));
        bSuccessivo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSuccessivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSuccessivoActionPerformed(evt);
            }
        });

        bNuovo.setFont(new java.awt.Font("URW Gothic L", 0, 11));
        bNuovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/tab_new.resized.png"))); // NOI18N
        bNuovo.setMnemonic('N');
        bNuovo.setText("Nuovo");
        bNuovo.setToolTipText("Nuovo");
        bNuovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bNuovo.setMaximumSize(new java.awt.Dimension(62, 48));
        bNuovo.setMinimumSize(new java.awt.Dimension(62, 48));
        bNuovo.setPreferredSize(new java.awt.Dimension(75, 48));
        bNuovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bNuovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNuovoActionPerformed(evt);
            }
        });

        bSalva.setFont(new java.awt.Font("URW Gothic L", 0, 11));
        bSalva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/3floppy_unmount.resized.png"))); // NOI18N
        bSalva.setMnemonic('S');
        bSalva.setText("Salva");
        bSalva.setToolTipText("Salva");
        bSalva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSalva.setPreferredSize(new java.awt.Dimension(72, 48));
        bSalva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvaActionPerformed(evt);
            }
        });

        bCerca.setFont(new java.awt.Font("URW Gothic L", 0, 11)); // NOI18N
        bCerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/d3A big pack search.resized.png"))); // NOI18N
        bCerca.setMnemonic('E');
        bCerca.setText("Cerca");
        bCerca.setToolTipText("Cerca");
        bCerca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bCerca.setPreferredSize(new java.awt.Dimension(75, 48));
        bCerca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bCerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCercaActionPerformed(evt);
            }
        });

        jCodice.setText("Codice*");

        tCodice.setToolTipText("Inserire un codice da 0 a 999");

        bModifica.setFont(new java.awt.Font("URW Gothic L", 0, 10));
        bModifica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/Documents8686.resized.png"))); // NOI18N
        bModifica.setMnemonic('M');
        bModifica.setText("Modifica");
        bModifica.setToolTipText("Modifica");
        bModifica.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bModifica.setMaximumSize(new java.awt.Dimension(62, 48));
        bModifica.setMinimumSize(new java.awt.Dimension(62, 48));
        bModifica.setPreferredSize(new java.awt.Dimension(75, 48));
        bModifica.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificaActionPerformed(evt);
            }
        });

        bAnnulla.setFont(new java.awt.Font("URW Gothic L", 0, 11)); // NOI18N
        bAnnulla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/button_cancel.resized.png"))); // NOI18N
        bAnnulla.setMnemonic('A');
        bAnnulla.setText("Annulla");
        bAnnulla.setToolTipText("Annulla");
        bAnnulla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bAnnulla.setMaximumSize(new java.awt.Dimension(62, 48));
        bAnnulla.setMinimumSize(new java.awt.Dimension(62, 48));
        bAnnulla.setPreferredSize(new java.awt.Dimension(75, 48));
        bAnnulla.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnnullaActionPerformed(evt);
            }
        });

        bElimina.setFont(new java.awt.Font("URW Gothic L", 0, 11)); // NOI18N
        bElimina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/tab_remove.resized.png"))); // NOI18N
        bElimina.setMnemonic('l');
        bElimina.setText("Elimina");
        bElimina.setToolTipText("Elimina");
        bElimina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bElimina.setPreferredSize(new java.awt.Dimension(75, 48));
        bElimina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminaActionPerformed(evt);
            }
        });

        bNuovoCodice.setMnemonic('x');
        bNuovoCodice.setText("Next Code");
        bNuovoCodice.setToolTipText("Clicca per inserire il prossimo codice");
        bNuovoCodice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNuovoCodiceActionPerformed(evt);
            }
        });

        bChiudi.setFont(new java.awt.Font("URW Gothic L", 0, 11)); // NOI18N
        bChiudi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/exit.resized.png"))); // NOI18N
        bChiudi.setMnemonic('C');
        bChiudi.setText("Esci");
        bChiudi.setToolTipText("Chiudi Finestra");
        bChiudi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bChiudi.setPreferredSize(new java.awt.Dimension(62, 48));
        bChiudi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bChiudi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChiudiActionPerformed(evt);
            }
        });

        bElenco.setFont(new java.awt.Font("URW Gothic L", 0, 11)); // NOI18N
        bElenco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/unina/scienzeinfo/labdb/Immagini/kghostview.resized.png"))); // NOI18N
        bElenco.setMnemonic('d');
        bElenco.setText("Dati");
        bElenco.setToolTipText("Elenco Dati");
        bElenco.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bElenco.setPreferredSize(new java.awt.Dimension(75, 48));
        bElenco.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bElenco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bElencoActionPerformed(evt);
            }
        });

        jLabel1.setText("I campi contrassegnati da (*) sono obbligatori");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(bPrecedente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bSuccessivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bNuovo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jCodice)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(tCodice)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(bModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bSalva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bCerca, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(6, 6, 6)
                        .add(bElimina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bAnnulla, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bElenco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bChiudi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(bNuovoCodice)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel1)))
                .add(211, 211, 211))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(bPrecedente, 0, 0, Short.MAX_VALUE)
                    .add(bSuccessivo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .add(bNuovo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bSalva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bCerca, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bElimina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bAnnulla, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bElenco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bChiudi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jCodice)
                    .add(tCodice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bNuovoCodice)
                    .add(jLabel1))
                .add(337, 337, 337))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNuovoCodiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuovoCodiceActionPerformed
        impostaCodice();
    }//GEN-LAST:event_bNuovoCodiceActionPerformed
    //Immette all'interno del campo codice, il massimo valore(+1) della colonna codice
    protected void impostaCodice() {
        String codice;
        codice=Database.leggiValore("select nvl(max(codice)+1,1) from "+Database.schema+"."+this.nomeTabella).toString();
        tCodice.setText(codice);
    }   

    //Elimina la riga selezionata all'interno della tabella corrente
    private void bEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminaActionPerformed

            String cmd=null;
            cmd="delete from "+ Database.schema+"."+nomeTabella+" where codice="+tCodice.getText();
            try {
                if (rs.isLast()) {
                    pos--;
                }
            } catch (SQLException e) {
                mostraErrori(e);
            }
            eseguiComando(cmd);//Esegue la query per l'eliminazione
            pulisci();
            eseguiQuery();//Visualizza la tabella aggiornata dopo l'eliminazione
    }//GEN-LAST:event_bEliminaActionPerformed

    private void bAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnnullaActionPerformed
         eseguiQuery();
    }//GEN-LAST:event_bAnnullaActionPerformed

    private void selezioneTabellaCambiata() {                                       
        try {
            if(tabFrameTable.getSelectionModel().getMinSelectionIndex()+1 != 0){
            rs.absolute(tabFrameTable.getSelectionModel().getMinSelectionIndex()+1);
            mostraDati();
            }
        } catch (SQLException e) {    
              mostraErrori(e);
        } catch (Exception a) {}
    }                   
    //Imposta la modalità di modifica
    private void bModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificaActionPerformed
       setModalita(UPDATE);
}//GEN-LAST:event_bModificaActionPerformed

    /**
     * Mostra i dati presenti nel record corrente.
     * <br>
     * Necessita di overriding in tutte le classi derivate di DBFrame.  
     **/
    protected void mostraDati()
    {
        try {
            tCodice.setText(rs.getString("codice"));
            pos=rs.getRow();
            tabFrameTable.setRowSelectionInterval(pos-1,pos-1);
        } catch (SQLException e) {    
              mostraErrori(e);
        }
    }
    //Incrementa di una posizione nella tabella dei risultati
    private void bSuccessivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSuccessivoActionPerformed
        try{
            if (!rs.isLast())
                rs.next();            
        } catch (SQLException e) {    
              mostraErrori(e);
        }
        mostraDati();
    }//GEN-LAST:event_bSuccessivoActionPerformed
    //Decrementa di una posizione nella tabella dei risultati
    private void bPrecedenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrecedenteActionPerformed
        try {
            if (!rs.isFirst())
                rs.previous();
        } catch (SQLException e) {    
              mostraErrori(e);
        }
        mostraDati();
    }//GEN-LAST:event_bPrecedenteActionPerformed
 
    
    /**
     * Crea lo statement di ricerca.
     * <br>
     * Necessita di overriding in tutte le classi derivate di DBFrame.  
     **/
    protected PreparedStatement creaSelectStatement() {
        query="select * from "+Database.schema+"."+nomeTabella+ " ";
        return null;
    }
    private void bCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCercaActionPerformed
        eseguiQuery ();
    }//GEN-LAST:event_bCercaActionPerformed

    /**
     * Esegue una ricerca in base ai valori impostati nel form.
     * <br>
     * Non necessita di overriding nella classi derivate, occorre invece 
     * specializzare il metodo <i>creaSelectStatement</i>. 
     */
    private void eseguiQuery () {  
        PreparedStatement st; 
        try {
            st=creaSelectStatement();
            rs = st.executeQuery();
            
            modelloTabella.setRS(rs);
            if(pos!=0)
            {
                rs.absolute(pos);
                mostraDati();
            }

            setModalita(BROWSE);
        } catch (SQLException e) {
                        
              mostraErrori(e,CONTESTO_ESEGUI_QUERY);
        } catch (java.lang.NullPointerException e) {  
            //non devo mostrare nessun errore
            //si dovrebbe verificare solo se st=null
            //quando la connessione è caduta
        }
    }
    //Crea una finestra con l'elenco dati per facilitare l'inserimento
      private void elenco() {
        Elenco m;
        m = new Elenco(nomeTabella);
        m.setVisible(true);
    }
    
    /**
     * Cancella i dati presenti in tutti i controlli presenti sul form.
     * <br>
     * Necessita di overriding in tutte le classi derivate di DBFrame.  
     **/
    protected void pulisci() {        
        tCodice.setText("");
    }
    //Abilita la modalità di inserimento di un nuovo dato
    private void bNuovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuovoActionPerformed
        pulisci();
        pos=1;
        try {
           if (rs!=null )
              rs.close(); 
           rs=null;
           modelloTabella.setRS(rs);
           setModalita(APPEND_QUERY);
        } catch (SQLException e) {    
              mostraErrori(e);
        }
    }//GEN-LAST:event_bNuovoActionPerformed

    /**
     * Crea lo statement di inserimento.
     * <br>
     * Necessita di overriding in tutte le classi derivate di DBFrame.  
     **/
    abstract protected PreparedStatement getComandoInserimento(Connection c) throws SQLException;
    
    /**
     * Crea lo statement di aggiornamento.
     * <br>
     * Necessita di overriding in tutte le classi derivate di DBFrame.  
     **/
    abstract protected PreparedStatement getComandoAggiornamento(Connection c) throws SQLException;

    
    private void bSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvaActionPerformed
        Salva(evt);
    }//GEN-LAST:event_bSalvaActionPerformed
    //Salva all'intero del database i dati contenuti nel form
    protected void Salva(java.awt.event.ActionEvent evt)
    {
        PreparedStatement st;
        boolean ret;
        Connection c=null;
        //Se il campo codice risulta vuoto viene assegnato un codice valido
        if (this.tCodice.getText().trim().length()==0 && modalita!=SELL)
        {
            impostaCodice();
        }
        try {
            c=Database.nuovaConnessione();
            //Controlla se bisogna inserire un nuovo dato o aggiornare un dato esistente
            if (modalita==APPEND_QUERY || modalita==TICKET || modalita==SELL)
            {
                st=getComandoInserimento(c);
            } else {
                st=getComandoAggiornamento(c);
            }
            ret=false;
            c.setAutoCommit(false);
            if(st!= null)
            {  
               ret= st.executeUpdate()>=0;
               if(ret){
                   ret = eseguiSalva(c);
                   
               }
               if (ret){
                   c.commit();
               }
               else
                   c.rollback();
               c.setAutoCommit(true);
            }
         } catch (SQLException e) {
            mostraErrori(e);
            ret=false;
        }
        if (ret) {
             if (modalita==APPEND_QUERY)
             {
                bCercaActionPerformed(evt);
             }
            else if (modalita!=TICKET && modalita!=SELL)
            {
                eseguiQuery ();
            }
        } else {
            try {        
                c.rollback();
                c.setAutoCommit(true);
            } catch (SQLException e) {
                mostraErrori(e);
                ret=false;
            }
        }
}

     //Esegue la query passata in input
     protected void SendQuery(String cmd)
     {
            eseguiComando(cmd);//Esegue la query per l'eliminazione
            pulisci();
            eseguiQuery();//Visualizza la tabella aggiornata dopo l'eliminazione
     }
    //Quando viene premuto il tasto chiudi elimina la finestra
    private void bChiudiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChiudiActionPerformed
        dispose();
}//GEN-LAST:event_bChiudiActionPerformed
    //Quando viene premuto il tasto Dati mostra una finestra con il supporto per l'inserimento
    private void bElencoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bElencoActionPerformed
        elenco();
}//GEN-LAST:event_bElencoActionPerformed
                                  
    private boolean eseguiComando(String cmd) {
        return eseguiComando(cmd,null);
    }
    //Esegue la query passata in input
    private boolean eseguiComando(String cmd,Connection c) {
        Statement s;
        String query;
        Connection mycon;
        try {
                if (c==null)
                    mycon=Database.nuovaConnessione();
                else
                    mycon=c;
                s=mycon.createStatement();
                s.execute(cmd);
                if (c==null)
                  mycon.close();
            } catch (SQLException e) {    
                  mostraErrori(e);
                  return false;
            }
        return true;
    }

    //Controlla che i dati all'intero dei campi di corretti
    protected boolean ControllaInput(int Size, String Field,String Tipo)
    {
       boolean Controllo=false;
       if(Tipo.matches("N1toSize"))
       {
          //Controlla che il campo contenga numeri di lunghezza variabile da 1 a Size
          if(Field.matches("[0-9]{1,"+Size+"}"))
          {
             Controllo=true;
          }
       }
       else if(Tipo.matches("NSize"))
       {
          //Controlla che il campo contenga numeri di lunghezza pari a Size
          if(Field.matches("[0-9]{"+Size+"}"))
          {
             Controllo=true;
          }
       }
       else if(Tipo.matches("N0toSize"))
       {
          //Controlla che il campo contenga numeri di lunghezza variabile da 0 a Size
          if(Field.matches("[0-9]{0,"+Size+"}"))
          {
             Controllo=true;
          }
       }
       else if(Tipo.matches("NSSize"))
       {
          //Controlla che il campo contenga una stringa di n caratteri
          if(Field.matches("[a-zA-Z0-9]{"+Size+"}"))
          {
             Controllo=true;
          }
       }
       else if(Tipo.matches("S1toSize"))
       {
          //Controlla che il campo contenga una stringa di lunghezza variabile da 1 a Size
          if(Field.matches("[a-zA-Z]{1,"+Size+"}"))
          {
             Controllo=true;
          }
       }
       else if(Tipo.matches("S0toSize"))
       {
          //Controlla che il campo contenga una stringa di lunghezza variabile da 0 a Size
          if(Field.matches("[a-zA-Z]{0,"+Size+"}"))
          {
             Controllo=true;
          }
       }
       else if(Tipo.matches("F5.2"))
       {
          //Controlla che il campo contenga un float di 5 cifre prima della virgola e due dopo la virgola
          if(Field.matches("^[0-9]{1,5}(\\.[0-9]{1,2})?$"))
          {
             Controllo=true;
          }
       }
       else if(Tipo.matches("F3.2"))
       {
          //Controlla che il campo contenga un float di 3 cifre prima della virgila e due dopo la virgola
          if(Field.matches("^[0-9]{1,3}(\\.[0-9]{1,2})?$"))
          {
              Controllo=true;
          }
       }
       else if(Tipo.matches("Obb"))
       {
          //Controlla che il campo non contenga la stringa Scegli...
          if(!Field.matches("Scegli..."))
          {
             Controllo=true;
          }
       }
      return Controllo;
    }

    //Mostra un popup di errore con il messaggio
    protected void MostraMessaggioErrore(String msg)
    {
       JOptionPane.showMessageDialog(this, msg, "JetMarket", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main1(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new DBFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnnulla;
    private javax.swing.JButton bCerca;
    private javax.swing.JButton bChiudi;
    private javax.swing.JButton bElenco;
    private javax.swing.JButton bElimina;
    private javax.swing.JButton bModifica;
    private javax.swing.JButton bNuovo;
    private javax.swing.JButton bNuovoCodice;
    private javax.swing.JButton bPrecedente;
    private javax.swing.JButton bSalva;
    private javax.swing.JButton bSuccessivo;
    private javax.swing.JLabel jCodice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tCodice;
    // End of variables declaration//GEN-END:variables
    
}
