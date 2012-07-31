import java.sql.*;

public class Shipment {

	private int sid;
	private String supName;
	private String sdate;
	private Statement stmt;
	private ResultSet rs;
	
	public Shipment (int sid)
	{
	sid = this.sid;
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