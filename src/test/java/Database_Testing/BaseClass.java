package Database_Testing;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseClass {
    Connection con;

    @BeforeMethod
    public Connection setup() {
        try {
            String url = "jdbc:mysql://localhost:3306/cep";
            String username = "root";
            String password = "Aamir@105";
            con = DriverManager.getConnection(url,username,password);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return con;
    }
    @AfterMethod
    public void tearDown() throws SQLException{
        con.close();
    }

}
