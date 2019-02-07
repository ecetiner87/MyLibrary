/**
 * 
 */
package appDemoSplashScreen;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




/**
 * @author erkance
 *
 */
public class DBConnection {

		private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		private static final String JDBC_URL = "jdbc:derby:LibraryDB; create=true";
		
		Connection conn;
		
		public DBConnection(){
			
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {		
				this.conn = DriverManager.getConnection(JDBC_URL);
				if (this.conn != null) {
					System.out.println("Connected to DB");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void createTable() {

			if ( ! tableExists( conn, "LIBRARY" ) )  {
				System.out.println("LIBRARY Table does not exists on Derby DB! Creating...");
					try {
						conn.createStatement().execute("CREATE TABLE LIBRARY\n" + 
								"(  \"BOOK_ID\" INT not null primary key\n" + 
								"        GENERATED ALWAYS AS IDENTITY\n" + 
								"        (START WITH 1, INCREMENT BY 1),   \n" + 
								"   \"BOOKNAME\" VARCHAR(50),\n" + 
								"   \"AUTHOR_FNAME\" VARCHAR(50),\n" + 
								"   \"AUTHOR_LNAME\" VARCHAR(50),\n" + 
								"   \"PUBLISHER\" VARCHAR(50),\n" + 
								"   \"PRICE\" DOUBLE,\n" + 
								"   \"CATEGORY\" VARCHAR(50),\n" + 
								"   \"SUBCATEGORY\" VARCHAR(100),\n" + 
								"   \"PUBLISHDATE\" INT,\n" + 
								"   \"TRANSLATOR\" VARCHAR(100),\n" + 
								"   \"READED\" VARCHAR(10))");
						
						if (this.conn != null) {
							System.out.println("LIBRARY Table Created on Derby DB!");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
		}
			else {
				System.out.println("LIBRARY Table already exists on Derby DB! Skipping...");
			}
			
			if ( ! tableExists( conn, "WISHLIST" ) )  {
				System.out.println("WISHLIST Table does not exists on Derby DB! Creating...");
					try {
						conn.createStatement().execute("CREATE TABLE WISHLIST\n" + 
								"(  \"BOOK_ID\" INT not null primary key\n" + 
								"        GENERATED ALWAYS AS IDENTITY\n" + 
								"        (START WITH 1, INCREMENT BY 1),   \n" + 
								"   \"BOOKNAME\" VARCHAR(50),\n" + 
								"   \"AUTHOR\" VARCHAR(50))");
						
						if (this.conn != null) {
							System.out.println("WISHLIST Table Created on Derby DB!");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
		}
			else {
				System.out.println("WISHLIST Table already exists on Derby DB! Skipping...");
			}
		}
		public  void insertToTable(String bookName,String authorFname, String authorLname, String publisher,double price,String category,String subcategory,String readed, int Date, String trnsltr) {
			try {
				
				String insertSQL = "INSERT INTO LIBRARY (BOOKNAME, AUTHOR_FNAME, AUTHOR_LNAME, PUBLISHER, PRICE, CATEGORY, SUBCATEGORY, PUBLISHDATE, TRANSLATOR, READED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(insertSQL);
				ps.setString(1, bookName);
				ps.setString(2, authorFname);
				ps.setString(3, authorLname);
				ps.setString(4, publisher);
				ps.setDouble(5, price);
				ps.setString(6, category);
				ps.setString(7, subcategory);
				ps.setInt(8, Date);
				ps.setString(9, trnsltr);
				ps.setString(10, readed);
				ps.executeUpdate();
	
				if (this.conn != null) {
					System.out.println("Record added to Library Table!");
					conn.createStatement().close();
				
				}
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public  int showLibrary(JTable table, DefaultTableModel model) {
		
				String bookid = "";
			    String bookname = "";
				String authorf = "";
				String authorl = "";
				String publisher = "";
				String price = "";
				String cat = "";
				String subcat = "";
				String pdate = "";
				String translate = "";
				String readstat = "";
				int totalCount = 0;
				try
				{ 
				
				String sql = "select * from LIBRARY";
				PreparedStatement ps = conn.prepareStatement(sql);
				java.sql.ResultSet rs =  ps.executeQuery();
				
					while(rs.next())
					{
					bookid = rs.getString("BOOK_ID");
					bookname = rs.getString("BOOKNAME");
					authorf = rs.getString("AUTHOR_FNAME");
					authorl = rs.getString("AUTHOR_LNAME");
					publisher = rs.getString("PUBLISHER"); 
					price = rs.getString("PRICE");
					cat = rs.getString("CATEGORY");
					subcat = rs.getString("SUBCATEGORY");
					pdate = rs.getString("PUBLISHDATE");
					translate = rs.getString("TRANSLATOR");
					readstat = rs.getString("READED");
					model.addRow(new Object[]{bookid, bookname, authorf, authorl, publisher, price, cat, subcat, pdate, translate, readstat});
					totalCount++;
					}
							
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return totalCount;
		}
		
		public  void deleteBook(int id) {
			try {
				
				
				String deleteSQL = "DELETE FROM LIBRARY WHERE BOOK_ID = ?";
				PreparedStatement stmt = conn.prepareStatement(deleteSQL);
				stmt.setInt( 1, id );
				stmt.executeUpdate();
				stmt.close();
	
				if (this.conn != null) {
					System.out.println("Record deleted from Library Successfully!");
					conn.createStatement().close();
				
				}
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public  void deleteAllBooks() {
			try {
				
				
				String deleteSQL = "DELETE FROM LIBRARY";
				PreparedStatement stmt = conn.prepareStatement(deleteSQL);
				stmt.executeUpdate();
				stmt.close();
	
				if (this.conn != null) {
					System.out.println("All Records are deleted from Library Successfully!");
					conn.createStatement().close();
				
				}
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		public int importLibrary(Path path) {
			int result = 0;
			
			try {
				
	
				String mypath = path.toString();
	            String importSQL = "CALL SYSCS_UTIL.SYSCS_IMPORT_DATA (?,?,?,?,?,?,?,?,?)";
				PreparedStatement stmt = conn.prepareStatement(importSQL);
				stmt.setString( 1, null );
				stmt.setString( 2, "LIBRARY");
				stmt.setString( 3, "BOOKNAME,AUTHOR_FNAME,AUTHOR_LNAME,PUBLISHER,PRICE,CATEGORY,SUBCATEGORY,PUBLISHDATE,TRANSLATOR,READED");
				stmt.setString( 4, null);
				stmt.setString( 5, mypath );
				stmt.setString( 6, null );
				stmt.setString( 7, null );
				stmt.setString( 8, null );
				stmt.setInt( 9, 0 );
				stmt.executeUpdate();
				stmt.close();
	
				if (this.conn != null) {
					System.out.println("All Records are imported from related file successfully!");
					conn.createStatement().close();
					result=1;
				
				}
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		public  void searchLibrary(String searchby, String criteria, JTable table, DefaultTableModel model) {
			
				String bookid = "";
			    String bookname = "";
				String authorf = "";
				String authorl = "";
				String publisher = "";
				String price = "";
				String cat = "";
				String subcat = "";
				String pdate = "";
				String translate = "";
				String readstat = "";
				try
				{ 
				
				
				
				String sql = " select * from LIBRARY WHERE "+ searchby +" LIKE '" + criteria + "%' ";
				PreparedStatement ps = conn.prepareStatement(sql);
				java.sql.ResultSet rs =  ps.executeQuery();
				
					while(rs.next())
					{
					bookid = rs.getString("BOOK_ID");
					bookname = rs.getString("BOOKNAME");
					authorf = rs.getString("AUTHOR_FNAME");
					authorl = rs.getString("AUTHOR_LNAME");
					publisher = rs.getString("PUBLISHER"); 
					price = rs.getString("PRICE");
					cat = rs.getString("CATEGORY");
					subcat = rs.getString("SUBCATEGORY");
					pdate = rs.getString("PUBLISHDATE");
					translate = rs.getString("TRANSLATOR");
					readstat = rs.getString("READED");
					model.addRow(new Object[]{bookid, bookname, authorf, authorl, publisher, price, cat, subcat, pdate, translate, readstat});
					}
							
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
		public  void deleteWishBook(int id) {
			try {
				
				
				String deleteSQL = "DELETE FROM WISHLIST WHERE BOOK_ID = ?";
				PreparedStatement stmt = conn.prepareStatement(deleteSQL);
				stmt.setInt( 1, id );
				stmt.executeUpdate();
				stmt.close();
	
				if (this.conn != null) {
					System.out.println("Record deleted from Wishlist Successfully!");
					conn.createStatement().close();
				
				}
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public  int updateBook(int id, String book, String afname, String alname, String readst) {
			int result = 0;

			try {
				
				String updateSQL = "UPDATE LIBRARY SET READED = ? , BOOKNAME = ? , AUTHOR_FNAME = ? , AUTHOR_LNAME = ? WHERE BOOK_ID = ?";
				PreparedStatement stmt = conn.prepareStatement(updateSQL);
				stmt.setString( 1, readst );
				stmt.setString( 2, book);
				stmt.setString( 3, afname);
				stmt.setString( 4, alname);
				stmt.setInt( 5, id );

				
				stmt.executeUpdate();
				stmt.close();
	
				if (this.conn != null) {
					System.out.println("Record updated successfully!");
					conn.createStatement().close();
					result=1;
				
				}
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return result;
		}
		
		public  void showWishList(JTable table, DefaultTableModel model) {
			
			
		    String bookid = "";
			String bookname = "";
			String author = "";
			
			
			try
			{ 
			
			String sql = "select * from WISHLIST";
			PreparedStatement ps = conn.prepareStatement(sql);
			java.sql.ResultSet rs =  ps.executeQuery();
			
				while(rs.next())
				{
				bookid = rs.getString("BOOK_ID");
				bookname = rs.getString("BOOKNAME");
				author = rs.getString("AUTHOR");
				
				model.addRow(new Object[]{bookid, bookname, author});
				}
						
			}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		public  void insertToWish(String bookName,String authorName) {
			try {
				
				String insertSQL = "INSERT INTO WISHLIST (BOOKNAME, AUTHOR) VALUES (?, ?)";
				PreparedStatement ps = conn.prepareStatement(insertSQL);
				ps.setString(1, bookName);
				ps.setString(2, authorName);
				ps.executeUpdate();
	
				if (this.conn != null) {
					System.out.println("Record added to Wishlist Table!");
					conn.createStatement().close();
				
				}
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		 private static boolean tableExists ( Connection con, String table ) {
			    int numRows = 0;
			    try {
			      DatabaseMetaData dbmd = con.getMetaData();
			      // Note the arguments to getTables are case-sensitive!
			      java.sql.ResultSet rs = dbmd.getTables( null, "APP", table.toUpperCase(), null);
			      while( rs.next() ) ++numRows;
			    } catch ( SQLException e ) {
			        String theError = e.getSQLState();
			        System.out.println("Can't query DB metadata: " + theError );
			        System.exit(1);
			    }
			    return numRows > 0;
			  }
		 public  int countLibrary(JTable table) {
				
				int totalReadCount = 0;
				//int totalNReadCount = 0;
				try
				{ 
				
				String sql = "select count(*) as MY_COUNT from LIBRARY WHERE READED='YES'";
				PreparedStatement ps = conn.prepareStatement(sql);
				java.sql.ResultSet rs =  ps.executeQuery();
				rs.next();
				totalReadCount = rs.getInt("MY_COUNT");		
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return totalReadCount;
		}
		 
		 public  double sumPriceBooks(JTable table) {
				
				double totalPrice = 0;
				//int totalNReadCount = 0;
				try
				{ 
				
				String sql = "select sum(PRICE) as LIBRPRICE from LIBRARY";
				PreparedStatement ps = conn.prepareStatement(sql);
				java.sql.ResultSet rs =  ps.executeQuery();
				rs.next();
				totalPrice = rs.getDouble("LIBRPRICE");		
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return totalPrice;
		}
}
