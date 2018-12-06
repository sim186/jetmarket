/*
 * StatisticheVendita.java
 *
 * Created on 11-lug-2009, 10.44.22
 */

package it.unina.scienzeinfo.labdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;

/**
 *
 * @author Gruppo 3
 */
public class StatisticheVendita extends DBFrame {
    String query;
    /* Invoca il costruttore di StatisticheVendita*/
    public StatisticheVendita() {
        initComponents();
        ImpostaPosizioneFinestra();//Gestione posizione della finestra
        setNomeTabella("statistica");
        super.setModalita(SELL); //Imposta la finestra per l'inserimento
    }


    //Funzione per la creazione del file contenente i dati per le statistiche
    private static void generateCsvFile(String Filename,ResultSet rs) throws SQLException, IOException{

        FileWriter Writer= new FileWriter(Filename);

        // I dati dell'RS vengono scritti nel file separati da una virgola
        while(!rs.isAfterLast())
        {
           Writer.append(rs.getString("mese"));
           Writer.append(",");
           Writer.append(rs.getString("anno"));
           Writer.append(",");
           Writer.append(rs.getString("quantita"));
           Writer.append((","));
           Writer.append(rs.getString("prezzo"));
           Writer.append("\n");
           Writer.flush();
           rs.next();
        }
        Writer.close();
    }

    //Cerca a seconda dei dati inseriti all'interno della tabella
    protected ResultSet generaRs() {
        Connection con;
        PreparedStatement st=null;
        String Area,Prodotto,DataI,DataF;
        Pattern pat;
        Matcher matc;
        int k=1;
        //Acquisizione dei campi nel form
        Area=tArea.getText();
        Prodotto=tProdotto.getText();
        DataI=tDataInizio.getText();
        DataF=tDataFine.getText();
        //Completamento della query a seconda dei campi riempiti
        query ="select mese,anno,sum(quantita) as quantita ,sum(prezzo) as prezzo from ViewStatistica where";

        if (Area.length()>0)
        {
            if (Area.indexOf("%")>=0)
                query+=" area like ? and";
            else
                query+=" area = ? and";
        }
        if (Prodotto.length()>0)
        {
            if (Prodotto.indexOf("%")>=0)
                query+=" prodotto like ? and";
            else
                query+=" prodotto = ? and";
        }
        if (DataI.length()>0)
        {
            
                 query+=" anno > ? and";
        }
        if (DataF.length()>0)
        {
        
                 query+=" anno < ?";
        }
     
        
        pat= Pattern.compile("where$|and$");
        matc =pat.matcher(query);
        query=matc.replaceAll("");
        query +=" group by mese,anno";

        /*Collegamento al database ed esecuzione della query, in modalità protetta
        da SQLInjection*/
        try {
            con=Database.getDefaultConnection();
            st=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if (Area.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Area).intValue());
            }
            if (Prodotto.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Prodotto).intValue());
            }
            if (DataI.length()>0)
            {
                st.setInt(k++,Integer.valueOf(DataI).intValue());
            }
            if (DataF.length()>0)
            {
                st.setInt(k++,Integer.valueOf(DataI).intValue());
            }

        ResultSet rs= st.executeQuery();
        
        if (rs.next())
        {
           return rs;
        }
        else
        {
            return null;
        }

        } catch (SQLException e) {
            mostraErrori(e);
            return null;
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jArea = new javax.swing.JLabel();
        jProdotto = new javax.swing.JLabel();
        tArea = new javax.swing.JTextField();
        tProdotto = new javax.swing.JTextField();
        tDataInizio = new javax.swing.JTextField();
        jDataFine = new javax.swing.JLabel();
        jDataInzio = new javax.swing.JLabel();
        tDataFine = new javax.swing.JTextField();
        bGenera = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Statistiche Vendita");

        jArea.setText("Area");

        jProdotto.setText("Prodotto");

        tArea.setToolTipText("digitare un codice area esistente");

        tProdotto.setToolTipText("digitare un codice prodotto esistente");

        tDataInizio.setToolTipText("inserire un anno da cui far partire le statisiche. Inserire 4 cifre YYYY es. 2009");

        jDataFine.setText("Anno Inizio");

        jDataInzio.setText("Anno Fine");

        tDataFine.setToolTipText("inserire un anno da cui far terminare le statisiche. Inserire 4 cifre YYYY es. 2009");

        bGenera.setText("Genera Grafico");
        bGenera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGeneraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProdotto)
                    .addComponent(jArea))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tArea, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(tProdotto, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDataFine)
                    .addComponent(jDataInzio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bGenera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tDataFine)
                    .addComponent(tDataInizio, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jArea)
                    .addComponent(tArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tDataInizio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDataFine))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jProdotto)
                        .addComponent(tProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tDataFine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDataInzio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bGenera)))
                .addContainerGap(214, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Prendendo il tasto Genera Grafico, genera il file .csv e invoca due istanze della classe statistiche
    private void bGeneraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGeneraActionPerformed

        ResultSet rs = generaRs();

        if (rs!=null)
        {
            try {
                generateCsvFile("data.csv", rs);
                Statistiche stat = new Statistiche("Volume");
                Statistiche stat2= new Statistiche("Profitto");
            }
            catch (SQLException ex) {
                Logger.getLogger(StatisticheVendita.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) {
                Logger.getLogger(StatisticheVendita.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (Exception ex) {
                Logger.getLogger(StatisticheVendita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
              MostraMessaggioErrore("I dati inseriti non hanno generato nessun risultato.\nQuindi non verrà generato nessun grafico.");
              pulisci();
        }
}//GEN-LAST:event_bGeneraActionPerformed

        @Override
    /*Pulisce il contenuto dei campi nella finestra*/
    protected void pulisci() {
        tArea.setText("");
        tProdotto.setText("");
        tDataInizio.setText("");
        tDataFine.setText("");

    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatisticheVendita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bGenera;
    private javax.swing.JLabel jArea;
    private javax.swing.JLabel jDataFine;
    private javax.swing.JLabel jDataInzio;
    private javax.swing.JLabel jProdotto;
    private javax.swing.JTextField tArea;
    private javax.swing.JTextField tDataFine;
    private javax.swing.JTextField tDataInizio;
    private javax.swing.JTextField tProdotto;
    // End of variables declaration//GEN-END:variables

    @Override
    protected PreparedStatement getComandoInserimento(Connection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected PreparedStatement getComandoAggiornamento(Connection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
