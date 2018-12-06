/*
 * Database.java
 *
 * Created on 21 aprile 2009, 9.07
 *
 */

package it.unina.scienzeinfo.labdb;

import javax.swing.*;

/**
 *
 * @author Gruppo 3
 */
import java.sql.*;
public class Database {
    //Variabili per la connessione al database
    static public String host="host";
    static public String servizio="xe";
    static public String porta="1521";
    static public String user="user";
    static public String password="pass";
    static public String schema="GR1003";
    static private Connection defaultConnection;
    /**
     * Crea una nuova istanza di Database
     */
    public Database() {}
   
    /**
     * Restituisce la connessione di default al DB.
     **/
    static public Connection getDefaultConnection() throws SQLException
    {
        //Se non è già presente una connessione, ristabilisce quella di default.
        if (defaultConnection==null || defaultConnection.isClosed()) {
            defaultConnection=DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+porta+":"+servizio, user,password);
        }
        
         return defaultConnection;
    }
    /**
     * Imposta la connessione di default al DB.
     **/
    static public void setDefaultConnection(Connection c) throws SQLException
    {
            defaultConnection=c;
    }
    
    /**
     * Restituisce una nuova connessione al DB.
     **/
    static public Connection nuovaConnessione() throws SQLException
    {
        
         return DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+porta+":"+servizio, user,password);
    }
    /**Effettua una query e restituisce il primo valore*/
    static public Object leggiValore(String query) {
        Object ret;
        Connection con;
        Statement st;
        ResultSet rs;
        ret=null;
        try {
            con=getDefaultConnection();
            st=con.createStatement(); 
            rs=st.executeQuery(query);
            rs.next();
            ret=rs.getObject(1);
        } catch (SQLException e) {} 
        return ret;
    }
    //Effettura una query e restituisce il ResultSet
    static public ResultSet getRS(String query) {
        Connection con;
        Statement st;
        ResultSet rs = null;
        try {
            con=getDefaultConnection();
            st=con.createStatement();
            rs=st.executeQuery(query);

        } catch (SQLException e) {}
        return rs;
    }
    
    /**Effettua una query e restituisce il primo valore.*/
    static public Object leggiValore(String query,int codice) {
        Object ret;
        Connection con;
        PreparedStatement st;
        ResultSet rs;
        ret=null;
        try {
            con=getDefaultConnection();
            st=con.prepareStatement(query);
            st.setInt(1,codice);
            rs=st.executeQuery();
            rs.next();
            ret=rs.getObject(1);
        } catch (SQLException e) {} 
        return ret;
    }
          
     /**Carica il driver JDBC.*/
    static {   
        try {  
           Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Driver del database non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
