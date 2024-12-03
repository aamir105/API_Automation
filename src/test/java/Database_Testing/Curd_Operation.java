package Database_Testing;

import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLInput;
import java.sql.Statement;

public class Curd_Operation extends BaseClass {
    @Test
    public void DB_Test_InsertData() throws SQLException{
        con=this.setup();
        PreparedStatement ps = con.prepareStatement("insert into User values(?,?)");
        ps.setString(1,"1020");
        ps.setString(2,"SeethaRam");
        ps.executeUpdate();
//        DB_Test_SelectQuery();
    }

    @Test
    public void createTable() throws SQLException {
        con = this.setup();
        Statement stmt = con.createStatement();
        String Query = "Create table Student (Roll_no INT AUTO_INCREMENT,Name VARCHAR(50),Mobile_number VARCHAR(10),PRIMARY KEY(Roll_no));";
        stmt.execute(Query);
        System.out.println("Table Created Successfully");
        con.close();
    }

    @Test
    public void insertData() throws SQLException{
        con=this.setup();
        PreparedStatement ps = con.prepareStatement("Insert into Student (Roll_no,Name,Mobile_number) VALUES (?,?,?);");
        ps.setString(1,"1");
        ps.setString(2,"Aamir");
        ps.setString(3,"123456789");
        ps.executeUpdate();
        readData();
        con.close();
    }

    @Test
    public void readData() throws SQLException {
        con = this.setup();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from Student");
        while (rs.next()) {
            int Roll_no = rs.getInt(1);
            String Name = rs.getString(2);
            String Mobile_Number = rs.getString(3);
            System.out.println(" " + Roll_no + " " + Name + " " + Mobile_Number);
        }
    }

    @Test
    public void updateData() throws SQLException{
        con = this.setup();
        PreparedStatement preparedStatement = con.prepareStatement("Update Student SET Mobile_number =987654321 where Roll_no = 1 ");
        preparedStatement.executeUpdate();
        readData();
        con.close();
    }

    @Test
    public void DeleteData() throws SQLException {
        con = this.setup();
        PreparedStatement preparedStatement = con.prepareStatement("delete from Student  WHERE Roll_no = 1");
        preparedStatement.executeUpdate();
        readData();
        con.close();
    }

}
