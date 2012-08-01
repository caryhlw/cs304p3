
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Purchase
{

    private ArrayList<Item> purchaseItems;
    private float subtotal;
    private int receiptId;
    private Date date;
    private int cardnum;
    private int expiry;
    private String expectedDate;
    private String deliveredDate;
    private Statement stmt;
    private ResultSet rs;
    private Connection con;
    private PreparedStatement ps;

    public Purchase(Connection con)
    {
        this.con = con;
        this.purchaseItems = new ArrayList<Item>();
        this.date = new Date();
        long t = date.getTime();
        java.sql.Date dt = new java.sql.Date(t);
        try
        {
            ps = con.prepareStatement("INSERT INTO Purchase VALUES"
                    + "(receipt_counter.nextval, ?, null, null, null, null, null)");
            ps.setDate(1, dt);
            ps.executeUpdate();
            ps = con.prepareStatement("SELECT receipt_counter.currval " +
            		"FROM dual");
            rs = ps.executeQuery();
            rs.next();
            this.receiptId = rs.getInt(1);
            ps.close();
            
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public Purchase(Connection con, int receiptId)
    {
        this.purchaseItems = new ArrayList<Item>();

        try
        {
            ps = con.prepareStatement("SELECT receiptID, purchaseDate, card#, expire, expectedDate, deliveredDate "
                    + "FROM Purchase "
                    + "WHERE receiptID = ?");
            ps.setInt(1, receiptId);
            rs = ps.executeQuery();

            if (rs.next() == true)
            {
                this.receiptId = rs.getInt(1);
                this.date = rs.getDate(2);
                this.cardnum = rs.getInt(3);
                this.expiry = rs.getInt(4);
                this.expectedDate = rs.getString(5);
                this.deliveredDate = rs.getString(6);
                ps = con.prepareStatement("SELECT upc, quantity " +
                		"FROM PurchaseItem " +
                		"WHERE receiptID = ?");
                ps.setInt(1, receiptId);
                rs = ps.executeQuery();
                
                while (rs.next())
                {
                    for (int i = 0; i < rs.getInt(2); i++)
                    {
                        purchaseItems.add(new Item(con, rs.getInt(1)));
                    }
                }
            } else
            {
                throw new SQLException("ReceiptId not found");
            }
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public void addItem(int upc)
    {
        try
        {
            Item Item = new Item(con, upc);
            ps = con.prepareStatement("SELECT quantity "
                    + "FROM PurchaseItem "
                    + "WHERE receiptID = ? AND upc = ?");
            ps.setInt(1, this.receiptId);
            ps.setInt(2, Item.getUPC());
            rs = ps.executeQuery();

            if (rs.next() == false)
            {
                if (checkStock(Item, 1) == true)
                {
                    ps = con.prepareStatement("INSERT INTO PurchaseItem VALUES"
                            + "(?, ?, 1)");
                    ps.setInt(1, this.receiptId);
                    ps.setInt(2, Item.getUPC());
                    ps.executeUpdate();
                    ps.close();
                    purchaseItems.add(Item);
                    decreaseStock(Item);
                }
            } else
            {
                if (checkStock(Item, rs.getInt(1)))
                {
                    ps = con.prepareStatement("UPDATE PurchaseItem "
                            + "SET quantity = quantity + 1 "
                            + "WHERE receiptID = ? AND upc = ?");
                    ps.setInt(1, this.receiptId);
                    ps.setInt(2, Item.getUPC());
                    ps.executeUpdate();
                    ps.close();
                    purchaseItems.add(Item);
                    decreaseStock(Item);
                }
            }
            calculateSubtotal();

        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public void removeItem(int upc)
    {
    	Item item = new Item(con, upc);
        try
        {
            ps = con.prepareStatement("UPDATE PurchaseItem "
                    + "SET quantity = quantity - 1 "
                    + "WHERE receiptID = ? AND upc = ?");
            ps.setInt(1, this.receiptId);
            ps.setInt(2, item.getUPC());
            ps.executeUpdate();
            ps.close();

            for (int i = 0; i < this.purchaseItems.size(); i++)
            {
            	if (purchaseItems.get(i).getUPC() == item.getUPC())
            			purchaseItems.remove(i);
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
        for (int i = 0; i < purchaseItems.size(); i++)
        {
        	subtotal += purchaseItems.get(i).getSellPrice();
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

    public void Cancel()
    {
        try
        {
            ps = con.prepareStatement("DELETE FROM PurchaseItem "
                    + "WHERE receiptID = ?");
            ps.setInt(1, this.receiptId);
            ps.executeUpdate();
            ps.close();

            ps = con.prepareStatement("DELETE FROM Purchase "
                    + "WHERE receiptID = ?");
            ps.setInt(1, this.receiptId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private boolean checkStock(Item pi, int purchaseQuantity)
    {
        int stock = pi.getQuantity();
        if (stock > purchaseQuantity)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public int getReceiptId()
    {
        return receiptId;
    }

    public Date getDate()
    {
        return date;
    }

    public int getCardnum()
    {
        return cardnum;
    }

    public int getExpiry()
    {
        return expiry;
    }

    public String getExpectedDate()
    {
        return expectedDate;
    }

    public String getDeliveredDate()
    {
        return deliveredDate;
    }

    public ArrayList<Item> getPurchaseItems()
    {
        return purchaseItems;
    }
    
    public float getSubtotal()
    {
    	return subtotal;
    }
}
