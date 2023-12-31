package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Rental;
import model.Movie;

public class MovieDao {

	
	public List<Movie> getMovies() {
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of all the movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */

		List<Movie> movies = new ArrayList<Movie>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM movie M;";
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));
				
				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	
	public Movie getMovie(String movieID) {
		/*
		 * The students code to fetch data from the database based on "movieID" will be written here
		 * movieID, which is the Movie's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Movie" class object
		 */
		
		Movie movie = new Movie();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM Movie M WHERE M.Id = " + movieID + ";";
			
			rs = st.executeQuery(query);
			if (rs.next()) {
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
						
		return movie;
	}
	
	public String addMovie(Movie movie) {
		/*
		 * All the values of the add movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the movie details and return "success" or "failure" based on result of the database insertion.
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
			
			query = "INSERT INTO movie "
					+ "(Name, Type, Rating, DistrFee, NumCopies)"
					+ " VALUES ('" 
					+ movie.getMovieName() + "', '"
					+ movie.getMovieType() + "', "
					+ movie.getRating() + ", "
					+ movie.getDistFee() + ", "
					+ movie.getNumCopies() + ");";
			
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
	
	public String editMovie(Movie movie) {
		/*
		 * All the values of the edit movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Movie class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
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
			
			query = "UPDATE movie SET "
					+ "Name = '" + movie.getMovieName()
					+ "', Type = '" + movie.getMovieType()
					+ "', Rating = " + movie.getRating()
					+ ", DistrFee = " + movie.getDistFee()
					+ ", NumCopies = " + movie.getNumCopies()
					+ " WHERE Name = '" + movie.getMovieName() + "';";
			
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

	public String deleteMovie(String movieID) {
		/*
		 * movieID, which is the Movie's ID which has to be deleted, is given as method parameter
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
			
			query = "DELETE FROM movie WHERE Id = "
						+ movieID + ";";
			
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
	
	
	public List<Movie> getBestsellerMovies() {
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of the bestseller movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */

		List<Movie> movies = new ArrayList<Movie>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT *, TotalOrders FROM ("
					+ "SELECT MovieId, COUNT(*) AS TotalOrders FROM Rental"
					+ " GROUP BY MovieId LIMIT 10) AS M JOIN movie"
					+ " ON movie.Id = M.MovieId ORDER BY TotalOrders DESC;";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));
				
				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}

	public List<Movie> getSummaryListing(String searchKeyword) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of summary listing of revenue generated by a particular movie or movie type must be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * Store the revenue generated by an movie in the soldPrice attribute, using setSoldPrice method of each "movie" object
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}

	public List<Movie> getMovieSuggestions(String customerID) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie suggestions for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom the movie suggestions are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();

		
		Connection con= null;
		Statement st =null;
		ResultSet rs =null;
		String password = "root";
		
		System.out.println(customerID);
		//need to implement login to get customer id
		//customerID="444444444";

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st =con.createStatement();

			String query = "		SELECT *\r\n"
					+ "		FROM movie\r\n"
					+ "		WHERE Type = (\r\n"
					+ "		    SELECT DISTINCT movie.Type\r\n"
					+ "		    FROM movieq\r\n"
					+ "		    JOIN movie\r\n"
					+ "		    ON movieq.MovieId=movie.Id\r\n"
					+ "		    JOIN account\r\n"
					+ "		    ON account.Id=movieq.AccountId\r\n"
					+ "		    WHERE account.Customer = " +customerID
					+");";
			rs =st.executeQuery(query);
			
		
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movies.add(movie);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
						
		return  movies;	

	}
	
	public List<Movie> getCurrentMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch currently hold movie for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom currently hold movie are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		//System.out.println(customerID);
		List<Movie> movies = new ArrayList<Movie>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		//need to implement login to get customer id
		customerID="444444444";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT movie.Id, movie.Name,movie.Type \r\n"
					+ "FROM customerorder JOIN rental\r\n"
					+ "ON customerorder.Id = rental.OrderId\r\n"
					+ "JOIN movie\r\n"
					+ "ON rental.MovieId = movie.Id\r\n"
					+ "JOIN account\r\n"
					+ "ON account.Id=rental.AccountId\r\n"
					+ "WHERE account.Customer = "+ customerID +"\r\n"
					+ "AND ReturnDate IS NULL;";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				
				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	
public List<Movie> getQueueOfMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie queue for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom movie queue are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		
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

			String query = "SELECT movie.Id,movie.Type,movie.Name FROM movieq\r\n"
					+ "JOIN movie\r\n"
					+ "ON movieq.MovieId=movie.Id\r\n"
					+ "JOIN account\r\n"
					+ "ON account.Id=movieq.AccountId\r\n"
					+ "WHERE account.Customer=" +customerID;
			rs =st.executeQuery(query);
			
		
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movies.add(movie);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
						
		return  movies;	
	}
	

//	public List getMoviesBySeller(String sellerID) {
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * Query to fetch movies sold by a given seller, indicated by sellerID, must be implemented
//		 * sellerID, which is the Sellers's ID who's movies are fetched, is given as method parameter
//		 * The bid and order details of each of the movies should also be fetched
//		 * The bid details must have the highest current bid for the movie
//		 * The order details must have the details about the order in which the movie is sold
//		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
//		 * Each bid record is required to be encapsulated as a "Bid" class object and added to the "bids" List
//		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
//		 * The movies, bids and orders Lists have to be added to the "output" List and returned
//		 */
//
//		List output = new ArrayList();
//		List<Movie> movies = new ArrayList<Movie>();
//		List<Bid> bids = new ArrayList<Bid>();
//		List<Order> orders = new ArrayList<Order>();
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			Movie movie = new Movie();
//			movie.setMovieID(123);
//			movie.setDescription("sample description");
//			movie.setType("BOOK");
//			movie.setName("Sample Book");
//			movies.add(movie);
//			
//			Bid bid = new Bid();
//			bid.setCustomerID("123-12-1234");
//			bid.setBidPrice(120);
//			bids.add(bid);
//			
//			Order order = new Order();
//			order.setMinimumBid(100);
//			order.setBidIncrement(10);
//			order.setOrderID(123);
//			orders.add(order);
//		}
//		/*Sample data ends*/
//		
//		output.add(movies);
//		output.add(bids);
//		output.add(orders);
//		
//		return output;
//	}

	public List<Movie> getMovieTypes() {
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * A query to fetch the unique movie types has to be implemented
		 * Each movie type is to be added to the "movie" object using setType method
		 */
		
		List<Movie> movies = new ArrayList<Movie>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT DISTINCT Type FROM movie;";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieType(rs.getString("Type"));
				
				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}

	public List getMoviesByName(String movieName) {
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM movie WHERE Name LIKE '%"
					+ movieName + "%';";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));

				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	
	public List getMoviesByActor(String actorName) {
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT M.Id, M.Name, M.Type FROM movie M JOIN appearedin AI ON"
					+ " M.Id = AI.MovieId JOIN actor A ON AI.ActorId = A.Id WHERE A.Name"
					+ " LIKE '%" + actorName + "%';";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));

				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	

	public List getMoviesByType(String movieType) {
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieType, which is the movie's type on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieType as their type has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT * FROM movie WHERE Type = '"
					+ movieType + "';";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));
				
				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	
	public List getMovieRentalsByName(String movieName) {
		List<Movie> movies = new ArrayList<Movie>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT DISTINCT M.Id, M.Name, M.Type FROM movie M JOIN rental R"
					+ " ON M.Id = R.MovieId WHERE M.Name LIKE '%"
					+ movieName + "%';";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));

				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	
	public List getMovieRentalsByCustomer(String customerName) {
		List<Movie> movies = new ArrayList<Movie>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT M.Id, M.Name, M.Type FROM customer C JOIN person P ON"
					+ " C.Id = P.SSN JOIN account A ON C.Id = A.Customer"
					+ " JOIN rental R ON A.Id = R.AccountId JOIN movie M"
					+ " ON R.MovieId = M.Id WHERE P.FirstName LIKE '%"
					+ customerName + "%' OR P.LastName LIKE '%" + customerName + "%';";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));

				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
				
		return movies;
	}
	

	public List getMovieRentalsByType(String movieType) {
		List<Movie> movies = new ArrayList<Movie>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse305?useSSL=false", "root", password);
			st = con.createStatement();
			
			query = "SELECT DISTINCT M.Id, M.Name, M.Type FROM movie M JOIN "
					+ "rental R ON M.Id = R.MovieId WHERE M.Type LIKE '" + movieType + "';";
			
			rs = st.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				
				movies.add(movie);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	

	public List<Movie> getBestsellersForCustomer(String customerID) {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList.
		 * Query to get the Best-seller list of movies for a particular customer, indicated by the customerID, has to be implemented
		 * The customerID, which is the customer's ID for whom the Best-seller movies have to be fetched, is given as method parameter
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}

}
