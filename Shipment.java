
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Shipment
{
    private int sid;
    private String supName;
    private Date date;
    private ArrayList<Item> shipItems;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;

    public Shipment(Connection con, String supName)
    {
        this.con = con;
        this.supName = supName.toLowerCase();
        shipItems = new ArrayList<Item>(0);
        this.date = new Date();
        long t = date.getTime();
        java.sql.Date dt = new java.sql.Date(t);
        try
        {
            ps = con.prepareStatement("INSERT INTO Shipment VALUES"
                    + "(shipment_counter.next, ?, ?)");
            ps.setString(1, this.supName);
            ps.setDate(2, dt);

        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public void addItem(int UPC)
    {
        shipItems.add(new Item(con, UPC));
    }

    public void setSupName(String supName)
    {
        try
        {
            this.supName = supName.toLowerCase();
            ps = con.prepareStatement("Update Into Shipment set supName = ?"
                    + "WHERE sid = " + sid);
            ps.setString(1, supName.toLowerCase());
            ps.executeUpdate();
            con.commit();
            ps.close();
            con.close();
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            } catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }

    }

    public float getPrice(int itemUPC)
    {
        for (int i = 0; i < shipItems.size(); i++)
        {
            if (shipItems.get(i).getUPC() == itemUPC)
            {
                return shipItems.get(i).getSellPrice();
            }

        }
        System.out.println("Item doesn't exist in this shipment");
        return 0.0f;
    }

    public int getSid()
    {
        return sid;
    }

    public String getSupName()
    {
        return supName;
    }

    public Date getDate()
    {
        return date;
    }
}
