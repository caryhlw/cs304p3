import java.sql.*;
import java.util.ArrayList;

public class Search {
	
	private ArrayList retItem;
	private String sTerm;
	private Connection con;
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement  ps;
	
	public Search(Connection con){
		retItem = new ArrayList(0);
		this.con = con;
		sTerm = null;
		
	}
	
	void searchTitle(){
		try{
		ps = con.prepareStatement("SELECT upc, type, category, company, year, sellPrice"
				+"FROM Item"
				+"WHERE title = ?");
		sTerm = in.readLine();
		if (sTerm.length() == 0)
        {
	      ps.setString(1, null);
	  }
	  else
	  {
	      ps.setString(1, sTerm);
	  }
		ps.executeUpdate();

		  // commit work 
		  con.commit();

		  ps.close();
		}
		
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    try 
		    {
			// undo the insert
			con.rollback();	
		    }
		    catch (SQLException ex2)
		    {
			System.out.println("Message: " + ex2.getMessage());
			System.exit(-1);
		    }
		}
	}
}