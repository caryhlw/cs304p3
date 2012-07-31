import java.sql.*;

public class Item {

	private int upc;
	private String title;
	private String type;
	private String category;
	private String company;
	private int year;
	private float sellPrice;
	private int quantity;
	private Statement stmt;
	private ResultSet rs;
	
	public LeadSinger (int upc)
	{
	upc = this.upc;
	
		try
        {
			Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug",
                    "username",
                    "password");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT title, type, category, company, year, sellPrice, quantity"
                    + "FROM Item"
                    + "WHERE upc = " + upc + ";");
            
            if (rs.next() == true)
            {
              title = rs.getNString(1);
			  type = rs.getNString(2);
			  category = rs.getNString(3);
			  company = rs.getNString(4);
			  year = rs.getInt(5);
			  sellPrice = rs.getFloat(6);
			  quantity = rs.getInt(7);
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
String gettitle(){
	return title;
	}
String gettype(){
	return type;
	}
String getcategory(){
	return category;
	}
String getcompany(){
	return company;
	}
int getyear(){
	return year;
	}
float getsellPrice(){
	return sellPrice;
	}
int getquantity(){
	return quantity;
	}
	
}