import java.sql.*;

// for reading from the command line
import java.io.*;



public class HasSong{
	
	private int upc;
	
	private String title;
	
	private Statement stmt;
    private ResultSet rs;
    
    public HasSong( int upc, String title){
    	
    	try{
    		Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug",
                    "username",
                    "password");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT upc"
                    + "FROM item"
                    + "WHERE upc = " + upc + ";");
            
            if (rs.next() == true)
            {
                upc = this.upc;
                title = this.title;
            }
            
        
    	}
    	
    	catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    	title = this.title;
    	
    }
    
    int getUPC() {
    	return upc;
    }
    String getTitle(){
    	return title;
    }
	
}