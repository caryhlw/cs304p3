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
    
    private float subtotal;

    private Statement stmt;
    private ResultSet rs;
    private Connection con;

    public Purchase(Connection con) {
        //fetchReceiptID
        //getCurrentDate
        this.con = con;
        ArrayList purchaseItems = new ArrayList();
    }

    public void addItem(int upc) {
        try
        {
            Item Item = new Item(con, upc);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT quantity"
                    + "FROM PurchaseItem"
                    + "WHERE receiptId = " + receiptId + ", upc = " + upc + ";");
            if (rs.next() == false)
            {
                if (checkStock(Item, 1) == true)
                stmt.executeUpdate("insert into PurchaseItem values"
                        + "(" + receiptId + "," + upc + ", 1);");
                purchaseItems.add(Item);
            }

            else
            {
                if (checkStock(Item, rs.getInt(1)))
                stmt.executeUpdate("Update PurchaseItem"
                        + "SET quantity = quantity + 1"
                        + "WHERE receiptId = " + receiptId + ", upc = " + upc + ";");
                purchaseItems.add(Item);
            }

        }

        catch (SQLException ex)
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

    private boolean checkStock(Item pi, int purchaseQuantity) {
        int stock = pi.getQuantity();
        if (stock > purchaseQuantity)
            return true;
        else
            return false;
    }

    private void calculateSubtotal ()
    {
        float subtotal = 0;
        Object purchaseItems[] = this.purchaseItems.toArray();
        for (int i = 0; i < this.purchaseItems.size(); i++)
        {
            subtotal =+ ((Item) purchaseItems[i]).getSellPrice();
        }
        this.subtotal = subtotal;
    }
    
}
