package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Rental;

public class RentalDao {
	
	public List<Rental> getOrderHisroty(String customerID) {
		
	System.out.println(customerID);
		
		List<Rental> rentals = new ArrayList<Rental>();
		
		Connection con= null;
		Statement st =null;
		ResultSet rs =null;
		String password = "root";

		
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

}
