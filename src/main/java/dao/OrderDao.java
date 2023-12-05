package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.Order;
import model.Rental;


public class OrderDao {
	
	public List<Order> getAllOrders() {
		
		List<Order> orders = new ArrayList<Order>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Order" class object and added to the "orders" ArrayList
		 * Query to get data about all the orders should be implemented
		 */
		
		Connection con= null;
		Statement st =null;
		ResultSet rs =null;
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st =con.createStatement();
			
			String query = "SELECT * FROM customerorder";
			rs =st.executeQuery(query);
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrderID(rs.getInt("Id"));
				order.setDateTime(rs.getString("DateTime"));
				order.setReturnDate(rs.getString("ReturnDate"));
				orders.add(order);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return orders;

	}

	public List<Order> getOrders(String customerID) {
		List<Order> orders = new ArrayList<Order>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Order" class object and added to the "orders" ArrayList
		 * Query to get data about all the orders in which a customer participated should be implemented
		 * customerID is the customer's primary key, given as method parameter
		 */
		
		
		Connection con= null;
		Statement st =null;
		ResultSet rs =null;
		String password = "root";

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st =con.createStatement();
			
			String findaccount="SELECT accountID from Account WHERE customerID= "+customerID;
			rs =st.executeQuery(findaccount);
			int accountID=rs.getInt("accountID");
			
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
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrderID(rs.getInt("OrderID"));
				order.setDateTime(rs.getString("DateTime"));
				order.setReturnDate(rs.getString("ReturnDate"));
				orders.add(order);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return orders;

	}

	public List<Order> getOpenOrders(String employeeEmail) {
		System.out.println(employeeEmail);
		List<Order> orders = new ArrayList<Order>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Order" class object and added to the "orders" ArrayList
		 * Query to get data about all the open orders monitored by a customer representative should be implemented
		 * employeeEmail is the email ID of the customer representative, which is given as method parameter
		 */
		
		
		Connection con= null;
		Statement st =null;
		ResultSet rs =null;
		String password = "root";

		//not able to get order because the email is null
		employeeEmail="dsmith@gmail.com";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st =con.createStatement();
			
			String query="SELECT rental.OrderID, customerorder.DateTime, customerorder.ReturnDate\r\n"
					+ "FROM rental\r\n"
					+ "JOIN customerorder ON rental.OrderID = customerorder.Id\r\n"
					+ "JOIN employee ON rental.CustRepId = employee.SSN\r\n"
					+ "WHERE employee.email = '"+ employeeEmail +"'\r\n"
					+ "AND customerorder.ReturnDate IS NULL;";
			rs =st.executeQuery(query);
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrderID(rs.getInt("OrderID"));
				order.setDateTime(rs.getString("DateTime"));
				order.setReturnDate(null);
				orders.add(order);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
						
		return orders;

		
		
	}

	public String recordSale(String orderID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the order ID, should be implemented
		 * orderID is the Order's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		
		Connection con= null;
		//Statement st =null;
		int rs =0;
		String password = "root";

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			//st =con.createStatement();
			
			//use executeupdate for modifying data
			String query = "INSERT INTO CustomerOrder (Id, DateTime, ReturnDate) VALUES (?, ?, ?)";
			
			LocalDate currentDate = LocalDate.now();
			String startDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String returndate= null;
			int ID=Integer.parseInt(orderID);
			
			PreparedStatement prepare= con.prepareStatement(query);
			prepare.setInt(1,ID);
			prepare.setString(2, startDate);
			prepare.setString(3, returndate);
			
			
			rs =prepare.executeUpdate();
			
			if(rs>0) {
				return "success";
			}else {
				return "failure";
			}
		}catch (Exception e) {
			System.out.println(e);
		}

		return "failure";
	}
	
	public List<Rental> getOrderHisroty(String customerID) {
		
		
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
	

//	public List getOrderData(String orderID, String movieID) {
//		
//		List output = new ArrayList();
//		Movie movie = new Movie();
//		Account account = new Account();
//		Order order = new Order();
//		Customer customer = new Customer();
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * The item details are required to be encapsulated as a "Item" class object
//		 * The bid details are required to be encapsulated as a "Bid" class object
//		 * The order details are required to be encapsulated as a "Order" class object
//		 * The customer details are required to be encapsulated as a "Customer" class object
//		 * Query to get data about order indicated by orderID and itemID should be implemented
//		 * orderID is the Order's ID, given as method parameter
//		 * itemID is the Item's ID, given as method parameter
//		 * The customer details must include details about the current winner of the order
//		 * The bid details must include details about the current highest bid
//		 * The item details must include details about the item, indicated by itemID
//		 * The order details must include details about the item, indicated by orderID
//		 * All the objects must be added in the "output" list and returned
//		 */
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			item.setItemID(123);
//			item.setDescription("sample description");
//			item.setType("BOOK");
//			item.setName("Sample Book");
//			
//			bid.setCustomerID("123-12-1234");
//			bid.setBidPrice(120);
//			
//			customer.setCustomerID("123-12-1234");
//			customer.setFirstName("Shiyong");
//			customer.setLastName("Lu");
//			
//			order.setMinimumBid(100);
//			order.setBidIncrement(10);
//			order.setCurrentBid(110);
//			order.setCurrentHighBid(115);
//			order.setOrderID(Integer.parseInt(orderID));
//		}
//		/*Sample data ends*/
//		
//		output.add(item);
//		output.add(bid);
//		output.add(order);
//		output.add(customer);
//		
//		return output;
//
//	}

	
}
