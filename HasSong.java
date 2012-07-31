import java.sql.*;

// for reading from the command line
import java.io.*;



public class HasSong{
	
	private int upc;
	
	private String title;
	
	private Statement stmt;
    private ResultSet rs;
    private Connection con;
    
    public HasSong(Connection con, int upc, String title){
    	
    	this.con = con;
    	try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT upc"
                    + "FROM Item"
                    + "WHERE upc = " + upc + ";");
          //check if upc exist
            if (rs.next() == true)
            {
                this.upc = upc;
                this.title = title;
            }
            
        
    	}
    	
    	catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    	
    }
    
    int getUPC() {
    	return upc;
    }
    String getTitle(){
    	return title;
    }
	
}