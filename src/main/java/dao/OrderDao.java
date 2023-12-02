package dao;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Rental;
import model.Account;
import model.Customer;
import model.Movie;

public class OrderDao {
	
	public List<Order> getAllOrders() {
		
		List<Order> orders = new ArrayList<Order>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Order" class object and added to the "orders" ArrayList
		 * Query to get data about all the orders should be implemented
		 */
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Order order = new Order();
			order.setOrderID(1);
			order.setDateTime("11-11-09 10:00");
			order.setReturnDate("11-14-09");
			orders.add(order);
		}
		/*Sample data ends*/
		
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
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Order order = new Order();
			order.setOrderID(1);
			order.setDateTime("11-11-09 10:00");
			order.setReturnDate("11-14-09");
			orders.add(order);
		}
		/*Sample data ends*/
		
		return orders;

	}

	public List<Order> getOpenOrders(String employeeEmail) {
		List<Order> orders = new ArrayList<Order>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Order" class object and added to the "orders" ArrayList
		 * Query to get data about all the open orders monitored by a customer representative should be implemented
		 * employeeEmail is the email ID of the customer representative, which is given as method parameter
		 */
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Order order = new Order();
			order.setOrderID(1);
			order.setDateTime("11-11-09 10:00");
			order.setReturnDate("11-14-09");
			orders.add(order);
		}
		/*Sample data ends*/
		
		return orders;

		
		
	}

	public String recordSale(String orderID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the order ID, should be implemented
		 * orderID is the Order's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		/* Sample data begins */
		return "success";
		/* Sample data ends */
	}
	
	public List<Rental> getOrderHisroty(String customerID) {
		
		List<Rental> rentals = new ArrayList<Rental>();
			
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Rental rental = new Rental();
			
			rental.setOrderID(1);
			rental.setMovieID(1);
			rental.setCustomerRepID(1);
		
			rentals.add(rental);
			
			
		}
		/*Sample data ends*/
						
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
