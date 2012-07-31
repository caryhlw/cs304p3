
import java.sql.*;

public class ReturnItem
{

    private Statement stmt;
    private ResultSet rs;
    private Connection con;
    private int upc;
    private String name;
    private int price;
    private int quantity;

    public ReturnItem(Connection con, int upc)
    {
        this.upc = upc;
        try
        {
            stmt = con.createStatement();
            //Find the upc + receipt #)

            rs = stmt.executeQuery("blah");
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }
}
