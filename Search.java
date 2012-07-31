
import java.sql.*;
import java.util.ArrayList;

public class Search
{

    private ArrayList<Item> retItem;
    private String sTerm;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;

    public Search(Connection con)
    {
        retItem = new ArrayList<Item>(0);
        this.con = con;
        sTerm = null;

    }

    void searchTitle(String title)
    {
        try
        {

            ps = con.prepareStatement("SELECT upc "
                    + "FROM Item"
                    + "WHERE title = ?");
            //sTerm = search Term
            if (title.length() == 0)
            {
                ps.setString(1, null);
            } else
            {
                ps.setString(1, sTerm);
            }
            rs = ps.executeQuery();

            while (rs.next())
            {

                retItem.add(new Item(con, rs.getInt(1)));
            }

            // commit work
            con.commit();

            ps.close();
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

    void searchCategory()
    {
        try
        {
            ps = con.prepareStatement("SELECT upc"
                    + "FROM Item"
                    + "WHERE category = ?");
            sTerm = in.readLine();
            //sTerm = search Term
            if (sTerm.length() == 0)
            {
                ps.setString(1, null);
            } else
            {
                ps.setString(1, sTerm);
            }
            rs = ps.executeQuery();

            while (rs.next())
            {

                retItem.add(new Item(con, rs.getInt(1)));
            }

            // commit work
            con.commit();

            ps.close();
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

    void searchLeadSinger()
    {
        try
        {
            ps = con.prepareStatement("SELECT upc"
                    + "FROM Item I, LeadingSinger L"
                    + "WHERE I.upc = L.upc AND L.name = ?");
            sTerm = in.readLine();
            //sTerm = search Term
            if (sTerm.length() == 0)
            {
                ps.setString(1, null);
            } else
            {
                ps.setString(1, sTerm);
            }
            rs = ps.executeQuery();

            while (rs.next())
            {

                retItem.add(new Item(con, rs.getInt(1)));
            }


            // commit work
            con.commit();

            ps.close();
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

    Item results(int index)
    {
        return retItem.get(index);
    }
}
