package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Movie;
import model.Customer;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers() {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM cse305.customer join cse305.person ON customer.Id = person.SSN JOIN cse305.location ON person.ZipCode = location.ZipCode;";
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				Customer customer = new Customer();
				
				customer.setCustomerID(rs.getString("Id"));
				customer.setEmail(rs.getString("Email"));
				customer.setRating(rs.getInt("Rating"));
				customer.setCreditCard(rs.getString("CREDITCARDNUMBER"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setAddress(rs.getString("Address"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
				customers.add(customer);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */

		Customer customer = new Customer();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT C.Id, P.FirstName, P.LastName, C.Email, SUM("
					+ " CASE"
					+ " WHEN A.type = 'limited' THEN 10"
					+ " WHEN A.type = 'unlimited-1' THEN 15"
					+ " WHEN A.type = 'unlimited-2' THEN 20"
					+ " WHEN A.type = 'unlimited-3' THEN 25 END)"
					+ " AS Revenue FROM"
					+ " customer C JOIN account A ON C.Id = A.Customer"
					+ " JOIN person P ON C.Id = P.SSN"
					+ " GROUP BY C.Id, P.FirstName, P.LastName, C.Email"
					+ " ORDER BY Revenue DESC"
					+ " LIMIT 1;";
			
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				customer.setCustomerID(rs.getString("Id"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setEmail(rs.getString("Email"));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	
		return customer;
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM cse305.customer join cse305.person ON customer.Id = person.SSN JOIN cse305.location ON person.ZipCode = location.ZipCode;";
	
			rs = st.executeQuery(query);
			while (rs.next()) {
                Customer customer = new Customer();
				customer.setCustomerID(rs.getString("Id"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setAddress(rs.getString("Address"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setEmail(rs.getString("Email"));

                customers.add(customer);
			}
          }
			catch (Exception e) {
			System.out.println(e);
		}
		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		
		Customer customer = new Customer();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM customer C"
					+ " JOIN person P ON C.Id = P.SSN"
					+ " JOIN location L ON L.ZipCode = P.ZipCode"
					+ " WHERE C.Id = '" + customerID + "';";
			
			rs = st.executeQuery(query);
			if (rs.next()) {
				customer.setCustomerID(rs.getString("Id"));
				customer.setEmail(rs.getString("Email"));
				customer.setRating(rs.getInt("Rating"));
				customer.setCreditCard(rs.getString("CREDITCARDNUMBER"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setAddress(rs.getString("Address"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return customer;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */

		Connection con = null;
		Statement st = null;
		int rowsAffected = 0;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "DELETE FROM customer WHERE Id = '"
						+ customerID + "';";
			
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


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
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
			
			query = "SELECT Id FROM customer WHERE Email = '" + username + "'";
			
			rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getString("customerID");
            }

            catch (Exception e) {
    			System.out.println(e);
    			return "failure";
    		}

        return "success";
    }


	public List<Customer> getSellers() {
		
		/*
		 * This method fetches the all seller details and returns it
		 * The students code to fetch data from the database will be written here
		 * The seller (which is a customer) record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setCustomerID("111-11-1111");
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Shiyong");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("shiyong@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);			
		}
		/*Sample data ends*/
		
		return customers;

	}


	public String addCustomer(Customer customer) {
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
			
			query = "SELECT COUNT(*) AS count FROM location L WHERE L.zipcode = "
						+ customer.getZipCode() + ";";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean locationExists = rs.getInt("count") > 0;
			if (!locationExists) {
				query = "INSERT INTO location (ZipCode, City, State) VALUES ("
						+ customer.getZipCode() + ", '"
						+ customer.getCity() + "', '"
						+ customer.getState() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "SELECT COUNT(*) AS count FROM person P WHERE P.SSN = '"
					+ customer.getCustomerID() + "';";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean personExists = rs.getInt("count") > 0;
			if (!personExists) {
				query = "INSERT INTO person "
						+ "(SSN, LastName, FirstName, Address, ZipCode, Telephone)"
						+ " VALUES ('" 
						+ customer.getCustomerID() + "', '"
						+ customer.getLastName() + "', '"
						+ customer.getFirstName() + "', '"
						+ customer.getAddress() + "', "
						+ customer.getZipCode() + ", '"
						+ customer.getTelephone() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "SELECT COUNT(*) AS count FROM Customer C WHERE C.ID = '"
					+ customer.getCustomerID() + "';";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean customerExists = rs.getInt("count") > 0;
			if (!customerExists) {
				query = "INSERT INTO customer "
						+ "(Id, Email, Rating, CreditCardNumber)"
						+ " VALUES ('" 
						+ customer.getCustomerID() + "', '"
						+ customer.getEmail() + "', "
						+ customer.getRating() + ", '"
						+ customer.getCreditCard() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			else {
				return "failure";
			}
		}
		catch (Exception e) {
			System.out.println(e);
			return "failure";
		}
		
		return "success";
	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
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
			
			query = "SELECT COUNT(*) AS count FROM location L WHERE L.zipcode = "
						+ customer.getZipCode() + ";";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean locationExists = rs.getInt("count") > 0;
			if (!locationExists) {
				query = "INSERT INTO location (ZipCode, City, State) VALUES ("
						+ customer.getZipCode() + ", '"
						+ customer.getCity() + "', '"
						+ customer.getState() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			else {
				query = "UPDATE location SET "
						+ "City = '" + customer.getCity() + "', "
						+ "State = '" + customer.getState()
						+ "' WHERE ZipCode = " + customer.getZipCode() + ";";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "SELECT COUNT(*) AS count FROM person P WHERE P.SSN = '"
					+ customer.getCustomerID() + "';";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean personExists = rs.getInt("count") > 0;
			if (!personExists) {
				query = "INSERT INTO person "
						+ "(SSN, LastName, FirstName, Address, ZipCode, Telephone)"
						+ " VALUES ('" 
						+ customer.getCustomerID() + "', '"
						+ customer.getLastName() + "', '"
						+ customer.getFirstName() + "', '"
						+ customer.getAddress() + "', "
						+ customer.getZipCode() + ", '"
						+ customer.getTelephone() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			else {
				query = "UPDATE person SET "
						+ "LastName = '" + customer.getLastName()
						+ "', FirstName = '" + customer.getFirstName()
						+ "', Address = '" + customer.getAddress()
						+ "', ZipCode = " + customer.getZipCode()
						+ ", Telephone = '" + customer.getTelephone()
						+ "' WHERE SSN = '" + customer.getCustomerID() + "';";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "UPDATE customer SET "
					+ "Email = '" + customer.getEmail()
					+ "', Rating = '" + customer.getRating()
					+ "', CREDITCARDNUMBER = '" + customer.getCreditCard()
					+ "' WHERE Id = '" + customer.getCustomerID() + "';";
				
			rowsAffected = st.executeUpdate(query);
			if (rowsAffected == 0) {
				return "failure";
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return "success";
	}

}
