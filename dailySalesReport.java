import java.sql.*;
import java.io.*;

public class dailySalesReport {

	private Date reportDate;
	private int upc;
	private String category;
	private float sellPrice;
	private int quantity;
	private float totalValue;
	
	private Connection con;
	private PreparedStatement getReport = null;
	
	
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public dailySalesReport(Connection con) {
		//try {
			this.con = con;
		//}
		//catch(SQLException e) {
		//	System.out.println("Message: " + e.getMessage());
		//}
		
	}
	
	public Date getDate() {
		try {
			System.out.print("Please enter a date (yyyy-mm-dd) to generate a sales report for: ");
			reportDate = Date.valueOf(in.readLine());
			return reportDate;
		}
		
		catch(IOException e)
		{
			 System.out.println("Message: " + e.getMessage());
			 return Date.valueOf("1111-11-11");
		}
	}
	
	public void printReport(Date d) {
		try {
			
			String reportString = "SELECT I.upc, I.category, I.sellPrice, P.quantity, (sellPrice*quantity)" +			// haven't tested query
											"FROM  Item I, PurchaseItem P, Purchase WHERE Purchase.date = ? AND " +
											"Purchase.receiptID = P.Item AND P.upc = I.upc" +
											"GROUP BY I.category" +
											"SORT BY I.category";
			
			getReport = con.prepareStatement(reportString);
			getReport.setDate(1, d);
			ResultSet rs = getReport.executeQuery();
			
			while(rs.next()) {
				upc = rs.getInt(1);
				category = rs.getString(2);
				sellPrice = rs.getFloat(3);
				quantity = rs.getInt(4);
				totalValue = rs.getFloat(5);
				System.out.println(upc + " " + category + " " + sellPrice + " " + quantity + " " + totalValue + "\n");
			}
			rs.close();
			getReport.close();
		}
		catch(SQLException e) {
			System.out.println("Message: " + e.getMessage());
		}
	}
	
	
	
	
}
