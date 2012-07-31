import java.sql.*;

public class LeadSinger {

	private int upc;
	private String name;
	private Statement stmt;
	private ResultSet rs;
	
	public LeadSinger (int upc, String name)
	{
	
		try
        {
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
                name = this.name;
            }
            
        
        }
        
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }
int getUPC(){
	return upc;
	}
String getname(){
	return name;
}
	
}