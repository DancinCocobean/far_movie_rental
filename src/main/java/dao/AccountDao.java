package dao;

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
	
			int income = 0;
					
			/*Sample data begins*/
			income = 100;
			/*Sample data ends*/
			
	
			return income;
			
		}
	
	public String setAccount(String customerID, String accountType) {

		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}
	

}
