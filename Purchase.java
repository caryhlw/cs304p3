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
                if (checkStock(purchaseItem, 1) == true)
                stmt.executeUpdate("insert into PurchaseItem values"
                        + "(" + receiptId + "," + upc + ", 1);");
                purchaseItems.add(purchaseItem);

            } else
            {
                if (checkStock(purchaseItem, rs.getInt(1)))
                stmt.executeUpdate("Update PurchaseItem"
                        + "SET quantity = quantity + 1"
                        + "WHERE receiptId = " + receiptId + ", upc = " + upc + ";");
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

    private boolean checkStock(PurchaseItem pi, int purchaseQuantity) {
        int stock = pi.getQuantity();
        if (stock > purchaseQuantity)
            return true;
        else
            return false;
    }
}
