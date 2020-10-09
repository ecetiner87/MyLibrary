package appDemoSplashScreen;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

 
@SuppressWarnings("serial")
public class PieDemo extends ApplicationFrame {
   

   
   public PieDemo(String title) {
		super(title);
	
	}


private static PieDataset createDatasetREADSTAT(int a, int b ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "READ" , ( a ) );  
      dataset.setValue( "NOT READ" , ( b ) );   
      return dataset;         
   }
   
   private static JFreeChart createChartREADSTAT( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Read/Not Read Books",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel(int a, int b) {
      JFreeChart chart = createChartREADSTAT(createDatasetREADSTAT(a,b) );  
      return new ChartPanel( chart ); 
   }
   
 
private static PieDataset createDatasetCATSTAT( int a, int b, int c, int d , int e, int f , int g, int h ) {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      dataset.setValue( "EDEBIYAT" ,( a ) );  
	      dataset.setValue( "ARASTIRMA-TARIH" , ( b ) );   
	      dataset.setValue( "DIN-MITOLOJI" , ( c ) );  
	      dataset.setValue( "FELSEFE" , ( d ) );   
	      dataset.setValue( "HOBI" , ( e ) );  
	      dataset.setValue( "BILIM" ,( f ) );   
	      dataset.setValue( "SANAT" , ( g ) );  
	      dataset.setValue( "DIGER" ,( h ) );   
	      return dataset;         
	   }
	   
	   private static JFreeChart createChartCATSTAT( PieDataset dataset ) {
	      JFreeChart chart = ChartFactory.createPieChart(      
	         "BOOKS by CATEGORY",   // chart title 
	         dataset,          // data    
	         true,             // include legend   
	         true, 
	         false);

	      return chart;
	   }
	   
	   public static JPanel createDemoPanelCATSTAT(int a, int b, int c, int d , int e, int f , int g, int h ) {
	      JFreeChart chart = createChartCATSTAT(createDatasetCATSTAT(a, b, c, d, e, f, g , h ) );  
	      return new ChartPanel( chart ); 
	   }

}

