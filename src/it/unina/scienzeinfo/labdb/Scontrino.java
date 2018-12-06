/*
 * Scontrino.java
 *
 * Created on 2-apr-2009, 12.02.09
 */

package it.unina.scienzeinfo.labdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gruppo 3
 */
/**
 * Classe che realizza la gestione della creazione di uno scontrino
 */
public class Scontrino extends DBFrame {
    /* Invoca il costruttore di Scontrino*/
    public Scontrino() {
        initComponents();
        ImpostaPosizioneFinestra();//Gestione posizione della finestra
        setNomeTabella("scontrino");
        setModalita(APPEND_QUERY); //Imposta la finestra per l'inserimento
        setFrameTable(tabScontrino);//Inizializza la tabella nella finestra
    }


     public void setModalita(int modo) {
        super.setModalita(TICKET);
        switch (modo)
        {//Impostiamo la modalità di visualizzazione della finestra
            case APPEND_QUERY: //Inserimento
                
                tImpiegato.setEnabled(true);
                tSDay.setEnabled(false);
                tData.setEnabled(false);
                bPopola.setEnabled(true);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE d/MMM/yyyy", Locale.ITALY);
                Date now = new Date();
                tData.setText(sdf.format(now));
                impostaCodice();
                impostaScontrino();
                break;
            case BROWSE://Visualizzazione
                tImpiegato.setEnabled(false);
                tSDay.setEnabled(false);
                tData.setEnabled(false);
                bPopola.setEnabled(false);
                break;
        }
    }


     /*Visualizza nei campi i dati della riga selezionata nella tabella*/
    protected void mostraDati()
    {
        try {
               tImpiegato.setText(rs.getString("Impiegato"));
               tSDay.setText(rs.getString("id_scontrino"));
               tData.setText(rs.getString("data"));
               super.mostraDati();
        } catch (SQLException e) {
              mostraErrori(e);
        }
    }

    @Override
    /*Pulisce il contenuto dei campi nella finestra*/
    protected void pulisci() {
        tImpiegato.setText("");
       // tNome.setText("");
       // tBarcode.setText("");
       // tPrezzo.setText("");
    }

     //Immette all'interno del campo codice, il massimo valore(+1) della colonna codice
    protected void impostaScontrino() {
        String scontrino;
        scontrino=Database.leggiValore("select nvl(max(id_scontrino)+1,1) from "+Database.schema+".Scontrino where codice = (select max(codice) from "+Database.schema+".Scontrino) and data like SYSDATE ").toString();
        tSDay.setText(scontrino);
    }

    //Invoca un oggetto di tipo Vendita per iniziare la gestione degli elementi dello scontrino
    private void vendita(int id_scontrino) {
        Vendita m;
        m = new Vendita(id_scontrino);
        m.setVisible(true);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jImpiegato = new javax.swing.JLabel();
        tImpiegato = new javax.swing.JTextField();
        jSDay = new javax.swing.JLabel();
        tSDay = new javax.swing.JTextField();
        jData = new javax.swing.JLabel();
        tData = new javax.swing.JTextField();
        spScontrino = new javax.swing.JScrollPane();
        tabScontrino = new javax.swing.JTable();
        bPopola = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Scontrino");
        setResizable(false);

        jImpiegato.setText("Impiegato*");

        tImpiegato.setToolTipText("Inserire un codice impiegato esistente");

        jSDay.setText("id_Scontrino Giornaliero*");

        jData.setText("Data*");

        tabScontrino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codice", "Impiegato", "C Giornaliero", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabScontrino.getTableHeader().setReorderingAllowed(false);
        spScontrino.setViewportView(tabScontrino);

        bPopola.setText("Popola Scontrino");
        bPopola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPopolaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jImpiegato)
                    .addComponent(jSDay))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tSDay)
                    .addComponent(tImpiegato, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(jData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bPopola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tData, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap(107, Short.MAX_VALUE))
            .addComponent(spScontrino, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jImpiegato)
                    .addComponent(tImpiegato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jData)
                    .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSDay)
                    .addComponent(tSDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPopola))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spScontrino, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleParent(tabScontrino);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Azioni alla pressione del tasto Popola Scontrino
    private void bPopolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPopolaActionPerformed
        String controllo,controllo2;
        controllo=Database.leggiValore("select nvl(max(codice)+1,1) from "+Database.schema+".Scontrino").toString();
        Salva(evt);
        controllo2=Database.leggiValore("select nvl(max(codice)+1,1) from "+Database.schema+".Scontrino").toString();
        //Se il controllo va a buon fine si può invocare la classe vendita
        if(!controllo.matches(controllo2))
        {
           vendita(Integer.valueOf(getTCodice().getText()).intValue());
        }
    }//GEN-LAST:event_bPopolaActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Scontrino().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bPopola;
    private javax.swing.JLabel jData;
    private javax.swing.JLabel jImpiegato;
    private javax.swing.JLabel jSDay;
    private javax.swing.JScrollPane spScontrino;
    private javax.swing.JTextField tData;
    private javax.swing.JTextField tImpiegato;
    private javax.swing.JTextField tSDay;
    private javax.swing.JTable tabScontrino;
    // End of variables declaration//GEN-END:variables

    @Override
     //Cerca a seconda dei dati inseriti all'interno della tabella
    protected PreparedStatement creaSelectStatement() {
        Connection con;
        PreparedStatement st=null;
        String Impiegato;
        Pattern pat;
        Matcher matc;
        int k=1;
        //Acquisizione dei campi nel form
        Impiegato=tImpiegato.getText();
        super.creaSelectStatement();
        //Completamento della query a seconda dei campi riempiti
        query+=" where";

        if (Impiegato.length()>0)
        {
            if (Impiegato.indexOf("%")>=0)
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
     
    

    @Override
    protected PreparedStatement getComandoInserimento(Connection c) throws SQLException {
        String query;
        PreparedStatement st = null;
        //Controllo del corretto inserimento dei dati
        if(ControllaInput(5,tImpiegato.getText(),"N1toSize"))
        {
           query="insert into "+Database.schema+".Scontrino(codice,data,Impiegato,id_Scontrino) values(?,SYSDATE,?,?)";
           st=c.prepareStatement(query);
           st.setInt(1,Integer.valueOf(getTCodice().getText()).intValue());
           st.setInt(2,Integer.valueOf(tImpiegato.getText()).intValue());
           st.setInt(3,Integer.valueOf(tSDay.getText()).intValue());
        }
        else
        {
           MostraMessaggioErrore("Il seguente dato è errato: \n -Impiegato");
        }
        return st;
    }

    @Override
    protected PreparedStatement getComandoAggiornamento(Connection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
