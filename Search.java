
import java.sql.*;
import java.util.ArrayList;

public class Search
{

    private ArrayList<Item> retItem;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;

    public Search(Connection con)
    {
        retItem = new ArrayList<Item>(0);
        this.con = con;

    }

    public void searchTitle(String title, int quantity)
    {
        try
        {

            ps = con.prepareStatement("SELECT upc FROM Item WHERE title = ? AND quantity >= ?");
            //sTerm = search Term
            if (title.length() == 0)
            {
                ps.setString(1, null);
            } else
            {
                ps.setString(1, title.toLowerCase());
            }
            ps.setInt(2, quantity);
            //maybe catch negative number exception
            rs = ps.executeQuery();

            while (rs.next())
            {

                retItem.add(new Item(con, rs.getInt(1)));
            }
            System.out.println(Integer.toString(retItem.size()));
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

   public void searchCategory(String category, int quantity)
    {
        try
        {
            ps = con.prepareStatement("SELECT upc FROM Item WHERE category = ? AND quantity >= ?");
        
            if (category.length() == 0)
            {
                ps.setString(1, null);
            } else
            {
                ps.setString(1, category.toLowerCase());
            }
            ps.setInt(2, quantity);
            
            rs = ps.executeQuery();

            while (rs.next())
            {
                retItem.add(new Item(con, rs.getInt(1)));
            }
            
            System.out.println(Integer.toString(retItem.size()));
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

   public void searchLeadSinger(String LeadingSinger, int quantity)
    {
        try
        {
            ps = con.prepareStatement("SELECT I.upc FROM Item I, LeadSinger L WHERE I.upc = L.upc AND L.name = ? AND I.quantity >= ?");

            if (LeadingSinger.length() == 0)
            {
                ps.setString(1, null);
            } else
            {
                ps.setString(1, LeadingSinger.toLowerCase());
            }
            ps.setInt(2, quantity);
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
   
   public void searchAll(String title, String category, String LeadingSinger, int quantity)
   {
       try
       {
           ps = con.prepareStatement("SELECT upc FROM Item I, LeadSinger L WHERE I.upc = L.upc AND L.name = ? AND I.quantity >= ? AND I.title = ? AND I.category = ?");

           if (LeadingSinger.length() == 0)
           {
               ps.setString(1, null);
           } else
           {
               ps.setString(1, LeadingSinger.toLowerCase());
           }
           	ps.setInt(2, quantity);
           if (title.length() == 0)
           {
               ps.setString(3, null);
           } else
           {
               ps.setString(3, title.toLowerCase());
           }
           if (category.length() == 0)
           {
               ps.setString(4, null);
           } else
           {
               ps.setString(4, category.toLowerCase());
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
   
    public Item results(int index)
    {
        return retItem.get(index);
    }
    
    public String printResults() {
    	String temp = "";
    	for(int i = 0; i < retItem.size(); i++)
    		temp += results(i).printItem();
    	return temp;
    }
}
