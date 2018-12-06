/*
 * StatisticheObbiettivo.java
 *
 * Created on 11-lug-2009, 10.44.53
 */

package it.unina.scienzeinfo.labdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Gruppo 3
 */
public class StatisticheObbiettivo extends DBFrame {

    /* Invoca il costruttore di StatisticheObbiettivo*/
    public StatisticheObbiettivo() {
        initComponents();
        ImpostaPosizioneFinestra();//Gestione posizione della finestra
        super.setModalita(SELL);//Imposta la finestra per l'inserimento
        setNomeTabella("statistica");
        setFrameTable(tabStatistiche); //Inizializza la tabella nella finestra
    }

     protected void pulisci() {
        super.pulisci();
        tArea.setText("");
        tImpiegato.setText("");
        tAnno.setText("");
        tTipo.setText("");
    }

//Cerca a seconda dei dati inseriti all'interno della tabella
    protected void generaStatistiche() {
        Connection con;
        PreparedStatement st=null;
        String codice,Area,Tipo,Valore,Anno,Impiegato;
        DBTableModel Tabella = new DBTableModel();
        ResultSet rs;
        Pattern pat;
        Matcher matc;
        int k=1;
        int posix=1;
        //Acquisizione dei campi nel form
        Area=tArea.getText();
        Tipo=tTipo.getText();
        Anno=tAnno.getText();
        Impiegato=tImpiegato.getText();
        //Completamento della query a seconda dei campi riempiti
        query ="select s.anno,ptot AS Profitto_Totale,ctot AS Costi_Totali,margine,tipo,valore from ";

        if (Area.length()>0)
        {
            query+="obb2 s join obbiettivo o on s.anno = o.anno and s.area=o.area where";
            if (Area.indexOf("%")>=0)
            {
               query+="s.area like ? and";
            }
            else
            {
                query+=" s.area = ? and";
            }
        }
        else
            query+="obb1 s join obbiettivo o on s.anno = o.anno where";               
        if (Anno.length()>0)
        {
            if (Anno.indexOf("%")>=0)
            {
                query+=" s.anno like ? and";
            }
            else
            {
                query+=" s.anno = ? and";
            }
        }
        if (Impiegato.length()>0)
        {
          if (Impiegato.indexOf("%")>=0)
                query+=" o.impiegato like ? and";
          else

                 query+=" o.impiegato = ? and";
        }
        if (Tipo.length()>0)
        {
          if (Tipo.indexOf("%")>=0)
                query+=" o.tipo like ? and";
          else

                 query+=" o.tipo = ? and";
        }
        pat= Pattern.compile("where$|and$");
        matc =pat.matcher(query);
        query=matc.replaceAll("");
        /*Collegamento al database ed esecuzione della query, in modalità protetta
        da SQLInjection*/
        try {
            con=Database.getDefaultConnection();
            st=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if (Area.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Area).intValue());
            }  
            if (Anno.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Anno).intValue());
            }
            if (Impiegato.length()>0)
            {
                st.setInt(k++,Integer.valueOf(Impiegato).intValue());
            }
            if (Tipo.length()>0)
            {
                st.setString(k++,Tipo);
            }

            rs = st.executeQuery();
            //Creazione e popolamento della tabella
            if (rs.next()){
            Tabella.setRS(rs);
            rs.absolute(posix);
            posix=rs.getRow();
            tabStatistiche.setModel(Tabella);
            tabStatistiche.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tabStatistiche.setRowSelectionInterval(posix -1, posix -1);
            setModalita(BROWSE);
            pulisci();
            }
            else
            {
                MostraMessaggioErrore("La ricerca non ha prodotto risultati\n Rimmettere i dati");
                pulisci();
            }


        } catch (SQLException e) {
            mostraErrori(e);
        }
    }

     
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jArea = new javax.swing.JLabel();
        tArea = new javax.swing.JTextField();
        jImpiegato = new javax.swing.JLabel();
        tImpiegato = new javax.swing.JTextField();
        jTipo = new javax.swing.JLabel();
        tTipo = new javax.swing.JTextField();
        jAnno = new javax.swing.JLabel();
        tAnno = new javax.swing.JTextField();
        bStat = new javax.swing.JButton();
        spStatistiche = new javax.swing.JScrollPane();
        tabStatistiche = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Statistiche Obbiettivi");

        jArea.setText("Area");

        tArea.setToolTipText("Inserire un codice area esistente.");

        jImpiegato.setText("Impiegato");

        tImpiegato.setToolTipText("Inserire un codice impiegato valido");

        jTipo.setText("Tipo");

        tTipo.setToolTipText("Inserire un tipo esistente: margine o fatturato");

        jAnno.setText("Anno");

        tAnno.setToolTipText("Inserire un anno (4 cifre YYYY es.2009)");

        bStat.setText("Genera Statistiche");
        bStat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTipo))
                    .addComponent(jArea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tTipo)
                    .addComponent(tArea, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jAnno)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jImpiegato)
                        .addGap(17, 17, 17)))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bStat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tAnno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(tImpiegato, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTipo)
                    .addComponent(tTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAnno)
                    .addComponent(tAnno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jArea, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jImpiegato)
                            .addComponent(tImpiegato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addComponent(bStat))
        );

        tabStatistiche.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        spStatistiche.setViewportView(tabStatistiche);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spStatistiche, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(375, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spStatistiche, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    private void bStatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStatActionPerformed
          generaStatistiche();
        

}//GEN-LAST:event_bStatActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatisticheObbiettivo().setVisible(true);
            }
        });
    }

    @Override
    protected PreparedStatement getComandoInserimento(Connection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected PreparedStatement getComandoAggiornamento(Connection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bStat;
    private javax.swing.JLabel jAnno;
    private javax.swing.JLabel jArea;
    private javax.swing.JLabel jImpiegato;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jTipo;
    private javax.swing.JScrollPane spStatistiche;
    private javax.swing.JTextField tAnno;
    private javax.swing.JTextField tArea;
    private javax.swing.JTextField tImpiegato;
    private javax.swing.JTextField tTipo;
    private javax.swing.JTable tabStatistiche;
    // End of variables declaration//GEN-END:variables

}
