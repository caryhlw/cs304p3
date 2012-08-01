
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Return
{

    private ArrayList returnItems;
    private float subtotal;
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
        subtotal = 0;
        try
        {
            stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO Return VALUES"
                    + "(retid_counter.nexval, )" + dt + ", " + this.purchase.getReceiptId() + ", null);");
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public void addItem(int upc)
    {

        try
        {
            Object purchaseItems[] = purchase.getPurchaseItems().toArray();
            for (int i = 0; i < purchase.getPurchaseItems().size(); i++)
            {
                if (((Item) purchaseItems[i]).getUPC() == upc)
                {
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
                        break;
                    } catch (SQLException ex)
                    {
                        System.out.println("Message: " + ex.getMessage());
                    }
                } else
                {
                    throw new NotFoundException("Item not in original purchase");
                }
            }
            calculateSubtotal();

        } catch (NotFoundException nf)
        {
            System.out.println(nf.getMessage());
        }

    }

    public void removeItem(Item item)
    {
        try
        {
            stmt = con.createStatement();
            stmt.executeUpdate("Update ReturnItem"
                    + "SET quantity = quantity + - 1"
                    + "WHERE receiptId = " + this.purchase.getReceiptId() + ", upc = " + item.getUPC() + ";");
            Object purchaseItems[] = this.returnItems.toArray();

            for (int i = 0; i < this.returnItems.size(); i++)
            {
                if (((Item) purchaseItems[i]).getUPC() == item.getUPC())
                {
                    this.returnItems.remove(i);
                }
            }
            calculateSubtotal();



        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());


        }

    }

    private void calculateSubtotal()
    {
        float subtotal = 0;
        Object purchaseItems[] = this.returnItems.toArray();
        for (int i = 0; i < this.returnItems.size(); i++)
        {
            subtotal = +((Item) purchaseItems[i]).getSellPrice();
        }
        this.subtotal = subtotal;
    }
}
