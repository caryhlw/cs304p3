
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Shipment
{
    private ArrayList shipItems;
    private int sid;
    private String supName;
    private Date date;
    private Statement stmt;
    private ResultSet rs;
    private Connection con;
    private PreparedStatement ps;

    public Shipment(Connection con, String supName)
    {
        this.con = con;
        this.supName = supName;
        this.shipItems = new ArrayList();
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

    int getSid()
    {
        return sid;
    }

    String getSupName()
    {
        return supName;
    }

    String getSDate()
    {
        return sdate;
    }
}
