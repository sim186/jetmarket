/*
 * Statistiche.java
 *
 * Created on 17-apr-2009, 9.58.00
 */

package it.unina.scienzeinfo.labdb;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;



/**
 *
 * @author Gruppo 3
 */
public class Statistiche extends DBFrame {
	static String Titolo;


	//Costruttore della classe, prende in input il titolo della finestra
    public Statistiche( String pStrTitle ) throws Exception {

        Titolo=pStrTitle;
        initComponents();
        setVisible(true);

        //Impostazioni di visualizzazione e posizione della finestra
        Dimension screen=getToolkit().getScreenSize();
        int posX = 0;
        int Alt=screen.height;
        //In base al titolo, la finestra viene posizionata in parti diverse
        if(pStrTitle.matches("Profitto"))
        {
            posX=screen.width - 500;
        }
        int posY=(Alt - 270)/2;
        setLocation(posX,posY);

        ChartPanel lChartPanel = (ChartPanel)createDemoPanel();
        lChartPanel.setMouseZoomable(true, false);
        setContentPane( lChartPanel );
        
    }

	/**
	 * Metodo che crea il Dataset necessario per la creazione del TimeSeries Chart
	 * @return XYDataset
	 */
	private static XYDataset createDataset() throws FileNotFoundException, IOException, ParseException {

     //I dati si trovano nel file "data.csv" che quindi viene aperto e letto
     File f=new File("data.csv");
     FileInputStream fis = new FileInputStream(f);
     InputStreamReader isr= new InputStreamReader(fis);
     BufferedReader br = new BufferedReader(isr);
     String linea= br.readLine();
     String data, anno ,mese;

     TimeSeries lTimeSeries = new TimeSeries( "Indice Mensile", Month.class);

     //Analizziamo riga per riga i dati
     while(linea != null)
     {
         //Scomponiamo la riga in base al divisore, nel nostro caso la virgola
         StringTokenizer st = new StringTokenizer(linea, ",");
         //Salviamo i singoli valori
         mese = st.nextToken() ;
         anno= st.nextToken() ;
         int quantita    = Integer.parseInt( st.nextToken() );
         float prezzo=Float.parseFloat( st.nextToken() );
         if(Titolo.matches("Volume"))
             lTimeSeries.add( new Month( Integer.valueOf(mese).intValue(), Integer.valueOf(anno).intValue() ), quantita );
         else
             lTimeSeries.add( new Month( Integer.valueOf(mese).intValue(), Integer.valueOf(anno).intValue() ), prezzo );
         linea=br.readLine();
     }

     TimeSeriesCollection lDataset = new TimeSeriesCollection();
     lDataset.addSeries( lTimeSeries );

        return lDataset;
    }

	/**
	 * Metodo per la generazione del grafico
	 * @param pDataset Dataset con i dati con cui popolare il grafico
	 * @return JFreeChart
	 */
	private static JFreeChart createChart( XYDataset pDataset ) {

		// Creazione dell'oggetto Chart tramite il CahrtFactory.
		// I parametri in ingresso al metodo createTimeSeriesChart sono:
		// titolo
		// label per l'asse delle ascisse
		// label per l'asse delle ordinate
		// Dataset con i dati con cui popolare il grafico
		// boolean che indica se creare una legenda oppure no
		// boolean che indica se creare i tooltip oppure no
		// boolean che indica se creare dei collegamenti a punti del grafico
        JFreeChart lChart = ChartFactory.createTimeSeriesChart(
            Titolo,
            "Date",
            "Valori",
            pDataset,
            true,
            true,
            false
        );

        // Imposta il colore di sfondo
        lChart.setBackgroundPaint( Color.BLUE );

        // Impostazione delle proprietà del diagramma
        XYPlot lXYPlot = (XYPlot)lChart.getPlot();
        lXYPlot.setBackgroundPaint( Color.lightGray );
        lXYPlot.setDomainGridlinePaint( Color.white );
        lXYPlot.setRangeGridlinePaint( Color.white );
        lXYPlot.setAxisOffset( new RectangleInsets( 5.0, 5.0, 5.0, 5.0 ) );
        lXYPlot.setDomainCrosshairVisible( true );
        lXYPlot.setRangeCrosshairVisible( true );

        // Rendering
        XYItemRenderer lXYItemRenderer = lXYPlot.getRenderer();
        if ( lXYItemRenderer instanceof XYLineAndShapeRenderer )
        {
            XYLineAndShapeRenderer renderer =
            	(XYLineAndShapeRenderer)lXYItemRenderer;
            renderer.setBaseShapesVisible( true );
            renderer.setBaseShapesFilled( true );
        }

        // Impostazione dell'asse delle ascisse
        DateAxis lXYAxis = (DateAxis)lXYPlot.getDomainAxis();
        lXYAxis.setDateFormatOverride( new SimpleDateFormat( "MMM-yyyy" ) );

        return lChart;
    }

	/**
	 * Metodo che crea il Frame contenitore del Chart
	 * @return JPanel
	 */
	public static JPanel createDemoPanel() throws FileNotFoundException, IOException, ParseException {
        JFreeChart lChart = createChart( createDataset() );
        return new ChartPanel( lChart );
    }

	public static void main( String[] args ) throws Exception {
		Statistiche lTSE =
			new Statistiche( "Esempio di Time Series Chart" );
		lTSE.pack();
	    RefineryUtilities.centerFrameOnScreen( lTSE );
	    lTSE.setVisible( true );
	}

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Statistiche");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected PreparedStatement getComandoInserimento(Connection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected PreparedStatement getComandoAggiornamento(Connection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
