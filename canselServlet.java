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
 * Servlet implementation class canselServlet
 */
// @WebServlet("/canselServlet")
public class canselServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public canselServlet() {
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

		if (request.getParameter("id").isEmpty()) {//if user wants to cansel all tickets
			try {
				TDAO.deleteAll();//all tickets will delete from DB
				out.println("All tickets canseled successfully!");//printing the msg of successful delete
			} catch (SQLException e) {
			}
		} else {//if user wants to just cansel a specific ticket
			int id = Integer.parseInt(request.getParameter("id"));//getting the ticket id from html page
			try {
				TDAO.delete(id);//delete the ticket from DB
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
				out.println("Ticket with number " + id + " deleted successfully!");//successful msg of deleting a ticket with its ID
				out.print("<br>");
				out.print("</body>");
				out.print("</html>");
			} catch (SQLException e) {
			}
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
