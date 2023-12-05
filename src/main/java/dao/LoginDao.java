package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */
		
		//Connection con = null;
		//Statement st = null;
		//String query = "";
		//ResultSet rs = null;

		//try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", "root");
			//st = con.createStatement();
			//query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
			//rs = st.executeQuery(query);
			//if (rs.next()) {
                // User (from query) found, create a Login object
				Login login = new Login();
                //String role = rs.getString("role");
                // Check if the role is "customerRepresentative"
                //if ("customerRepresentative".equalsIgnoreCase(role)) {
                    login.setRole("customerRepresentative");
                    //login.setRole("manager");
                   //login.setRole("customer");
                    return login;
                //}
                //Other role checks here maybe ¯\_(ツ)_/¯ 
             // If the role is not a customer representative, return null
               // return null;
			//}
		//}
		//	catch (Exception e) {
		//		System.out.println(e);
		//	}
	     //   return null;

	}
		
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
	}

}
