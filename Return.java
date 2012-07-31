import java.sql.*;

        /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author melstrom
 */
public class Return {
    private int receiptId;
    private String date;
    private int cardnum;
    private int expiry;
    private Statement stmt;
    private ResultSet rs;
    private Connection con;

    public Return (Connection con, int receiptId)
    {
        try
        {
            stmt = con.createStatement();
            stmt.executeQuery("BLAH BLAH"); //Find the receipt and return the information required

            date = rs.getString();
            cardnum = rs.getInt();
            expiry = rs.getInt();
        }

        catch

        {

        }
    }

    public void returnItem (int upc)
    {
        //Searches for item and
    }
}
