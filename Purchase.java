
import java.sql.*;
import java.util.ArrayList;

public class Purchase {

    private ArrayList purchaseItems;
    private int receiptId;
    private String date;
    private int cardnum;
    private int expiry;
    private String expectedDate;
    private String deliveredDate;
    private Statement stmt;
    private ResultSet rs;

    public Purchase() {
        //fetchReceiptID
        //getCurrentDate
        ArrayList purchaseItems = new ArrayList();
    }

    public void addItem(int upc) {
        try
        {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug",
                    "username",
                    "password");

            PurchaseItem purchaseItem = new PurchaseItem(upc, receiptId);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT quantity"
                    + "FROM PurchaseItem"
                    + "WHERE receiptId = " + receiptId + ", upc = " + upc + ";");
            if (rs.next() == false)
            {
                
                stmt.executeUpdate("insert into PurchaseItem values"
                        + "(" + receiptId + "," + upc + ", 1);");
                purchaseItems.add(purchaseItem);

            }
            
            else
            {
                //check if enough stock to add
                //update the entry
                            purchaseItems.add(purchaseItem);

            }

        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public int receiptId() {
        return receiptId;
    }

    public String date() {
        return date;
    }

    public int cardnum() {
        return cardnum;
    }

    public int expiry() {
        return expiry;
    }

    public String expectedDate() {
        return expectedDate;
    }

    public String deliveredDate() {
        return deliveredDate;
    }
}
