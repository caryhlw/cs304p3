import java.sql.*;

// no where near complete...
public class ShipItem {
	private int sid;
	private int upc;
	private int supPrice;
	private int quantity;
	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	
	public ShipItem (Connection con, int sid, int upc)
	{
		try{
			stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT upc"
                    + "FROM Item"
                    + "WHERE upc = " + upc + ";");
		}
		 if (rs.next() == true)
         {
			 this.con = con;
				this.sid = sid;
				this.upc = upc;
         }
		
	}

	
	int getSid(){
		return sid;
	}
	int getUPC(){
		return upc;
	}
	int getSupPrice(){
		return supPrice;
	}
	int getQuantity(){
		return quantity;
	}
}