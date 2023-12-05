package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Movie;
import model.Account;

public class AccountDao {
	
	public int getSalesReport(Account account) {
			/*
			 * The students code to fetch data from the database will be written here
			 * Query to get sales report for a particular month must be implemented
			 * account, which has details about the month and year for which the sales report is to be generated, is given as method parameter
			 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the dateOpened attribute of account object
			 * The month and year can be accessed by getter method, i.e., account.getAcctCreateDate()
			 */
		
			String[] dateParts = account.getAcctCreateDate().split("-");
			int month = Integer.parseInt(dateParts[0]);
	        int year = Integer.parseInt(dateParts[1]);
			
			int income = 0;
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			String query = "";
			String password = "root";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
				st = con.createStatement();
				
				query = "SELECT SUM(Revenue) AS TotalRevenue FROM ("
						+ "SELECT Type, (CASE"
						+ " WHEN type = 'limited' THEN COUNT(*) * 10"
						+ " WHEN type = 'unlimited-1' THEN COUNT(*) * 15"
						+ " WHEN type = 'unlimited-2' THEN COUNT(*) * 20"
						+ " WHEN type = 'unlimited-3' THEN COUNT(*) * 25"
						+ " END) AS Revenue" 
						+ " FROM ACCOUNT WHERE MONTH(DateOpened) = "
						+ month + " AND YEAR(DateOpened) = " + year 
						+ " GROUP BY Type) AS Subquery;";
				
				rs = st.executeQuery(query);
				while (rs.next()) {
					income = rs.getInt("TotalRevenue");
				}
			}
			catch (Exception e) {
				System.out.println(e);
			}			
	
			return income;
		}
	
	public String setAccount(String customerID, String accountType) {

		Connection con = null;
		Statement st = null;
		int rowsAffected = 0;
		String query = "";
		String password = "root";
		//System.out.println(customerID);
		//need to implement login to get customer id
		customerID="444444444";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "UPDATE account\r\n"
					+ "SET Type ='" + accountType+ "' \r\n"
					+ "WHERE customer = "+ customerID +";";
			
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
