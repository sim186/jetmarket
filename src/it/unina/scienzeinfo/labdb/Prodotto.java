/*
 * Prodotto.java
 *
 * Created on 31-mar-2009, 18.53.49
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
/**
 * Classe che realizza la gestione dei prodotti,
 * con inserimento, modifica e cancellazione
 */
public class Prodotto extends DBFrame {
    /* Invoca il costruttore di Prodotto*/
    public Prodotto() {
        initComponents();
        ImpostaPosizioneFinestra();//Gestione posizione della finestra
        setModalita(APPEND_QUERY); //Imposta la finestra per l'inserimento
        setFrameTable(tabProdotto);//Inizializza la tabella nella finestra
        setNomeTabella("prodotto");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spProdotto = new javax.swing.JScrollPane();
        tabProdotto = new javax.swing.JTable();
        jNome = new javax.swing.JLabel();
        jbarcode = new javax.swing.JLabel();
        tNome = new javax.swing.JTextField();
        tBarcode = new javax.swing.JTextField();
        jArea = new javax.swing.JLabel();
        tArea = new javax.swing.JTextField();
        jPrezzo = new javax.swing.JLabel();
        tPrezzo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Prodotto");
        setResizable(false);

        tabProdotto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codice", "Area", "Nome", "Barcode", "Prezzo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
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
        spProdotto.setViewportView(tabProdotto);
        tabProdotto.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabProdotto.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabProdotto.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabProdotto.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabProdotto.getColumnModel().getColumn(4).setPreferredWidth(35);

        jNome.setText("Nome");

        jbarcode.setText("Barcode*");

        tNome.setToolTipText("Stringa di soli caratteri maiuscoli e minuscoli");

        tBarcode.setToolTipText("Stringa alfanumerica di 13 caratteri");

        jArea.setText("Area*");

        tArea.setToolTipText("Inserire un codice area esistente");

        jPrezzo.setText("Prezzo*");

        tPrezzo.setToolTipText("Esempio 123.12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spProdotto, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jNome)
                            .addComponent(jbarcode)
                            .addComponent(jArea))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tNome)
                            .addComponent(tBarcode)
                            .addComponent(tArea, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jPrezzo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tPrezzo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPrezzo)
                            .addComponent(tPrezzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jArea)
                            .addComponent(tArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jNome)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbarcode)
                            .addComponent(tBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)))
                .addComponent(spProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @Override
    /*Imposta a seconda della modalità di visualizzazione, gli elementi
     all'intero della finestra*/
     public void setModalita(int modo) {
        super.setModalita(modo);
        switch (modo)
        {//Impostiamo la modalità di visualizzazione della finestra
            case APPEND_QUERY: //Inserimento
                tArea.setEnabled(true);
                tNome.setEnabled(true);
                tBarcode.setEnabled(true);
                tPrezzo.setEnabled(true);
                break;
            case BROWSE://Visualizzazione
                tArea.setEnabled(false);
                tNome.setEnabled(false);
                tBarcode.setEnabled(false);
                tPrezzo.setEnabled(false);
                break;
            case UPDATE://Modifica
                tArea.setEnabled(true);
                tNome.setEnabled(true);
                tBarcode.setEnabled(true);
                tPrezzo.setEnabled(true);
                break;
        }
    }
    @Override
     /*Visualizza nei campi i dati della riga selezionata nella tabella*/
    protected void mostraDati()
    {
        try {
               tArea.setText(rs.getString("Area"));
               tNome.setText(rs.getString("Nome"));
               tBarcode.setText(rs.getString("barcode"));
               tPrezzo.setText(rs.getString("Prezzo"));
               super.mostraDati();
        } catch (SQLException e) {
              mostraErrori(e);
        }
    }

    @Override
    /*Pulisce il contenuto dei campi nella finestra*/
    protected void pulisci() {
        super.pulisci();
        tArea.setText("");
        tNome.setText("");
        tBarcode.setText("");
        tPrezzo.setText("");
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prodotto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jArea;
    private javax.swing.JLabel jNome;
    private javax.swing.JLabel jPrezzo;
    private javax.swing.JLabel jbarcode;
    private javax.swing.JScrollPane spProdotto;
    private javax.swing.JTextField tArea;
    private javax.swing.JTextField tBarcode;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextField tPrezzo;
    private javax.swing.JTable tabProdotto;
    // End of variables declaration//GEN-END:variables

    @Override
     //Cerca a seconda dei dati inseriti all'interno della tabella
    protected PreparedStatement creaSelectStatement() {
        Connection con;
        PreparedStatement st=null;
        String codice,Barcode,Nome,Area,Prezzo;
        Pattern pat;
        Matcher matc;
        int k=1;
        //Acquisizione dei campi nel form
        codice=getTCodice().getText();
        Area=tArea.getText();
        Nome=tNome.getText();
        Barcode=tBarcode.getText();
        Prezzo=tPrezzo.getText();
        super.creaSelectStatement();
        //Completamento della query a seconda dei campi riempiti
        query+=" where";

        if (codice.length()>0) {
            query+=" codice= ? and";
        }
        if (Area.length()>0)
        {
            if (Area.indexOf("%")>=0)
                query+=" Area like ? and";
            else
                query+=" Area = ? and";
        }
        if (Nome.length()>0)
        {
            if (Nome.indexOf("%")>=0)
                query+=" Nome like ? and";
            else
                query+=" Nome = ? and";
        }
        if (Barcode.length()>0)
        {
            if(Barcode.indexOf("%")>=0)
                 query+=" barcode like ? and";
            else
                 query+=" barcode = ? and";
        }
        if (Prezzo.length()>0)
        {
            if(Prezzo.indexOf("%")>=0)
                 query+=" prezzo like ?";
            else
                 query+=" prezzo = ?";
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
            if (Nome.length()>0)
            {
                st.setString(k++,Nome);
            }
            if (Barcode.length()>0)
            {
                st.setString(k++,Barcode);
            }
            if (Prezzo.length()>0)
            {
                st.setFloat(k++,Float.valueOf(Prezzo).floatValue());
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
                ControllaInput(255,tNome.getText(),"S0toSize") &&
                ControllaInput(13,tBarcode.getText(),"NSSize") &&
                ControllaInput(0,tPrezzo.getText(),"F3.2"))
        {
           query="insert into "+Database.schema+".Prodotto(codice,Area,Nome,Barcode,Prezzo) values(?,?,?,?,?)";
           st=c.prepareStatement(query);
           st.setInt(1,Integer.valueOf(getTCodice().getText()).intValue());
           st.setInt(2,Integer.valueOf(tArea.getText()).intValue());
           st.setString(3,tNome.getText());
           st.setString(4,tBarcode.getText());
           st.setFloat(5,Float.valueOf(tPrezzo.getText()).floatValue());
        }
        else
        {
           MostraMessaggioErrore("Uno dei seguenti dati è errato: \n -Codice Prodotto \n -Area \n -Nome \n -Barcode \n -Prezzo");
        }
        return st;
    }
    /*Genera ed esegue la query per la modifica di un dato*/
    protected PreparedStatement getComandoAggiornamento(Connection c)  throws SQLException {
        String query;
        PreparedStatement st = null;
        //Controllo del corretto inserimento dei dati
         if(ControllaInput(5,tArea.getText(),"N1toSize") &&
                ControllaInput(255,tNome.getText(),"S0toSize") &&
                ControllaInput(13,tBarcode.getText(),"NSSize") &&
                ControllaInput(0,tPrezzo.getText(),"F3.2"))
        {
            query="update "+Database.schema+".Prodotto set Area=?,Nome=?,Barcode=?,Prezzo=? where codice=?";
            st=c.prepareStatement(query);
           st.setInt(5,Integer.valueOf(getTCodice().getText()).intValue());
           st.setInt(1,Integer.valueOf(tArea.getText()).intValue());
           st.setString(2,tNome.getText());
           st.setString(3,tBarcode.getText());
           st.setFloat(4,Float.valueOf(tPrezzo.getText()).floatValue());
        }
        else
        {
           MostraMessaggioErrore("Uno dei seguenti dati è errato: \n -Area \n -Nome \n -Barcode \n -Prezzo");
        }
        return st;
    }
}