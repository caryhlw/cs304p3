import java.sql.*;
import java.io.*;

public class Report {

	private Date reportDate;
	private String upc;
	private String category;
	private String sellPrice;
	private String quantity;
	private String totalValue;
	
	private String title;
	private String company;
	private String stock;
	private String quantitySold;
	
	private Connection con;
	private PreparedStatement getReport;
	
	
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

			String reportString = "SELECT I.upc, I.category, I.sellPrice, P.quantity, (I.sellPrice*P.quantity) FROM  Item I, PurchaseItem P, Purchase WHERE Purchase.purchaseDate = ? AND " +
											"Purchase.receiptID = P.receiptID AND P.upc = I.upc ORDER BY I.category"; 
											

			getReport = con.prepareStatement(reportString);
			getReport.setDate(1, d);
			ResultSet rs = getReport.executeQuery();

			while(rs.next()) {

				upc = rs.getString(1);
				category = rs.getString(2);
				sellPrice = rs.getString(3);
				quantity = rs.getString(4);
				totalValue = rs.getString(5);
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
		String topString = "SELECT I.title, I.company, I.quantity, QuantitySold FROM (SELECT upc AS linkupc, " +
				"sum(quantity) AS QuantitySold FROM (SELECT upc, PI.quantity FROM PurchaseItem PI, Purchase P WHERE P.purchaseDate = ? " +
				"AND P.receiptID = PI.receiptID) GROUP BY upc ORDER BY QuantitySold desc) INNER JOIN Item I ON linkupc = I.upc WHERE ROWNUM < ?";
		getReport = con.prepareStatement(topString);
		getReport.setDate(1, d);
		getReport.setInt(2, num+1);
		ResultSet rs = getReport.executeQuery();
		
		while(rs.next()){
			title = rs.getString(1);
			company = rs.getString(2);
			stock = rs.getString(3);
			quantitySold = rs.getString(4);
			System.out.println(title + " " + company + " " + quantity + " " + quantitySold + "\n");
			
		}
		
		rs.close();
		getReport.close();
		
	}
	catch(SQLException e) {
		System.out.println("Message: " + e.getMessage());
	}
 	
	}
	
}
