import java.sql.*;
import java.io.*;

public class Report {

	private Date reportDate;
	private int upc;
	private String category;
	private float sellPrice;
	private int quantity;
	private float totalValue;
	
	private String title;
	private String company;
	private int stock;
	private int quantitySold;
	
	private Connection con;
	private PreparedStatement getReport = null;
	
	
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public Report(Connection con) {
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
			System.out.println(d);
			String reportString = "SELECT I.upc, I.category, I.sellPrice, P.quantity FROM  Item I, PurchaseItem P, Purchase WHERE Purchase.purchaseDate = ? AND " +
											"Purchase.receiptID = P.receiptID AND P.upc = I.upc";// +
											//"GROUP BY I.category" +
											//"SORT BY I.category;";
			
			getReport = con.prepareStatement(reportString);
			getReport.setDate(1, d);
			ResultSet rs = getReport.executeQuery();
			
			System.out.println(getReport.toString());
			
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
	
	public void printTopSellers(Date d, int num) {
	try {
		String topString = "SELECT I.title, I.company, I.quantity, SUM(P.quantity) AS QuantitySold" +
				"FROM Item I, PurchaseItem P, Purchase " +
				"WHERE ROWNUM < ? AND Purchase.purchaseDate = ? AND Purchase.receiptID = P.receiptID AND P.upc = I.upc" +
				"GROUP BY upc" +
				"ORDER BY quantity_sold";
		
		getReport = con.prepareStatement(topString);
		getReport.setDate(2, d);
		getReport.setInt(1, num);
		ResultSet rs = getReport.executeQuery();
		
		while(rs.next()){
			title = rs.getString(1);
			company = rs.getString(2);
			quantity = rs.getInt(3);
			quantitySold = rs.getInt(4);
			System.out.println(title + " " + company + " " + quantity + " " + quantitySold + "\n");
			
		}
	}
	catch(SQLException e) {
		System.out.println("Message: " + e.getMessage());
	}
 	
	}
	
}
