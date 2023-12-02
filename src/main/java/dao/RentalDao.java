package dao;

import java.util.ArrayList;
import java.util.List;

import model.Rental;

public class RentalDao {
	
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

}
