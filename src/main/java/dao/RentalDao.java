package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Rental;

public class RentalDao {
	
	public List<Rental> getOrderHisroty(String customerID) {
		
		System.out.println(customerID);
		
		List<Rental> rentals = new ArrayList<Rental>();
		
		Connection con= null;
		Statement st =null;
		ResultSet rs =null;
		String password = "root";
		//need to implement login to get customer id
		customerID="444444444";

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st =con.createStatement();

			String query = "SELECT rental.orderID, \r\n"
					+ "       rental.movieID, \r\n"
					+ "       movie.name AS movie_name,\r\n"
					+ "       customerorder.dateTime,\r\n"
					+" 		rental.CustRepID\r\n"
					+ "FROM rental\r\n"
					+ "JOIN account ON rental.accountID = account.ID\r\n"
					+ "JOIN movie ON rental.movieID = movie.ID\r\n"
					+ "JOIN customerorder ON rental.orderID = customerorder.ID\r\n"
					+ "WHERE account.Customer =" +customerID;
			rs =st.executeQuery(query);
			
			//ok i just realize i don't even need to get all those info
			while(rs.next()) {
				Rental rental = new Rental();
				
				rental.setOrderID(rs.getInt("OrderID"));
				rental.setMovieID(rs.getInt("MovieID"));
				rental.setCustomerRepID(rs.getInt("CustRepID"));
			
				rentals.add(rental);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
						
		return rentals;
		
	}



public String addrental(String accountID, String cusrep, String orderID, String movieID ) {
    // Replace this query with the actual query to insert a new customer into the database
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	int rowsAffected = 0;
	String query = "";
	String password = "root";
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
		st = con.createStatement();
		
		//check if customer exist
		query = "SELECT * FROM account\r\n"
				+ "WHERE Id= ' "+ accountID+ "';";
		
		rs = st.executeQuery(query);
		rs.next();
		
		if (rs.next()&&rs.getString("Id")==null) {
			System.out.println("invalid account id");
		}
		
		//check if movie exist
		query =  "SELECT * FROM movie\r\n"
				+ "WHERE Id= ' "+ movieID+ "';";
		
		rs = st.executeQuery(query);
		rs.next();
		
		if (rs.next()&&rs.getString("Id")==null) {
			System.out.println("invalid movie id");
		}
		
			query = "INSERT INTO rental "
					+ "(AccountId, CustrepId, OrderId, MovieId)"
					+ " VALUES ('" 
					+ accountID + "', '"
					+ cusrep + "', "
					+ orderID + ", '"
					+ movieID + "');";
			
			rowsAffected = st.executeUpdate(query);
			if (rowsAffected == 0) {
				return "failure";
			}
		
	}
	catch (Exception e) {
		System.out.println(e);
		return "failure";
	}
	
	return "success";
}
}
