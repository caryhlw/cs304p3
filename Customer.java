
import java.sql.*;

public class Customer
{

    private String customerId;
    private String password;
    private String name;
    private String address;
    private String phone;
    private Connection con;
    private Statement stmt;
    private PreparedStatement ps;
    
    private Boolean existFlag = false;

    public Customer(Connection con, String customerId, String password, String name, String address, String phone)
    {
       
            this.con = con;
            if (!existingCustomer(customerId))
            {
            	
                setCustomerId(customerId);
                setPassword(password);
                setName(name);
                setAddress(address);
                setPhone(phone);
                
                addCustomer();
                
            } else {
            	System.out.println("User already exists in the database\n");
            }

    }

    public Customer(Connection con, String customerId, String password)
    // Searches for the Customer in the database, given his customerId and password. If the Customer exists, and
    // the password matches the customerId, create a new Customer object with the information stored in the database.
    // Otherwise, throw a UserException.
    {
    try {
    	this.con = con;
        if (existingCustomer(customerId))
        {
            String getPass = "select password from Customer where cid = ?";
            ps = con.prepareStatement(getPass);
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String tempPass = rs.getString(1);
            System.out.println(tempPass);
            if (!tempPass.equals(password))
            {
                System.out.println("Incorrect password");
            } else
            {
                this.customerId = customerId;
                password = tempPass;
                String getUser = "select name, address, phone from Customer where cid = ?";
                ps = con.prepareStatement(getUser);
                ps.setString(1, customerId);
                rs = ps.executeQuery();
                rs.next();
                name = rs.getString(1);
                address = rs.getString(2);
                phone = rs.getString(3);
            }
        } else
        {
            System.out.println("User does not exist");
        }
    	} catch (SQLException e) {
    		System.out.println("Message: SQLException " + e.getMessage());
    		
    	} 
        //Find customer and see if password matches
    }

    private void setCustomerId(String cid)
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
            ps.setString(1, customerId);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.setString(5, phone);

            ps.executeUpdate();

            ps.close();
            con.close();

            //stmt.executeUpdate("BLAH BLAH"); //Insert new customer into DB

        } catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }

    }

    private boolean existingCustomer(String cid)
    {
        // returns true if cid already exists in the database; otherwise returns false
        try
        {
            String searchCust = "select count(*) from Customer C where C.cid = ?";
            ps = con.prepareStatement(searchCust);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            if (rowCount != 0)
            {
            	existFlag = true;
            } else
            {
            	//System.out.println("User does not exist \n");
                existFlag = false;
            }
            
            return existFlag;
        } catch (SQLException ex)
        {
            System.out.println("cid not found. Message: " + ex.getMessage());
            return true;
        }

    }
}
