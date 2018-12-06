/*
 * DBTableModel.java
 *
 * Created on 29 aprile 2009, 9.41
 */
package it.unina.scienzeinfo.labdb;
import javax.swing.table.*;
import java.sql.*;

/**
 * Modello di JTable basate su un ResultSet.
 * <br>
 * Si preferisce basare il modello su un ResultSet, piuttosto che su una query,
 * in modo da poter condividerlo con il DBFrame.
 * @author Gruppo 3
 */
public class DBTableModel extends AbstractTableModel {
    
    /**Il Resultset su cui si basa il modello.*/
    private ResultSet rs;
    
    /**
     * Costruttori della tabella
     */
    public DBTableModel() {
        super();
    }
    public DBTableModel(ResultSet r) {
        super();
        rs=r;
    }
    
    /**Imposta il Resultset su cui si basa il modello.*/
    public void setRS(ResultSet r) {
        rs=r;
        fireTableStructureChanged();
        
    }
    /*Metodo che restituisce il nome di una colonna del resultset
     (il nome della colonna restituita corrisponde all'indice dato in input)*/
    public String getColumnName(int col)  {
        col++;
        if (rs==null)
            return "";
        try {
            return  rs.getMetaData().getColumnName(col);
        } catch (SQLException e) {    
              System.out.println(e.getMessage());
              return "";
        }
    }
    /*Metodo che restituisce il numero di righe della tabella*/
    public int getRowCount() {
        if (rs==null)
            return 0;
        try {
            int currentPosition,last;
            currentPosition=rs.getRow();
            rs.last();
            last=rs.getRow();
            if(currentPosition!=0)
            {
               rs.absolute(currentPosition);
            }
            return last; 
        } catch (SQLException e) {    
              System.out.println(e.getMessage());
              return 0;
        }
    }
    /*Metodo che restituisce il numero di colonne presenti nella tabella*/
    public int getColumnCount() {
        if (rs==null)
            return 0;
        try {
            return rs.getMetaData().getColumnCount(); 
        } catch (SQLException e) {    
              System.out.println(e.getMessage());
              return 0;
        }
    }
    /*Metodo che restituisce il valore degli indici passati in input*/
    public Object getValueAt(int row, int col) {
        int currentPosition;
        Object ob;
        row++;
        col++;
        try {
            currentPosition=rs.getRow();
            rs.absolute(row);
            ob=rs.getObject(col);
            rs.absolute(currentPosition);
            return ob;
        } catch (SQLException e) {    
              System.out.println(e.getMessage());
              return null;
        }
    }
    @Override
    public boolean isCellEditable(int row, int col)
         { return false; }

}