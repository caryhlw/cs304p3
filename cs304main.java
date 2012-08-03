
import java.sql.*;

public class cs304main {

	public static void main(String args[])
	{
		try {
			
		dbConnection c = new dbConnection();
		
		swing gui = new swing(c.getConnection());

		PreparedStatement ps;
		for (int i = 0; i < 100; i++) {
			ps = c.getConnection().prepareStatement("INSERT INTO Item VALUES (upc_counter.nextval, 'Random', 'dvd', , 'LOL DVDs', 2015, 1.05, 100");
			ps.executeUpdate();
			System.out.println(i);
		}
		
		} catch (SQLException e) {
			
		}
		//Search s = new Search(c.getConnection());
		//s.searchCategory("cd", 0);
		//System.out.println(s.results(1).printItem());
		//System.out.println(s.printResults());
	} 

}
