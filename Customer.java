import java.sql.*;

public class Customer {

    private int customerId;
    private String password;
    private String name;
    private String address;
    private String phone;
    private Connection con;
    private Statement stmt;

    public Customer (Connection con, int customerId, String password, String name, String address, String phone)
    {
        this.con = con;
        //Find customer with duplicate id
        //If not found, add to DB
    }

    public Customer (int customerId)
    {
        this.customerId = customerId;
        //Find customer and see if password matches
    }

    private void addCustomer ()
    {
        try
        {
                             stmt = con.createStatement();
                             stmt.executeUpdate("BLAH BLAH"); //Insert new customer into DB

        }
                   catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }

    }

}
