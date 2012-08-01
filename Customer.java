
import java.sql.*;

public class Customer
{

    private int customerId;
    private String password;
    private String name;
    private String address;
    private String phone;
    private Connection con;
    private Statement stmt;
    private PreparedStatement ps = null;

    public Customer(Connection con, int customerId, String password, String name, String address, String phone)
    {
        try
        {
            this.con = con;
            if (!existingCustomer(customerId))
            {
                //If not found, add to DB
                String addCust = "insert into Customer values (?, ?, ?, ?, ?)";
                ps = con.prepareStatement(addCust);
                ps.setInt(1, customerId);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setString(4, phone);
                ps.executeUpdate();

                setCustomerId(customerId);
                setPassword(password);
                setName(name);
                setAddress(address);
                setPhone(phone);
            }

        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public Customer(int customerId, String password)
    // Searches for the Customer in the database, given his customerId and password. If the Customer exists, and
    // the password matches the customerId, create a new Customer object with the information stored in the database.
    // Otherwise, throw a UserException.
    {
    try {
        if (existingCustomer(customerId))
        {
            String getPass = "select password from Customer where cid = ?";
            ps = con.prepareStatement(getPass);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            String tempPass = rs.getString(1);
            if (tempPass != password)
            {
                throw new Exception("Incorrect password");
            } else
            {
                this.customerId = customerId;
                password = tempPass;
                String getUser = "select name, address, phone from Customer where cid = ?";
                ps = con.prepareStatement(getUser);
                ps.setInt(1, customerId);
                rs = ps.executeQuery();
                name = rs.getString(1);
                address = rs.getString(2);
                phone = rs.getString(3);
            }
        } else
        {
            throw new Exception("User does not exist");
        }
    	} catch (SQLException e) {
    		System.out.println("Message: " + e.getMessage());
    		
    	} catch (Exception ex) {
    		System.out.println("Message: " + ex.getMessage());
    	}
        //Find customer and see if password matches
    }

    private void setCustomerId(int cid)
    {
        customerId = cid;
    }

    private void setPassword(String pwd)
    {
        password = pwd;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    private void setPhone(String phone)
    {
        this.phone = phone;
    }

    private void setAddress(String address)
    {
        this.address = address;
    }

    private void addCustomer()
    {
        try
        {
            String addCust = "insert into Customer values (?, ?, ?, ?, ?)";
        	ps = con.prepareStatement(addCust);
            ps.setInt(1, customerId);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, phone);
            ps.executeUpdate();

            ps.close();
            con.close();

            //stmt.executeUpdate("BLAH BLAH"); //Insert new customer into DB

        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }

    }

    private boolean existingCustomer(int cid)
    {
        // returns true if cid already exists in the database; otherwise returns false
        try
        {
            String searchCust = "select count(*) from Customer C"
                    + "where C.cid = ?";
            ps = con.prepareStatement(searchCust);
            ps.setString(1, searchCust);
            ResultSet rs = ps.executeQuery();
            int rowCount = rs.getInt(1);
            if (rowCount == 1)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            return true;
        }

    }
}
