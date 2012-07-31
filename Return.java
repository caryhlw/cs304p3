
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Return
{

    private ArrayList returnItems;
    private Purchase purchase;
    private java.util.Date date;
    private Statement stmt;
    private ResultSet rs;
    private Connection con;

    public Return(Connection con, int receiptId)
    {
        this.con = con;
        this.purchase = new Purchase(con, receiptId);
        this.returnItems = new ArrayList();
        this.date = new Date();
        long t = date.getTime();
        java.sql.Date dt = new java.sql.Date(t);

        try
        {
            stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO Return VALUES"
                    + "(retid_counter.nexval, )" + this.date + ", " + this.purchase.getReceiptId() + ", null);");
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public void addItem(int upc)
    {
        purchase.getPurchaseItems();
        try
        {
            Item Item = new Item(con, upc);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT quantity"
                    + "FROM ReturnItem"
                    + "WHERE receiptId = " + this.purchase.getReceiptId() + ", upc = " + Item.getUPC() + ";");
            if (rs.next() == false)
            {
                stmt.executeUpdate("insert into ReternItem values"
                        + "(" + this.purchase.getReceiptId() + "," + Item.getUPC() + ", 1);");
            } else
            {
                stmt.executeUpdate("Update PurchaseItem"
                        + "SET quantity = quantity + 1"
                        + "WHERE receiptId = " + this.purchase.getReceiptId() + ", upc = " + upc + ";");
            }

            returnItems.add(Item);
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());


        }
    }

    public void removeItem(int upc)
    {
        try
        {
            stmt = con.createStatement();
            stmt.executeUpdate("Update PurchaseItem"
                    + "SET quantity = quantity + - 1"
                    + "WHERE receiptId = " + receiptId + ", upc = " + item.getUPC() + ";");
            Object purchaseItems[] = this.purchaseItems.toArray();




            for (int i = 0; i
                    < this.purchaseItems.size(); i++)
            {
                if (((Item) purchaseItems[i]).getUPC() == item.getUPC())
                {
                    this.purchaseItems.remove(i);




                }
            }
            this.subtotal = subtotal;




        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());


        }

    }
