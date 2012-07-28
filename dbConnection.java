// We need to import the java.sql package to use JDBC
import java.sql.*;

// for reading from the command line
import java.io.*;



public class dbConnection {
	
	public static void main(String args[])
	{
		dbConnection c = new dbConnection();
	}

	private java.sql.Connection con;
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
		
		try {
			while(!connected) {
				System.out.println("\nEnter your Oracle username: ");
				username = in.readLine();
				System.out.println("\nEnter your Oracle password: ");
				password = in.readLine();
				if ( Connect(username, password) ) connected = true;
			}
		}
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
		
	
	private boolean Connect(String username, String password) {
		
		String connectURL = "jdbc:oracle:thin:@localhost:1521:ug";
		
		try {
			con = DriverManager.getConnection(connectURL, username, password);
			System.out.println("\nSuccessfully Connected!");
			return true;
		}
		catch(SQLException ex) {
			System.out.println("Error message: " + ex.getMessage());
			return false;
		}
	}
	
}
