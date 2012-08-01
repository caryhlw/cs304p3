
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Shipment
{

	private int sid;
	private String supName;
	private Date date;
	private ArrayList <int> shipItems;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
	
	public Shipment (Connection con, int sid, String supName, int firstItemUPC)
	{
		this.con = con;
		this.sid = sid;
		this.supName = supName;
		shipItems = new ArrayList<int>(1);
		shipItems.set(0, firstItemUPC);
		this.date = new Date();
        long t = date.getTime();
        java.sql.Date dt = new java.sql.Date(t);
        try
        {
            ps = con.prepareStatement("INSERT INTO Shipment VALUES"
                    + "(shipment_counter.next, ?, ?)");
            ps.setString(1, this.supName);
            ps.setDate(2, dt);
            
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
	}
	
	public void addItem( int UPC ){
		
		try{
		ps = con.prepareStatement("SELECT  title, type, category, company, year, sellPrice, quantity"
				+ "FROM Item"
				+ "WHERE upc = ?");
		ps.setInt(1,UPC);
		rs = ps.executeQuery();
		while (rs.next())
        {

            retItem.add(new Item(con, rs.getInt(1)));
        }
		con.commit();

        ps.close();
		}catch (SQLException ex)
	       {
	           System.out.println("Message: " + ex.getMessage());
	           try
	           {
	               // undo the insert
	               con.rollback();
	           } catch (SQLException ex2)
	           {
	               System.out.println("Message: " + ex2.getMessage());
	               System.exit(-1);
	           }
	       }
	}
	
	public int getSid()
    {
        return sid;
    }
    public String getSupName()
    {
        return supName;
    }

    public String getSDate()
    {
        return sdate;
    }
}

