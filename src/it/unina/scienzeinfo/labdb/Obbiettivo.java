/*
 * Obbiettivo.java
 *
 * Created on 6-giu-2009, 12.46.40
 */

package it.unina.scienzeinfo.labdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gruppo 3
 */
public class Obbiettivo extends DBFrame {

    /* Invoca il costruttore di Obbiettivo*/
    public Obbiettivo() {
        initComponents();
        ImpostaPosizioneFinestra();//Gestione posizione della finestra
        setModalita(APPEND_QUERY); //Imposta la finestra per l'inserimento
        setFrameTable(tabObbiettivi); //Inizializza la tabella nella finestra
        setNomeTabella("obbiettivo");
    }

    /*Imposta a seconda della modalità di visualizzazione, gli elementi
     all'intero della finestra*/
    public void setModalita(int modo) {
        super.setModalita(modo);
        switch (modo)
        {
            case APPEND_QUERY: //Inserimento
                tArea.setEnabled(true);
                cbTipo.setEnabled(true);
                tAnno.setEnabled(true);
                tValore.setEnabled(true);
                tImpiegato.setEnabled(true);
                break;
            case BROWSE://Visualizzazione
                tArea.setEnabled(false);
                cbTipo.setEnabled(false);
                tAnno.setEnabled(false);
                tValore.setEnabled(false);
                tImpiegato.setEnabled(false);
                break;
            case UPDATE://Modifica
                tArea.setEnabled(true);
                cbTipo.setEnabled(true);
                tAnno.setEnabled(true);
                tValore.setEnabled(true);
                tImpiegato.setEnabled(true);
                break;
        }
    }

    /*Visualizza nei campi i dati della riga selezionata nella tabella*/
    protected void mostraDati()
    {
        try {
               tArea.setText(rs.getString("Area"));
               cbTipo.setSelectedItem(rs.getString("tipo"));
               tAnno.setText(rs.getString("anno"));
               tValore.setText(rs.getString("valore"));
               tImpiegato.setText(rs.getString("Impiegato"));
               super.mostraDati();
        } catch (SQLException e) {
              mostraErrori(e);
        }
    }

    /*Pulisce il contenuto dei campi nella finestra*/
    protected void pulisci() {
        super.pulisci();
        tArea.setText("");
        cbTipo.setSelectedIndex(0);
        tAnno.setText("");
        tValore.setText("");
        tImpiegato.setText("");
    }

 //Cerca a seconda dei dati inseriti all'interno della tabella
    protected PreparedStatement creaSelectStatement() {
        Connection con;
        PreparedStatement st=null;
        String codice,Area,Tipo,Valore,Anno,Impiegato;
        Pattern pat;
        Matcher matc;
        int k=1;
        super.creaSelectStatement();
        //Acquisizione dei campi nel form
        codice=getTCodice().getText();
        Area=tArea.getText();
        Tipo=(String) cbTipo.getSelectedItem();
        Valore=tValore.getText();
        Anno=tAnno.getText();
        Impiegato=tImpiegato.getText();
        //Completamento della query a seconda dei campi riempiti
        query+=" where";

        if (codice.length()>0) {
            query+=" codice= ? and";
        }
        if (Area.length()>0)
        {
            if (Area.indexOf("%")>=0)
                query+=" area like ? and";
            else
                query+=" area = ? and";
        }
        if (Tipo.length()>0 && !Tipo.matches("Scegli..."))
        {
            if (Tipo.indexOf("%")>=0)
                query+=" tipo like ? and";
            else
                query+=" tipo = ? and";
        }
        if (Valore.length()>0)
        {
            if(Valore.indexOf("%")>=0)
                 query+=" valore like ? and";
            else
                 query+=" valore = ? and";
        }
        if (Anno.length()>0)
        {
            if(Anno.indexOf("%")>=0)
                 query+=" anno like ? and";
            else
                 query+=" anno = ? and";
        }
        if (Impiegato.length()>0)
        {
            if(Impiegato.indexOf("%")>=0)
                 query+=" impiegato like ?";
            else
                 query+=" impiegato = ?";
        }
        pat= Pattern.compile("where$|and$");
        matc =pat.matcher(query);
        query=matc.replaceAll("");

        /*Collegamento al database ed esecuzione della query, in modalità protetta
        da SQLInjection*/
        try {
            con=Database.getDefaultConnection();
            st=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if (codice.length()>0)
            {
                st.setInt(k++,Integer.valueOf(codice).intValue());
            }
            if (Area.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Area).intValue());
            }
            if (Tipo.length()>0 && !Tipo.matches("Scegli..."))
            {
                st.setString(k++,Tipo);
            }
            if (Valore.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Valore).intValue());
            }
            if (Anno.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Anno).intValue());
            }
            if (Impiegato.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Impiegato).intValue());
            }
        return st;
        } catch (SQLException e) {
            mostraErrori(e);
            return null;
        }
    }

     /* Genera ed esegue la query per l'inserimento di un dato*/
    protected PreparedStatement getComandoInserimento(Connection c)  throws SQLException {
        String query;
        PreparedStatement st = null;
        //Controllo del corretto inserimento dei dati
        if(ControllaInput(5,getTCodice().getText(),"N1toSize") &&
                ControllaInput(5,tArea.getText(),"N1toSize") &&
                ControllaInput(0,(String) cbTipo.getSelectedItem(),"Obb") &&
                ControllaInput(6,tValore.getText(),"N1toSize") &&
                ControllaInput(4,tAnno.getText(),"NSize") &&
                ControllaInput(5,tImpiegato.getText(),"N1toSize"))
        {
           query="insert into "+Database.schema+".Obbiettivo(codice,Area,Tipo,Valore,Anno,Impiegato) values(?,?,?,?,?,?)";
           st=c.prepareStatement(query);
           st.setInt(1,Integer.valueOf(getTCodice().getText()).intValue());
           st.setInt(2,Integer.valueOf(tArea.getText()).intValue());
           st.setString(3,(String) cbTipo.getSelectedItem());
           st.setInt(4,Integer.valueOf(tValore.getText()).intValue());
           st.setInt(5,Integer.valueOf(tAnno.getText()).intValue());
           st.setInt(6,Integer.valueOf(tImpiegato.getText()).intValue());
        }
        else
        {
           MostraMessaggioErrore("Uno dei seguenti campi sono errati: \n -Obbiettivo \n -Area \n -Tipo \n -Valore \n -Anno \n -Impiegato");
        }

        return st;
    }
    /*Genera ed esegue la query per la modifica di un dato*/
    protected PreparedStatement getComandoAggiornamento(Connection c)  throws SQLException {
        String query;
        PreparedStatement st = null;
        //Controllo del corretto inserimento dei dati
         if(ControllaInput(5,tArea.getText(),"N1toSize") &&
                ControllaInput(6,tValore.getText(),"N1toSize") &&
                ControllaInput(0,(String) cbTipo.getSelectedItem(),"Obb") &&
                ControllaInput(4,tAnno.getText(),"NSize") &&
                ControllaInput(5,tImpiegato.getText(),"N1toSize"))
        {
           query="update "+Database.schema+".Obbiettivo set Area=?, tipo=?,valore=?, anno=?, impiegato=? where codice=?";
           st=c.prepareStatement(query);
           st.setInt(6,Integer.valueOf(getTCodice().getText()).intValue());
           st.setInt(1,Integer.valueOf(tArea.getText()).intValue());
           st.setString(2,(String) cbTipo.getSelectedItem());
           st.setInt(3,Integer.valueOf(tValore.getText()).intValue());
           st.setInt(4,Integer.valueOf(tAnno.getText()).intValue());
           st.setInt(5,Integer.valueOf(tImpiegato.getText()).intValue());
        }
        else
        {
            MostraMessaggioErrore("Uno dei seguenti campi sono errati: \n -Area \n -Tipo \n -Valore \n -Anno \n -Impiegato");
        }
        return st;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spObbiettivi = new javax.swing.JScrollPane();
        tabObbiettivi = new javax.swing.JTable();
        jArea = new javax.swing.JLabel();
        jTipo = new javax.swing.JLabel();
        jValore = new javax.swing.JLabel();
        jAnno = new javax.swing.JLabel();
        tArea = new javax.swing.JTextField();
        tAnno = new javax.swing.JTextField();
        tValore = new javax.swing.JTextField();
        jImpiegato = new javax.swing.JLabel();
        tImpiegato = new javax.swing.JTextField();
        cbTipo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Obbiettivo");

        tabObbiettivi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codice", "Area", "Tipo", "Anno", "Valore"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spObbiettivi.setViewportView(tabObbiettivi);

        jArea.setText("Area*");

        jTipo.setText("Tipo*");

        jValore.setText("Valore*");

        jAnno.setText("Anno*");

        tArea.setToolTipText("Inserire un codice area esistente");

        tAnno.setToolTipText("Esempio 2009");

        tValore.setToolTipText("Intero di massimo 6 cirfe,Esempio 30000");

        jImpiegato.setText("Impiegato*");

        tImpiegato.setToolTipText("Inserire un codice impiegato esistente");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli...", "margine", "fatturato" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spObbiettivi, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jArea)
                    .addComponent(jTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tArea, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                .addGap(135, 135, 135)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jImpiegato)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jAnno)
                            .addComponent(jValore))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tAnno, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tImpiegato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(tValore))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jArea)
                    .addComponent(tArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tAnno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAnno))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tValore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTipo)
                    .addComponent(jValore)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jImpiegato))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(tImpiegato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(spObbiettivi, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Obbiettivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JLabel jAnno;
    private javax.swing.JLabel jArea;
    private javax.swing.JLabel jImpiegato;
    private javax.swing.JLabel jTipo;
    private javax.swing.JLabel jValore;
    private javax.swing.JScrollPane spObbiettivi;
    private javax.swing.JTextField tAnno;
    private javax.swing.JTextField tArea;
    private javax.swing.JTextField tImpiegato;
    private javax.swing.JTextField tValore;
    private javax.swing.JTable tabObbiettivi;
    // End of variables declaration//GEN-END:variables

}
