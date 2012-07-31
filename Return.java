
import java.sql.*;
import java.util.ArrayList;

public class Return
{

    private ArrayList returnItems;
    private Purchase purchase;
    private int receiptId;
    private String date;
    private int cardnum;
    private int expiry;
    private Statement stmt;
    private ResultSet rs;
    private Connection con;

    public Return(Connection con, int receiptId)
    {
        this.purchase = new Purchase(con, receiptId);
        this.returnItems = new ArrayList();

        try
        {
            stmt = con.createStatement();
            stmt.executeQuery("SELECT "); //Find the receipt and return the information required

            date = rs.getString();
            cardnum = rs.getInt();
            expiry = rs.getInt();
        } catch
        {
        }
    }

    public void returnItem(int upc)
    {
        if
    }
}
