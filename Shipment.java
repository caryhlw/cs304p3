import java.sql.*;

public class Shipment {

	private int sid;
	private String supName;
	private String sdate;
	private ArrayList <int> itemList;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public Shipment (Connection con, int sid, String supName, String sdate, int firstItemUPC)
	{
		this.con = con;
		this.sid = sid;
		this.supName = supName;
		this.sdate = sdate;
		itemList = new ArrayList<int>(1);
		itemList.set(0, firstItemUPC);
	}
	
	public void addItem( int UPC ){
		con.prepareStatement(sql);
	}
	
	int getSid(){
		return sid;
	}
	String getSupName(){
		return supName;
	}
	String getSDate(){
		return sdate;
	}
}