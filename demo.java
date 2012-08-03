import java.sql.*;

public class demo
{
    private ResultSet rs;
    private PreparedStatement ps;

    public demo(Connection con)
    {
        try
        {
            ps = con.prepareStatement("INSERT INTO Item VALUES (upc_counter.nextval, 'Random', 'dvd', null, 'Company', 2015, 1, 50)");
            for (int i = 0; i < 100; i++)
            {
                ps.executeUpdate();
            }

            Purchase p = new Purchase(con);
            ps = con.prepareStatement("SELECT upc FROM Item WHERE year = 2015");
            rs = ps.executeQuery();
            while (rs.next())
            {
                p.addItem(rs.getInt(1));
            }

        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }
}
