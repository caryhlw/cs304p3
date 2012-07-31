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
			
		ps = con.prepareStatement("SELECT upc "
				+"FROM Item"
				+"WHERE title = ?");
		sTerm = in.readLine();
		//sTerm = search Term
		if (sTerm.length() == 0)
        {
	      ps.setString(1, null);
	  }
	  else
	  {
	      ps.setString(1, sTerm);
	  }
		rs = ps.executeQuery();

		while( rs.next() ){
			
			retItem.add(Item I(con,rs.getInt(1)));
		}
		
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
	
	
	void searchCategory(){
		try{
		ps = con.prepareStatement("SELECT upc"
				+"FROM Item"
				+"WHERE category = ?");
		sTerm = in.readLine();
		//sTerm = search Term
		if (sTerm.length() == 0)
        {
	      ps.setString(1, null);
	  }
	  else
	  {
	      ps.setString(1, sTerm);
	  }
		rs = ps.executeQuery();

		while( rs.next() ){
			
			retItem.add(Item I(con,rs.getInt(1)));
		}
		
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
	void searchLeadSinger(){
		try{
		ps = con.prepareStatement("SELECT upc"
				+"FROM Item I, LeadingSinger L"
				+"WHERE I.upc = L.upc AND L.name = ?");
		sTerm = in.readLine();
		//sTerm = search Term
		if (sTerm.length() == 0)
        {
	      ps.setString(1, null);
	  }
	  else
	  {
	      ps.setString(1, sTerm);
	  }
		rs = ps.executeQuery();

		while( rs.next() ){
			
			retItem.add(Item I(con,rs.getInt(1)));
		}
		

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