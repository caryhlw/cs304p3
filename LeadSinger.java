import java.sql.*;

public class LeadSinger {

	private int upc;
	private String name;
	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	
	public LeadSinger (Connection con, int upc, String name)
	{
		this.con = con;
		try
        {
			stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT upc"
                    + "FROM item"
                    + "WHERE upc = " + upc + ";");
            //check if upc exist
            if (rs.next() == true)
            {
                this.upc = upc;
                this.name = name;
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