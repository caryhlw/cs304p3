import java.sql.*;

public class PurchaseItem {

    private int upc;
    private String name;
    private int price;
    private int quantity;
    
    private int dummy;
    
    private Statement stmt;
    private ResultSet rs;

    public PurchaseItem(int upc, int receiptId) {
        upc = this.upc;
        try
        {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug",
                    "username",
                    "password");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT title, sellPrice, quantity"
                    + "FROM item"
                    + "WHERE upc = " + upc + ";");
            if (rs.next() == true)
            {
                name = rs.getNString(1);
                price = rs.getInt(2);
                quantity = rs.getInt(3);
                if (quantity <= 0)
                    //Throw new exceiption
                    dummy = 1;
            }
            
        }
        
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }
}
