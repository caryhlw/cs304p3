
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
    private PreparedStatement ps;

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
            ps = con.prepareStatement("INSERT INTO Return VALUES"
                    + "(retid_counter.next, ?, ?, null");
            ps.setDate(1, dt);
            ps.setInt(2, this.purchase.getReceiptId());
            ps.executeUpdate();
            ps.close();
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
                        Item item = new Item(con, upc);
                        ps = con.prepareStatement("SELECT quantity "
                                + "FROM ReturnItem "
                                + "WHERE receiptID = ?, upc = ?");
                        ps.setInt(1, this.purchase.getReceiptId());
                        ps.setInt(2, item.getUPC());
                        rs = ps.executeQuery();
                        stmt = con.createStatement();

                        if (rs.next() == false)
                        {
                            ps = con.prepareStatement("INSERT INTO ReturnItem VALUES"
                                    + "(?, ?, 1");
                            ps.setInt(1, this.purchase.getReceiptId());
                            ps.setInt(2, item.getUPC());
                            ps.executeUpdate();
                            ps.close();
                        } else
                        {
                            ps = con.prepareStatement("UPDATE PurchaseItem "
                                    + "SET quantity = quantity + 1 "
                                    + "WHERE receiptID = ?, upc = ?");
                            ps.setInt(1, this.purchase.getReceiptId());
                            ps.setInt(2, item.getUPC());
                            ps.executeUpdate();
                            ps.close();
                        }

                        returnItems.add(item);
                        increaseStock(item);
                        calculateSubtotal();
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

        } catch (NotFoundException nf)
        {
            System.out.println(nf.getMessage());
        }

    }

    public void removeItem(Item item)
    {
        try
        {
            ps = con.prepareStatement("UPDATE PurchaseItem "
                    + "SET quantity = quantity - 1 "
                    + "WHERE receiptID = ?, upc = ?");
            ps.setInt(1, this.purchase.getReceiptId());
            ps.setInt(2, item.getUPC());
            ps.executeUpdate();
            ps.close();
            Object purchaseItems[] = this.returnItems.toArray();

            for (int i = 0; i < this.returnItems.size(); i++)
            {
                if (((Item) purchaseItems[i]).getUPC() == item.getUPC())
                {
                    this.returnItems.remove(i);
                }
            }
            increaseStock(item);
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

    protected void decreaseStock(Item item)
    {
        try
        {
            ps = con.prepareStatement("UPDATE Item "
                    + "SET quantity = quantity - 1 "
                    + "WHERE upc = ?");
            ps.setInt(1, item.getUPC());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    protected void increaseStock(Item item)
    {
        try
        {
            ps = con.prepareStatement("UPDATE Item "
                    + "SET quantity = quantity + 1 "
                    + "WHERE upc = ?");
            ps.setInt(1, item.getUPC());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }
}
