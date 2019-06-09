package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HotelSearcher {
	public static Hotel[] search(int star, int rSingle, int rDouble, int rQuad) {
		Hotel[] whatever = {};
		return whatever;
	}
	public static void main(String[] args)
    {
      Connection connection = null;
      try
      {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:hotelList.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        ResultSet rs = statement.executeQuery
        		("SELECT * FROM hotels WHERE hotelStar = 5");
        while(rs.next())
        {
          // read the result set
          System.out.println("id = " + rs.getInt("hotelID"));
          System.out.println("star = " + rs.getInt("hotelStar"));
          System.out.println("address = " + rs.getString("address"));
        }
      }
      catch(SQLException e)
      {
        // if the error message is "out of memory",
        // it probably means no database file is found
        System.err.println(e.getMessage());
      }
      finally
      {
        try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          // connection close failed.
          System.err.println(e.getMessage());
        }
      }
    }
}
