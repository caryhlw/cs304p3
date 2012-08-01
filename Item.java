
import java.sql.*;

public class Item
{

    private int upc;
    private String title;
    private String type;
    private String category;
    private String company;
    private int year;
    private float sellPrice;
    private int quantity;
    private Statement stmt;
    private ResultSet rs;

    public Item(int upc, String title, String type, String category, String company, int year, float sellPrice)
    {
        this.upc = upc;
        this.title = title.toLowerCase();
        this.type = type.toLowerCase();
        this.category = category.toLowerCase();
        this.company = company.toLowerCase();
        this.year = year;
        this.sellPrice = sellPrice;
    }

    public Item(Connection con, int upc)
    {
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT upc, title, type, category, company, year, sellPrice, quantity"
                    + "FROM item"
                    + "WHERE upc = " + upc + ";");
            if (rs.next() == true)
            {
            this.upc = rs.getInt(1);
            this.title = rs.getString(2);
            this.type = rs.getString(3);
            this.category = rs.getString(4);
            this.company = rs.getString(5);
            this.year = rs.getInt(6);
            this.sellPrice = rs.getFloat(7);
            this.quantity = rs.getInt(8);
            }
            else
                throw new SQLException ("UPC does not exist");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public int getUPC()
    {
        return upc;
    }

    public String getTitle()
    {
        return title;




    }

    public String getType()
    {
        return type;




    }

    public String getCategory()
    {
        return category;




    }

    public String getCompany()
    {
        return company;




    }

    public int getYear()
    {
        return year;




    }

    public float getSellPrice()
    {
        return sellPrice;




    }

    public int getQuantity()
    {
        return quantity;


    }
}
