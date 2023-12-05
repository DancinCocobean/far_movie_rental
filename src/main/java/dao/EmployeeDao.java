package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	/* Test */
	
	public String addEmployee(Employee employee) {
		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
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
						+ employee.getZipCode() + ";";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean locationExists = rs.getInt("count") > 0;
			if (!locationExists) {
				query = "INSERT INTO location (ZipCode, City, State) VALUES ("
						+ employee.getZipCode() + ", '"
						+ employee.getCity() + "', '"
						+ employee.getState() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "SELECT COUNT(*) AS count FROM person P WHERE P.SSN = '"
					+ employee.getEmployeeID() + "';";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean personExists = rs.getInt("count") > 0;
			if (!personExists) {
				query = "INSERT INTO person "
						+ "(SSN, LastName, FirstName, Address, ZipCode, Telephone)"
						+ " VALUES ('" 
						+ employee.getEmployeeID() + "', '"
						+ employee.getLastName() + "', '"
						+ employee.getFirstName() + "', '"
						+ employee.getAddress() + "', "
						+ employee.getZipCode() + ", '"
						+ employee.getTelephone() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "SELECT COUNT(*) AS count FROM employee E WHERE E.SSN = '"
					+ employee.getEmployeeID() + "';";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean employeeExists = rs.getInt("count") > 0;
			if (!employeeExists) {
				query = "INSERT INTO employee "
						+ "(SSN, StartDate, HourlyRate, Email, Level)"
						+ " VALUES ('" 
						+ employee.getEmployeeID() + "', '"
						+ employee.getStartDate() + "', "
						+ employee.getHourlyRate() + ", '"
						+ employee.getEmail() + "', "
						+ "'CR');";
				
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

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
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
						+ employee.getZipCode() + ";";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean locationExists = rs.getInt("count") > 0;
			if (!locationExists) {
				query = "INSERT INTO location (ZipCode, City, State) VALUES ("
						+ employee.getZipCode() + ", '"
						+ employee.getCity() + "', '"
						+ employee.getState() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			else {
				query = "UPDATE location SET "
						+ "City = '" + employee.getCity() + "', "
						+ "State = '" + employee.getState()
						+ "' WHERE ZipCode = " + employee.getZipCode() + ";";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "SELECT COUNT(*) AS count FROM person P WHERE P.SSN = '"
					+ employee.getEmployeeID() + "';";
			
			rs = st.executeQuery(query);
			rs.next();
			
			boolean personExists = rs.getInt("count") > 0;
			if (!personExists) {
				query = "INSERT INTO person "
						+ "(SSN, LastName, FirstName, Address, ZipCode, Telephone)"
						+ " VALUES ('" 
						+ employee.getEmployeeID() + "', '"
						+ employee.getLastName() + "', '"
						+ employee.getFirstName() + "', '"
						+ employee.getAddress() + "', "
						+ employee.getZipCode() + ", '"
						+ employee.getTelephone() + "');";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			else {
				query = "UPDATE person SET "
						+ "LastName = '" + employee.getLastName()
						+ "', FirstName = '" + employee.getFirstName()
						+ "', Address = '" + employee.getAddress()
						+ "', ZipCode = " + employee.getZipCode()
						+ ", Telephone = '" + employee.getTelephone()
						+ "' WHERE SSN = '" + employee.getEmployeeID() + "';";
				
				rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 0) {
					return "failure";
				}
			}
			
			query = "UPDATE employee SET "
					+ "StartDate = '" + employee.getStartDate()
					+ "', HourlyRate = " + employee.getHourlyRate()
					+ ", Email = '" + employee.getEmail()
					+ "' WHERE SSN = '" + employee.getEmployeeID() + "';";
				
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

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
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
			
			query = "DELETE FROM employee WHERE SSN = '"
						+ employeeID + "';";
			
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
	
	public List<Employee> getEmployees() {
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM employee E"
					+ " JOIN person P ON E.SSN = P.SSN"
					+ " JOIN location L ON L.ZipCode = P.ZipCode;";
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				Employee employee = new Employee();
				
				employee.setEmployeeID(rs.getString("SSN"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("Address"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setTelephone(rs.getString("Telephone"));
				employee.setEmail(rs.getString("Email"));
				employee.setStartDate(rs.getString("StartDate"));
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
				
				employees.add(employee);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return employees;
	}

	public Employee getEmployee(String employeeID) {
		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM employee E"
					+ " JOIN person P ON E.SSN = P.SSN"
					+ " JOIN location L ON L.ZipCode = P.ZipCode"
					+ " WHERE E.SSN = '" + employeeID + "';";
			
			rs = st.executeQuery(query);
			if (rs.next()) {
				employee.setEmployeeID(rs.getString("SSN"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("Address"));
				employee.setCity(rs.getString("City"));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setTelephone(rs.getString("Telephone"));
				employee.setEmail(rs.getString("Email"));
				employee.setStartDate(rs.getString("StartDate"));
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
		/*Sample data begins*/
		employee.setEmail("shiyong@cs.sunysb.edu");
		employee.setFirstName("Shiyong");
		employee.setLastName("Lu");
		employee.setEmployeeID("631-413-5554");
		/*Sample data ends*/
		
		return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */

		return "123456789";
	}

}
