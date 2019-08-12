package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editServlet
 */
// @WebServlet("/editServlet")
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		ticketPackage.TicketDao TDAO = new ticketPackage.TicketDao();
		ticketPackage.Ticket t;
		//getting value of fields from html page
		int id = Integer.parseInt(request.getParameter("id"));
		String ownerName = request.getParameter("ownerName");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		String flightDate = request.getParameter("flightDate");
		int flightNumber = Integer.parseInt(request.getParameter("flightNumber"));
		if(!request.getParameter("ownerName").isEmpty() & !request.getParameter("source").isEmpty() & !request.getParameter("destination").isEmpty() & !request.getParameter("flightDate").isEmpty() & !request.getParameter("flightNumber").isEmpty())
		{//if all of the fields have been filled the request will continue
			t = new ticketPackage.Ticket(id, ownerName, source, destination, flightDate, flightNumber);
			try {
				TDAO.update(t);//updating a ticket using its ID
				//html page
				out.print("<html>");
				out.print("<head>");
				out.print("<meta charset=\"ISO-8859-1\">");
				out.print("<title>Airplane Ticket Reservation System</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<h1>Welcome to the Airplane Ticket Reservation System</h1><br>");
				out.print("<form name=\"myForm\" action=\"Servlet_ATR\"  method=\"get\">");
				out.print("ticketID: <input type=\"text\" name=\"id\"><br> ");
				out.print("ownerName: <input type=\"text\" name=\"ownerName\"><br>");
				out.print("source: <input type=\"text\" name=\"source\"><br>");
				out.print("destination: <input type=\"text\" name=\"destination\"><br>");
				out.print("flightDate: <input type=\"text\" name=\"flightDate\"><br>");
				out.print("flightNumber: <input type=\"text\" name=\"flightNumber\"><br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"show\"> show<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"buy\"> buy<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"edit\"> edit<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"cansel\"> cansel<br>");
				out.print("<input type=\"submit\" name=\"submit\" value=\"submit\"><br>");
				out.print("</form><br>");
				out.println("Ticket updated successfully!");//printing the successful msg
				out.print("<br>");
				out.print("</body>");
				out.print("</html>");
			} catch (SQLException e) {
			}
		}
		else{
			out.println("You must fill all fields!");//printing the msg of filling the fields
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
