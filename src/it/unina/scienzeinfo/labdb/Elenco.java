/*
 * Elenco.java
 *
 * Created on 10-apr-2009, 17.05.10
 */

package it.unina.scienzeinfo.labdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Gruppo 3
 */
/**
 * Classe che mostra un elenco dei dati per facilitare
 * l'inserimento nel form
 */
public class Elenco extends DBFrame {
    String NomeTab;

    /* Invoca il costruttore di Elenco*/
    public Elenco(String NomeTabe) {
        initComponents();
        super.setModalita(SELL); //Imposta la finestra per l'inserimento
        setFrameTable(tabElenco); //Inizializza la tabella nella finestra
        NomeTab = NomeTabe;
        /*Controlla che la classe sia stata invocata da una classe nella quale è
        possibile visualizzare l'elenco*/
        if(!NomeTab.matches("fattura") && !NomeTab.matches("statistica")) generaTabella();
        else JOptionPane.showMessageDialog(this, "In questa modalità non è possibile ottenere l'elenco dati", "Errore", JOptionPane.ERROR_MESSAGE);
    }

    // Genera la tabella contenente i dati utili alla classe "invocante"
     private void generaTabella () {
        PreparedStatement st = null;
        Connection con;
        DBTableModel Tabella = new DBTableModel();
        ResultSet rs;
        int posix=1;
        String queryt = null;
        try {
            //Creazione della query relativa
            if(NomeTab.matches("area") ||
                    NomeTab.matches("impiegato") ||
                    NomeTab.matches("prodotto") ||
                    NomeTab.matches("obbiettivo"))
                queryt= "select codice AS area from "+Database.schema+".area ";
            else if(NomeTab.matches("scontrino"))
                queryt= "select codice AS impiegato from "+Database.schema+".impiegato ";
            else if(NomeTab.matches("vendita") ||
                    NomeTab.matches("acquisto"))
                queryt= "select codice AS prodotto from "+Database.schema+".prodotto ";
            //Connessione al Database ed esecuzione query
            con=Database.getDefaultConnection();
            st=con.prepareStatement(queryt,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery();
            //Creazione e popolamento della tabella
            Tabella.setRS(rs);
            rs.absolute(posix);
            posix=rs.getRow();
            tabElenco.setModel(Tabella);
            tabElenco.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tabElenco.setRowSelectionInterval(posix -1, posix -1);
            setModalita(BROWSE);
        } catch (SQLException e) {
            //mostraErrori(e,queryt,CONTESTO_ESEGUI_QUERY);
        } catch (java.lang.NullPointerException e) {
            System.out.println(e.toString());
            //non devo mostrare nessun errore
            //si dovrebbe verificare solo se st=null
            //quando la connessione è caduta
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

        spElenco = new javax.swing.JScrollPane();
        tabElenco = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Elenco Dati");
        setResizable(false);

        tabElenco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Reparto", "Scaffale", "Ripiano"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spElenco.setViewportView(tabElenco);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spElenco, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spElenco, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Elenco("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spElenco;
    private javax.swing.JTable tabElenco;
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