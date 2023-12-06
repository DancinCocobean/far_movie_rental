package resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.CustomerDao;
import dao.OrderDao;
import dao.RentalDao;
import model.Rental;

/**
 * Servlet implementation class RecordSaleController
 */
public class RecordSaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordSaleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderID = request.getParameter("orderID");
		String accountID = request.getParameter("accountID");
		String movieID = request.getParameter("movieId");
		String cusrepID = (String)request.getSession(false).getAttribute("employeeID");

		
		OrderDao orderDao = new OrderDao();
		String result = orderDao.recordSale(orderID);
		
		RentalDao rentalDao = new RentalDao();
		rentalDao.addrental(accountID, cusrepID, orderID, movieID);

		
		
		if(result.equals("success")) {
			response.sendRedirect("customerRepresentativeHome.jsp?status=recordSuccess");
		}
		else {
			response.sendRedirect("customerRepresentativeHome.jsp?status=recordFailure");
		}

	}

}
