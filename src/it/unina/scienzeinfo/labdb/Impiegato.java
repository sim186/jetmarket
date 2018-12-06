/*
 * Impiegato.java
 *
 * Created on 30-mar-2009, 11.32.10
 */

package it.unina.scienzeinfo.labdb;
import java.sql.*;
import java.util.regex.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Gruppo 3
 */
/**
 * Classe che realizza la gestione degli impiegati,
 * con inserimento, modifica e cancellazione
 */
public class Impiegato extends DBFrame {

    /* Invoca il costruttore di Impiegato*/
    public Impiegato() {
        initComponents();
        ImpostaPosizioneFinestra();//Gestione posizione della finestra
        setModalita(APPEND_QUERY); //Imposta la finestra per l'inserimento
        setFrameTable(tabImpiegato); //Inizializza la tabella nella finestra
        setNomeTabella("impiegato");
    }

     /*Imposta a seconda della modalità di visualizzazione, gli elementi
     all'intero della finestra*/
     public void setModalita(int modo)
     {
        super.setModalita(modo);
        switch (modo)
        {//Impostiamo la modalità di visualizzazione della finestra
            case APPEND_QUERY: //Inserimento
                tNome.setEnabled(true);
                tRuolo.setEnabled(true);
                tArea.setEnabled(true);
                tStipendio.setEnabled(true);
                tTipoContratto.setEnabled(true);
                break;
            case BROWSE://Visualizzazione
                tNome.setEnabled(false);
                tRuolo.setEnabled(false);
                tArea.setEnabled(false);
                tStipendio.setEnabled(false);
                tTipoContratto.setEnabled(false);
                break;
            case UPDATE://Modifica
                tNome.setEnabled(true);
                tRuolo.setEnabled(true);
                tArea.setEnabled(true);
                tStipendio.setEnabled(true);
                tTipoContratto.setEnabled(true);
                break;
        }
    }

    /*Visualizza nei campi i dati della riga selezionata nella tabella*/
    protected void mostraDati()
    {
        try {
            super.mostraDati();
            tNome.setText(rs.getString("Nome"));
            tRuolo.setText(rs.getString("Ruolo"));
            tArea.setText(rs.getString("area"));
            tStipendio.setText(rs.getString("stipendio"));
            tTipoContratto.setText(rs.getString("contratto"));
        } catch (SQLException e) {
              mostraErrori(e);
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

        jNome = new javax.swing.JLabel();
        jRuolo = new javax.swing.JLabel();
        tNome = new javax.swing.JTextField();
        tRuolo = new javax.swing.JTextField();
        spImpiegato = new javax.swing.JScrollPane();
        tabImpiegato = new javax.swing.JTable();
        jArea = new javax.swing.JLabel();
        tArea = new javax.swing.JTextField();
        jTipoContratto = new javax.swing.JLabel();
        jStipendio = new javax.swing.JLabel();
        tTipoContratto = new javax.swing.JTextField();
        tStipendio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Impiegato");
        setResizable(false);

        jNome.setText("Nome*");

        jRuolo.setText("Ruolo*");

        tNome.setToolTipText("Stringa di soli caratteri maiuscoli e minuscoli");

        tRuolo.setToolTipText("Stringa di soli caratteri maiuscoli e minuscoli");

        tabImpiegato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codice", "Nome", "Ruolo", "Area", "Tipo Contratto", "Stipendio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spImpiegato.setViewportView(tabImpiegato);

        jArea.setText("Area");

        tArea.setToolTipText("Inserire un codice area esistente");

        jTipoContratto.setText("Tipo Contratto*");

        jStipendio.setText("Stipendio*");

        tTipoContratto.setToolTipText("Stringa di soli caratteri maiuscoli e minuscoli");

        tStipendio.setToolTipText("Esempio 12312.12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spImpiegato, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jArea)
                            .addComponent(jNome)
                            .addComponent(jRuolo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tArea)
                            .addComponent(tRuolo)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTipoContratto)
                            .addComponent(jStipendio))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tStipendio)
                            .addComponent(tTipoContratto, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNome)
                    .addComponent(jTipoContratto)
                    .addComponent(tTipoContratto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tRuolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStipendio)
                            .addComponent(tStipendio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRuolo)
                        .addGap(18, 18, 18)
                        .addComponent(jArea)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spImpiegato, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        spImpiegato.getAccessibleContext().setAccessibleParent(spImpiegato);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Impiegato().setVisible(true);
            }
        });
    }
    /*Pulisce il contenuto dei campi nella finestra*/
    protected void pulisci() {
        super.pulisci();
        tNome.setText("");
        tRuolo.setText("");
        tArea.setText("");
        tStipendio.setText("");
        tTipoContratto.setText("");
    }

    //Cerca a seconda dei dati inseriti all'interno della tabella
    protected PreparedStatement creaSelectStatement() {
        Connection con;
        PreparedStatement st=null;
        String codice,Nome,Ruolo,Area,Stipendio,Contratto;
        Pattern pat;
        Matcher matc;
        int k=1;
        super.creaSelectStatement();
        //Completamento della query a seconda dei campi riempiti
        codice=getTCodice().getText();
        Nome=tNome.getText();
        Ruolo=tRuolo.getText();
        Area=tArea.getText();
        Stipendio=tStipendio.getText();
        Contratto=tTipoContratto.getText();
        //Completamento della query
        query+=" where";

        if (codice.length()>0) {
            query+=" codice= ? and";
        }
        if (Nome.length()>0)
        {
            if (Nome.indexOf("%")>=0)
                query+=" nome like ? and";
            else
                query+=" nome = ? and";
        }
        if (Ruolo.length()>0)
        {
            if (Ruolo.indexOf("%")>=0)
                query+=" ruolo like ? and";
            else
                query+=" ruolo = ? and";
        }
        if (Area.length()>0)
        {
            if(Area.indexOf("%")>=0)
                query+=" area like ? and";
            else 
                query+=" area = ? and";
        }
        if (Stipendio.length()>0)
        {
            if(Stipendio.indexOf("%")>=0)
                query+=" stipendio like ? and";
            else
                query+=" stipendio = ? and";
        }
        if (Contratto.length()>0)
        {
            if(Contratto.indexOf("%")>=0)
                query+=" contratto like ?";
            else
                query+=" contratto = ?";
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
            if (Nome.length()>0)
            {
                st.setString(k++,Nome);
            }
            if (Ruolo.length()>0)
            {
                st.setString(k++,Ruolo);
            }
            if (Area.length()>0)
            {
                st.setString(k++,Area);
            }
            if (Stipendio.length()>0)
            {
                st.setFloat(k++,Float.valueOf(Stipendio).floatValue());
            }
            if (Contratto.length()>0)
            {
                st.setString(k++,Contratto);
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
                ControllaInput(3,tArea.getText(),"N0toSize") &&
                ControllaInput(255,tNome.getText(),"S1toSize") &&
                ControllaInput(255,tRuolo.getText(),"S1toSize") &&
                ControllaInput(255,tTipoContratto.getText(),"S1toSize") &&
                ControllaInput(0,tStipendio.getText(),"F5.2"))
       {
           query="insert into "+Database.schema+".Impiegato (codice,Nome,Ruolo,Area,Contratto,Stipendio) values(?,?,?,?,?,?)";
           st=c.prepareStatement(query);
           st.setInt(1,Integer.valueOf(getTCodice().getText()).intValue());
           st.setString(2,tNome.getText());
           st.setString(3,tRuolo.getText());
           if(tArea.getText().matches(""))
           {
              st.setString(4,tArea.getText());
           }
           else
           {
               st.setInt(4,Integer.valueOf(tArea.getText()).intValue());
           }
           st.setString(5,tTipoContratto.getText());
           st.setFloat(6,Float.valueOf(tStipendio.getText()).floatValue());
        }
        else
        {
           MostraMessaggioErrore("I seguenti campi sono errati: \n -Codice Impiegato \n -Nome \n -Ruolo \n -Area \n -Contratto \n -Stipendio ");
        }
        return st;
    }
    /*Genera ed esegue la query per la modifica di un dato*/
    protected PreparedStatement getComandoAggiornamento(Connection c)  throws SQLException {
        String query;
        PreparedStatement st = null;
         //Controllo del corretto inserimento dei dati
        if(ControllaInput(3,tArea.getText(),"N0toSize") &&
                ControllaInput(255,tNome.getText(),"S1toSize") &&
                ControllaInput(255,tRuolo.getText(),"S1toSize") &&
                ControllaInput(255,tTipoContratto.getText(),"S1toSize") &&
                ControllaInput(0,tStipendio.getText(),"F5.2"))
        {
           query="update "+Database.schema+".Impiegato set Nome=? , Ruolo=? , Area =?,Contratto =?,Stipendio =? where codice=?";
           st=c.prepareStatement(query);
           st.setInt(6,Integer.valueOf(getTCodice().getText()).intValue());
           st.setString(1,tNome.getText());
           st.setString(2,tRuolo.getText());
           st.setString(3,tArea.getText());
           st.setString(4,tTipoContratto.getText());
           st.setFloat(5,Float.valueOf(tStipendio.getText()).floatValue());
        }
        else
        {
            MostraMessaggioErrore("I seguenti campi sono errati: \n -Nome \n -Ruolo \n -Area \n -Contratto \n -Stipendio ");
        }
        return st;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jArea;
    private javax.swing.JLabel jNome;
    private javax.swing.JLabel jRuolo;
    private javax.swing.JLabel jStipendio;
    private javax.swing.JLabel jTipoContratto;
    private javax.swing.JScrollPane spImpiegato;
    private javax.swing.JTextField tArea;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextField tRuolo;
    private javax.swing.JTextField tStipendio;
    private javax.swing.JTextField tTipoContratto;
    private javax.swing.JTable tabImpiegato;
    // End of variables declaration//GEN-END:variables

 

}
