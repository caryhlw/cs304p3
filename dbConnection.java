// We need to import the java.sql package to use JDBC
import java.sql.*;

// for reading from the command line
import java.io.*;



public class dbConnection {

	private static java.sql.Connection con;
	private String username;
	private String password;
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private Boolean connected = false;
	 
	public dbConnection() {
		
		try 
	      {
		// Load the Oracle JDBC driver
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.out.println("\nDriver loaded!");
	      }
	      catch (SQLException ex)
	      {
		System.out.println("Message: " + ex.getMessage());
		System.exit(-1);
	      }
		
		//try {
			while(!connected) {
			//	System.out.println("\nEnter your Oracle username: ");
			//	username = in.readLine();
			//	System.out.println("\nEnter your Oracle password: ");
			//	password = in.readLine();
				if ( Connect("ora_e8u7", "a62473087") ) connected = true;
		//	}
		}
		//catch(IOException ex) {
		//	System.out.println(ex.getMessage());
		//}
	}
		
	
	private boolean Connect(String username, String password) {
		
		String connectURL = "jdbc:oracle:thin:@localhost:1521:ug";
		
		try {
			con = DriverManager.getConnection(connectURL, username, password);
			System.out.println("\nSuccessfully Connected!\n\n");
			return true;
		}
		catch(SQLException ex) {
			System.out.println("Error message: " + ex.getMessage());
			return false;
		}
	}
	
	private boolean Disconnect()  {
		try {
			con.close();
			System.out.println("\nConnection closed. Bye for now!\n\n");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Message: " + e.getMessage());
			return false;
		}
	
	}
	
	public Connection getConnection() {
		return con;
	}
	
}
