package appDemoSplashScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.table.DefaultTableModel;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.miginfocom.swing.MigLayout;

/**
 * @author erkance
 * Bibliophile v2.0 is the Library Application which helps you to keep your book records.
 */


public class splashScreen {
	
	static class myGUI{

		JFrame frame = new JFrame("BiblioPhile 3.0");
		JPanel actionPanel, queryPanel;
		JMenuBar menuBar;
		JMenu fileMenu, helpMenu;
		JMenuItem about,quitItem;
		JButton showLibrary, addBook, deleteBook, searchBook, updateBook, wishList, bookbyyear,statistics,borrowed;  
		Image appLogo;
		DBConnection connect = new DBConnection();
		File file = new File("testFile1.txt");
		
		
		public myGUI() throws IOException{
			
			
		    
			appLogo = Toolkit.getDefaultToolkit().getImage("images/logo.jpg");
			
			Icon image = new ImageIcon(("images/imagepanel.jpg")); 
			JLabel imagelabel = new JLabel(image);
			
			actionPanel = new JPanel();
			queryPanel = new JPanel();
			actionPanel.setBorder(BorderFactory.createTitledBorder("ACTION"));
			queryPanel.setBorder(BorderFactory.createTitledBorder("QUERY"));
			
			showLibrary = new JButton("Show All Library");
			addBook = new JButton("Add New Book");
			deleteBook = new JButton("Delete Existing Book");
			searchBook = new JButton("Search Book");
			updateBook = new JButton("Update Book");
			wishList = new JButton("WishList");
			bookbyyear = new JButton("Read History");
			statistics = new JButton("Statistics");
			borrowed = new JButton("Borrowed Books");


			
			showLibrary.setMaximumSize(new Dimension(175,30));			
			addBook.setMaximumSize(new Dimension(175,30));
			deleteBook.setMaximumSize(new Dimension(175,30));
			searchBook.setMaximumSize(new Dimension(175,30));
			updateBook.setMaximumSize(new Dimension(175,30));
			wishList.setMaximumSize(new Dimension(175,30));
			bookbyyear.setMaximumSize(new Dimension(175,30));
			statistics.setMaximumSize(new Dimension(175,30));
			borrowed.setMaximumSize(new Dimension(175,30));

			
			if (file.createNewFile())
			{
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
			 
			
			addBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		addBookGUI();
		        }
		    });
			
			showLibrary.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		showAllGUI();
		        }
		    });
			
			deleteBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		deleteBookGUI();
		        }
		    });
			
			searchBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		searchBookGUI();
		        }
		    });
			
			wishList.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		wishListGUI();
		        }
		    });
			
			updateBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		updateBookGUI();
		        }
		    });
			
			bookbyyear.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		try {
							bookhistoryGUI();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
		        }
		    });
			
			statistics.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		try {
							statisticsGUI();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
		        }
		    });
			
			
			borrowed.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        		borrowGUI();
		        }
		    });
			
			
			actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
			actionPanel.add(showLibrary);
			actionPanel.add(addBook);
			actionPanel.add(deleteBook);
			actionPanel.add(searchBook);
			actionPanel.add(updateBook);
			actionPanel.add(wishList);
			actionPanel.add(bookbyyear);
			actionPanel.add(statistics);
			actionPanel.add(borrowed);
			
			
			queryPanel.add(imagelabel);		



			menuBar = new JMenuBar();
	        fileMenu = new JMenu("File");
	        quitItem = new JMenuItem("Quit");
	        quitItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ev) {
	              System.exit(0);
	            }
	          });
	        
	        helpMenu = new JMenu("Help");
	        about = new JMenuItem("About Bibliophile");
	        about.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ev) {
	            	if("About Bibliophile".equals(ev.getActionCommand())){

	                    JFrame awindow = new JFrame();
	                    awindow.setSize(400,400);
	                    awindow.getContentPane().setBackground(Color.LIGHT_GRAY);
	                    awindow.setLocationRelativeTo(null);
	                    awindow.setTitle("About Bibliophile 3.0");
	                    
	                    JPanel mypanel = new JPanel();
	                    
	                    
	                    JTextArea textArea = new JTextArea(
	                    	    "This desktop application is an open source project written in Java." + 
	                    	    "The purpose of this application is keeping book records and searching when requested.\n" + 
	                    	    "\nVersion: v3.0\n" + 
	                    	    "\nCreated By: Erkan Cetiner\n" + 
	                    	    "\ne-mail: ecetiner87@gmail.com\n"
	                    	    
	                    	);
	                    textArea.setFont(new Font("Serif", Font.PLAIN, 14));
	                    textArea.setRows(20);
	                    textArea.setColumns(30);
	                    textArea.setLineWrap(true);
	                    textArea.setEditable(false);
	                    textArea.setWrapStyleWord(true);
	                    mypanel.add(textArea);
	                    
	                    awindow.add(mypanel);
	                    awindow.setDefaultCloseOperation(1);
	                    awindow.setVisible(true);

	                }
	            }
	          });
	        
	        
	        
	        helpMenu.add(about);
	        fileMenu.add(quitItem);
	        menuBar.add(fileMenu);
	        menuBar.add(helpMenu);
	 

	       
	        
	        frame.setSize(1350,650);
	        frame.setIconImage(appLogo);
	        frame.setJMenuBar(menuBar);	
	        frame.setLayout(new BorderLayout());
			frame.add(actionPanel, BorderLayout.WEST);
			frame.add(queryPanel, BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setResizable(true);
			
			
		}
		
		public void display() {
			frame.setVisible(true);
		}
		
		public void addBookGUI() {
			queryPanel.removeAll();
			
			JLabel bookName, authFname, authLname, publisher, price, category, subcategory, readed, publishDate, translator;
			bookName = new JLabel("Book Name:");
			bookName.setMaximumSize(new Dimension(175,30));			
			authFname = new JLabel("Author Fname:");
			authFname.setMaximumSize(new Dimension(175,30));			
			authLname = new JLabel("Author  Lname:");
			authLname.setMaximumSize(new Dimension(175,30));			
			publisher = new JLabel("Publisher:");
			publisher.setMaximumSize(new Dimension(175,30));			
			price = new JLabel("Price(TL):");
			price.setMaximumSize(new Dimension(175,30));			
			category = new JLabel("Category:");
			category.setMaximumSize(new Dimension(175,30));			
			subcategory = new JLabel("Sub-Category:");
			subcategory.setMaximumSize(new Dimension(175,30));			
			readed = new JLabel("Read:(Yes/No)");
			readed.setMaximumSize(new Dimension(175,30));	
			publishDate = new JLabel("Publish Date:");
			publishDate.setMaximumSize(new Dimension(175,30));		
			translator = new JLabel("Translator:");
			translator.setMaximumSize(new Dimension(175,30));		


			JTextField bookNameField, authorFnameField, authorLnameField, publisherField, priceField, pdateField, transField;
		    
			JComboBox<String> subField = new JComboBox<String>();
			subField.setMaximumSize(new Dimension(175,30));
			
			
			String[] readList = {"YES", "NO"};
			JComboBox<String> readField = new JComboBox<String>(readList);
			readField.setMaximumSize(new Dimension(175,30));
			
			String[] catList = {"EDEBIYAT", "ARASTIRMA-TARIH", "DIN-MITOLOJI", "FELSEFE", "HOBI", "BILIM", "SANAT", "DIGER"};
			JComboBox<String> catField = new JComboBox<String>(catList);
			catField.setMaximumSize(new Dimension(175,30));
			
			catField.addActionListener(new ActionListener() {
				
			    @SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent event) {
			        JComboBox<String> combo = (JComboBox<String>) event.getSource();
			        String selectedCat = (String) combo.getSelectedItem();
			        
			        
			        if (selectedCat.equals("EDEBIYAT")) {
			        	subField.removeAllItems();
			            subField.addItem("ROMAN");
			            subField.addItem("SIIR");
			            subField.addItem("DENEME");
			            subField.addItem("OYKU");
			            subField.addItem("INCELEME");
			            subField.addItem("BIYOGRAFI");
			            subField.addItem("DUNYA KLASIKLERI");
			            subField.addItem("TURK KLASIKLERI");
			        } else if (selectedCat.equals("ARASTIRMA-TARIH")) {
			        	subField.removeAllItems();
			        	subField.addItem("TURK POLITIKASI");
				        subField.addItem("DUNYA POLITIKASI");
				        subField.addItem("TARIH");
				        subField.addItem("SOSYOLOJI");
				        subField.addItem("ARASTIRMA");
				        subField.addItem("TARIHI KISILER");
				        subField.addItem("GAZETECILIK");
			        }
			        else if (selectedCat.equals("DIN-MITOLOJI")) {
			        	subField.removeAllItems();
			        	subField.addItem("TASAVVUF");
				        subField.addItem("ISLAMIYET");
				        subField.addItem("MEZHEPLER");
				        subField.addItem("MITOLOJI");
				        subField.addItem("DIN ADAMLARI");
				        subField.addItem("DIGER DINLER");
			        }
			        else if (selectedCat.equals("FELSEFE")) {
			        	subField.removeAllItems();
			        	subField.addItem("FELSEFE BILIMI");
				        subField.addItem("FELSEFECILER");
				        subField.addItem("BILGELIK");
				        subField.addItem("FELSEFI ROMANLAR");
			        }
			        else if (selectedCat.equals("HOBI")) {
			        	subField.removeAllItems();
			        	subField.addItem("YEMEK");
				        subField.addItem("SPOR");
				        subField.addItem("PSIKOLOJI");
				        subField.addItem("HAYVANLAR");
				        subField.addItem("BITKILER");
				        subField.addItem("MODA");
				        subField.addItem("ASTROLOJI");
				        subField.addItem("RESIM");
			        }
			        else if (selectedCat.equals("BILIM")) {
			        	subField.removeAllItems();
			        	subField.addItem("POPULER BILIM");
				        subField.addItem("BILIM TARIHI");
				        subField.addItem("BILIM INSANLARI");
				        subField.addItem("KESIFLER");
				        subField.addItem("ASTRONOMI");
				        subField.addItem("COGRAFYA");
				        subField.addItem("KASIFLER");
				        subField.addItem("MATEMATIK");
			        }
			        else if (selectedCat.equals("SANAT")) {
			        	subField.removeAllItems();
			        	subField.addItem("SINEMA");
				        subField.addItem("MUZIK");
				        subField.addItem("TIYATRO");
				        subField.addItem("SANAT TARIHI");
				        subField.addItem("MIMARI");
				        subField.addItem("FOTOGRAF");
				        subField.addItem("SANATCILAR");
			        }
			        else if (selectedCat.equals("DIGER")) {
			        	subField.removeAllItems();
			        	subField.addItem("DIGER");
			        }

			    }
			});
			
			bookNameField = new JTextField(30);
			bookNameField.setMaximumSize(new Dimension(175,30));		
			authorFnameField = new JTextField(30);
			authorFnameField.setMaximumSize(new Dimension(175,30));		
			authorLnameField = new JTextField(30);
			authorLnameField.setMaximumSize(new Dimension(175,30));		
			publisherField = new JTextField(30);
			publisherField.setMaximumSize(new Dimension(175,30));		
			priceField = new JTextField(30);
			priceField.setMaximumSize(new Dimension(175,30));		
			pdateField = new JTextField(30);
			pdateField.setMaximumSize(new Dimension(175,30));	
			transField = new JTextField(30);
			transField.setMaximumSize(new Dimension(175,30));	
			
			JButton resetFields = new JButton("RESET");
			resetFields.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	bookNameField.setText("");
		        	authorFnameField.setText("");
		        	authorLnameField.setText("");
		        	publisherField.setText("");
		        	priceField.setText("");
		        	pdateField.setText("");
		        	transField.setText("");
		        }
		    });
			
		
			JButton addBook = new JButton("Add Book");
			addBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		  
		        	 double priceVal;
		        	 int pDate;
		    
		        	 String bookName = bookNameField.getText().toUpperCase();
		        	 String aFName = authorFnameField.getText().toUpperCase();
		        	 String aLName = authorLnameField.getText().toUpperCase();
		        	 String publisherName = publisherField.getText().toUpperCase();
		        	 String categ = (String)catField.getSelectedItem();
		        	 String subcateg = (String)subField.getSelectedItem();
		        	 String price = priceField.getText();	        		 
		        	 String readStatus = (String)readField.getSelectedItem();
		        	 String pubDate = pdateField.getText();	 
		        	 String trans = transField.getText().toUpperCase(); 
		        	 
		        
		        	
		        	  
		        
		         if ( bookName.isEmpty() || aFName.isEmpty() || aLName.isEmpty() || publisherName.isEmpty() || categ.isEmpty() || subcateg.isEmpty() || !isNumeric(price) || !isInteger(pubDate))
		        	 {
		        		 JOptionPane.showMessageDialog(null, "Please update all fields accordingly. Please use numbers for Date and Price Fields","Alert",JOptionPane.WARNING_MESSAGE);    
		        	 }
		        	 
		        	 else {  
		        		 
		        		 priceVal= price == null || price.isEmpty() ? 0 : Double.parseDouble(price);	
		        		 pDate= pubDate == null || pubDate.isEmpty() ? 0 : Integer.parseInt(pubDate);	
		        		
						connect.insertToTable(bookName, aFName, aLName, publisherName, priceVal, categ, subcateg, readStatus, pDate, trans );
		        		 JOptionPane.showMessageDialog(null, "Book Record added Successfully!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);    

		        	 }
		        	 }
		    });
			
			queryPanel.setLayout(new MigLayout());
			queryPanel.add(bookName, "align label");
			queryPanel.add(bookNameField);
			queryPanel.add(price);
			queryPanel.add(priceField, "wrap");
			queryPanel.add(authFname, "align label");
			queryPanel.add(authorFnameField);
			queryPanel.add(category);
			queryPanel.add(catField, "wrap");
			queryPanel.add(authLname, "align label");
			queryPanel.add(authorLnameField);
			queryPanel.add(subcategory);
			queryPanel.add(subField, "wrap");
			queryPanel.add(publisher, "align label");
			queryPanel.add(publisherField);
			queryPanel.add(readed);
			queryPanel.add(readField, "wrap");
			queryPanel.add(publishDate, "align label");
			queryPanel.add(pdateField);
			queryPanel.add(translator);
			queryPanel.add(transField, "wrap");
			queryPanel.add(addBook, "align label");
			queryPanel.add(resetFields, "wrap");

			
			queryPanel.setBorder(BorderFactory.createTitledBorder("ADD NEW BOOK"));
			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public void showAllGUI() {
			queryPanel.removeAll();
			
			int totalBooks=0;
			int readedBooks=0;
			double libraryvalue=0;
			
			JPanel tablePanel = new JPanel();
			JPanel countPanel = new JPanel();
			JLabel label = new JLabel("# of Books in Library:");
			JTextField field= new JTextField(5);
			JLabel label2 = new JLabel("# of Books Read:");
			JTextField field2= new JTextField(5);
			JLabel label3 = new JLabel("Total Value of Library(TL):");
			JTextField field3= new JTextField(5);
			JButton export = new JButton("Export All Library");
			JButton impToLib = new JButton("Import to Library");


			JTable table = new JTable();
			table.setAutoCreateRowSorter(true);
			
			String[] columnNames = {"BOOKID", "BOOKNAME", "AUTHOR_FNAME", "AUTHOR_LNAME", "PUBLISHER", "PRICE", "CATEGORY", "SUBCATEGORY", "PUBLISHDATE", "TRANSLATOR", "READ"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model); 
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(120);
			table.getColumnModel().getColumn(5).setPreferredWidth(70);
		    table.getColumnModel().getColumn(6).setPreferredWidth(80);
		    table.getColumnModel().getColumn(7).setPreferredWidth(80);
		    table.getColumnModel().getColumn(8).setPreferredWidth(50);
		    table.getColumnModel().getColumn(9).setPreferredWidth(110);
		    table.getColumnModel().getColumn(10).setPreferredWidth(30);
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setFillsViewportHeight(true);
			table.setEnabled(false);
		
			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(920, 300));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			
			totalBooks = connect.showLibrary(table, model);
			System.out.println("Total Number of Books:" + totalBooks);
			String totalbooknumber = Integer.toString(totalBooks);
			
			readedBooks = connect.countLibrary(table);
			System.out.println("Total Number of Readed Books:" + readedBooks);
			String totalreadnumber = Integer.toString(readedBooks);
			
			libraryvalue = connect.sumPriceBooks(table);
			System.out.println("Total Value of Books(TL):" + libraryvalue);
			String totallibrprice = Double.toString(libraryvalue);
			
			field.setText(totalbooknumber);
			field.setEditable(false);
			field2.setText(totalreadnumber);
			field2.setEditable(false);
			field3.setText(totallibrprice);
			field3.setEditable(false);
			
			
			export.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	JFileChooser fileChooser = new JFileChooser();
		        	fileChooser.setSelectedFile(new File("MyLibrary.csv"));
		        	 int returnVal = fileChooser.showSaveDialog(null);
		        	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	        try {
		        	            File file = fileChooser.getSelectedFile();
		        	            PrintWriter os = new PrintWriter(file);
		        	            os.println("");
		        	            for (int col = 0; col < table.getColumnCount(); col++) {
		        	                os.print(table.getColumnName(col) + "\t");
		        	            }

		        	            os.println("");
		        	            os.println("");

		        	            for (int i = 0; i < table.getRowCount(); i++) {
		        	                for (int j = 0; j < table.getColumnCount(); j++) {
		        	                    os.print(table.getValueAt(i, j).toString() + "\t");

		        	                }
		        	                os.println("");
		        	            }
		        	            os.close();
		        	            System.out.println("Done!");
		        	        } catch (IOException e2) {
		        	            
		        	            e2.printStackTrace();
		        	        }
		        	    }
		        	 }
		    });
			
			impToLib.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	int result=0;
		        	
		        	Path path;
		        	JFileChooser jd = new JFileChooser();
		        	jd.setDialogTitle("Choose input file");
		        	
		        	int returnVal= jd.showOpenDialog(null);
		        	if(returnVal != JFileChooser.APPROVE_OPTION) {
		        		path = null;
		        	}
		        	
		        	path = jd.getSelectedFile().toPath();
		        	String ext = getFileExtension(path);
		        	
		           System.out.println(path + " chosen.");
		           System.out.println(ext);
		           
		           if(ext.equals("txt")  || ext.equals("csv")) {

		           
		        	   result = connect.importLibrary(path);
		           
		        	   if(result==1) {
		        		   JOptionPane.showMessageDialog(null, "Records imported successfully!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);    

		        	   }
		        	   else
	        		 
		        	   {
			        	 JOptionPane.showMessageDialog(null, "Records Could NOT IMPORTED . Error!","ERROR",JOptionPane.ERROR_MESSAGE);    

		        	   }
		            
		           }
		           else
		           {

			        	 JOptionPane.showMessageDialog(null, "No Valid Input. Use CSV or TXT files only. Error!","ERROR",JOptionPane.ERROR_MESSAGE);    

		           }
		        	 }
		    });
			
			tablePanel.add(scroll);
			countPanel.setLayout(new BoxLayout(countPanel, BoxLayout.Y_AXIS));
			countPanel.add(label);
			countPanel.add(Box.createRigidArea(new Dimension(0,5)));
			countPanel.add(field);
			countPanel.add(Box.createRigidArea(new Dimension(0,5)));
			countPanel.add(label2);
			countPanel.add(Box.createRigidArea(new Dimension(0,5)));
			countPanel.add(field2);
			countPanel.add(Box.createRigidArea(new Dimension(0,5)));
			countPanel.add(label3);
			countPanel.add(Box.createRigidArea(new Dimension(0,5)));
			countPanel.add(field3);
			countPanel.add(Box.createRigidArea(new Dimension(0,5)));
			countPanel.add(export);
			countPanel.add(Box.createRigidArea(new Dimension(0,5)));
			countPanel.add(impToLib);
			
			
			
			
			queryPanel.setLayout(new FlowLayout());
			queryPanel.add(tablePanel);
			queryPanel.add(countPanel);

			queryPanel.setBorder(BorderFactory.createTitledBorder("ALL BOOKS"));
			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public void deleteBookGUI() {
			queryPanel.removeAll();
			JTable table = new JTable();
			
			String[] columnNames = {"BOOKID", "BOOKNAME", "AUTHOR_FNAME", "AUTHOR_LNAME", "PUBLISHER", "PRICE", "CATEGORY", "SUBCATEGORY", "PUBLISHDATE", "TRANSLATOR", "READ"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model); 
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(170);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(140);
			table.getColumnModel().getColumn(5).setPreferredWidth(80);
		    table.getColumnModel().getColumn(6).setPreferredWidth(100);
		    table.getColumnModel().getColumn(7).setPreferredWidth(100);
		    table.getColumnModel().getColumn(8).setPreferredWidth(60);
		    table.getColumnModel().getColumn(9).setPreferredWidth(130);
		    table.getColumnModel().getColumn(10).setPreferredWidth(50);
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setFillsViewportHeight(true);
			table.setAutoCreateRowSorter(true);

			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(920, 300));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			
			connect.showLibrary(table, model);
			JButton deleteBook = new JButton("Delete Book");
			deleteBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		         if(table.getSelectionModel().isSelectionEmpty()) {
			            	
		            	 JOptionPane.showMessageDialog(null, "You should select a row first.","Alert",JOptionPane.WARNING_MESSAGE);    
		            }
		         else {
		        	int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this book?", "DELETE BOOK",JOptionPane.YES_NO_OPTION);
		            
		        	if(dialogDelete==JOptionPane.YES_OPTION){      
		            	DefaultTableModel model = (DefaultTableModel)table.getModel();
		            	int row = table.getSelectedRow();
		            	int id = Integer.parseInt(table.getValueAt(row, 0).toString());
		            	int modelRow = table.convertRowIndexToModel( row );
		            	model.removeRow( modelRow );
		            
		            	System.out.println(id);
		            	connect.deleteBook(id);
		            }
		        	 }
		        }
		    });
			
			JButton deleteAll = new JButton("Delete All");
			deleteAll.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		     
		        	int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete all library? There is no turning back! ", "DELETE BOOK",JOptionPane.YES_NO_OPTION);
		            
		        	if(dialogDelete==JOptionPane.YES_OPTION){      
		   
		            	connect.deleteAllBooks();
		            }
		        	 
		        }
		    });
			
			
			queryPanel.setLayout(new FlowLayout());
			queryPanel.add(scroll);
			queryPanel.add(deleteBook);
			queryPanel.add(deleteAll);
			queryPanel.setBorder(BorderFactory.createTitledBorder("DELETE BOOK"));
			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public void updateBookGUI() {
			queryPanel.removeAll();
			JPanel list = new JPanel();
			JPanel updatePanel = new JPanel();
			
			JTable table = new JTable();
			
			String[] columnNames = {"BOOKID", "BOOKNAME", "AUTHOR_FNAME", "AUTHOR_LNAME", "PUBLISHER", "PRICE", "CATEGORY", "SUBCATEGORY", "PUBLISHDATE", "TRANSLATOR", "READ"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model); 
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(170);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(140);
			table.getColumnModel().getColumn(5).setPreferredWidth(80);
		    table.getColumnModel().getColumn(6).setPreferredWidth(100);
		    table.getColumnModel().getColumn(7).setPreferredWidth(100);
		    table.getColumnModel().getColumn(8).setPreferredWidth(60);
		    table.getColumnModel().getColumn(9).setPreferredWidth(130);
		    table.getColumnModel().getColumn(10).setPreferredWidth(50);
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setFillsViewportHeight(true);
			table.setAutoCreateRowSorter(true);

			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(920, 200));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 

		
			
			connect.showLibrary(table, model);
		
        	
			JButton update = new JButton("UPDATE");
			JLabel idName = new JLabel("ID:");
        	idName.setMaximumSize(new Dimension(175,30));		
			JLabel bookname = new JLabel("Book Name:");
        	bookname.setMaximumSize(new Dimension(175,30));		
        	JLabel authorfname = new JLabel("Author Fname:");
        	authorfname.setMaximumSize(new Dimension(175,30));		
        	JLabel authorlname = new JLabel("Author Lname:");
        	authorlname.setMaximumSize(new Dimension(175,30));		
        	JLabel readStat = new JLabel("Read?:");
        	readStat.setMaximumSize(new Dimension(175,30));	
			String[] readList = {"YES", "NO"};
			JComboBox<String> readField = new JComboBox<String>(readList);
        	
        	JTextField idField, bookNameField, authorFnameField, authorLnameField;
        	idField = new JTextField(30);
			idField.setMaximumSize(new Dimension(175,30));	
        	idField.setEditable(false);
        	bookNameField = new JTextField(30);
			bookNameField.setMaximumSize(new Dimension(175,30));	
			bookNameField.setEditable(false);
			authorFnameField = new JTextField(30);
			authorFnameField.setMaximumSize(new Dimension(175,30));	
			authorFnameField.setEditable(false);
			authorLnameField = new JTextField(30);
			authorLnameField.setMaximumSize(new Dimension(175,30));
			authorLnameField.setEditable(false);

			readField.setMaximumSize(new Dimension(175,30));
        	readField.setEditable(false);
        	
        	updatePanel.setLayout(new MigLayout());
        	updatePanel.add(idName, "align label");
			updatePanel.add(idField, "wrap");
			updatePanel.add(bookname, "align label");
			updatePanel.add(bookNameField, "wrap");
			updatePanel.add(authorfname, "align label");
			updatePanel.add(authorFnameField, "wrap");
			updatePanel.add(authorlname, "align label");
			updatePanel.add(authorLnameField, "wrap");
			updatePanel.add(readStat, "align label");
			updatePanel.add(readField, "wrap");
			updatePanel.add(update, "align label");
			
			
			JButton updateBook = new JButton("SELECT");
			updateBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	int row;
		            int id ;
		            
		            if(table.getSelectionModel().isSelectionEmpty()) {
		            	
		            	 JOptionPane.showMessageDialog(null, "You should select a row first.","Alert",JOptionPane.WARNING_MESSAGE);    
		            }
		            else {
		            
		        	int dialogUpdate=JOptionPane.showConfirmDialog(null,"Are you sure you want to update this book?", "UPDATE BOOK",JOptionPane.YES_NO_OPTION);
		            
		        	if(dialogUpdate==JOptionPane.YES_OPTION){      
		        		row = table.getSelectedRow();
		            	id = Integer.parseInt(table.getValueAt(row, 0).toString());
		            	
		            	bookNameField.setEditable(true);
		            	authorFnameField.setEditable(true);
		            	authorLnameField.setEditable(true);
		            	readField.setEditable(true);
		            	
		            	String book = (table.getValueAt(row, 1).toString());
		            	String afname = (table.getValueAt(row, 2).toString());
		            	String alname = (table.getValueAt(row, 3).toString());
		            	String readst = (table.getValueAt(row, 10).toString());
		            	
		            	System.out.println(book);
		            	System.out.println(afname);
		            	System.out.println(alname);
		            	System.out.println(readst);

		            	idField.setText(table.getValueAt(row, 0).toString());
		            	bookNameField.setText(book);			
		            	authorFnameField.setText(afname);
		            	authorLnameField.setText(alname); 
		            	readField.setSelectedItem(readst);
		            	System.out.println(id);
		            }
		            }
		        	 }
		    });
			
			update.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	int result=0;

			        if ( bookNameField.getText().isEmpty() ||authorFnameField.getText().isEmpty() || authorLnameField.getText().isEmpty())
			        {
			        		 JOptionPane.showMessageDialog(null, "Please update all fields accordingly.","Alert",JOptionPane.WARNING_MESSAGE);    
			        	 }
		            
			         else
					{
			        	 result=connect.updateBook(Integer.parseInt(idField.getText()),bookNameField.getText(), authorFnameField.getText(), authorLnameField.getText(), (String)readField.getSelectedItem());
			        	 
		        		 if(result==1) {
			        	 JOptionPane.showMessageDialog(null, "Record Updated Successfully!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);    
			        	
			        	 	if(readField.getSelectedItem().equals("YES")) {
			        		
			        	 		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
			        	 		String textToAppend = "*** "+bookNameField.getText() + " is finished on " + timeStamp;
			        	 		
			        	 		 BufferedWriter writer = null;
								try {
									writer = new BufferedWriter(
									            new FileWriter(file, true));
								} catch (IOException e1) {
									
									e1.printStackTrace();
								} 
			        	 		 try {
									writer.newLine();
									writer.newLine();
								} catch (IOException e1) {
								
									e1.printStackTrace();
								}   
			        	 		 try {
									writer.write(textToAppend);
								} catch (IOException e1) {
								
									e1.printStackTrace();
								}
			        	 		 try {
									writer.close();
								} catch (IOException e1) {
									
									e1.printStackTrace();
								}
			        	 		
			        	 	}

		        		 }
		        		 else
		        		 
		        		 {
				        	 JOptionPane.showMessageDialog(null, "Record Could NOT be updated. Error!","ERROR",JOptionPane.ERROR_MESSAGE);    

		        		 }
					}
		        	 }
		    });
		
			list.add(scroll);
			list.add(updateBook);
			
			queryPanel.setBorder(BorderFactory.createTitledBorder("UPDATE BOOK"));
			queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
			queryPanel.add(list);
			queryPanel.add(updatePanel);
			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public void wishListGUI() {
			queryPanel.removeAll();
			JPanel addPanel = new JPanel();
			JPanel listPanel = new JPanel();
			
			JLabel bookName, author;
			bookName = new JLabel("Book Name:");
			bookName.setMaximumSize(new Dimension(175,30));			
			author = new JLabel("Author Name:");
			author.setMaximumSize(new Dimension(175,30));			
			JTextField bookNameField, authorField;
			bookNameField = new JTextField(30);
			bookNameField.setMaximumSize(new Dimension(175,30));		
			authorField = new JTextField(30);
			authorField.setMaximumSize(new Dimension(175,30));	
			
		
			
			JButton addWish = new JButton("Add Wish");
			addWish.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		  
		        
		    
		        	 String bookName = bookNameField.getText().toUpperCase();
		        	 String author = authorField.getText().toUpperCase();
		
		        
		         if ( bookName.isEmpty() || author.isEmpty())
		        	 {
		        		 JOptionPane.showMessageDialog(null, "Please update all fields accordingly.","Alert",JOptionPane.WARNING_MESSAGE);    
		        	 }
		        	 
		        	 else {  
		        		
						connect.insertToWish(bookName, author);
		        		 JOptionPane.showMessageDialog(null, "Book wish Record added Successfully!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);    

		        	 }
		        	 }
		    });
			
			addPanel.setLayout(new MigLayout());
			addPanel.add(bookName, "align label");
			addPanel.add(bookNameField, "wrap");
			addPanel.add(author,  "align label");
			addPanel.add(authorField, "wrap");
			addPanel.add(addWish, "wrap");
			
			JTable table = new JTable();
			
			String[] columnNames = {"BOOK_ID", "BOOKNAME", "AUTHOR"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model); 
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(170);
			table.getColumnModel().getColumn(2).setPreferredWidth(170);
	
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setFillsViewportHeight(true);
		
			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(395, 300));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			
			connect.showWishList(table, model);
			JButton boughtButton = new JButton("BUY");
			JButton deleteButton = new JButton("DELETE");
			
			boughtButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
	        		//DefaultTableModel model = (DefaultTableModel)table.getModel();
	            	int row = table.getSelectedRow();
		        	if(row >= 0) {
		        	int dialogBought=JOptionPane.showConfirmDialog(null,"Do you want to buy this book online?", "BUY?",JOptionPane.YES_NO_OPTION);
		            
		        	if(dialogBought==JOptionPane.YES_OPTION){      
		        	
		            	String searchname = (table.getValueAt(row, 1).toString());
		            	
		            	Desktop d = Desktop.getDesktop();
		            	try {
							URI uri=new URI(searchname.replace(" ", "%20"));
							d.browse(new URI("https://www.google.com/search?q="+uri+"&source=univ&tbm=shop"));
						} catch (IOException e1) {
							
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							
							e1.printStackTrace();
						}
		            	
		     
		           }
		        	}
		        	else
		        	{
		        		System.out.println("No wishbook is selected! Skipping action.");
		        		
		        	}
		        	 }
		   });
			
			deleteButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		        	if(table.getSelectionModel().isSelectionEmpty()) {
		            	
		            	 JOptionPane.showMessageDialog(null, "You should select a row first.","Alert",JOptionPane.WARNING_MESSAGE);    
		            }
		        	else {
		        	int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this book from wish list?", "DELETE BOOK",JOptionPane.YES_NO_OPTION);
		            
		        	if(dialogDelete==JOptionPane.YES_OPTION){      
		            	DefaultTableModel model = (DefaultTableModel)table.getModel();
		            	int row = table.getSelectedRow();
		            	int id = Integer.parseInt(table.getValueAt(row, 0).toString());
		            	int modelRow = table.convertRowIndexToModel( row );
		            	model.removeRow( modelRow );
		            
		            	System.out.println(id);
		            	connect.deleteWishBook(id);
		            }
		        	 }
		        }
		   });
			
			listPanel.setLayout(new MigLayout());
			listPanel.add(scroll, "align label");
			listPanel.add(boughtButton);
			listPanel.add(deleteButton, "wrap");
			
		
			
			queryPanel.setBorder(BorderFactory.createTitledBorder("WISHLIST"));
			queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
			queryPanel.add(addPanel);
			queryPanel.add(listPanel);
			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public void searchBookGUI() {
			queryPanel.removeAll();
			
			JTable table = new JTable();

			String[] columnNames = {"BOOKID", "BOOKNAME", "AUTHOR_FNAME", "AUTHOR_LNAME", "PUBLISHER", "PRICE", "CATEGORY", "SUBCATEGORY", "PUBLISHDATE", "TRANSLATOR", "READ"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model); 
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(170);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(140);
			table.getColumnModel().getColumn(5).setPreferredWidth(80);
		    table.getColumnModel().getColumn(6).setPreferredWidth(100);
		    table.getColumnModel().getColumn(7).setPreferredWidth(100);
		    table.getColumnModel().getColumn(8).setPreferredWidth(60);
		    table.getColumnModel().getColumn(9).setPreferredWidth(130);
		    table.getColumnModel().getColumn(10).setPreferredWidth(50);
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setFillsViewportHeight(true);
			
			JPanel searchPanel = new JPanel();
			JPanel listPanel = new JPanel();
			
			JLabel criteria, textfield;
			criteria = new JLabel("Search By:");
			criteria.setMaximumSize(new Dimension(175,30));	
			textfield = new JLabel("Search Key:");
			textfield.setMaximumSize(new Dimension(175,30));
			
			String[] searchList = {"AUTHORNAME", "BOOKNAME"};
			JComboBox<String> searchField = new JComboBox<String>(searchList);
			searchField.setMaximumSize(new Dimension(175,50));
			
			JTextField searchtext = new JTextField(50);
			searchtext.setMaximumSize(new Dimension(175,30));
			
			JButton searchButton = new JButton("SEARCH");
			
			searchButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		        	String keyword = (String)searchField.getSelectedItem();
		        	String field =  searchtext.getText().toUpperCase();
		        	
		        	if(field.isEmpty()) {	
		        	JOptionPane.showMessageDialog(null, "Please update all fields accordingly.","Alert",JOptionPane.WARNING_MESSAGE);    	
		        	}
		        	else
		        	{
			        model.setRowCount(0);
		        	if (keyword == "AUTHORNAME") {
		        	String searchby = "AUTHOR_FNAME";	
		        	connect.searchLibrary(searchby,field,table,model);
		        	
		        	}
		        	else {
		        		String searchby = "BOOKNAME";	
			        	connect.searchLibrary(searchby,field,table,model);	
		        	}
		        	if(table.getRowCount()==0) {
			        	JOptionPane.showMessageDialog(null, "No record found.","Alert",JOptionPane.WARNING_MESSAGE);    	
        		
		        	}
		            }
		        }
		   });
			
			
			JButton resetButton = new JButton("RESET");
			
			resetButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		        	searchtext.setText("");
		        	model.setRowCount(0);
		        }
		    });
			
			searchPanel.setLayout(new MigLayout());
			searchPanel.add(criteria, "align label");
			searchPanel.add(searchField, "wrap");
			searchPanel.add(textfield,  "align label");
			searchPanel.add(searchtext, "wrap");
			searchPanel.add(searchButton, "align label");
			searchPanel.add(resetButton, "wrap");
			
			
		
			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(920, 300));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			listPanel.setLayout(new MigLayout());
			listPanel.add(scroll, "align label");
			
			queryPanel.setBorder(BorderFactory.createTitledBorder("SEARCH BOOK"));
			queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
			queryPanel.add(searchPanel);
			queryPanel.add(listPanel);
			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public void bookhistoryGUI() throws IOException {
			
			queryPanel.removeAll();
			JPanel yearStats = new JPanel();
			JLabel allyears= new JLabel("BOOKS ALL YEARS:");
			JLabel currentYear= new JLabel("BOOKS THIS YEAR:");
			JLabel lastYear= new JLabel("BOOKS LAST YEAR:");
			JTextField allYearsField = new JTextField(30);
			allYearsField.setMaximumSize(new Dimension(175,30));
			JTextField currentYearField = new JTextField(30);
			currentYearField.setMaximumSize(new Dimension(175,30));
			JTextField lastYearField = new JTextField(30);
			lastYearField.setMaximumSize(new Dimension(175,30));
			JTextArea textbox = new JTextArea(25,80);
			
			
			JScrollPane scroll = new JScrollPane(textbox);
			scroll.setPreferredSize(new Dimension(920, 300));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			int allreadcount = 0;
			int currentyearcount = 0;
			int lastyearcount = 0;

			//long currenttime = System.currentTimeMillis();
		//	Date curyear = new Date(currenttime);
		//	int year = curyear.getYear();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			Date date = new Date (System.currentTimeMillis());
			String yearcurrent = formatter.format(date);
			int lasty = Integer.parseInt(yearcurrent);
			int lastYR = (lasty - 1);
			String yearlast = Integer.toString(lastYR);
			
			while(line != null){
				if(line.contains("***")){
					allreadcount++;
				}
				if( line.contains(yearcurrent))
				{
					currentyearcount++;
				}
				if( line.contains(yearlast))
				{
					lastyearcount++;
				}
			  textbox.append(line + "\n");
			  line = in.readLine();
			}
			String alyears = Integer.toString(allreadcount);
			allYearsField.setText(alyears);
		
			String curyears = Integer.toString(currentyearcount);
			currentYearField.setText(curyears);

			String lasyers = Integer.toString(lastyearcount);
			lastYearField.setText(lasyers);

			yearStats.setBorder(BorderFactory.createTitledBorder("BOOKS BY YEAR"));
			yearStats.setLayout(new MigLayout());
			yearStats.add(allyears, "align label");
			yearStats.add(allYearsField, "wrap");
			yearStats.add(currentYear, "align label");
			yearStats.add(currentYearField, "wrap");
			yearStats.add(lastYear, "align label");
			yearStats.add(lastYearField, "wrap");
			

			queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
			queryPanel.add(scroll);
			queryPanel.add(yearStats);
			queryPanel.setBorder(BorderFactory.createTitledBorder("RECENT BOOK HISTORY"));
			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public void borrowGUI() {
			queryPanel.removeAll();
			JPanel list = new JPanel();
			JPanel updatePanel = new JPanel();
			
			JTable table = new JTable();
			
			String[] columnNames = {"BOOKID", "BOOKNAME", "AUTHOR_FNAME", "AUTHOR_LNAME", "PUBLISHER", "PRICE", "CATEGORY", "SUBCATEGORY", "PUBLISHDATE", "TRANSLATOR", "READ"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model); 
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(170);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(140);
			table.getColumnModel().getColumn(5).setPreferredWidth(80);
		    table.getColumnModel().getColumn(6).setPreferredWidth(100);
		    table.getColumnModel().getColumn(7).setPreferredWidth(100);
		    table.getColumnModel().getColumn(8).setPreferredWidth(60);
		    table.getColumnModel().getColumn(9).setPreferredWidth(130);
		    table.getColumnModel().getColumn(10).setPreferredWidth(50);
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setFillsViewportHeight(true);
			table.setAutoCreateRowSorter(true);

			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(920, 200));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 

		
			
			connect.showLibrary(table, model);
		
        	
			JButton update = new JButton("BORROW");
			JLabel idName = new JLabel("ID:");
        	idName.setMaximumSize(new Dimension(175,30));		
			JLabel bookname = new JLabel("Book Name:");
        	bookname.setMaximumSize(new Dimension(175,30));		
        	JLabel borrowedto = new JLabel("BORROWED TO:");
        	borrowedto.setMaximumSize(new Dimension(175,30));		
        	
			JTextField idField, bookNameField, borrowedtoField; 
			idField = new JTextField(30);
			idField.setMaximumSize(new Dimension(175,30));	
        	idField.setEditable(false);
        	bookNameField = new JTextField(30);
			bookNameField.setMaximumSize(new Dimension(175,30));	
			bookNameField.setEditable(false);
			borrowedtoField = new JTextField(30);
			borrowedtoField.setMaximumSize(new Dimension(175,30));	
			borrowedtoField.setEditable(false);

			
			
			JButton updateBook = new JButton("SELECT");
			updateBook.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	int row;
		            int id ;
		            
		            if(table.getSelectionModel().isSelectionEmpty()) {
		            	
		            	 JOptionPane.showMessageDialog(null, "You should select a row first.","Alert",JOptionPane.WARNING_MESSAGE);    
		            }
		            else {
		            
		        	int dialogUpdate=JOptionPane.showConfirmDialog(null,"Are you sure you want to borrow this book?", "BORROW BOOK",JOptionPane.YES_NO_OPTION);
		            
		        	if(dialogUpdate==JOptionPane.YES_OPTION){      
		        		row = table.getSelectedRow();
		            	id = Integer.parseInt(table.getValueAt(row, 0).toString());
		            	
						bookNameField.setEditable(true);
						borrowedtoField.setEditable(true);
		            	
		            	
		            	String book = (table.getValueAt(row, 1).toString());
		            	
		            	
		            	System.out.println(book);
		            	
		            	idField.setText(table.getValueAt(row, 0).toString());
						bookNameField.setText(book);			
		            	System.out.println(id);
		            }
		            }
		        	 }
		    });
			
			update.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	int result=0;

			        if ( bookNameField.getText().isEmpty() ||borrowedtoField.getText().isEmpty())
			        {
			        		 JOptionPane.showMessageDialog(null, "Please update all fields accordingly.","Alert",JOptionPane.WARNING_MESSAGE);    
			        	 }
		            
			         else
					{	
						long millis=System.currentTimeMillis();
						Date currentBorrowDate = new Date(millis);
						result=connect.insertToBorrow(bookNameField.getText(), borrowedtoField.getText(), currentBorrowDate);
			        	 
		        		 if(result==1) {
			        	 JOptionPane.showMessageDialog(null, "Record Added to BORROW LIST Successfully!","SUCCESS",JOptionPane.INFORMATION_MESSAGE); 
		        		 }
		        		 else
		        		 {
				        	 JOptionPane.showMessageDialog(null, "Record Could NOT be updated. Error!","ERROR",JOptionPane.ERROR_MESSAGE);    
		        		 }
		        	 }
			}
		});
		JTable borrowtable = new JTable();
			
		String[] columns = {"BOOK_ID", "BOOKNAME", "BORROWED TO", "WHEN"};
		DefaultTableModel borrowmodel = new DefaultTableModel();
		borrowmodel.setColumnIdentifiers(columns);
		borrowtable.setModel(borrowmodel); 
		borrowtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		borrowtable.getColumnModel().getColumn(1).setPreferredWidth(175);
		borrowtable.getColumnModel().getColumn(2).setPreferredWidth(175);
		borrowtable.getColumnModel().getColumn(3).setPreferredWidth(175);
	

		//table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		borrowtable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		borrowtable.setFillsViewportHeight(true);
	
		JScrollPane borrowscroll = new JScrollPane(borrowtable);
		borrowscroll.setPreferredSize(new Dimension(195, 150));
		borrowscroll.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		borrowscroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		
		connect.showBorrowList(borrowtable, borrowmodel);
		JButton returnedButton = new JButton("RETURNED");
		
		returnedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(borrowtable.getSelectionModel().isSelectionEmpty()) {
					
					 JOptionPane.showMessageDialog(null, "You should select a row first.","Alert",JOptionPane.WARNING_MESSAGE);    
				}
				else {
				int dialogDelete=JOptionPane.showConfirmDialog(null,"Is this book returned to library?", "RETURNED BOOK",JOptionPane.YES_NO_OPTION);
				
				if(dialogDelete==JOptionPane.YES_OPTION){      
					DefaultTableModel model = (DefaultTableModel)borrowtable.getModel();
					int row = borrowtable.getSelectedRow();
					int id = Integer.parseInt(borrowtable.getValueAt(row, 0).toString());
					int modelRow = borrowtable.convertRowIndexToModel( row );
					model.removeRow( modelRow );
				
					System.out.println(id);
					connect.deleteBorrowedBook(id);
				}
				 }
			}
	   });
		

			list.add(scroll);
			list.add(updateBook);
			
			JPanel bottomPanel = new JPanel();
			JPanel borrowPanel = new JPanel();

        	updatePanel.setLayout(new MigLayout());
        	updatePanel.add(idName, "align label");
			updatePanel.add(idField, "wrap");
			updatePanel.add(bookname, "align label");
			updatePanel.add(bookNameField, "wrap");
			updatePanel.add(borrowedto, "align label");
			updatePanel.add(borrowedtoField, "wrap");
			updatePanel.add(update, "align label"); 

			
			borrowPanel.add(borrowscroll);
			borrowPanel.add(returnedButton);

			bottomPanel.setLayout(new MigLayout());
			bottomPanel.add(updatePanel);
			bottomPanel.add(borrowPanel);
			
			queryPanel.setBorder(BorderFactory.createTitledBorder("BOOKS BORROWED"));
			queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
			queryPanel.add(list);
			queryPanel.add(bottomPanel);
			queryPanel.revalidate();
			queryPanel.repaint();
		}

		public void statisticsGUI() throws IOException {
			
			queryPanel.removeAll();
			
			int totalBooks=0;
			int readedBooks=0;
			int notreadedbooks=0;
			
			int edebiyatNum=0;
			int tarihNum=0;
			int dinNum=0;
			int felseNum=0;
			int hobiNum=0;
			int bilimNum=0;
			int sanatNum=0;
			int diger=0;
			
			JTable table = new JTable();
			
			String[] columnNames = {"BOOKID", "BOOKNAME", "AUTHOR_FNAME", "AUTHOR_LNAME", "PUBLISHER", "PRICE", "CATEGORY", "SUBCATEGORY", "PUBLISHDATE", "TRANSLATOR", "READ"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model); 
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(170);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(140);
			table.getColumnModel().getColumn(5).setPreferredWidth(80);
		    table.getColumnModel().getColumn(6).setPreferredWidth(100);
		    table.getColumnModel().getColumn(7).setPreferredWidth(100);
		    table.getColumnModel().getColumn(8).setPreferredWidth(60);
		    table.getColumnModel().getColumn(9).setPreferredWidth(130);
		    table.getColumnModel().getColumn(10).setPreferredWidth(50);
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setFillsViewportHeight(true);
			table.setEnabled(false);
		
			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(920, 300));
			scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			
			totalBooks = connect.showLibrary(table, model);
		    readedBooks = connect.countLibrary(table);
			notreadedbooks= (totalBooks - readedBooks);
			edebiyatNum = connect.countCAT(table, "EDEBIYAT");
			tarihNum = connect.countCAT(table, "ARASTIRMA-TARIH");
			dinNum = connect.countCAT(table, "DIN-MITOLOJI");
			felseNum = connect.countCAT(table, "FELSEFE");
			hobiNum = connect.countCAT(table, "HOBI");
			bilimNum = connect.countCAT(table, "BILIM");
			sanatNum = connect.countCAT(table, "SANAT");
			diger = connect.countCAT(table, "DIGER");
			
			
			 JPanel results,results2;
			 results = PieDemo.createDemoPanel(readedBooks,notreadedbooks);
			 results.setPreferredSize(new Dimension(400,400));
			 
			 results2 = PieDemo.createDemoPanelCATSTAT(edebiyatNum, tarihNum, dinNum, felseNum, hobiNum, bilimNum, sanatNum, diger);
			 results2.setPreferredSize(new Dimension(500,400));
			 
			queryPanel.setBorder(BorderFactory.createTitledBorder("LIBRARY STATISTICS"));
			queryPanel.add(results);
			queryPanel.add(results2);

			queryPanel.revalidate();
			queryPanel.repaint();
		}
		
		public static boolean isNumeric(String strNum) {
		    try {
		        double d = Double.parseDouble(strNum);
		    } catch (NumberFormatException | NullPointerException nfe) {
		        return false;
		    }
		    return true;
		}
		public static boolean isInteger(String strNum) {
		    try {
		        int d = Integer.parseInt(strNum);
		    } catch (NumberFormatException | NullPointerException nfe) {
		        return false;
		    }
		    return true;
		}
		
	}

	   private static String getFileExtension(Path file) {
	        Path filePath = file.getFileName();
	        String fileName = filePath.toString();
	        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
	    }

	public static void main(String[] args) throws IOException {
		
		JWindow window = new JWindow();

		Icon imagesplash = new ImageIcon(("images/mylibnew.jpg")); 
		JLabel splashlable = new JLabel(imagesplash);

		window.getContentPane().add(splashlable);
		window.setBounds(350,350,525,350);
		window.setVisible(true);
		try {
			DBConnection bConnection = new DBConnection();
			bConnection.createTable();
			Thread.sleep(3000);	
		}
		catch(InterruptedException e) {	}
		window.dispose();
		
	    myGUI gui = new myGUI();
	    gui.display();
	   
		
		}
	
	}
